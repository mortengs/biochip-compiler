package operations;

import parser.AquaParser;

/**
 * Created by Jesper on 31/05/2017.
 */
public class Index {
    private AquaParser.ExprContext expr;

    public Index(AquaParser.ExprContext expr) {
        this.expr = expr;
    }

    public AquaParser.ExprContext getIndex() {
        return expr;
    }
}
