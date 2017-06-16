package model;

import ast.*;
import components.*;

import java.util.*;

/**
 * Created by Jesper on 29/05/2017.
 */
public class AnalyzerOptimizer {

//    private List<Node<Statement>> visited = new ArrayList<>();
    private Node<Component> componentTree;

    public AnalyzerOptimizer(Node<Statement> statementTree) {
        IRWalker irWalker = new IRWalker();
        Map<Node,Node> componentMap = resourceConstrainedListScheduling(statementTree,irWalker.getLongestPathTime(statementTree));
        for (Node node : componentMap.values()) {
            System.out.println(node.getData());
        }
        //componentTree = buildSchematicDesign(statementTree,componentMap);
    }

    // TODO: Solution to mixer ratio problem
    public void ratioApproximation() {

    }

    // TODO: LS algorithm
    public Map<Node,Node> resourceConstrainedListScheduling(Node<Statement> rootStatementTree, int longestTime) {
//        IRWalker walker = new IRWalker();
//        IntPair time = new IntPair(0,0);
//        parentComponentTree = rootComponentTree;
//        List<PathTime> pathTimes = walker.getAllPaths(rootStatementTree, constraints);
//        // Calculate the longest path
//        int longestTime = walker.getLongestPathTime(rootStatementTree, constraints);
//        for (PathTime pathTime : pathTimes) {
//            for (Node nodeStatementTree : pathTime.getPath()) {
//                System.out.println("Operation: " + nodeStatementTree.getData() + ", number of components: " + usedComponents.size());
//                if (!visited.contains(nodeStatementTree)) {
//                    // Input just needs to be initialized to the root, which is easy
//                    if (nodeStatementTree.getData() instanceof Input) {
//                        time.startTime += constraints.inputDefaultTime;
//                        // Initialize component
//                        Node<Component> input = new Node<Component>(new components.Input(((Input) nodeStatementTree.getData()).getInput_integer()));
//                        System.out.println(input);
//                        // Parent is always root here.
//                        input.addParent(parentComponentTree);
//                    } else if (nodeStatementTree.getData() instanceof Mix) {
//                        Node<Component>  mixer;
//                        // Add the time it takes to do this operation
//                        time.endTime += ((Mix) nodeStatementTree.getData()).getTime() + constraints.mixerDefaultTime;
//                        // Index of all the components that can be used for this operation
//                        List<Integer> indices = getIndex(usedComponents, components.Mixer.class);
//                        // If there are no components
//                        if (indices.isEmpty()) {
//                            // Initialize component
//                            mixer = new Node<Component>(new Mixer());
//                            mixer.getData().addTime(time);
//                            System.out.println(mixer.getData());
//                            usedComponents.add(mixer);
//                            // Add this component to the tree
//                            // TODO: Move this into a new method where it also checks if this is already its parent
//                            mixer.addParent(parentComponentTree);
//                        } else {
//                            // Used to break out of the search for a component
//                            // Save the next closest time available for if no time slot was found at this time
//                            boolean hasFoundAvailableComponent = false;
//                            for (Integer integer : indices) {
//                                mixer = usedComponents.get(integer);
//                                for (IntPair timePair : mixer.getData().getTimeSchedule()) {
//                                    if (hasAvailableTimeSlot(timePair,time)) {
//                                        hasFoundAvailableComponent = true;
//                                        mixer.getData().addTime(time);
//                                        mixer.addParent(parentComponentTree);
//                                        break;
//                                    }
//                                }
//                                if (hasFoundAvailableComponent) {
//                                    break;
//                                }
//                            }
//                            if (!hasFoundAvailableComponent) {
//                                // Get the component closest to this
//                            }
//                            // run over all the components in the list. Check for the right component
//                            // If the component has a time slot available, then use the component
//                            // If there are no more available components, then make a new one.
//                            // If this is not possible give the user an warning and assign a new component.
//                        }
//                    } else if (nodeStatementTree.getData() instanceof Incubate) {
//                        time.endTime += ((Incubate) nodeStatementTree.getData()).getTime() + constraints.heaterDefaultTime;
//                        List<Integer> indices = getIndex(usedComponents, components.Heater.class);
//                        if (indices.isEmpty()) {
//                            // Initialize component
//                            Node<Component> heater = new Node<Component>(new Heater());
//                            heater.getData().addTime(time);
//                            System.out.println(heater);
//                            usedComponents.add(heater);
//                        } else {
//                        }
//                    } else if (nodeStatementTree.getData() instanceof Sense) {
//                        time.startTime += constraints.detectorDefaultTime;
//                        List<Integer> indices = getIndex(usedComponents, components.Detector.class);
//                        if (indices.isEmpty()) {
//                            // Initialize component
//                            Node<Component> detector = new Node<Component>(new Detector());
//                            detector.getData().addTime(time);
//                            System.out.println(detector);
//                            usedComponents.add(detector);
//                        } else {
//                        }
//                    } else {
//                        System.out.println("Why am I here?");
//                    }
//                    visited.add(nodeStatementTree);
//                    System.out.println("Start time: "+time.startTime+", End time: "+time.endTime);
//                }
//                time.startTime += (time.endTime-time.startTime);
//            }
//            // The path has been traversed. Now reset time, since we're back at root
//            time.startTime = 0;
//            time.endTime = 0;
//            parentComponentTree = rootComponentTree;
//        }
//        assignComponents();

        // List of all the used components
        List<Node> usedComponents = new ArrayList<>();
        // Queue of statements
        Queue<Node> statementQueue = new LinkedList<>();
        // Map of statements and components.
        Map<Node,Node> componentMap = new HashMap<>();

        statementQueue.add(rootStatementTree);

        while(!statementQueue.isEmpty()) {
            Node node = statementQueue.remove();

            // Need to have a list for the children in order to set the urgency
            List<Node> children = new LinkedList<>();
            // Walk over every child.
            for (int i = 0; i < node.getChildren().size(); i++) {
                if (!statementQueue.contains(node.getChildren().get(i)) && !children.contains(node.getChildren().get(i))) {
                    // Check for urgency
                    if (((Node) node.getChildren().get(i)).getUrgency()) {
                        children.add(0,(Node) node.getChildren().get(i));
                    } else {
                        children.add((Node) node.getChildren().get(i));
                    }
                }
            }

            // Add the children to the queue
            statementQueue.addAll(children);

            if (node.getData() instanceof ast.Input) {
                components.Input input = new components.Input(((ast.Input) node.getData()).getInput_integer());
                input.addTime(DefaultValues.getInputDefaultTime());
                componentMap.put(node,new Node(input));
                System.out.println("This input: " + node.getData());
            } else if (node.getData() instanceof Incubate) {
                assignHeater(node,usedComponents,componentMap,longestTime);
            } else if (node.getData() instanceof Mix) {
                Mixer mixer = new Mixer();

                if (((Mix) node.getData()).getTime() != null) {
                    mixer.addTime(((Mix) node.getData()).getTime());
                } else {
                    mixer.addTime(DefaultValues.getMixerDefaultTime());
                }

                componentMap.put(node,new Node(mixer));
                System.out.println("This mix: " + node.getData());
            } else if (node.getData() instanceof Sense) {
                Detector detector = new Detector();
                detector.addTime(DefaultValues.getDetectorDefaultTime());
                componentMap.put(node,new Node(detector));
                System.out.println("This sense: " + node.getData());
            } else {
                // Implement filter.
            }
        }

        return componentMap;
    }

    // This algorithm combines all the directions for the component tree.
    public Node<Component> buildSchematicDesign(Node<Statement> rootStatementTree, Map<Node,Node> componentMap) {
        Node<Output> outputNode = new Node(new Output());
        Node<Component> root = new Node<Component>(null);
        Queue<Node> statementQueue = new LinkedList<>();
        statementQueue.add(rootStatementTree);
        while(!statementQueue.isEmpty()) {
            Node node = statementQueue.remove();
            // Loop over every child
            for (int i = 0; i < node.getChildren().size(); i++) {
                // Check if it's already in the queue
                if (!statementQueue.contains(node.getChildren().get(i))) {
                    // Add this child to the queue
                    statementQueue.add((Node) node.getChildren().get(i));
                }
            }
            // For each node. Set the parents of the component
            Node<Component> componentNode = componentMap.get(node);
            for (int i = 0; i < node.getChildren().size(); i++) {
                // If the node hasn't set this component to be it's child
                if (!componentNode.getChildren().contains(componentMap.get(node.getChildren().get(i)))) {
                    componentNode.addChild(componentMap.get(node.getChildren().get(i)));
                }
            }

            // If the node is a leaf then direct to output
            if (node.isLeaf()) {
                componentMap.get(node).addChild(outputNode);
            }

            // If the node is an input then direct the root to this
            if (node.getData() instanceof ast.Input) {
                root.addChild(componentMap.get(node.getData()));
            }
        }
        return null;
    }

    public Node<Component> getComponentTree() {
        return componentTree;
    }

    // Used both as getting index and as contains
    public static <E> List<Integer> getIndex(List<Node> list, Class clazz) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (clazz.isInstance(list.get(i).getData())) {
                indices.add(i);
            }
        }
        return indices;
    }

    private void assignComponents() {

    }

    private void assignHeater(Node node, List<Node> usedComponents, Map<Node,Node> componentMap, int time) {
        Node<Heater> heater = new Node(new Heater());
        List<Integer> indices = getIndex(usedComponents, components.Heater.class);

        // Initialize the first component. After this we know that there always is a component
        if (indices.isEmpty()) {
            if (((Incubate) node.getData()).getTime() != null) {
                heater.getData().addTime(((Incubate) node.getData()).getTime());
            } else {
                heater.getData().addTime(DefaultValues.getHeaterDefaultTime());
            }

            usedComponents.add(heater);
            componentMap.put(node,heater);
            System.out.println("Adding heater: " + heater.getData());
        } else {
            boolean isGoingOverUgerncy = false;
            // Loop over all the components
            for (Integer index : indices) {
                // Find a component that isn't going over the urgency limit
                if (((Heater) usedComponents.get(index).getData()).getTime()+((Incubate) node.getData()).getTime() <= time) {
                    System.out.println("Found heater: " + usedComponents.get(index).getData() + ", with time: "+(((Incubate) node.getData()).getTime()+((Heater) usedComponents.get(index).getData()).getTime()));
                    heater = usedComponents.get(index);
                    ((Heater) usedComponents.get(index).getData()).addTime(((Incubate) node.getData()).getTime());
                    componentMap.put(node,heater);
                    // If all the components are going over the urgency limit, create a new component
                } else if (indices.size() < Constraints.getNumberOfHeaters()) {
                    if (((Incubate) node.getData()).getTime() != null) {
                        heater.getData().addTime(((Incubate) node.getData()).getTime());
                    } else {
                        heater.getData().addTime(DefaultValues.getHeaterDefaultTime());
                    }
                    System.out.println("Time is: "+(((Incubate) node.getData()).getTime()+((Heater) usedComponents.get(index).getData()).getTime())+", adding new heater: "+heater.getData());
                    usedComponents.add(heater);
                    componentMap.put(node,heater);
                    // All available components have been allocated and the urgency limit has been reached
                } else {
                    isGoingOverUgerncy = true;
                }
            }
            // Going over the urgency limit with the shortest time possible
            if (isGoingOverUgerncy) {
                int shortestTime = 0;
                // Find the shortest time;
                for (Integer index : indices) {
                    if (((Heater) usedComponents.get(index).getData()).getTime() > shortestTime) {
                        heater = usedComponents.get(index);
                        shortestTime = ((Heater) usedComponents.get(index).getData()).getTime();
                    }
                }
                System.out.println("Going over time limit: "+heater.getData()+", with time: "+ heater.getData().getTime());
                // The shortest time heater will be added.
                componentMap.put(node,heater);
            }
        }

    }

    private void AssignMixer() {

    }

    private void assignLongestPath() {

    }
}
