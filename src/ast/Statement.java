package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public abstract class Statement {
    String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
