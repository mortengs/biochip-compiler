package model;

import ast.Assay;
import ast.Statement;

import java.util.List;

/**
 * Created by Jesper on 22/05/2017.
 */
public class Walker {
    public void walk(Assay assay) {
        List<Statement> statements = assay.getStatements();
        for (Statement stmt: statements) {

        }
    }

    public void walk(List<Statement> statements) {

    }
}
