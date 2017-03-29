package ast;

/**
 * Created by Jesper on 20/03/2017.
 */
public class Assign extends Statement {

    Mix mix;
    Incubate incubate;

    //assign: IDENTIFIER '=' (mix | incubate) | IDENTIFIER '=' expr;

    public Assign(String identifier, Mix mix, Incubate incubate) {
        setIdentifier(identifier);
        this.mix = mix;
        this.incubate = incubate;
    }
}
