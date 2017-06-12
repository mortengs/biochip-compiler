package model;

import components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRWalker {
    private List<IRSearchTree.Node<Component>> visited;
    private List<IRSearchTree.Node<Component>> path;
    private List<IRSearchTree.Node<Component>> longestPath;
    private int longestTime;

    public IRWalker() {
        longestTime = 0;
        path = new ArrayList<>();
        visited = new ArrayList<>();
        longestPath = new ArrayList<>();
    }

    // Used for debug, if you want to see the entire tree (with duplicates)
    public void walkOverTree(IRSearchTree.Node<Component> node, int depth) {
        System.out.println("This node: " + node.getData() + ", depth: " + depth);
        depth++;
        for (IRSearchTree.Node<Component> child : node.getChildren()) {
            walkOverTree(child, depth);
        }
    }

    public int getTimeForPath(IRSearchTree.Node<Component> root, String[] constraints) {
        if (longestTime != 0) {
            return longestTime;
        } else {
            getAllPaths(root, 0);
            System.out.println("PRINTING LONGEST PATH");
            for (IRSearchTree.Node<Component> node : longestPath) {
                System.out.println(node.getData());
            }
            System.out.println(longestTime);
            return longestTime;
        }
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

    private void getAllPaths(IRSearchTree.Node<Component> node, int time) {
        time += getTime(node, null);
        if (node.isLeaf()) {
            if (time > longestTime) {
                longestTime = time;
                longestPath.addAll(path);
            }
            path.remove(node);
        } else {
            for (IRSearchTree.Node<Component> child : node.getChildren()) {
                path.add(child);
                getAllPaths(child, time);
                path.remove(child);
            }
        }
        visited.add(node);
    }
}
