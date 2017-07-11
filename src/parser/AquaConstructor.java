package parser;

import operations.Assay;

import operations.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Jesper on 20/03/2017.
 */

public class AquaConstructor extends AquaBaseListener {

    private HashMap<String, Declaration> declarationsMapper = new HashMap<>();
    private List<Input> inputs = new ArrayList<>();
    private List<Statement> statements = new ArrayList<>();
    private Assay assay;
    private List<String> errors = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();
    private Identifier assignFluid;

    @Override
    public void enterAssay(AquaParser.AssayContext ctx) {
        assay = new Assay(ctx.IDENTIFIER().getText());
    }

    @Override
    public void exitAssay(AquaParser.AssayContext ctx) {
        checkIsInputAFluid();
        List<Declaration> declarations = new ArrayList<>(declarationsMapper.values());
        declarations.addAll(inputs);
        assay.setDeclarations(declarations);
        statements = appendStatementsIntoControlStatements(statements);
        statements = runOverLoops(statements);
        /* From this point the list contains evaluated expressions */
        for (Statement stmt : statements) {

        }
        assay.setStatements(statements);
    }

    /* DECLARATIONS */

    @Override
    public void enterFluid(AquaParser.FluidContext ctx) {
        String identifier = ctx.IDENTIFIER(0).getText();

        Dimension[] dimensions = null;
        if (ctx.dimension() != null) {
            dimensions = new Dimension[ctx.dimension().size()];
            for (int i = 0; i < dimensions.length; i++) {
                dimensions[i] = new Dimension(Integer.parseInt(ctx.dimension(i).INTEGER().getText()));
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
                dimensions[i] = new Dimension(Integer.parseInt(ctx.dimension(i).INTEGER().getText()));
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
        Declaration decl = declarationsMapper.get(ctx.identifier().IDENTIFIER().getText());
        if (decl instanceof Fluid) {
            assignFluid = new Identifier(ctx.identifier().IDENTIFIER().getText(),ctx.identifier().index());
        } else {
            errors.add("ERROR: "+ctx.identifier().IDENTIFIER()+" is not a FLUID");
        }
    }

    @Override
    public void enterAssignExpr(AquaParser.AssignExprContext ctx) {
        Declaration decl = declarationsMapper.get(ctx.identifier().IDENTIFIER().getText());
        if (decl instanceof Var) {
            // Var var = (Var) decl;
            Identifier identifier = new Identifier(ctx.identifier().IDENTIFIER().getText(),ctx.identifier().index());
            //var.setValue(getExprValue(ctx.expr()));
            // declarationsMapper.replace(var.getIdentifier(),var);
            statements.add(new AssignExpr(identifier,ctx.expr()));
        } else {
            errors.add("ERROR: "+ctx.identifier().IDENTIFIER()+" is not a VAR");
        }
    }

    @Override
    public void enterMix(AquaParser.MixContext ctx) {
        checkIsNameInitialized(ctx.identifier());

        Identifier[] identifiers = new Identifier[ctx.identifier().size()];

        // Check whether the fluids were the previously used
        if (ctx.identifier(0).getText().equals("it")) {
            identifiers[0] = new Identifier(ctx.identifier(0).IDENTIFIER().getText(),null);
        } else {
            for (int i = 0; i < ctx.identifier().size(); i++) {
                Declaration decl = declarationsMapper.get(ctx.identifier(i).IDENTIFIER().getText());
                if (decl instanceof Fluid || ctx.identifier(i).IDENTIFIER().getText().equals("it")) {
                    identifiers[i] = new Identifier(ctx.identifier(i).IDENTIFIER().getText(),ctx.identifier(i).index());
                } else {
                    errors.add("ERROR: "+ctx.identifier(i).IDENTIFIER()+" is not a FLUID");
                }
            }
        }

        List<AquaParser.ExprContext> ratioList = new ArrayList<>();

        for (AquaParser.ExprContext expr: ctx.expr()) {
            ratioList.add(expr);
        }

        AquaParser.ExprContext forValue = ratioList.get(ratioList.size()-1);
        ratioList.remove(ratioList.size()-1);

        if (ratioList.size() > identifiers.length) {
            errors.add("ERROR: '"+ctx.getText()+"' has more ratios than incoming fluids");
        } else if ((ratioList.size() < identifiers.length) && ratioList.size() != 0) {
            errors.add("ERROR: '"+ctx.getText()+"' has more incomming fluids than ratios");
        }

        if (assignFluid != null) {
            statements.add(new Mix(assignFluid, identifiers, ratioList.toArray(new AquaParser.ExprContext[ratioList.size()]), forValue));
            assignFluid = null;
        } else {
            statements.add(new Mix(new Identifier("it", null), identifiers, ratioList.toArray(new AquaParser.ExprContext[ratioList.size()]), forValue));
        }
    }

    @Override
    public void enterIncubate(AquaParser.IncubateContext ctx) {
        checkIsNameInitialized(ctx.identifier());

        Declaration decl = declarationsMapper.get(ctx.identifier().IDENTIFIER().getText());
        if (decl instanceof Fluid || ctx.identifier().IDENTIFIER().getText().equals("it")) {
            Identifier identifier = new Identifier(ctx.identifier().IDENTIFIER().getText(),ctx.identifier().index());
            if (assignFluid != null) {
                statements.add(new Incubate(assignFluid, identifier, ctx.expr(0), ctx.expr(1)));
                assignFluid = null;
            } else {
                statements.add(new Incubate(new Identifier("it", null), identifier, ctx.expr(0), ctx.expr(1)));
            }
        } else {
            errors.add("ERROR: "+ctx.identifier().IDENTIFIER().getText()+" is not a FLUID");
        }
        // Save the fluids for when calling "it"
    }

    @Override
    public void enterSense(AquaParser.SenseContext ctx) {
        checkIsNameInitialized(ctx.identifier());

        Identifier[] identifiers = new Identifier[ctx.identifier().size()];
        Declaration fluid = declarationsMapper.get(ctx.identifier(0).IDENTIFIER().getText());
        Declaration var = declarationsMapper.get(ctx.identifier(1).IDENTIFIER().getText());
        if (fluid instanceof Fluid || ctx.identifier(0).IDENTIFIER().getText().equals("it")) {
            if (var instanceof Var) {
                SenseType senseType;
                if (ctx.sense_type().getText().equals("FLUORESCENCE")) {
                    senseType = SenseType.FLUORESCENCE;
                } else {
                    senseType = SenseType.OPTICAL;
                } // Parser will take care of every other string

                for (int i = 0; i < ctx.identifier().size(); i++) {
                    identifiers[i] = new Identifier(ctx.identifier(i).IDENTIFIER().getText(),ctx.identifier(i).index());
                }

                statements.add(new Sense(senseType, identifiers[0], identifiers[1]));
            } else {
                errors.add("ERROR: "+ctx.identifier(1).IDENTIFIER().getText()+" is not a VAR");
            }
        } else {
            errors.add("ERROR: "+ctx.identifier(0).IDENTIFIER().getText()+" is not a FLUID");
        }
    }

    /* CONTROL STATEMENTS */

    @Override
    public void enterFor_loop(AquaParser.For_loopContext ctx) {
        statements.add(new ForLoop(ctx.IDENTIFIER().getText(), ctx.expr(0), ctx.expr(1), null, true));
    }

    @Override
    public void exitFor_loop(AquaParser.For_loopContext ctx) {
        statements.add(new ForLoop(ctx.IDENTIFIER().getText(), null, null, null, false));
    }

    @Override
    public void enterRepeat(AquaParser.RepeatContext ctx) {
        statements.add(new Repeat(ctx.expr(), null, true));
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

    public List<String> getWarnings() {
        return warnings;
    }

    private void checkDimensionBoundaries(Identifier[] identifiers) {
        for (Identifier identifier : identifiers) {
            checkDimensionBoundaries(identifier);
        }
    }

    private void checkDimensionBoundaries(Identifier identifier) {
        if (identifier.getIdentifier().equals("it")) {
            return;
        }
        Declaration decl = declarationsMapper.get(identifier.getIdentifier());
        if (decl instanceof Var) {
            if (identifier.getIndeces() == null) {
                return;
            }
            for (int i = 0; i<identifier.getIndeces().size(); i++) {
                if (identifier.getIndeces().get(i) == null || ((Var) decl).getDimensions()[i].getDimension() == null) {
                    errors.add("ERROR: "+identifier.getIdentifier()+" null index");
                } else if (getExprValue(identifier.getIndeces().get(i).expr()) > ((Var) decl).getDimensions()[i].getDimension()) {
                    errors.add("ERROR: "+identifier.getIdentifier()+ " index out of bounds");
                }
            }
        } else if (decl instanceof Fluid) {
            if (identifier.getIndeces() == null) {
                return;
            }
            for (int i = 0; i<identifier.getIndeces().size(); i++) {
                if (identifier.getIndeces().get(i) == null || ((Fluid) decl).getDimensions()[i].getDimension() == null) {
                    errors.add("ERROR: "+identifier.getIdentifier()+" null index");
                } else if (getExprValue(identifier.getIndeces().get(i).expr()) > ((Fluid) decl).getDimensions()[i].getDimension()) {
                    errors.add("ERROR: "+identifier.getIdentifier()+ " index out of bounds");
                }
            }
        }
    }

    private void checkIsUniqueIdentifier(String id) {
        if (declarationsMapper.containsKey(id)) {
            errors.add("ERROR: " + id + " is not a unique declaration");
        }
    }

    private void checkIsNameInitialized(AquaParser.IdentifierContext id) {
        if (id.getText().equals("it")) {
            return;
        } else if (!declarationsMapper.containsKey(id.IDENTIFIER().getText())) {
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

    /* Append all the statements within a loop into the loop object */
    /* This is done to specify when the loop start and stops properly */
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
                    newList.add(new ForLoop(((ForLoop) stmt).getIdentifier(), ((ForLoop) stmt).getFrom(), ((ForLoop) stmt).getTo(), appendStatementsIntoControlStatements(statements), false));
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

    private HashMap<String, Integer> varMap = new HashMap<>();
    /* Remove all the loops for the intermediate representation */
    private List<Statement> runOverLoops(List<Statement> statements) {
        List<Statement> newList = new ArrayList<>();
        for (Statement stmt : statements) {
            if (stmt instanceof AssignExpr) {
                String name = getIndex(((AssignExpr) stmt).getIdentifier().getIdentifier(),((AssignExpr) stmt).getIdentifier().getIndeces());
                varMap.put(name, getExprValue(((AssignExpr) stmt).getExpr()));
            } else if (stmt instanceof Repeat) {
                Repeat repeat = (Repeat) stmt;
                Integer numberOfIterations = getExprValue(repeat.getExpr());
                if (numberOfIterations == null) {
                    System.out.println("NOT SUPPOSED TO BE HERE");
                    return null;
                }
                for (int i = 0; i < numberOfIterations; i++) {
                    newList.addAll(runOverLoops(repeat.getStatements()));
                }
            } else if (stmt instanceof ForLoop) {
                ForLoop forLoop = (ForLoop) stmt;
                Integer index = getExprValue(forLoop.getFrom());
                Integer to = getExprValue(forLoop.getTo());
                if (varMap.get(forLoop.getIdentifier()) != null) {
                    warnings.add("WARNING: "+forLoop.getIdentifier()+" has already been initialized and will be overwritten in loop");
                }
                varMap.put(forLoop.getIdentifier(),index);
                if (index == null || to == null) {
                    System.out.println("NOT SUPPOSED TO BE HERE");
                    return null;
                } else if (to < index) {
                    while (index >= to) {
                        newList.addAll(runOverLoops(forLoop.getStatements()));
                        index--;
                        varMap.put(forLoop.getIdentifier(),index);
                    }
                } else {
                    while (index <= to) {
                        newList.addAll(runOverLoops(forLoop.getStatements()));
                        index++;
                        varMap.put(forLoop.getIdentifier(),index);
                    }
                }
                varMap.remove(forLoop.getIdentifier());
            } else if(stmt instanceof Mix) {
                Identifier[] identifiers = new Identifier[((Mix) stmt).getIdentifiers().length];
                for (int i = 0; i<identifiers.length; i++) {
                    identifiers[i] = new Identifier(getIndex(((Mix) stmt).getIdentifiers()[i].getIdentifier(),((Mix) stmt).getIdentifiers()[i].getIndeces()),null);
                }
                Integer[] ratio = new Integer[((Mix) stmt).getRatioExpr().length];
                for (int j = 0; j < ((Mix) stmt).getRatioExpr().length; j++) {
                    ratio[j] = getExprValue(((Mix) stmt).getRatioExpr()[j]);
                }
                Identifier assign = new Identifier(getIndex(((Mix) stmt).getAssign().getIdentifier(), ((Mix) stmt).getAssign().getIndeces()),null);
                checkDimensionBoundaries(((Mix) stmt).getIdentifiers());
                newList.add(new Mix(assign,identifiers,ratio,getExprValue(((Mix) stmt).getTimeExpr())));
            } else if (stmt instanceof Incubate) {
                checkDimensionBoundaries(((Incubate) stmt).getIdentifier());
                Identifier identifier = new Identifier(getIndex(((Incubate) stmt).getIdentifier().getIdentifier(), ((Incubate) stmt).getIdentifier().getIndeces()),null);
                Identifier assign = new Identifier(getIndex(((Incubate) stmt).getAssign().getIdentifier(), ((Incubate) stmt).getAssign().getIndeces()),null);
                newList.add(new Incubate(assign,identifier, getExprValue(((Incubate) stmt).getTemperatureExpr()),getExprValue(((Incubate) stmt).getTimeExpr())));
            } else if (stmt instanceof Sense) {
                Identifier from = new Identifier(getIndex(((Sense) stmt).getFrom().getIdentifier(), ((Sense) stmt).getFrom().getIndeces()),null);
                Identifier into = new Identifier(getIndex(((Sense) stmt).getInto().getIdentifier(), ((Sense) stmt).getInto().getIndeces()),null);
                newList.add(new Sense(((Sense) stmt).getSenseType(), from, into));
            } else {
                // TODO: Revisit dimensions to make them into an array and check whether the length is out of bounds
                newList.add(stmt);
            }
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
        Integer var = varMap.get(expr.identifier().IDENTIFIER().getText());
        if (var == null) {
            errors.add("ERROR: "+expr.identifier().getText()+" has not been declared");
            return null;
        }
        return var;
    }

    private String getIndex(String name, List<AquaParser.IndexContext> indexContexts) {
        // If there are no indices, the list will be null
        if (indexContexts == null) {
            return name;
        }
        // Handle multiple dimensions.
        for (AquaParser.IndexContext index: indexContexts) {
            name+=("["+getExprValue(index.expr())+"]");
        }
        return name;
    }
}
