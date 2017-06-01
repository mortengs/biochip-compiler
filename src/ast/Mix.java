package ast;

import com.sun.xml.internal.bind.v2.model.core.ID;
import parser.AquaParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Mix extends Statement {
    // 'MIX' identifier ('AND' identifier)+ ('IN RATIOS' expr (':' expr)+)? 'FOR' expr
    private Identifier assign;
    private Identifier[] identifiers;
    private Integer[] ratio;
    private Integer forvalue;

    public Mix(Identifier assign, Identifier[] identifiers, Integer[] ratio, Integer forvalue) {
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
