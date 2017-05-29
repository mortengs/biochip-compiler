package ast;

/**
 * Created by Jesper on 28/05/2017.
 */
public class AssignFluid extends Statement {

    //assign: IDENTIFIER '=' (mix | incubate) | IDENTIFIER '=' expr;
    private Mix mix;
    private Incubate incubate;

    public AssignFluid(String identifier, Mix mix) {
        this.identifier = identifier;
        this.mix = mix;
    }

    public AssignFluid(String identifier, Incubate incubate) {
        this.identifier = identifier;
        this.incubate = incubate;
    }

    public AssignFluid(String identifier) {
        this.identifier = identifier;
    }

}
