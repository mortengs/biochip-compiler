package ast;

/**
 * Created by Jesper on 31/05/2017.
 */
public class Identifier {
    String identifier;
    Dimension[] dimensions;
    public Identifier(String identifier, Dimension[] dimensions) {
        this.identifier = identifier;
        this.dimensions = dimensions;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getNumberOfDimensions() {
        return dimensions.length;
    }

    public Dimension[] getDimensions() {
        return dimensions;
    }
}
