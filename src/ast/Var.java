package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Var extends Declaration {

    private Integer value = null;

    private List<Dimension> dimensions = new ArrayList<>();

    public Var(String identifier) {
        setIdentifier(identifier);
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void appendDimension(Dimension dimension) {
        dimensions.add(dimension);
    }
}
