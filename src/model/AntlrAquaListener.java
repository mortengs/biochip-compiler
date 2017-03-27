package model;

import ast.Assay;

import ast.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import parser.AquaBaseListener;
import parser.AquaParser;

import java.util.List;
import java.util.ArrayList;


/**
 * Created by Jesper on 20/03/2017.
 */
public class AntlrAquaListener extends AquaBaseListener {
    Assay assay;
    @Override
    public void enterAssay(AquaParser.AssayContext ctx) {
        assay = new Assay(ctx.IDENTIFIER().getText());
    }

    @Override
    public void enterDecl(AquaParser.DeclContext ctx)  {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if(ctx.getChild(i) instanceof AquaParser.FluidContext) {
//                System.out.println("fluid");
                //assay.appendDeclarationList(new Fluid(ctx.fluid().IDENTIFIER().toString(),ctx.fluid().dimension()));
            } else if (ctx.getChild(i) instanceof AquaParser.Input_Context) {
//                System.out.println("input");
            } else if (ctx.getChild(i) instanceof AquaParser.VarContext) {
//                System.out.println("var");
            } else if (ctx.getChild(i) instanceof AquaParser.ConflictContext) {
//                System.out.println("conflict");
            }
        }
    }

    @Override
    public void enterFluid(AquaParser.FluidContext ctx) {
        Fluid fluid = new Fluid(ctx.IDENTIFIER(0).toString());
        for (AquaParser.DimensionContext dimc: ctx.dimension()) {
            fluid.appendDimension(new Dimension(Integer.parseInt(dimc.getText().substring(1, dimc.getText().length()-1))));
        }
        if (ctx.IDENTIFIER(1) != null) {
            fluid.setWash_identifier(ctx.IDENTIFIER(1).getText());
        }
        if (ctx.INTEGER() != null) {
            fluid.setPort_integer(Integer.parseInt(ctx.INTEGER().getText()));
        }
        assay.appendDeclarationList(fluid);
    }

    @Override
    public void enterInput_(AquaParser.Input_Context ctx) {
        Input input = new Input(ctx.IDENTIFIER().getText());
        if (ctx.INTEGER() != null) {
            input.setInput_integer(Integer.parseInt(ctx.INTEGER().getText()));
        }
        assay.appendDeclarationList(input);
    }

    @Override
    public void enterVar(AquaParser.VarContext ctx) {
        Var var = new Var(ctx.IDENTIFIER().toString());
        for (AquaParser.DimensionContext dimc: ctx.dimension()) {
            var.appendDimension(new Dimension(Integer.parseInt(dimc.getText().substring(1, dimc.getText().length()-1))));
        }
        assay.appendDeclarationList(var);
    }

    @Override 
    public void enterConflict(AquaParser.ConflictContext ctx) {
        Conflict conflict = new Conflict(ctx.IDENTIFIER(0).getText(),ctx.IDENTIFIER(1).getText());
        if (ctx.IDENTIFIER(2) != null) {
            conflict.setWash_identifier(ctx.IDENTIFIER(2).getText());
        }
        assay.appendDeclarationList(conflict);
    }
}
