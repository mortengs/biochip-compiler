package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Conflict extends Declaration {

    private String follows_identifier;
    private String wash_identifier;

    //conflict: 'CONFLICT' IDENTIFIER ('FOLLOWS' IDENTIFIER | ',' IDENTIFIER) ('WASH' IDENTIFIER)

    public Conflict(String identifier, String follows_identifier, String wash_identifier) {
        setIdentifier(identifier);
        this.follows_identifier = follows_identifier;
        this.wash_identifier = wash_identifier;

    }
}
