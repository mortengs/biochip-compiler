package ast;

/**
 * Created by Jesper on 07/06/2017.
 */
public class CalculatedIncubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;
    Identifier identifier;
    Identifier assign;
    Integer at;
    Integer forValue;

    public CalculatedIncubate(Identifier assign, Identifier identifier, Integer at, Integer forValue) {
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

    public Integer getAt() {
        return at;
    }

    public Integer getForValue() {
        return forValue;
    }
}
