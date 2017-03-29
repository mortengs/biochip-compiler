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

    HashMap<String, Declaration> declarationsMapper = new HashMap();
    List<Input> inputs = new ArrayList<>();
    List<Statement> statements = new ArrayList<>();
    Assay assay;

    @Override
    public void enterAssay(AquaParser.AssayContext ctx) {
        assay = new Assay(ctx.IDENTIFIER().getText());
    }

    @Override
    public void exitAssay(AquaParser.AssayContext ctx) {
        isInputAFluid();
        toString();
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

    }

    @Override
    public void enterMix(AquaParser.MixContext ctx) {
        isInMap(ctx.identifier());

        String[] identifiers = new String[ctx.identifier().size()];
        for (int i = 0; i < ctx.identifier().size(); i++) {
            identifiers[i] = ctx.identifier(i).getText();
        }

        statements.add(new Mix(identifiers));
    }

    @Override
    public void enterIncubate(AquaParser.IncubateContext ctx) {
        isInMap(ctx.identifier());

        statements.add(new Incubate(ctx.identifier().getText()));
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
        // todo: Append start loop to list
        statements.add(new ForLoop(ctx.IDENTIFIER().getText()));
    }

    @Override
    public void exitFor_loop(AquaParser.For_loopContext ctx) {
        // todo: Append end loop to list
        statements.add(new ForLoop(ctx.IDENTIFIER().getText()));
    }

    @Override
    public void enterRepeat(AquaParser.RepeatContext ctx) {
        statements.add(new Repeat());
    }

    @Override
    public void exitRepeat(AquaParser.RepeatContext ctx) {
        statements.add(new Repeat());
    }

    /* HELPER FUNCTIONS */

    public void isInMap(AquaParser.IdentifierContext id) {
        if (!declarationsMapper.containsKey(id.getText())) {
            System.out.println("ERROR: " + id.getText() + " is not a declaration");
        }
    }

    public void isInMap(List<AquaParser.IdentifierContext> ids) {
        for (AquaParser.IdentifierContext id : ids) {
            if (!declarationsMapper.containsKey(id.getText())) {
                System.out.println("ERROR: " + id.getText() + " is not a declaration");
            }
        }
    }

    public boolean isInputAFluid() {
        boolean isFluid = true;
        for (Input input : inputs) {
            if (!declarationsMapper.containsKey(input.getIdentifier())) {
                isFluid = false;
                System.out.println("ERROR: "+input.getIdentifier()+" is not declared as a fluid");
            }
        }
        return isFluid;
    }

    public String toString() {
        for (Statement stmt : statements) {
            if (stmt instanceof Mix) {
                System.out.println("Mix");
            } else if (stmt instanceof Sense) {
                System.out.println("Sense");
            } else if (stmt instanceof Incubate) {
                System.out.println("Incubate");
            } else if (stmt instanceof ForLoop) {
                System.out.println("ForLoop");
            } else if (stmt instanceof Repeat) {
                System.out.println("Repeat");
            }
        }
        return "";
    }
}
