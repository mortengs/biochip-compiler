package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Input extends Declaration {
    int input_integer;

    public Input(String identifier, int input_integer) {
        setIdentifier(identifier);
        this.input_integer = input_integer;
    }
}
