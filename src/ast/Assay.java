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

    public void appendDeclarationList(Declaration declaration) {
        declarations.add(declaration);
    }

    public void appendStatementList(Statement statement) {
        statements.add(statement);
    }
}
