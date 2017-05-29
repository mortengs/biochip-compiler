package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Var extends Declaration {

    private Integer value = null;
    private Dimension[] dimensions;

    public Var(String identifier, Dimension[] dimensions) {
        this.identifier = identifier;
        this.dimensions = dimensions;
    }

    public Dimension[] getDimensions() {
        return dimensions;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
