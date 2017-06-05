package model;

import ast.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public final class IRWalker {
    private static List<IRSearchTree.Node<Statement>> children = new ArrayList<>();
    private static HashMap<IRSearchTree.Node<Statement>,Boolean> hasVisited = new HashMap<>();

    private IRWalker() {
        children = null;
    }

    public static void walk(IRSearchTree.Node<Statement> node, int depth) {
        depth++;
        for (IRSearchTree.Node newNode : node.getChildren()) {
            if (hasVisited.containsKey(newNode)) {
                continue;
            } else {
                hasVisited.put(newNode,true);
                walk(newNode,depth);
            }
        }
    }
}
