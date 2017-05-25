package model;

import ast.Assay;

import ast.*;
import parser.AquaBaseListener;
import parser.AquaParser;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Jesper on 20/03/2017.
 */
public class AntlrAquaListener extends AquaBaseListener {

    private HashMap<String, Declaration> declarationsMapper = new HashMap();
    private List<Input> inputs = new ArrayList<>();
    private List<Statement> statements = new ArrayList<>();
    private Assay assay;
    private List<String> errors = new ArrayList<>();

    @Override
    public void enterAssay(AquaParser.AssayContext ctx) {
        assay = new Assay(ctx.IDENTIFIER().getText());
    }

    @Override
    public void exitAssay(AquaParser.AssayContext ctx) {
        isInputAFluid();
        assay.setDeclarations(new ArrayList<>(declarationsMapper.values()));
        assay.setStatements(appendStatementsIntoControlStatements(statements));
    }

    /* DECLARATIONS */

    @Override
    public void enterFluid(AquaParser.FluidContext ctx) {
        Fluid fluid = new Fluid(ctx.IDENTIFIER(0).getText());
        for (AquaParser.DimensionContext dimc: ctx.dimension()) {
            fluid.appendDimension(new Dimension(Integer.parseInt(dimc.getText().substring(1, dimc.getText().length()-1))));
        }
        if (ctx.IDENTIFIER(1) != null) {
            fluid.setWash_identifier(ctx.IDENTIFIER(1).getText());
        }
        if (ctx.INTEGER() != null) {
            fluid.setPort_integer(Integer.parseInt(ctx.INTEGER().getText()));
        }
        declarationsMapper.put(ctx.IDENTIFIER(0).getText(),fluid);
    }

    @Override
    public void enterInput_(AquaParser.Input_Context ctx) {
        Input input = new Input(ctx.IDENTIFIER().getText());
        if (ctx.INTEGER() != null) {
            input.setInput_integer(Integer.parseInt(ctx.INTEGER().getText()));
        }
        inputs.add(input);
    }

    @Override
    public void enterVar(AquaParser.VarContext ctx) {
        Var var = new Var(ctx.IDENTIFIER().getText());
        for (AquaParser.DimensionContext dimc: ctx.dimension()) {
            var.appendDimension(new Dimension(Integer.parseInt(dimc.getText().substring(1, dimc.getText().length()-1))));
        }
        declarationsMapper.put(ctx.IDENTIFIER().getText(),var);
    }

    @Override
    public void enterConflict(AquaParser.ConflictContext ctx) {
        Conflict conflict = new Conflict(ctx.IDENTIFIER(0).getText(),ctx.IDENTIFIER(1).getText());
        if (ctx.IDENTIFIER(2) != null) {
            conflict.setWash_identifier(ctx.IDENTIFIER(2).getText());
        }
        declarationsMapper.put(ctx.IDENTIFIER(0).getText(),conflict);
    }

    /* STATEMENTS */

    @Override
    public void enterAssignFluid(AquaParser.AssignFluidContext ctx) {
        // TODO: implement
        new AssignFluid(ctx.identifier().getText());
    }

    @Override
    public void enterAssignExpr(AquaParser.AssignExprContext ctx) {
        Declaration decl = declarationsMapper.get(ctx.identifier().getText());
        if (decl instanceof Var) {
            Var var = (Var) decl;
            var.setValue(getExprValue(ctx.expr()));
            declarationsMapper.replace(var.getIdentifier(),var);
        } else {
            System.out.println("ERROR: "+decl.getIdentifier()+" is not a VAR");
        }
    }

    @Override
    public void enterMix(AquaParser.MixContext ctx) {
        isInMap(ctx.identifier());

        String[] identifiers = new String[ctx.identifier().size()];
        for (int i = 0; i < ctx.identifier().size(); i++) {
            identifiers[i] = ctx.identifier(i).getText();
        }

        List<Integer> ratioList = new ArrayList<>();

        for (AquaParser.ExprContext expr: ctx.expr()) {
            ratioList.add(getExprValue(expr));
        }

        Integer forValue = ratioList.get(ratioList.size()-1);
        ratioList.remove(ratioList.size()-1);

        statements.add(new Mix(identifiers, ratioList.toArray(new Integer[ratioList.size()]), forValue));
    }

    @Override
    public void enterIncubate(AquaParser.IncubateContext ctx) {
        isInMap(ctx.identifier());

        statements.add(new Incubate(ctx.identifier().getText(),getExprValue(ctx.expr(0)),getExprValue(ctx.expr(1))));
    }

    @Override
    public void enterSense(AquaParser.SenseContext ctx) {
        isInMap(ctx.identifier());

        SenseType senseType;
        if (ctx.sense_type().getText().equals("FLUORESCENCE")) {
            senseType = SenseType.FLUORESCENCE;
        } else {
            senseType = SenseType.OPTICAL;
        } // Parser will take care of every other string

        statements.add(new Sense(senseType,ctx.identifier(0).getText(),ctx.identifier(1).getText()));
    }

    /* CONTROL STATEMENTS */

    @Override
    public void enterFor_loop(AquaParser.For_loopContext ctx) {
        statements.add(new ForLoop(ctx.IDENTIFIER().getText(), getExprValue(ctx.expr(0)), getExprValue(ctx.expr(1)),null, true));
    }

    @Override
    public void exitFor_loop(AquaParser.For_loopContext ctx) {
        statements.add(new ForLoop(ctx.IDENTIFIER().getText(), null, null, null, false));
    }

    @Override
    public void enterRepeat(AquaParser.RepeatContext ctx) {
        statements.add(new Repeat(getExprValue(ctx.expr()), null, true));
    }

    @Override
    public void exitRepeat(AquaParser.RepeatContext ctx) {
        statements.add(new Repeat(null, null, false));
    }

    /* HELPER FUNCTIONS */

    public Assay getAssay() {
        return assay;
    }

    public List<String> getErrors() {
        return errors;
    }

    private boolean isUniqueIdentifier(String identifier) {
        if(declarationsMapper.containsKey(identifier)) {
            return true;
        } else {
            return false;
        }
    }

    private void isInMap(AquaParser.IdentifierContext id) {
        if (!declarationsMapper.containsKey(id.getText())) {
            errors.add("ERROR: " + id.getText() + " is not a declaration");
        }
    }

    private void isInMap(List<AquaParser.IdentifierContext> ids) {
        for (AquaParser.IdentifierContext id : ids) {
            if (!declarationsMapper.containsKey(id.getText())) {
                errors.add("ERROR: " + id.getText() + " is not a declaration");
            }
        }
    }

    private boolean isInputAFluid() {
        boolean isFluid = true;
        for (Input input : inputs) {
            if (!declarationsMapper.containsKey(input.getIdentifier())) {
                isFluid = false;
                errors.add("ERROR: "+input.getIdentifier()+" is not declared as a fluid");
            }
        }
        return isFluid;
    }

    private List<Statement> appendStatementsIntoControlStatements(List<Statement> statements) {
        List<Statement> newList = new ArrayList<>();
        while (!statements.isEmpty()) {
            Statement stmt = statements.get(0);
            if (stmt instanceof Repeat) {
                if (((Repeat) stmt).getIsStart()) {
                    statements.remove(0);
                    newList.add(new Repeat(((Repeat) stmt).getExpr(), appendStatementsIntoControlStatements(statements), false));
                    continue;
                } else {
                    statements.remove(0);
                    break;
                }
            }

            if (stmt instanceof ForLoop) {
                if (((ForLoop) stmt).getIsStart()) {
                    statements.remove(0);
                    newList.add(new ForLoop(stmt.getIdentifier(), ((ForLoop) stmt).getFrom(), ((ForLoop) stmt).getTo(), appendStatementsIntoControlStatements(statements), false));
                    continue;
                } else {
                    statements.remove(0);
                    break;
                }
            }

            statements.remove(0);
            newList.add(stmt);
        }
        return newList;
    }

    /* Expression handling */

    private Integer getExprValue(AquaParser.ExprContext expr) {
        // TODO: Handle operations on null value
        // TODO: Handle parenthesis
        if(expr instanceof AquaParser.AddSubContext) {
            if(((AquaParser.AddSubContext) expr).op.getText().equals("+")) {
                return getExprValue(((AquaParser.AddSubContext) expr).expr(0))+getExprValue(((AquaParser.AddSubContext) expr).expr(1));
            } else {
                return getExprValue(((AquaParser.AddSubContext) expr).expr(0))-getExprValue(((AquaParser.AddSubContext) expr).expr(1));
            }
        } else if( expr instanceof AquaParser.MulDivContext) {
            if(((AquaParser.MulDivContext) expr).op.getText().equals("*")) {
                return getExprValue(((AquaParser.AddSubContext) expr).expr(0))*getExprValue(((AquaParser.AddSubContext) expr).expr(1));
            } else {
                return getExprValue(((AquaParser.AddSubContext) expr).expr(0))/getExprValue(((AquaParser.AddSubContext) expr).expr(1));
                // TODO: handle illegal divisions
            }
        } else if (expr instanceof AquaParser.ConstExprContext) {
            return checkConstExpr((AquaParser.ConstExprContext) expr);
        } else if (expr instanceof AquaParser.VarExprContext) {
            return getVarExprValue((AquaParser.VarExprContext) expr);
        } else {
            return null;
        }
    }

    private Integer checkConstExpr(AquaParser.ConstExprContext expr) {
        return Integer.parseInt(expr.INTEGER().getText());
    }

    private Integer getVarExprValue(AquaParser.VarExprContext expr) {
        Declaration decl = declarationsMapper.get(expr.identifier().getText());
        if (decl == null) {
            errors.add("ERROR: "+expr.identifier().getText()+" has not been declared");
            return null;
        }
        Integer value = ((Var) decl).getValue();
        return value;
    }
}
