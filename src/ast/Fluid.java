package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */

public class Fluid extends Declaration {

    private List<Dimension> dimensions = new ArrayList<>();
    private String wash_identifier;
    private int port_integer;

    public Fluid(String identifer) {
        setIdentifier(identifer);
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void appendDimension(Dimension dimension) {
        dimensions.add(dimension);
    }

    public String getWash_identifier() {
        return wash_identifier;
    }

    public void setWash_identifier(String wash_identifier) {
        this.wash_identifier = wash_identifier;
    }

    public int getPort_integer() {
        return port_integer;
    }

    public void setPort_integer(int port_integer) {
        this.port_integer = port_integer;
    }
}
