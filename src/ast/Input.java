package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Input extends Declaration {
    int input_integer;

    public Input(String identifier) {
        setIdentifier(identifier);
    }

    public int getInput_integer() {
        return input_integer;
    }

    public void setInput_integer(int input_integer) {
        this.input_integer = input_integer;
    }
}
