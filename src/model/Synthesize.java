package model;

import components.*;

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

    ArrayList<Node<Component>> map = new ArrayList<>();

    public void synthesize(Node<Component> root) {
        visitNode(root);

        connections.append("END LIST;\n");

    }

    public void visitNode(Node<Component> node) {
        if(node.isLeaf()) {

            return;
        } else {
            for (Node<Component> child: node.getChildren()) {
                //Do this before going down

                //Check to see if the parent is root
                //If it is, create a source node
                //---Since we are observing the root, do we need this?
                for (Node<Component> par : node.getParents()) {
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

    private void createConnection(Node<Component> node, Node<Component> child) {
        // connections.append();
    }

    private void createComponent(Node<Component> child) {
        if(child.getData() instanceof Mixer) {
            addMixer();
        } else if(child.getData() instanceof Heater) {
            addIncubator();
        } else if(child.getData() instanceof Detector) {
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
