package model;

import ast.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRSearchTree {

    /* The start is a null value node */
    Node<Statement> root = new Node<Statement>(null);

    /* Creating the search tree from a list of statements */
    public IRSearchTree(List<Statement> statements) {
        BuildSearchTree(statements);
    }

    private void BuildSearchTree(List<Statement> statements) {
        // TODO: Implement solution to dimensions
        HashMap<String, Node<Statement>> fluidMap = new HashMap<>();

        /* it will always be overwritten for each statement */
        Identifier it = new Identifier("it",null);

        /* Specifies at which depth the fluid is */
        for (Statement statement : statements) {
            // Remember at which depth a fluid is overwritten.
            // Check if assign is a fluid
            // else if assign is null.
            if (statement instanceof Mix) {
                Node mix = new Node<>(statement);
                Identifier assignedValue = ((Mix) statement).getAssign();
                for (Identifier identifier : ((Mix) statement).getIdentifiers()) {
                    if (identifier.getIdentifier().equals(it.getIdentifier())) {
                        mix.addParent(fluidMap.get(it.getIdentifier()));
                    } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                        mix.addParent(fluidMap.get(identifier.getIdentifier()));
                    } else {
                        mix.addParent(root);
                    }
                }
                fluidMap.put(assignedValue.getIdentifier(),mix);
                fluidMap.put(it.getIdentifier(),mix);
            } else if (statement instanceof Incubate) {
                Node incubate = new Node<>(statement);
                Identifier assignedValue = ((Incubate) statement).getAssign();
                Identifier identifier = ((Incubate) statement).getIdentifier();
                if (identifier.getIdentifier().equals(it.getIdentifier())) {
                    incubate.addParent(fluidMap.get(it.getIdentifier()));
                } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                    incubate.addParent(fluidMap.get(identifier.getIdentifier()));
                } else {
                    incubate.addParent(root);
                }
                fluidMap.put(assignedValue.getIdentifier(),incubate);
                fluidMap.put(it.getIdentifier(),incubate);
            } else if (statement instanceof Sense) {
                Node sense = new Node<>(statement);
                Identifier identifier = ((Sense) statement).getFrom();
                if (identifier.getIdentifier().equals(it.getIdentifier())) {
                    sense.addParent(fluidMap.get(it.getIdentifier()));
                } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                    sense.addParent(fluidMap.get(identifier.getIdentifier()));
                } else {
                    sense.addParent(root);
                }
                fluidMap.put(identifier.getIdentifier(),sense);
                fluidMap.put(it.getIdentifier(),sense);
            }
        }
    }

    public class Node<Statement> {
        private List<Node<Statement>> children = new ArrayList<Node<Statement>>();
        private List<Node<Statement>> parents = new ArrayList<Node<Statement>>();
        private Statement data = null;

        public Node(Statement data) {
            this.data = data;
        }

        public Node(Statement data, Node<Statement> parent) {
            this.data = data;
            parent.addChild(parent);
        }

        public Node(Statement data, List<Node<Statement>> parent) {
            this.data = data;
            this.parents = parent;
        }

        public List<Node<Statement>> getChildren() {
            return children;
        }

        public List<Node<Statement>> getParents() {
            return parents;
        }

        public void addParent(Node<Statement> parent) {
            parent.addChild(this);
            parents.add(parent);
        }

        public void addChild(Node<Statement> child) {
            children.add(child);
        }

        public Statement getData() {
            return this.data;
        }

        public void setData(Statement data) {
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
    }
}
