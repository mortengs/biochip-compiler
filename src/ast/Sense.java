package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Sense extends Statement {

    //sense: 'SENSE' sense_type IDENTIFIER 'INTO' IDENTIFIER;

    private String from;
    private String into;
    private SenseType senseType;

    public Sense(SenseType senseType, String from, String into) {
        this.senseType = senseType;
        this.from = from;
        this.into = into;
    }

    public SenseType getSenseType() {
        return senseType;
    }

    public String getFrom() {
        return from;
    }

    public String getInto() {
        return into;
    }
}
