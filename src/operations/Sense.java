package operations;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Sense extends Statement {

    //sense: 'SENSE' sense_type IDENTIFIER 'INTO' IDENTIFIER;

    private Identifier from;
    private Identifier into;
    private SenseType senseType;

    public Sense(SenseType senseType, Identifier from, Identifier into) {
        this.senseType = senseType;
        this.from = from;
        this.into = into;
    }

    public SenseType getSenseType() {
        return senseType;
    }

    public Identifier getFrom() {
        return from;
    }

    public Identifier getInto() {
        return into;
    }
}
