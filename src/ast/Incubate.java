package ast;

import parser.AquaParser;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Incubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;
    private Identifier identifier;
    private Identifier assign;
    private Integer at;
    private Integer forvalue;

    public Incubate(Identifier assign, Identifier identifier, Integer at, Integer forvalue) {
        this.assign = assign;
        this.identifier = identifier;
        this.at = at;
        this.forvalue = forvalue;
    }

    public Identifier getAssign() {
        return assign;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Integer getAt() {
        return at;
    }

    public Integer getForvalue() {
        return forvalue;
    }
}
