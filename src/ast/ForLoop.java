package ast;

/**
 * Created by Jesper on 22/03/2017.
 */

public class ForLoop extends ControlStatement {
    // 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR'
    String identifier;
    Expr from;
    Expr to;
    Statement[] statements;

    public ForLoop(String identifier, Expr from, Expr to, Statement[] statements) {
        this.identifier = identifier;
        this.from = from;
        this.to = to;
        this.statements = statements;
    }
}
