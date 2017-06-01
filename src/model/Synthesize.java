package model;

import ast.*;

/**
 * Created by Jesper on 22/05/2017.
 */
public class Synthesize {
    public void synthesize(Assay assay) {
        for (Statement stmt : assay.getStatements()) {
            if (stmt instanceof Repeat) {
                Repeat repeat = (Repeat) stmt;
                System.out.println("Repeat: "+repeat.getExpr()+", statements "+repeat.getStatements().size());
            } else if (stmt instanceof ForLoop) {
                ForLoop forLoop = (ForLoop) stmt;
                System.out.println("ForLoop: "+forLoop.getIdentifier()+", from "+forLoop.getFrom()+", to "+forLoop.getTo()+", statements "+forLoop.getStatements().size());
            } else if (stmt instanceof Mix) {
                Mix mix = (Mix) stmt;
                System.out.println("Mix: "+mix.getAssign().getIdentifier()+", identifiers "+mix.getIdentifiers().length+", ratio "+mix.getRatio().length+", forvalue "+mix.getForvalue());
            } else if (stmt instanceof Incubate) {
                Incubate incubate = (Incubate) stmt;
                System.out.println("Incubate: "+incubate.getAssign().getIdentifier()+", identifier "+incubate.getIdentifier().getIdentifier()+ ", at "+incubate.getAt()+", for "+incubate.getForvalue());
            } else if (stmt instanceof Sense) {
                Sense sense = (Sense) stmt;
                System.out.println("Sense: "+sense.getSenseType()+", from "+sense.getFrom().getIdentifier()+ ", into "+sense.getInto().getIdentifier());
            } else if (stmt instanceof AssignExpr) {
                System.out.println("AssignExpr: ");
            } else {
                System.out.println("Doesn't exist");
            }
        }
    }
}
