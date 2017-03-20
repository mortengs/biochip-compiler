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

    public Fluid(String identifer, List<Dimension> dimensions, String wash_identifier, int port_integer) {
        setIdentifier(identifer);
        this.dimensions = dimensions;
        this.wash_identifier = wash_identifier;
        this.port_integer = port_integer;
    }
}
