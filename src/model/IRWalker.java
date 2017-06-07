package model;

import ast.Incubate;
import ast.Mix;
import ast.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRWalker {
    private HashMap<IRSearchTree.Node<Statement>,Boolean> hasVisited;
    private List<List<IRSearchTree.Node<Statement>>> paths;
    private int time;

    public IRWalker() {
        time = 0;
        paths = new ArrayList<>();
        hasVisited = new HashMap<>();
    }

    public void walkOverTree(IRSearchTree.Node<Statement> node, int depth) {
        System.out.println("This node: " + node.getData() + ", depth: " + depth);
        depth++;
        for (IRSearchTree.Node<Statement> newNode : node.getChildren()) {
//            if (!hasVisited.containsKey(newNode)) {
                walkOverTree(newNode, depth);
//                hasVisited.put(newNode,true);
//            }
        }
//        hasVisited = new HashMap<>();
    }

    public int getTimeForPath(IRSearchTree.Node root, String[] constraints) {
        getPath(root);
        int time = 0;
        for (List<IRSearchTree.Node<Statement>> path : paths) {
            for (IRSearchTree.Node<Statement> node : path) {
                time += getTime(node,constraints);
            }
        }
        System.out.println(time);
        return time;
    }

    private int getTime(IRSearchTree.Node node, String[] constraints) {
        // TODO: Manage constraints and additional time for operations and moving between them
        if (node.getData() instanceof Mix) {
            return ((components.Mix) node.getData()).getTime();
        } else if (node.getData() instanceof Incubate) {
            return ((components.Incubate) node.getData()).getTime();
        } else {
            // Sense
            return 0;
        }
    }

    private void getPath(IRSearchTree.Node<Statement> node) {
        List<IRSearchTree.Node<Statement>> path = new ArrayList<>();
        for (IRSearchTree.Node<Statement> newNode : node.getChildren()) {
            if (node.isLeaf()) {
                path.add(node);
                return;
            } else {
                for (IRSearchTree.Node child : node.getChildren()) {
                    path.add(child);
                    getPath(child);
                    return;
                }
                System.out.println("Exhausted children of node "+node.getData());
            }
            path.remove(node);
        }
    }
}
