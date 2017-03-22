package ast;

/**
 * Created by Jesper on 22/03/2017.
 */
public class VarExpr {
    //    | identifier             #VarExpr
    String identifer;

    public VarExpr(String identifer) {
        this.identifer = identifer;
    }
}
