package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Mix extends Statement {

    String[] identifiers;
    List<Expr> ratios = new ArrayList<>();
    Expr forvalue;

    public Mix(String[] identifiers, List<Expr> ratios, Expr forvalue) {
        this.identifiers = identifiers;
        this.ratios = ratios;
        this.forvalue = forvalue;
    }
}
