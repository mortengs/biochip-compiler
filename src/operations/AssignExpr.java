package operations;

import parser.AquaParser;

/**
 * Created by Jesper on 31/05/2017.
 */
public class AssignExpr extends Statement {
    private Identifier identifier;
    private AquaParser.ExprContext expr;
    public AssignExpr(Identifier identifier, AquaParser.ExprContext expr) {
        this.identifier = identifier;
        this.expr = expr;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public AquaParser.ExprContext getExpr() {
        return expr;
    }
}
