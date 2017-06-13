package model;

import ast.Statement;

import java.util.List;

/**
 * Created by Jesper on 12/06/2017.
 */
public class PathTime {
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
