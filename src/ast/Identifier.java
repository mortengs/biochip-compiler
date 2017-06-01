package ast;

/**
 * Created by Jesper on 31/05/2017.
 */
public class Identifier {
    String identifier;
    Index[] indices;
    public Identifier(String identifier, Index[] indices) {
        this.identifier = identifier;
        this.indices = indices;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getNumberOfIndeces() {
        return indices.length;
    }

    public Index[] getIndeces() {
        return indices;
    }
}
