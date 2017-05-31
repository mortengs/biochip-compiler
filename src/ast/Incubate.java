package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Incubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;
    private String assign;
    private Integer at;
    private Integer forvalue;

    public Incubate(String assign, String identifier, Integer at, Integer forvalue) {
        this.assign = assign;
        this.identifier = identifier;
        this.at = at;
        this.forvalue = forvalue;
    }
}
