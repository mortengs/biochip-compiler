package model;

import operations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
class IRComponentPathAnalyser {

    // TODO: Move this into IRComponentTree

    private List<Node<Statement>> path;
    private List<PathTime> paths;
    private int longestPathTime;

    IRComponentPathAnalyser() {
        longestPathTime = 0;
        path = new ArrayList<>();
        paths = new ArrayList<>();
    }

    int getLongestPathTime(Node<Statement> root) {
        int urgency = 0;
        if (longestPathTime != 0) {
            return longestPathTime;
        } else {
            getAllPaths(root, 0);
            paths.sort(Comparator.comparingInt(PathTime::getTime).reversed());
            for (PathTime pathtime : paths) {
                for (Node node : pathtime.getPath()) {
                    if (node.getUrgency() == Integer.MAX_VALUE) {
                        node.setUrgency(urgency);
                    }
                }
                urgency++;
            }
            longestPathTime = paths.get(0).getTime();
            return longestPathTime;
        }
    }

    private int getTime(Node node) {
        if (node.getData() instanceof Mix) {
            if (((Mix) node.getData()).getTime() != null) {
                return ((Mix) node.getData()).getTime();
            } else {
                // Applying default time, since none was given
                return Configuration.getMixerDefaultTime();
            }
        } else if (node.getData() instanceof Incubate) {
            if (((Incubate) node.getData()).getTemperature() != null) {
                return ((Incubate) node.getData()).getTime();
            } else {
                // Applying default time, since none was given
                return Configuration.getHeaterDefaultTime();
            }
        } else if (node.getData() instanceof Sense) {
            return Configuration.getDetectorDefaultTime();
        } else if (node.getData() instanceof Input){
            return Configuration.getInputDefaultTime();
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

        PathTime(int time, List<Node<Statement>> path) {
            this.time = time;
            this.path = path;
        }

        public int getTime() {
            return time;
        }

        List<Node<Statement>> getPath() {
            return path;
        }
    }
}
