package ast;

/**
 * Created by Jesper on 25/05/2017.
 */
public class AssignFluid extends Statement {

    Mix mix;
    Incubate incubate;

    //assign: IDENTIFIER '=' (mix | incubate) | IDENTIFIER '=' expr;

    public AssignFluid(String identifier, Mix mix, Incubate incubate) {
        this.identifier=identifier;
        this.mix = mix;
        this.incubate = incubate;
    }

    public AssignFluid(String identifier) {
        this.identifier=identifier;
    }
}
