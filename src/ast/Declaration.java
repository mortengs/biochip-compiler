package ast;


/**
 * Created by Jesper on 15/03/2017.
 */
public abstract class Declaration {

    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) { this.identifier=identifier; }

}