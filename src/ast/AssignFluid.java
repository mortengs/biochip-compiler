package ast;

/**
 * Created by Jesper on 28/05/2017.
 */
public class AssignFluid extends Statement {

    //assign: IDENTIFIER '=' (mix | incubate) | IDENTIFIER '=' expr;
    public AssignFluid(String identifier) {
        this.identifier = identifier;
    }
}
