package model;

import ast.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRWalker {

    private Constraints constraints;
    private List<Node<Statement>> path;
    private List<PathTime> paths;
    private List<Node<Statement>> longestPath;
    private int longestPathTime;

    public IRWalker() {
        longestPathTime = 0;
        path = new ArrayList<>();
        paths = new ArrayList<>();
        longestPath = new ArrayList<>();
    }

    // Used for debug, if you want to see the entire tree (with duplicates)
    public void walkOverTree(Node<Statement> node, int depth) {
        System.out.println("This node: " + node.getData() + ", depth: " + depth);
        depth++;
        for (Node<Statement> child : node.getChildren()) {
            walkOverTree(child, depth);
        }
    }

    public List<PathTime> getAllPaths(Node<Statement> root, Constraints constraints) {
        if (paths.isEmpty()) {
            getAllPaths(root, 0);
        }
        paths.sort(Comparator.comparing(PathTime::getTime).reversed());
        return paths;
    }

    public List<Node<Statement>> getLongestPath(Node<Statement> root, Constraints constraints) {
        if (longestPath.isEmpty()) {
            getAllPaths(root, 0);
        }
        return longestPath;
    }

    public int getLongestPathTime(Node<Statement> root, Constraints constraints) {
        this.constraints = constraints;
        if (longestPathTime != 0) {
            return longestPathTime;
        } else {
            getAllPaths(root, 0);
            return longestPathTime;
        }
    }

    private int getTime(Node<Statement> node) {
        // TODO: Manage constraints and additional time for operations and moving between them
        if (node.getData() instanceof Mix) {
            return ((Mix) node.getData()).getTime();
        } else if (node.getData() instanceof Incubate) {
            return ((Incubate) node.getData()).getTime();
        } else {
            // Detector
            return 0;
        }
    }

    private void getAllPaths(Node<Statement> node, int time) {
        time += getTime(node);
        if (node.isLeaf()) {
            List<Node<Statement>> savedList = new ArrayList<>();
            savedList.addAll(path);
            paths.add(new PathTime(time,savedList));
            if (time > longestPathTime) {
                longestPathTime = time;
                longestPath.addAll(path);
            }
            path.remove(node);
        } else {
            for (Node<Statement> child : node.getChildren()) {
                path.add(child);
                getAllPaths(child, time);
                path.remove(child);
            }
        }
    }
}
