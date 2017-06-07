package model;

import ast.*;
import ast.Incubate;
import ast.Mix;
import ast.Sense;
import components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRSearchTree {

    /* The start is a null value node */
    Node<Component> root = new Node<Component>(null);

    /* Creating the search tree from a list of statements */
    public IRSearchTree(List<Statement> statements) {
        BuildSearchTree(statements);
    }

    private void BuildSearchTree(List<Statement> statements) {
        // TODO: Implement solution to dimensions
        HashMap<String, Node<Component>> fluidMap = new HashMap<>();

        /* it will always be overwritten for each statement */
        Identifier it = new Identifier("it",null);

        /* Specifies at which depth the fluid is */
        for (Statement statement : statements) {
            // Remember at which depth a fluid is overwritten.
            // Check if assign is a fluid
            // else if assign is null.
            if (statement instanceof CalculatedMix) {
                Node mix = new Node<>(new components.Mix(((CalculatedMix) statement).getRatio(),((CalculatedMix) statement).getForvalue()));
                Identifier assignedValue = ((CalculatedMix) statement).getAssign();
                for (Identifier identifier : ((CalculatedMix) statement).getIdentifiers()) {
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
            } else if (statement instanceof CalculatedIncubate) {
                Node incubate = new Node<>(new components.Incubate(((CalculatedIncubate) statement).getAt(),((CalculatedIncubate) statement).getForValue()));
                Identifier assignedValue = ((CalculatedIncubate) statement).getAssign();
                Identifier identifier = ((CalculatedIncubate) statement).getIdentifier();
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

    public class Node<Component> {
        private List<Node<Component>> children = new ArrayList<Node<Component>>();
        private List<Node<Component>> parents = new ArrayList<Node<Component>>();
        private Component data = null;

        public Node(Component data) {
            this.data = data;
        }

        public Node(Component data, Node<Component> parent) {
            this.data = data;
            parent.addChild(parent);
        }

        public Node(Component data, List<Node<Component>> parent) {
            this.data = data;
            this.parents = parent;
        }

        public List<Node<Component>> getChildren() {
            return children;
        }

        public List<Node<Component>> getParents() {
            return parents;
        }

        public void addParent(Node<Component> parent) {
            parent.addChild(this);
            parents.add(parent);
        }

        public void addChild(Node<Component> child) {
            children.add(child);
        }

        public Component getData() {
            return this.data;
        }

        public void setData(Component data) {
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
