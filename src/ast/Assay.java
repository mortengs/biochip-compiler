package ast;

import ast.Declaration;
import ast.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jesper on 15/03/2017.
 */

public class Assay {

    private String identifier;
    private List<Declaration> declarations = new ArrayList<>();
    private List<Statement> statements = new ArrayList<>();

    public Assay(String identifier) {
        this.identifier = identifier;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
