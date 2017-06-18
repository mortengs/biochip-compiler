package model;

import operations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRWalker {

    // TODO: Move this into IRComponentTree

    private List<Node<Statement>> path;
    private List<PathTime> paths;
    private int longestPathTime;

    public IRWalker() {
        longestPathTime = 0;
        path = new ArrayList<>();
        paths = new ArrayList<>();
    }

    public int getLongestPathTime(Node<Statement> root) {
        if (longestPathTime != 0) {
            return longestPathTime;
        } else {
            getAllPaths(root, 0);
            for (PathTime pathtime : paths) {
                if (pathtime.getTime() > longestPathTime) {
                    path = pathtime.getPath();
                    longestPathTime = pathtime.getTime();
                }
            }
            for (Node node : path) {
                node.setUrgency(true);
            }
            return longestPathTime;
        }
    }

    private int getTime(Node node) {
        if (node.getData() instanceof Mix) {
            if (((Mix) node.getData()).getTime() != null) {
                return ((Mix) node.getData()).getTime();
            } else {
                // Applying default time, since none was given
                return DefaultValues.getMixerDefaultTime();
            }
        } else if (node.getData() instanceof Incubate) {
            if (((Incubate) node.getData()).getTemperature() != null) {
                return ((Incubate) node.getData()).getTime();
            } else {
                // Applying default time, since none was given
                return DefaultValues.getHeaterDefaultTime();
            }
        } else if (node.getData() instanceof Sense) {
            return DefaultValues.getDetectorDefaultTime();
        } else if (node.getData() instanceof Input){
            return DefaultValues.getInputDefaultTime();
        } else {
            return 0;
        }
    }

    private void getAllPaths(Node<Statement> node, int time) {
        time += getTime(node);
        if (node.isLeaf()) {
            List<Node<Statement>> savedList = new ArrayList<>();
            savedList.addAll(path);
            paths.add(new PathTime(time,savedList));
            path.remove(node);
        } else {
            for (Node<Statement> child : node.getChildren()) {
                path.add(child);
                getAllPaths(child, time);
                path.remove(child);
            }
        }
    }

    private class PathTime {
        private int time;
        private List<Node<Statement>> path;

        public PathTime(int time, List<Node<Statement>> path) {
            this.time = time;
            this.path = path;
        }

        public int getTime() {
            return time;
        }

        public List<Node<Statement>> getPath() {
            return path;
        }
    }

}
