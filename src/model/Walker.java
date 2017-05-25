package model;

import ast.Assay;
import ast.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 22/05/2017.
 */
public class Walker {
    List<Statement> statements = new ArrayList<>();

    public Statement walk() {
        if(!statements.isEmpty()) {
            Statement stmt = statements.get(0);
            statements.remove(0);
            return stmt;
        }
        return null;
    }
}
