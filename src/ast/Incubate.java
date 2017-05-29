package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Incubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;
    private Integer at;
    private Integer forvalue;

    public Incubate(String identifier, Integer at, Integer forvalue) {
        this.identifier = identifier;
        this.at = at;
        this.forvalue = forvalue;
    }
}
