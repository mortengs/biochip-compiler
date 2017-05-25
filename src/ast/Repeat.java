package ast;

import java.util.List;

/**
 * Created by Jesper on 22/03/2017.
 */
public class Repeat extends ControlStatement {

    private Integer expr;
    private List<Statement> statements;
    // Used when creating the IR
    private boolean isStart;

    public Repeat(Integer expr, List<Statement> statements, boolean isStart) {
        this.expr = expr;
        this.statements = statements;
        this.isStart = isStart;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public Integer getExpr() {
        return expr;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}