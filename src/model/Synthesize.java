package model;

import components.*;
import java.io.PrintWriter;
import java.util.*;

class Synthesize {
    private StringBuilder entities = new StringBuilder().insert(0,"ENTITY LIST:\n");
    private StringBuilder components = new StringBuilder().insert(0,"COMPONENT LIST:\n");
    private StringBuilder connections = new StringBuilder().insert(0,"CONNECTION LIST:\n");

    private Map<Component,String> componentsNameMap;
    private List<Node> visitedComponents = new LinkedList<>();
    private int longestPath = 0;

    private int numberOfDetectors = 0;
    private int numberOfFilters = 0;
    private int numberOfHeaters = 0;
    private int numberOfInputs = 0;
    private int numberOfMixers = 0;
    private int numberOfOutputs = 0;
    private int numberOfSeparators = 0;

    Map<String, List<String>> nameMap = new HashMap<>();
    Map<String, String> typeMap = new HashMap<>();

    public void synthesize(String aquaName, List<Node<Component>> componentTrees, List<Integer> longestPaths) {
        for (Integer longestPath : longestPaths) {
            if (longestPath > this.longestPath) {
                this.longestPath = longestPath;
            }
        }

        for (Node<Component> componentTree : componentTrees) {
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
            numberOfDetectors = 0;
            numberOfFilters = 0;
            numberOfHeaters = 0;
            numberOfInputs = 0;
            numberOfMixers = 0;
            numberOfOutputs = 0;
            numberOfSeparators = 0;
            for (Node node : visitedComponents) {
                createComponents(node);
            }

            // Then write the interconnection of each component
            for (Node node : visitedComponents) {
                createConnection(node);
            }
            visitedComponents.clear();
        }

        writeEntityList();
        writeComponentList();
        writeConnectionList();

        entities.append("END LIST;\n");
        components.append("END LIST;\n");
        connections.append("END LIST;");

        try {
            PrintWriter out = new PrintWriter(aquaName+".mhdl");
            out.print(entities);
            out.print(components);
            out.print(connections);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        printInformation();
    }

    private void writeEntityList() {
        entities.append("\t");
        entities.append("Mixer OF TYPE ");
        entities.append(Configuration.getMixerType());
        entities.append(";\n");
    }

    private void createComponents(Node node) {
        String name = "";
        String type = "";
        if (node.getData() instanceof Detector) {
            name = "d"+numberOfDetectors;
            type = "Detector";
            numberOfDetectors++;
        } else if (node.getData() instanceof Heater) {
            name = "h"+numberOfHeaters;
            type = "Heater";
            numberOfHeaters++;
        } else if (node.getData() instanceof Input) {
            name = "i"+numberOfInputs;
            type = "Input";
            numberOfInputs++;
        } else if (node.getData() instanceof Mixer) {
            name = "m"+numberOfMixers;
            type = "Mixer";
            numberOfMixers++;
        } else if (node.getData() instanceof Output) {
            name = "o"+numberOfOutputs;
            type = "Output";
            numberOfOutputs++;
        // The last two aren't fully implemented
        } else if (node.getData() instanceof Seperator) {
            name = "s"+numberOfSeparators;
            type = "Separator";
            numberOfSeparators++;
        } else if (node.getData() instanceof Filter) {
            name = "f"+numberOfFilters;
            type = "Filter";
            numberOfFilters++;
        } else {
            System.out.println("Something went wrong");
        }
        if (!componentsNameMap.containsKey((Component) node.getData())) {
            typeMap.put(name,type);
            nameMap.put(name,new ArrayList<>());
        }
        componentsNameMap.put((Component) node.getData(),(name));
    }

    private void createConnection(Node node) {
        if (!node.getChildren().isEmpty()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                if (!nameMap.get(componentsNameMap.get(node.getData())).contains(componentsNameMap.get(((Node) node.getChildren().get(i)).getData()))) {
                    nameMap.get(componentsNameMap.get(node.getData())).add(componentsNameMap.get(((Node) node.getChildren().get(i)).getData()));
                }
            }
        }
    }

    private void writeComponentList() {
        for (String component : componentsNameMap.values()) {
            components.append("\t");
            components.append(component);
            components.append(" OF ");
            components.append(typeMap.get(component));
            components.append(";\n");
        }
    }

    private void writeConnectionList() {
        for (String component : new ArrayList<String>(nameMap.keySet())) {
            if (nameMap.get(component).isEmpty()) {
                continue;
            }
            connections.append("\t");
            connections.append(component);
            connections.append(" CONNECTS TO ");
            for (int i = 0; i<nameMap.get(component).size(); i++) {
                connections.append(nameMap.get(component).get(i));
                if (i < (nameMap.get(component).size()-1)) {
                    connections.append(",");
                }
            }
            connections.append(";\n");
        }
    }

    private void printInformation() {
        System.out.println("\n"+entities);
        System.out.println(components+"\n");
        System.out.println(connections+"\n");
        System.out.println("Number of components assigned: "+(numberOfMixers+numberOfHeaters+numberOfFilters+numberOfDetectors+numberOfSeparators));
        System.out.println("Inputs:\t\t"+numberOfInputs);
        System.out.println("Mixers:\t\t"+numberOfMixers);
        System.out.println("Heaters:\t"+numberOfHeaters);
        System.out.println("Filters:\t"+numberOfFilters);
        System.out.println("Detectors:\t"+numberOfDetectors);
        System.out.println("Outputs:\t"+numberOfOutputs);


        // Print out the time it takes to run all the operations (the path of operations with the longest time)
        System.out.println("Longest running time: "+longestPath);
    }
}
