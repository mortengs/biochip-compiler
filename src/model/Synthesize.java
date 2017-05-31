package model;

import ast.*;

/**
 * Created by Jesper on 22/05/2017.
 */
public class Synthesize {

    public void synthesize(Assay assay) {
        Statement stmt = IRWalker.walk(assay.getStatements());
        while(stmt != null) {
            if (stmt instanceof Repeat) {
                System.out.println("Repeat");
            } else if (stmt instanceof ForLoop) {
                System.out.println("ForLoop");
            } else if (stmt instanceof Mix) {
                System.out.println("Mix");
            } else if (stmt instanceof Incubate) {
                System.out.println("Incubate");
            } else if (stmt instanceof Sense) {
                System.out.println("Sense");
            } else {
                System.out.println("Doesn't exist");
            }

            stmt = IRWalker.walk();
        }
    }
}
