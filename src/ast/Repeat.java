package ast;

/**
 * Created by Jesper on 22/03/2017.
 */
public class Repeat extends ControlStatement {

    Statement[] statements;

    public Repeat(Statement[] statements) {
        this.statements = statements;
    }

    public Repeat() {

    }
}
