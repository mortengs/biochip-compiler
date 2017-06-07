package model;

import ast.*;

import java.util.ArrayList;


/**
 * Created by Jesper on 22/05/2017.
 */
public class Synthesize {
    StringBuilder str = new StringBuilder();

    StringBuilder components = new StringBuilder().insert(0,"COMPONENT LIST:\n");
    StringBuilder connections = new StringBuilder().insert(0,"CONNECTION LIST:\n");

    int mixerCount = 0;
    int heatCount = 0;
    int detectorCount = 0;

    ArrayList<IRSearchTree.Node<Statement>> map = new ArrayList<>();

    public void synthesize(IRSearchTree.Node<Statement> root) {
        visitNode(root);

        connections.append("END LIST;\n");

    }

    public void visitNode(IRSearchTree.Node<Statement> node) {
        if(node.isLeaf()) {

            return;
        } else {
            for (IRSearchTree.Node<Statement> child: node.getChildren()) {
                //Do this before going down

                //Check to see if the parent is root
                //If it is, create a source node
                //---Since we are observing the root, do we need this?
                for (IRSearchTree.Node<Statement> par : node.getParents()) {
                    if(par.isRoot()) {

                    }
                }
                if(!map.contains(node)) {
                    createComponent(child);
                    visitNode(child);
                }
                createConnection(node,child);

                //This is done on the way back

            }
            map.add(node);
            //Add node to map, to show that all children have been visited
        }
    }

    private void createConnection(IRSearchTree.Node<Statement> node, IRSearchTree.Node<Statement> child) {
        connections.append()
    }

    private void createComponent(IRSearchTree.Node<Statement> child) {
        if(child.getData() instanceof Mix) {
            addMixer();
        } else if(child.getData() instanceof Incubate) {
            addIncubator();
        } else if(child.getData() instanceof Sense) {
            addDetector();
        }
    }

    private void addDetector() {
        components.append("    det" + heatCount + " OF Detector;\n");
        detectorCount++;
    }

    private void addIncubator() {
        components.append("    heat" + heatCount + " OF Heater;\n");
        heatCount++;
    }

    private void addMixer() {
        components.append("    mix" + mixerCount + " OF Mixer;\n");
        mixerCount++;
    }
}
