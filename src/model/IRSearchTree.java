package model;

import ast.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesper on 02/06/2017.
 */
public class IRSearchTree {

    /* The start is a null value node */
    Node<Statement> root = new Node<Statement>(null);

    /* Creating the search tree from a list of statements */
    public IRSearchTree(List<Declaration> declarations, List<Statement> statements) {
        BuildSearchTree(declarations, statements);
    }

    private void BuildSearchTree(List<Declaration> declarations, List<Statement> statements) {
        // TODO: Implement solution to dimensions
        HashMap<String, Node<Statement>> fluidMap = new HashMap<>();

        /* it will always be overwritten for each statement */
        Identifier it = new Identifier("it",null);

        // No fluid can be linked to root

        // Add input as start for fluids
        for (Declaration declaration : declarations) {
            if (declaration instanceof Input) {
                Node input = new Node<Declaration>(((Input) declaration));
                input.addParent(root);
                fluidMap.put(declaration.getIdentifier(),input);
            }
        }

        /* Specifies at which depth the fluid is */
        for (Statement statement : statements) {
            // Remember at which depth a fluid is overwritten.
            // Check if assign is a fluid
            // else if assign is null.
            if (statement instanceof Mix) {
                Node mix = new Node<>((Mix) statement);
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
                Node incubate = new Node<>((Incubate) statement);
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
                // Since sense takes no time, one has to add physical constraints to it.
                Node sense = new Node<>((Sense) statement);
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
}
