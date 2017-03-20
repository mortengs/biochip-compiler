package model;

import parser.AquaBaseListener;
import parser.AquaParser;

/**
 * Created by Jesper on 20/03/2017.
 */
public class AntlrAquaListener extends AquaBaseListener {
    @Override
    public void enterAssay(AquaParser.AssayContext ctx) {
        System.out.println(ctx.getText());
    }
}
