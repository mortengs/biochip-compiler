package model;

import ast.*;
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
    public IRSearchTree(List<Declaration> declarations, List<Statement> statements) {
        BuildSearchTree(declarations, statements);
    }

    private void BuildSearchTree(List<Declaration> declarations, List<Statement> statements) {
        // TODO: Implement solution to dimensions
        HashMap<String, Node<Component>> fluidMap = new HashMap<>();

        /* it will always be overwritten for each statement */
        Identifier it = new Identifier("it",null);

        // No fluid can be linked to root

        // Add input as start for fluids
        for (Declaration declaration : declarations) {
            System.out.println(declaration);
            if (declaration instanceof ast.Input) {
                Node input = new Node<>(new components.Input(((ast.Input) declaration).getInput_integer()));
                input.addParent(root);
                fluidMap.put(declaration.getIdentifier(),input);
            }
        }

        /* Specifies at which depth the fluid is */
        for (Statement statement : statements) {
            // Remember at which depth a fluid is overwritten.
            // Check if assign is a fluid
            // else if assign is null.
            if (statement instanceof ast.Mix) {
                Node mix = new Node<>(new components.Mix(((ast.Mix) statement).getRatio(),((ast.Mix) statement).getTime()));
                Identifier assignedValue = ((ast.Mix) statement).getAssign();
                for (Identifier identifier : ((ast.Mix) statement).getIdentifiers()) {
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
            } else if (statement instanceof ast.Incubate) {
                Node incubate = new Node<>(new components.Incubate(((ast.Incubate) statement).getTemperature(),((ast.Incubate) statement).getTime()));
                Identifier assignedValue = ((ast.Incubate) statement).getAssign();
                Identifier identifier = ((ast.Incubate) statement).getIdentifier();
                if (identifier.getIdentifier().equals(it.getIdentifier())) {
                    incubate.addParent(fluidMap.get(it.getIdentifier()));
                } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                    incubate.addParent(fluidMap.get(identifier.getIdentifier()));
                } else {
                    incubate.addParent(root);
                }
                fluidMap.put(assignedValue.getIdentifier(),incubate);
                fluidMap.put(it.getIdentifier(),incubate);
            } else if (statement instanceof ast.Sense) {
                // Since sense takes no time, one has to add physical constraints to it.
                Node sense = new Node<>(new components.Sense(0));
                Identifier identifier = ((ast.Sense) statement).getFrom();
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
