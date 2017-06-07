package ast;

import parser.AquaParser;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Incubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;
    Identifier identifier;
    Identifier assign;
    AquaParser.ExprContext at;
    AquaParser.ExprContext forValue;

    public Incubate(Identifier assign, Identifier identifier, AquaParser.ExprContext at, AquaParser.ExprContext forValue) {
        this.assign = assign;
        this.identifier = identifier;
        this.at = at;
        this.forValue = forValue;
    }

    public Identifier getAssign() {
        return assign;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public AquaParser.ExprContext getAt() {
        return at;
    }

    public AquaParser.ExprContext getForValue() {
        return forValue;
    }
}
