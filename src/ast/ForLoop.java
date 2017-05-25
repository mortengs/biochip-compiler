package ast;

import java.util.List;

/**
 * Created by Jesper on 22/03/2017.
 */

public class ForLoop extends ControlStatement {
    // 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR'
    Integer from;
    Integer to;
    List<Statement> statements;
    boolean isStart;

    public ForLoop(String identifier, Integer from, Integer to, List<Statement> statements, boolean isStart) {
        this.identifier = identifier;
        this.from = from;
        this.to = to;
        this.statements = statements;
        this.isStart=isStart;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }
}
