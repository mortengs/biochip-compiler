package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Var extends Declaration {

    private List<Dimension> dimensions = new ArrayList<>();

    public Var(String identifier, List<Dimension> dimensions) {
        setIdentifier(identifier);
        this.dimensions = dimensions;
    }

}
