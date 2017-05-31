package model;

import ast.Assay;

import ast.*;
import org.antlr.v4.codegen.model.decl.Decl;
import parser.AquaBaseListener;
import parser.AquaParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Jesper on 20/03/2017.
 */
public class AntlrAquaListener extends AquaBaseListener {

    private HashMap<String, Declaration> declarationsMapper = new HashMap<>();
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
        checkIsInputAFluid();
        assay.setDeclarations(new ArrayList<>(declarationsMapper.values()));
        assay.setStatements(appendStatementsIntoControlStatements(statements));
    }

    /* DECLARATIONS */

    @Override
    public void enterFluid(AquaParser.FluidContext ctx) {
        String identifier = ctx.IDENTIFIER(0).getText();

        Dimension[] dimensions = null;
        if (ctx.dimension() != null) {
            dimensions = new Dimension[ctx.dimension().size()];
            for (int i = 0; i < dimensions.length; i++) {
                dimensions[i] = new Dimension(Integer.parseInt(ctx.dimension(i).getText().substring(1, ctx.dimension(i).getText().length()-1)));
            }
        }

        String wash = null;
        if (ctx.IDENTIFIER(1) != null) {
            wash = ctx.IDENTIFIER(1).getText();
        }

        Integer port = null;
        if (ctx.INTEGER() != null) {
            port = Integer.parseInt(ctx.INTEGER().getText());
        }

        checkIsUniqueIdentifier(identifier);

        declarationsMapper.put(identifier,new Fluid(identifier,dimensions,wash,port));
    }

    @Override
    public void enterInput_(AquaParser.Input_Context ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Integer input_integer = null;
        if (ctx.INTEGER() != null) {
            input_integer = Integer.parseInt(ctx.INTEGER().getText());
        }
        inputs.add(new Input(identifier,input_integer));
    }

    @Override
    public void enterVar(AquaParser.VarContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        Dimension[] dimensions = null;
        if (ctx.dimension() != null) {
            dimensions = new Dimension[ctx.dimension().size()];
            for (int i = 0; i < dimensions.length; i++) {
                dimensions[i] = new Dimension(Integer.parseInt(ctx.dimension(i).getText().substring(1, ctx.dimension(i).getText().length() - 1)));
            }
        }
        declarationsMapper.put(identifier, new Var(identifier,dimensions));
    }

    @Override
    public void enterConflict(AquaParser.ConflictContext ctx) {
        String identifier = ctx.IDENTIFIER(0).getText();
        String follows_identifier = ctx.IDENTIFIER(1).getText();
        String wash_identifier = ctx.IDENTIFIER(2).getText();

        declarationsMapper.put(identifier,new Conflict(identifier,follows_identifier,wash_identifier));
    }

    /* STATEMENTS */

    @Override
    public void enterAssignFluid(AquaParser.AssignFluidContext ctx) {
        Declaration decl = declarationsMapper.get(ctx.identifier().getText());
        if (decl instanceof Fluid) {
            statements.add(new AssignFluid(ctx.identifier().getText()));
        } else {
            System.out.println("ERROR: "+decl.getIdentifier()+" is not a FLUID");
        }
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
        checkIsNameInitialized(ctx.identifier());

        String[] identifiers = new String[ctx.identifier().size()];

        // Check whether the fluids were the previously used
        if (ctx.identifier(0).getText().equals("it")) {
            identifiers[0] = ctx.identifier(0).getText();
        } else {
            for (int i = 0; i < ctx.identifier().size(); i++) {
                Declaration decl = declarationsMapper.get(ctx.identifier(i).getText());
                if (decl instanceof Fluid) {
                    identifiers[i] = ((Fluid) decl).getIdentifier();
                } else {
                    errors.add("ERROR: "+decl.getIdentifier()+" is not a FLUID");
                }
            }
        }

        List<Integer> ratioList = new ArrayList<>();

        for (AquaParser.ExprContext expr: ctx.expr()) {
            ratioList.add(getExprValue(expr));
        }

        Integer forValue = ratioList.get(ratioList.size()-1);
        ratioList.remove(ratioList.size()-1);

        // Check if the last statement was an assign instruction
        Statement assign = null;
        if (statements.size() > 0) {
            assign = statements.get(statements.size()-1);
        }

        if (assign instanceof AssignFluid) {
            // This assign now holds this mix instruction
            statements.remove(assign);
            statements.add(new Mix(assign.getIdentifier(), identifiers, ratioList.toArray(new Integer[ratioList.size()]), forValue));
        } else {
            statements.add(new Mix("it", identifiers, ratioList.toArray(new Integer[ratioList.size()]), forValue));
        }
    }

    @Override
    public void enterIncubate(AquaParser.IncubateContext ctx) {
        checkIsNameInitialized(ctx.identifier());

        String identifier = ctx.identifier().getText();

        // Check whether the fluids were the previously used
        if (ctx.identifier().getText().equals("it")) {
            identifier = "it";
        } else {
            Declaration decl = declarationsMapper.get(identifier);
            if (decl instanceof Fluid) {
                identifier = ((Fluid) decl).getIdentifier();
            } else {
                errors.add("ERROR: "+decl.getIdentifier()+" is not a FLUID");
            }
            // Save the fluids for when calling "it"
        }

        // Check if the last statement was an assign instruction
        Statement assign = null;
        if (statements.size() > 0) {
            assign = statements.get(statements.size()-1);
        }

        if (assign instanceof AssignFluid) {
            // This assign now holds this instruction
            statements.remove(assign);
            statements.add(new Incubate(assign.getIdentifier(), identifier, getExprValue(ctx.expr(0)), getExprValue(ctx.expr(1))));
        } else {
            statements.add(new Incubate("it", identifier, getExprValue(ctx.expr(0)), getExprValue(ctx.expr(1))));
        }
    }

    @Override
    public void enterSense(AquaParser.SenseContext ctx) {
        checkIsNameInitialized(ctx.identifier());

        String fluid = ctx.identifier(0).getText();
        Declaration decl1 = declarationsMapper.get(fluid);
        String var = ctx.identifier(1).getText();
        Declaration decl2 = declarationsMapper.get(var);
        if (decl1 instanceof Fluid || fluid.equals("it")) {
            if (decl2 instanceof Var) {
                SenseType senseType;
                if (ctx.sense_type().getText().equals("FLUORESCENCE")) {
                    senseType = SenseType.FLUORESCENCE;
                } else {
                    senseType = SenseType.OPTICAL;
                } // Parser will take care of every other string

                statements.add(new Sense(senseType, fluid, var));
            } else {
                errors.add("ERROR: "+var+" is not a VAR");
            }
        } else {
            errors.add("ERROR: "+fluid+" is not a FLUID");
        }
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

    private void checkIsUniqueIdentifier(String id) {
        if (declarationsMapper.containsKey(id)) {
            errors.add("ERROR: " + id + " is not a unique declaration");
        }
    }

    private void checkIsNameInitialized(AquaParser.IdentifierContext id) {
        if (id.getText().equals("it")) {
            return;
        } else if (!declarationsMapper.containsKey(id.getText())) {
            errors.add("ERROR: " + id.getText() + " is not a declaration");
        }
    }

    private void checkIsNameInitialized(List<AquaParser.IdentifierContext> ids) {
        for (AquaParser.IdentifierContext id : ids) {
            checkIsNameInitialized(id);
        }
    }

    private void checkIsInputAFluid() {
        for (Input input : inputs) {
            if (!declarationsMapper.containsKey(input.getIdentifier())) {
                errors.add("ERROR: "+input.getIdentifier()+" is not declared as a fluid");
            }
        }
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
        if(expr instanceof AquaParser.AddSubContext) {
            Integer value0 = getExprValue(((AquaParser.AddSubContext) expr).expr(0));
            Integer value1 = getExprValue(((AquaParser.AddSubContext) expr).expr(1));
            if (value0 == null || value1 == null) {
                return null;
            }
            if(((AquaParser.AddSubContext) expr).op.getText().equals("+")) {
                return value0+value1;
            } else {
                return value0-value1;
            }
        } else if( expr instanceof AquaParser.MulDivContext) {
            Integer value0 = getExprValue(((AquaParser.MulDivContext) expr).expr(0));
            Integer value1 = getExprValue(((AquaParser.MulDivContext) expr).expr(1));
            if (value0 == null || value1 == null) {
                return null;
            }
            if(((AquaParser.MulDivContext) expr).op.getText().equals("*")) {
                return value0*value1;
            } else {
                // Illegal divisions with null or 0
                if (value1 == 0) {
                    errors.add("ERROR: "+((AquaParser.MulDivContext) expr).expr(1).getText()+" illegal division with zero");
                    return null;
                } else {
                    return value0 / value1;
                }
            }
        } else if (expr instanceof AquaParser.ConstExprContext) {
            return checkConstExpr((AquaParser.ConstExprContext) expr);
        } else if (expr instanceof AquaParser.VarExprContext) {
            return getVarExprValue((AquaParser.VarExprContext) expr);
        } else if (expr instanceof AquaParser.ParExprContext) {
            return getExprValue(((AquaParser.ParExprContext) expr).expr());
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
        return ((Var) decl).getValue();
    }
}
