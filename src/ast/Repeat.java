package ast;

/**
 * Created by Jesper on 22/03/2017.
 */
public class Repeat extends ControlStatement {

    Expr expr;
    Statement[] statements;

    public Repeat(Expr expr, Statement[] statements) {
        this.expr = expr;
        this.statements = statements;
    }
}
