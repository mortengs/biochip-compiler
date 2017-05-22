package ast;

import java.util.List;

/**
 * Created by Jesper on 22/03/2017.
 */
public class Repeat extends ControlStatement {

    List<Statement> statements;
    // Used when creating the IR
    boolean isStart;

    public Repeat(List<Statement> statements) {
        this.statements = statements;
    }

    public Repeat(boolean isStart) {
        this.isStart=isStart;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}