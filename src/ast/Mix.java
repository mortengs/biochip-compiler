package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Mix extends Statement {
    // 'MIX' identifier ('AND' identifier)+ ('IN RATIOS' expr (':' expr)+)? 'FOR' expr
    private String assign;
    private String[] identifiers;
    private Integer[] ratio;
    private Integer forvalue;

    public Mix(String assign, String[] identifiers, Integer[] ratio, Integer forvalue) {
        this.assign = assign;
        this.identifiers = identifiers;
        this.ratio = ratio;
        this.forvalue = forvalue;
    }

    public String getAssign() {
        return assign;
    }

    public String[] getIdentifiers() {
        return identifiers;
    }

    public Integer[] getRatio() {
        return ratio;
    }

    public Integer getForvalue() {
        return forvalue;
    }
}
