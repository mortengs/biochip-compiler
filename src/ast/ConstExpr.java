package ast;

/**
 * Created by Jesper on 22/03/2017.
 */
public class ConstExpr {

    //    | INTEGER                #ConstExpr

    int integer;

    public ConstExpr(int integer) {
        this.integer = integer;
    }
}
