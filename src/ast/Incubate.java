package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Incubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;

    Expr atexpr;
    Expr forexpr;

    public Incubate(String identifier, Expr atexpr, Expr forexpr) {
        setIdentifier(identifier);
        this.atexpr = atexpr;
        this.forexpr = forexpr;
    }
}
