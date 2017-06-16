package model;

import components.*;

import java.util.*;


/**
 * Created by Jesper on 22/05/2017.
 */
public class Synthesize {
    StringBuilder components = new StringBuilder().insert(0,"COMPONENT LIST:\n");
    StringBuilder connections = new StringBuilder().insert(0,"CONNECTION LIST:\n");

    private Map<Component,String> componentsNameMap;
    List<Node> visitedComponents = new LinkedList<>();

    private int numberOfDetectors = 0;
    private int numberOfFilters = 0;
    private int numberOfHeaters = 0;
    private int numberOfInputs = 0;
    private int numberOfMixers = 0;
    private int numberOfOutputs = 0;
    private int numberOfSeparators = 0;

    public void synthesize(Node<Component> componentTree) {

        componentsNameMap = new HashMap<>();

        Queue<Node> componentQueue = new LinkedList<>();

        componentQueue.add(componentTree);


        while(!componentQueue.isEmpty()) {
            Node node = componentQueue.remove();
            for (int i = 0; i < node.getChildren().size(); i++) {
                // Check if it's already in the queue
                if (!visitedComponents.contains(((Node) node.getChildren().get(i)))) {
                    visitedComponents.add((Node) node.getChildren().get(i));
                    componentQueue.add((Node) node.getChildren().get(i));
                }
            }
        }

        for (Node node : visitedComponents) {
            createComponents(node);
        }
        for (Node node : visitedComponents) {
            createConnection(node);
        }

        components.append("END LIST;");
        connections.append("END LIST;");

        System.out.println(components);
        System.out.println(connections);
    }

    private void createComponents(Node node) {
        String name = "no name";
        if (node.getData() instanceof Detector) {
            name = "d"+numberOfDetectors;
            components.append(name);
            components.append(" OF Detector");
            numberOfDetectors++;
        } else if (node.getData() instanceof Heater) {
            name = "h"+numberOfHeaters;
            components.append(name);
            components.append(" OF Heater");
            numberOfHeaters++;
        } else if (node.getData() instanceof Input) {
            name = "i"+numberOfInputs;
            components.append(name);
            components.append(" OF Input");
            numberOfInputs++;
        } else if (node.getData() instanceof Mixer) {
            name = "m"+numberOfMixers;
            components.append(name);
            components.append(" OF Mixer");
            numberOfMixers++;
        } else if (node.getData() instanceof Output) {
            name = "o"+numberOfOutputs;
            components.append(name);
            components.append(" OF Output");
            numberOfOutputs++;
        // The last two aren't fully implemented
        } else if (node.getData() instanceof Seperator) {
            name = "s"+numberOfSeparators;
            components.append(name);
            components.append(" OF Separator");
            numberOfSeparators++;
        } else if (node.getData() instanceof Filter) {
            name = "f"+numberOfFilters;
            components.append(name);
            components.append(" OF Filter");
            numberOfFilters++;
        } else {
            System.out.println("Something went wrong");
        }
        componentsNameMap.put((Component) node.getData(),name);
        components.append(";\n");
    }

    private void createConnection(Node node) {
        StringBuilder connectedTo = new StringBuilder();
        if (!node.getChildren().isEmpty()) {
            connections.append(componentsNameMap.get(node.getData()));
            connections.append(" CONNECTS TO ");
            for (int i = 0; i < node.getChildren().size(); i++) {
                connections.append(componentsNameMap.get(((Node) node.getChildren().get(i)).getData()));
                if (i < node.getChildren().size()-1) {
                    connections.append(",");
                }
            }
            connections.append(connectedTo);
            connections.append(";\n");
        }
    }
}
