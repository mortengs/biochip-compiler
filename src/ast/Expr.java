package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public abstract class Expr {

    //    expr
    //    : expr op=('*'|'/') expr #MulDiv
    //    | expr op=('+'|'-') expr #AddSub
    //    | '(' expr ')'           #ParExpr
    //    | identifier             #VarExpr
    //    | INTEGER                #ConstExpr
    //    ;

    // todo: Should this only work with identifiers, since we only build?

    public Expr() {

    }
}
