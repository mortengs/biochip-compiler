package model;

import operations.*;
import components.*;

import java.util.*;

class IRComponentTree {

    private Node<Component> componentTree;
    private List<Node> urgentPath = new ArrayList<>();
    private int longestTime;

    IRComponentTree(Node<Statement> statementTree) {
        IRWalker irWalker = new IRWalker();
        Map<Node,Node> componentMap = resourceConstrainedListScheduling(statementTree,irWalker.getLongestPathTime(statementTree));
        componentTree = buildSchematicDesign(statementTree,componentMap);
    }

    // LS algorithm
    private Map<Node,Node> resourceConstrainedListScheduling(Node<Statement> rootStatementTree, int longestTime) {
        this.longestTime = longestTime;
        // List of all the used components
        List<Node> usedComponents = new ArrayList<>();
        // Queue of statements
        Queue<Node> statementQueue = new LinkedList<>();
        // Map of statements and components.
        Map<Node,Node> componentMap = new HashMap<>();
        // The most urgent path
        statementQueue.add(rootStatementTree);

        while(!statementQueue.isEmpty()) {
            Node node = statementQueue.remove();
            // Need to have a list for the children in order to set the urgency
            // Walk over every child.
            List<Node> children = new ArrayList<>();

            for (int i = 0; i < node.getChildren().size(); i++) {
                // Get the most urgent first
                if (((Node) node.getChildren().get(i)).getUrgency()) {
                    statementQueue.add((Node) node.getChildren().get(i));
                    urgentPath.add((Node) node.getChildren().get(i));
                } else {
                    children.add((Node) node.getChildren().get(i));
                }
            }
            statementQueue.addAll(children);

            if (node.getData() instanceof operations.Input) {
                assignInput(node,componentMap);
            } else if (node.getData() instanceof Incubate) {
                assignHeater(node,usedComponents,componentMap,longestTime);
            } else if (node.getData() instanceof Mix) {
                assignMixer(node,usedComponents,componentMap,longestTime);
            } else if (node.getData() instanceof Sense) {
                assignDetector(node,usedComponents,componentMap,longestTime);
            } else {
                // TODO: Implement filter and separator.
            }
        }

        return componentMap;
    }

    // This algorithm combines all the directions of the component tree.
    private Node<Component> buildSchematicDesign(Node<Statement> rootStatementTree, Map<Node,Node> componentMap) {
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

            if (node.getData() == null) {
                continue;
            }
            // For each node. Set the children of the component
            Node componentNode = componentMap.get(node);
            for (int i = 0; i < node.getChildren().size(); i++) {
                // If the node hasn't set this component to be it's child
                if (!componentNode.getChildren().contains(componentMap.get(node.getChildren().get(i)))) {
                    componentNode.addChild(componentMap.get(node.getChildren().get(i)));
                }
            }

            // If the node is a leaf then direct to output
            if (node.isLeaf() && !componentMap.get(node).getChildren().contains(outputNode)) {
                componentMap.get(node).addChild(outputNode);
            }

            // If the node is an input then direct the root to this
            if (node.getData() instanceof operations.Input) {
                root.addChild(componentMap.get(node));
            }
        }
        return root;
    }

    Node<Component> getComponentTree() {
        return componentTree;
    }

    // Used both as getting index and as contains
    private static <E> List<Integer> getIndex(List<Node> list, Class clazz) {
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
        } else {
            boolean isGoingOverUgerncy = false;
            // Loop over all the components
            for (Integer index : indices) {
                // Find a component that isn't going over the urgency limit
                if (((Heater) usedComponents.get(index).getData()).getTime()+((Incubate) node.getData()).getTime() <= time) {
                    isGoingOverUgerncy = false;
                    heater = usedComponents.get(index);
                    ((Heater) usedComponents.get(index).getData()).addTime(((Incubate) node.getData()).getTime());
                    componentMap.put(node,heater);
                    // If all the components are going over the urgency limit, create a new component
                } else {
                    isGoingOverUgerncy = true;
                }
            }

            // Going over the urgency limit with the shortest time possible
            if (isGoingOverUgerncy) {
                if (indices.size() < Constraints.getNumberOfHeaters()) {
                    if (((Incubate) node.getData()).getTime() != null) {
                        heater.getData().addTime(((Incubate) node.getData()).getTime());
                    } else {
                        heater.getData().addTime(DefaultValues.getHeaterDefaultTime());
                    }
                    usedComponents.add(heater);
                    componentMap.put(node, heater);
                    // All available components have been allocated and the urgency limit has been reached
                } else {
                    int shortestTime = 0;
                    // Find the shortest time;
                    for (Integer index : indices) {
                        if (((Heater) usedComponents.get(index).getData()).getTime() > shortestTime) {
                            heater = usedComponents.get(index);
                            shortestTime = ((Heater) usedComponents.get(index).getData()).getTime();
                        }
                    }
                    // The shortest time heater will be added.
                    componentMap.put(node, heater);
                }
            }
        }
    }

    private void assignMixer(Node node, List<Node> usedComponents, Map<Node,Node> componentMap, int time) {
        Node<Mixer> mixer = new Node(new Mixer());
        List<Integer> indices = getIndex(usedComponents, components.Mixer.class);

        // Initialize the first component. After this we know that there always is a component
        if (indices.isEmpty()) {
            if (((Mix) node.getData()).getTime() != null) {
                mixer.getData().addTime(((Mix) node.getData()).getTime());
            } else {
                mixer.getData().addTime(DefaultValues.getMixerDefaultTime());
            }

            usedComponents.add(mixer);
            componentMap.put(node,mixer);
        } else {
            boolean isGoingOverUgerncy = false;
            // Loop over all the components
            for (Integer index : indices) {
                // Find a component that isn't going over the urgency limit
                if (((Mixer) usedComponents.get(index).getData()).getTime()+((Mix) node.getData()).getTime() <= time) {
                    isGoingOverUgerncy = false;
                    mixer = usedComponents.get(index);
                    ((Mixer) usedComponents.get(index).getData()).addTime(((Mix) node.getData()).getTime());
                    componentMap.put(node,mixer);
                    // If all the components are going over the urgency limit, create a new component
                } else {
                    isGoingOverUgerncy = true;
                }
            }

            // Going over the urgency limit with the shortest time possible
            if (isGoingOverUgerncy) {
                if (indices.size() < Constraints.getNumberOfMixers()) {
                    if (((Mix) node.getData()).getTime() != null) {
                        mixer.getData().addTime(((Mix) node.getData()).getTime());
                    } else {
                        mixer.getData().addTime(DefaultValues.getMixerDefaultTime());
                    }
                    usedComponents.add(mixer);
                    componentMap.put(node, mixer);
                    // All available components have been allocated and the urgency limit has been reached
                } else {
                    int shortestTime = 0;
                    // Find the shortest time;
                    for (Integer index : indices) {
                        if (((Mixer) usedComponents.get(index).getData()).getTime() > shortestTime) {
                            mixer = usedComponents.get(index);
                            shortestTime = ((Mixer) usedComponents.get(index).getData()).getTime();
                        }
                    }
                    // The shortest time heater will be added.
                    componentMap.put(node, mixer);
                }
            }
        }
    }

    private void assignInput(Node node, Map<Node,Node> componentMap) {
        components.Input input = new components.Input(((operations.Input) node.getData()).getInput_integer());
        input.addTime(DefaultValues.getInputDefaultTime());
        componentMap.put(node,new Node(input));
    }

    private void assignDetector(Node node, List<Node> usedComponents, Map<Node,Node> componentMap, int time) {
        Node<Detector> detector = new Node(new Detector());
        List<Integer> indices = getIndex(usedComponents, components.Detector.class);

        // Initialize the first component. After this we know that there always is a component
        if (indices.isEmpty()) {
            detector.getData().addTime(DefaultValues.getDetectorDefaultTime());
            usedComponents.add(detector);
            componentMap.put(node,detector);
        } else {
            boolean isGoingOverUgerncy = false;
            // Loop over all the components
            for (Integer index : indices) {
                // Find a component that isn't going over the urgency limit
                if (((Detector) usedComponents.get(index).getData()).getTime() <= time) {
                    isGoingOverUgerncy = false;
                    detector = usedComponents.get(index);
                    ((Detector) usedComponents.get(index).getData()).addTime(DefaultValues.getDetectorDefaultTime());
                    componentMap.put(node,detector);
                    // If all the components are going over the urgency limit, create a new component
                } else {
                    isGoingOverUgerncy = true;
                }
            }

            // Going over the urgency limit with the shortest time possible
            if (isGoingOverUgerncy) {
                if (indices.size() < Constraints.getNumberOfDetectors()) {
                    detector.getData().addTime(DefaultValues.getDetectorDefaultTime());
                    usedComponents.add(detector);
                    componentMap.put(node, detector);
                    // All available components have been allocated and the urgency limit has been reached
                } else {
                    int shortestTime = 0;
                    // Find the shortest time;
                    for (Integer index : indices) {
                        if (((Detector) usedComponents.get(index).getData()).getTime() > shortestTime) {
                            detector = usedComponents.get(index);
                            shortestTime = ((Detector) usedComponents.get(index).getData()).getTime();
                        }
                    }
                    // The shortest time heater will be added.
                    componentMap.put(node, detector);
                }
            }
        }
    }

    public int getLongestTime() {
        return longestTime;
    }
}
