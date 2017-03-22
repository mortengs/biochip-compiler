package ast;

/**
 * Created by Jesper on 22/03/2017.
 */
public class ParExpr {

    //    | '(' expr ')'           #ParExpr
    Expr expr;

    public ParExpr(Expr expr) {
        this.expr = expr;
    }
}
