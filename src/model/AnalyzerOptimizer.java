package model;

import ast.*;
import ast.Input;
import components.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 29/05/2017.
 */
public class AnalyzerOptimizer {

    private List<Node<Statement>> visited = new ArrayList<>();
    private List<Node<Component>> usedComponents = new ArrayList<>();
    private Node<Component> rootComponentTree = new Node<Component>(null);
    private Node<Component> parentComponentTree;

    // TODO: LS algorithm
    public Node<Component> resourceConstrainedListScheduling(Node<Statement> rootStatementTree, Constraints constraints) {
        IRWalker walker = new IRWalker();
        IntPair time = new IntPair(0,0);
        parentComponentTree = rootComponentTree;
        List<PathTime> pathTimes = walker.getAllPaths(rootStatementTree, constraints);
        // Calculate the longest path
        int longestTime = walker.getLongestPathTime(rootStatementTree, constraints);
        for (PathTime pathTime : pathTimes) {
            for (Node nodeStatementTree : pathTime.getPath()) {
                System.out.println("Operation: " + nodeStatementTree.getData() + ", number of components: " + usedComponents.size());
                if (!visited.contains(nodeStatementTree)) {
                    // Input just needs to be initialized to the root, which is easy
                    if (nodeStatementTree.getData() instanceof Input) {
                        time.startTime += constraints.inputDefaultTime;
                        // Initialize component
                        Node<Component> input = new Node<Component>(new components.Input(((Input) nodeStatementTree.getData()).getInput_integer()));
                        System.out.println(input);
                        // Parent is always root here.
                        input.addParent(parentComponentTree);
                    } else if (nodeStatementTree.getData() instanceof Mix) {
                        Node<Component>  mixer;
                        // Add the time it takes to do this operation
                        time.endTime += ((Mix) nodeStatementTree.getData()).getTime() + constraints.mixerDefaultTime;
                        // Index of all the components that can be used for this operation
                        List<Integer> indices = getIndex(usedComponents, components.Mixer.class);
                        // If there are no components
                        if (indices.isEmpty()) {
                            // Initialize component
                            mixer = new Node<Component>(new Mixer());
                            mixer.getData().addTime(time);
                            System.out.println(mixer.getData());
                            usedComponents.add(mixer);
                            // Add this component to the tree
                            mixer.addParent(parentComponentTree);
                        } else {
                            // Used to break out of the search for a component
                            // Save the next closest time available for if no time slot was found at this time
                            boolean hasFoundAvailableComponent = false;
                            for (Integer integer : indices) {
                                mixer = usedComponents.get(integer);
                                for (IntPair timePair : mixer.getData().getTimeSchedule()) {
                                    if (hasAvailableTimeSlot(timePair,time)) {
                                        hasFoundAvailableComponent = true;
                                        mixer.getData().addTime(time);
                                        mixer.addParent(parentComponentTree);
                                        break;
                                    }
                                }
                                if (hasFoundAvailableComponent) {
                                    break;
                                }
                            }
                            if (!hasFoundAvailableComponent) {
                                // Get the component closest to this
                            }
                            // run over all the components in the list. Check for the right component
                            // If the component has a time slot available, then use the component
                            // If there are no more available components, then make a new one.
                            // If this is not possible give the user an warning and assign a new component.
                        }
                    } else if (nodeStatementTree.getData() instanceof Incubate) {
                        time.endTime += ((Incubate) nodeStatementTree.getData()).getTime() + constraints.heaterDefaultTime;
                        List<Integer> indices = getIndex(usedComponents, components.Heater.class);
                        if (indices.isEmpty()) {
                            // Initialize component
                            Node<Component> heater = new Node<Component>(new Heater());
                            heater.getData().addTime(time);
                            System.out.println(heater);
                            usedComponents.add(heater);
                        } else {
                        }
                    } else if (nodeStatementTree.getData() instanceof Sense) {
                        time.startTime += constraints.detectorDefaultTime;
                        List<Integer> indices = getIndex(usedComponents, components.Detector.class);
                        if (indices.isEmpty()) {
                            // Initialize component
                            Node<Component> detector = new Node<Component>(new Detector());
                            detector.getData().addTime(time);
                            System.out.println(detector);
                            usedComponents.add(detector);
                        } else {
                        }
                    } else {
                        System.out.println("Why am I here?");
                    }
                    visited.add(nodeStatementTree);
                    System.out.println("Start time: "+time.startTime+", End time: "+time.endTime);
                }
                time.startTime += (time.endTime-time.startTime);
            }
            // The path has been traversed. Now reset time, since we're back at root
            time.startTime = 0;
            time.endTime = 0;
            parentComponentTree = rootComponentTree;
        }
        assignComponents();
        return rootComponentTree;
    }

    // TODO: Solution to mixer ratio problem
    public void ratioApproximation() {

    }

    // Used both as getting index and as contains
    public static <E> List<Integer> getIndex(List<E> list, Class clazz) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (clazz.isInstance(list.get(i))) {
                indices.add(i);
            }
        }
        // If the list doesn't contain this value, then return -1.
        // This way we don't need a function to check if the list contains this type.
        return indices;
    }

    private void assignComponents() {

    }

    private void assignLongestPath() {

    }

    // Check whether the component has a free slot
    private boolean hasAvailableTimeSlot(IntPair component, IntPair newStatement) {
        if (component.startTime >= newStatement.endTime || component.endTime <= newStatement.startTime) {
            return true;
        } else {
            return false;
        }
    }

    private int getClosestTimeSlot() {
        return 0;
    }
}
