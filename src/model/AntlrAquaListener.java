package model;

import ast.Assay;

import ast.Fluid;
import parser.AquaBaseListener;
import parser.AquaParser;


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
            System.out.println(ctx.getChild(i).getText());
            if(ctx.getChild(i) instanceof AquaParser.FluidContext) {
                assay.appendDeclarationList(new Fluid(ctx.fluid().IDENTIFIER().toString(),));
            }
        }
    }

    @Override
    public void enterFluid(AquaParser.FluidContext ctx) {

    }

    @Override
    public void enterInput_(AquaParser.Input_Context ctx) {

    }

    @Override
    public void enterVar(AquaParser.VarContext ctx) {
        
    }

    @Override 
    public void enterConflict(AquaParser.ConflictContext ctx) {
        
    }
}
