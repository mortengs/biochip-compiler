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
    public IRSearchTree.Node<Component> resourceConstrainedListScheduling(IRSearchTree.Node<Component> root, String[] constraints) {
        IRWalker walker = new IRWalker();
        int time = 0;
        int longestTime = 0;
        // TODO: Calculate the longest path
        // Find the end nodes, add time while doing so
        // Each node will have a specific time
        longestTime = walker.getTimeForPath(root,null);
        //walker.getTimeForPath(root,null);
        // TODO: implement the rest

        return root;
    }

    // TODO: Solution to mixer ratio problem

    public void ratioApproximation() {

    }
}
