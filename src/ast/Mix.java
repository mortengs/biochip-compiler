package ast;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Mix extends Statement {

    String[] identifiers;
    int[] ratios;
    int forvalue;

    public Mix(String[] identifiers, int[] ratios, int forvalue) {
        this.identifiers = identifiers;
        this.ratios = ratios;
        this.forvalue = forvalue;
    }
}
