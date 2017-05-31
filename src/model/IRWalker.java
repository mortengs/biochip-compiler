package model;

import ast.ForLoop;
import ast.Repeat;
import ast.Statement;

import java.util.*;

/**
 * Created by Jesper on 22/05/2017.
 */
public final class IRWalker {
    private static Stack<Statement> stack = new Stack<>();

    private IRWalker() { // private constructor
        stack = null;
    }

    public static Statement walk(List<Statement> statements) {
        for (int i = statements.size()-1; i >= 0; i--) {
            stack.push(statements.get(i));
        }
        return walk();
    }

    public static Statement walk() {
        if(!stack.isEmpty()) {
            Statement stmt = stack.pop();
            if (stmt instanceof ForLoop || stmt instanceof Repeat) {
                List<Statement> innerStmts = loop(stmt);
                for (int i = innerStmts.size()-1; i >= 0; i--) {
                    stack.push(innerStmts.get(i));
                }
            }
            return stmt;
        }
        return null;
    }

    private static List<Statement> loop(Statement loop) {
        if (loop instanceof Repeat) {
            List<Statement> list = ((Repeat) loop).getStatements();
            return list;
        } else {
            List<Statement> list = ((ForLoop) loop).getStatements();
            return list;
        }
    }
}
