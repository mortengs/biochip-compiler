package ast;

/**
 * Created by Jesper on 22/03/2017.
 */
public class AddSub {

    //    | expr op=('+'|'-') expr #AddSub

    Expr expr1;
    Expr expr2;

    public AddSub(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
}
