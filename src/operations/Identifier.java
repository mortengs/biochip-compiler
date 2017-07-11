package operations;

import parser.AquaParser;

import java.util.List;

/**
 * Created by Jesper on 31/05/2017.
 */
public class Identifier {
    String identifier;
    List<AquaParser.IndexContext> index;
    public Identifier(String identifier, List<AquaParser.IndexContext> index) {
        this.identifier = identifier;
        this.index = index;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<AquaParser.IndexContext> getIndeces() {
        return index;
    }
}
