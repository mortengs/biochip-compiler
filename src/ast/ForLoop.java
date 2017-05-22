package ast;

import java.util.List;

/**
 * Created by Jesper on 22/03/2017.
 */

public class ForLoop extends ControlStatement {
    // 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR'
    List<Statement> statements;
    boolean isStart;

    public ForLoop(String identifier, List<Statement> statements) {
        this.identifier = identifier;
        this.statements = statements;
    }

    public ForLoop(boolean isStart, String identifier) {
        this.isStart=isStart;
    }

    public boolean getIsStart() {
        return isStart;
    }
}
