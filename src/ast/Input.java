package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Input extends Declaration {
    private Integer input_integer;

    public Input(String identifier, Integer input_integer) {
        this.identifier = identifier;
        this.input_integer = input_integer;
    }

    public Integer getInput_integer() {
        return input_integer;
    }
}
