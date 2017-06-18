package operations;

/**
 * Created by Jesper on 15/03/2017.
 */

public class Fluid extends Declaration {

    private Dimension[] dimensions;
    private String wash_identifier;
    private Integer port_integer;

    public Fluid(String identifier, Dimension[] dimensions, String wash_identifier, Integer port_integer) {
        this.identifier = identifier;
        this.dimensions = dimensions;
        this.wash_identifier = wash_identifier;
        this.port_integer = port_integer;
    }

    public Dimension[] getDimensions() {
        return dimensions;
    }

    public String getWash_identifier() {
        return wash_identifier;
    }

    public Integer getPort_integer() {
        return port_integer;
    }
}
