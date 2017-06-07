package ast;

/**
 * Created by Jesper on 07/06/2017.
 */
public class CalculatedMix extends Statement {
    // 'MIX' identifier ('AND' identifier)+ ('IN RATIOS' expr (':' expr)+)? 'FOR' expr
    Identifier assign;
    Identifier[] identifiers;
    Integer[] ratio;
    Integer forvalue;

    public CalculatedMix(Identifier assign, Identifier[] identifiers, Integer[] ratio, Integer forvalue) {
        this.assign = assign;
        this.identifiers = identifiers;
        this.ratio = ratio;
        this.forvalue = forvalue;
    }

    public Identifier getAssign() {
        return assign;
    }

    public Identifier[] getIdentifiers() {
        return identifiers;
    }

    public Integer[] getRatio() {
        return ratio;
    }

    public Integer getForvalue() {
        return forvalue;
    }
}
