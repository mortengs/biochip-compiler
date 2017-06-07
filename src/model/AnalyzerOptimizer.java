package model;

import ast.*;
import components.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 29/05/2017.
 */
public class AnalyzerOptimizer {


    // TODO: LS algorithm
    public List<Statement> resourceConstrainedListScheduling(IRSearchTree.Node<Component> root, String[] constraints) {
        int time = 0;
        // TODO: Calculate the longest path
        // Find the end nodes, add time while doing so
        // Each node will have a specific time
        IRWalker walker = new IRWalker();
        walker.getTimeForPath(root,null);
        // TODO:
        return null;
    }

    // TODO: Solution to mixer ratio problem

    public void ratioApproximation() {

    }
}
