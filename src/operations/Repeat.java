package operations;

import parser.AquaParser;

import java.util.List;

/**
 * Created by Jesper on 22/03/2017.
 */
public class Repeat extends ControlStatement {

    private AquaParser.ExprContext expr;
    private List<Statement> statements;
    // Used when creating the IR
    private boolean isStart;

    public Repeat(AquaParser.ExprContext expr, List<Statement> statements, boolean isStart) {
        this.expr = expr;
        this.statements = statements;
        this.isStart = isStart;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public AquaParser.ExprContext getExpr() {
        return expr;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}