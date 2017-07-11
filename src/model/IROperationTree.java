package model;

import operations.*;
import java.util.*;

class IROperationTree {

    /* The start is a null value node */
    private Node<Statement> operationTree = new Node<>(null);
    // No fluid can be linked to operationTree
    /* Specifies at which depth the fluid is */
    private HashMap<String, Node<Statement>> fluidMap = new HashMap<>();

    /* Creating the search tree from a list of statements */
    IROperationTree(List<Declaration> declarations, List<Statement> statements) {
        buildOperationTree(declarations, statements);
    }

    Node<Statement> getOperationTree() {
        return operationTree;
    }

    private void buildOperationTree(List<Declaration> declarations, List<Statement> statements) {
        /* it will always be overwritten for each statement */
        Identifier it = new Identifier("it",null);

        // Add input as start for fluids
        for (Declaration declaration : declarations) {
            if (declaration instanceof Input) {
                Node input = new Node<Declaration>(((Input) declaration));
                input.addParent(operationTree);
                fluidMap.put(declaration.getIdentifier(),input);
            }
        }

        for (Statement statement : statements) {
            // Remember at which depth a fluid is overwritten.
            // Check if assign is a fluid
            // else if assign is null.
            if (statement instanceof Mix) {

                System.out.print(((Mix) statement).getAssign().getIdentifier()+" = MIX ");
                for (int i = 0; i < ((Mix) statement).getIdentifiers().length; i++) {
                    System.out.print(((Mix) statement).getIdentifiers()[i].getIdentifier());
                    if ((i+1)<((Mix) statement).getIdentifiers().length) {
                        System.out.print(" AND ");
                    }
                }
                System.out.print(" ");
                for (int i = 0; i < ((Mix) statement).getRatio().length; i++) {
                    System.out.print(((Mix) statement).getRatio()[i]);
                    if ((i+1)<((Mix) statement).getRatio().length) {
                        System.out.print(" : ");
                    }
                }
                System.out.println(" FOR " +((Mix) statement).getTime());

                Node<Statement> mix;
                if (Configuration.getMixerType() == MixerType.ONE_TO_ONE) {
                    int[] approximatedRatios = ratioApproximation((Mix) statement);
                    mix = minMix(((Mix) statement), approximatedRatios);
                } else {
                    mix = new Node<>(new Mix(((Mix) statement).getAssign(),((Mix) statement).getIdentifiers(),((Mix) statement).getRatio(),((Mix) statement).getTime()));
                    for (Identifier identifier: ((Mix) mix.getData()).getIdentifiers()) {
                        if (identifier.getIdentifier().equals(it.getIdentifier())) {
                            mix.addParent(fluidMap.get(it.getIdentifier()));
                        } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                            mix.addParent(fluidMap.get(identifier.getIdentifier()));
                        } else {
                            mix.addParent(operationTree);
                        }
                    }
                }
                Identifier assignedValue = ((Mix) statement).getAssign();
                fluidMap.put(assignedValue.getIdentifier(),mix);
                fluidMap.put(it.getIdentifier(),mix);
            } else if (statement instanceof Incubate) {

                System.out.println(((Incubate) statement).getAssign().getIdentifier() +" = INCUBATE "+((Incubate) statement).getIdentifier().getIdentifier()+" AT " +((Incubate) statement).getTemperature() + " FOR "+((Incubate) statement).getTime());

                Node incubate = new Node<>((Incubate) statement);
                Identifier assignedValue = ((Incubate) statement).getAssign();
                Identifier identifier = ((Incubate) statement).getIdentifier();
                if (identifier.getIdentifier().equals(it.getIdentifier())) {
                    incubate.addParent(fluidMap.get(it.getIdentifier()));
                } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                    incubate.addParent(fluidMap.get(identifier.getIdentifier()));
                } else {
                    incubate.addParent(operationTree);
                }
                fluidMap.put(assignedValue.getIdentifier(),incubate);
                fluidMap.put(it.getIdentifier(),incubate);
            } else if (statement instanceof Sense) {

                System.out.println("SENSE "+((Sense) statement).getSenseType().name()+" FROM "+((Sense) statement).getFrom().getIdentifier()+" INTO "+((Sense) statement).getInto().getIdentifier());

                // Since sense takes no time, one has to add physical constraints to it.
                Node sense = new Node<>((Sense) statement);
                Identifier identifier = ((Sense) statement).getFrom();
                if (identifier.getIdentifier().equals(it.getIdentifier())) {
                    sense.addParent(fluidMap.get(it.getIdentifier()));
                } else if (fluidMap.containsKey(identifier.getIdentifier())) {
                    sense.addParent(fluidMap.get(identifier.getIdentifier()));
                } else {
                    sense.addParent(operationTree);
                }
                fluidMap.put(identifier.getIdentifier(),sense);
                fluidMap.put(it.getIdentifier(),sense);
            }
        }
    }

    // TODO: Solution to mixer ratio problem
    private int[] ratioApproximation(Mix mix) {
        Integer ratio[] = mix.getRatio();
        if (ratio.length != 0) {
            double sum = 0;
            for (int value : ratio) {
                sum += value;
            }

            int closestApproximation = 2;
            while(closestApproximation < sum) {
                closestApproximation = closestApproximation*2;
            }

            double p[] = new double[ratio.length];
            int a[] = new int[ratio.length];

            for (int i = 0; i < ratio.length; i++) {
                double value = ratio[i]*(closestApproximation/sum);
                a[i] = Math.round(Math.round(value));
                p[i] = a[i]-value;
            }

            int approximationErrorSum = 0;
            for (int value : a) {
                approximationErrorSum += value;
            }

            int goodToGo = approximationErrorSum-closestApproximation;

            while (goodToGo > 0) {
                int index = getArrayMaxIndex(p);
                a[index] -= 1;
                p[index] -= 1;
                goodToGo -= 1;
            }

            while (goodToGo < 0) {
                int index = getArrayMinIndex(p);
                a[index] += 1;
                p[index] += 1;
                goodToGo += 1;
            }

            return a;
        } else {
            int noRatio[] = new int[mix.getIdentifiers().length];
            for (int i = 0; i < noRatio.length; i++) {
                noRatio[i] = 1;
            }

            return noRatio;
        }
    }

    private static int getArrayMaxIndex(double[] arr) {
        double max = -Double.MAX_VALUE;
        int index = -Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                index = i;
                max = arr[i];
            }
        }

        return index;
    }

    private static int getArrayMinIndex(double[] arr) {
        double min = Double.MAX_VALUE;
        int index = -Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                index = i;
                min = arr[i];
            }
        }

        return index;
    }

    private Node minMix(Mix mix, int[] r) {
        int sum = 0;
        for (int value : r) {
            sum += value;
        }
        int depth = (int) (Math.log(sum)/Math.log(2));

        List<List<Integer>> bins = new ArrayList<>(depth+1);

        for (int i = 1; i < depth+2; i++) {
            List<Integer> innerList = new ArrayList<>();
            bins.add(innerList);
        }

        for (int i = 0; i < r.length; i++) {
            int mask = 1;
            for (int j = 0; j < depth; j++) {
                if ((mask & r[i]) != 0) {
                    bins.get(j).add(i);
                }
                mask <<= 1;
            }
        }

        return buildMixingTree(mix, bins, depth);
    }

    private Node buildMixingTree(Mix mix, List<List<Integer>> bins, int depth) {
        Integer newRatio[] = {1,1};
        if (bins.get(depth).isEmpty()) {
            Node<Mix> newMix = new Node<Mix>(new Mix(mix.getAssign(), null, newRatio, mix.getTime()));
            Node input1 = buildMixingTree(mix, bins, depth-1);
            Node input2 = buildMixingTree(mix, bins, depth-1);
            newMix.addParent(input1);
            newMix.addParent(input2);
            return newMix;
        } else {
            Integer index = bins.get(depth).get(0);
            bins.get(depth).remove(0);
            return fluidMap.get(mix.getIdentifiers()[index].getIdentifier());
        }
    }
}
