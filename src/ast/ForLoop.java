package ast;

/**
 * Created by Jesper on 22/03/2017.
 */

public class ForLoop extends ControlStatement {
    // 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR'
    String identifier;
    Statement[] statements;

    public ForLoop(String identifier, Statement[] statements) {
        this.identifier = identifier;
        this.statements = statements;
    }

    public ForLoop(String identifier) {
        this.identifier = identifier;
    }
}
