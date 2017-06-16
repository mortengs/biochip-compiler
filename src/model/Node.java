package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 12/06/2017.
 */
public class Node<T> {
    private List<Node<T>> children = new ArrayList<>();
    private List<Node<T>> parents = new ArrayList<>();
    private T data = null;
    private boolean isUrgent = false;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        parent.addChild(parent);
    }

    public Node(T data, List<Node<T>> parent) {
        this.data = data;
        this.parents = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public List<Node<T>> getParents() {
        return parents;
    }

    public void addParent(Node<T> parent) {
        parent.addChild(this);
        parents.add(parent);
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parents == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public boolean getUrgency() {
        return isUrgent;
    }

    public void setUrgency(boolean urgency) {
        this.isUrgent = urgency;
    }
}