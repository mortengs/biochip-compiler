package operations;

import parser.AquaParser;

import java.util.List;

/**
 * Created by Jesper on 22/03/2017.
 */

public class ForLoop extends ControlStatement {
    // 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR'
    private String identifier;
    private AquaParser.ExprContext from;
    private AquaParser.ExprContext to;
    private List<Statement> statements;
    private boolean isStart;

    public ForLoop(String identifier, AquaParser.ExprContext from, AquaParser.ExprContext to, List<Statement> statements, boolean isStart) {
        this.identifier = identifier;
        this.from = from;
        this.to = to;
        this.statements = statements;
        this.isStart=isStart;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public AquaParser.ExprContext getFrom() {
        return from;
    }

    public AquaParser.ExprContext getTo() {
        return to;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public String getIdentifier() {
        return identifier;
    }
}
