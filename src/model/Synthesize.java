package model;

import components.*;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Created by Jesper on 22/05/2017.
 */
public class Synthesize {
    private StringBuilder components = new StringBuilder().insert(0,"COMPONENT LIST:\n");
    private StringBuilder connections = new StringBuilder().insert(0,"CONNECTION LIST:\n");

    private Map<Component,String> componentsNameMap;
    private List<Node> visitedComponents = new LinkedList<>();

    private boolean success = false;
    private int longestTime;

    private int numberOfDetectors = 0;
    private int numberOfFilters = 0;
    private int numberOfHeaters = 0;
    private int numberOfInputs = 0;
    private int numberOfMixers = 0;
    private int numberOfOutputs = 0;
    private int numberOfSeparators = 0;

    public void synthesize(String aquaName, Node<Component> componentTree, int longestTime) {
        this.longestTime = longestTime;

        componentsNameMap = new HashMap<>();

        Queue<Node> componentQueue = new LinkedList<>();

        componentQueue.add(componentTree);

        // Convert the tree into a list of all components
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

        // First write all the components and map them a name
        for (Node node : visitedComponents) {
            createComponents(node);
        }

        // Then write the interconnection of each component
        for (Node node : visitedComponents) {
            createConnection(node);
        }

        components.append("END LIST;\n");
        connections.append("END LIST;");

        try {
            PrintWriter out = new PrintWriter(aquaName+".mhdl");
            out.print(components);
            out.print(connections);
            out.close();
        } catch (Exception e) {
            return;
        }

        printInformation();

        success = true;
    }

    private void createComponents(Node node) {
        String name = "no name";
        components.append("\t");
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
            connections.append("\t");
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

    private void printInformation() {
        System.out.println(components);
        System.out.println(connections+"\n");
        System.out.println("Number of components assigned: "+(numberOfMixers+numberOfHeaters+numberOfFilters+numberOfDetectors+numberOfSeparators));
        System.out.println("Inputs:\t\t"+numberOfInputs);
        System.out.println("Mixers:\t\t"+numberOfMixers);
        System.out.println("Heaters:\t"+numberOfHeaters);
        System.out.println("Filters:\t"+numberOfFilters);
        System.out.println("Detectors:\t"+numberOfDetectors);
        System.out.println("Outputs:\t"+numberOfOutputs);
        int time = 0;
        for (Node node : visitedComponents) {
            if (((Component) node.getData()).getTime() > time) {
                time = ((Component) node.getData()).getTime();
            }
        }

        System.out.println("Longest path has the time: "+longestTime);
        // Print out the time it takes to run all the operations (the path of operations with the longest time)
        // System.out.println("Longest running time: "+time);
    }

    public boolean getSuccess() {
        return success;
    }
}
