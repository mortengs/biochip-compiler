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
    Identifier assign;
    Identifier[] identifiers;
    AquaParser.ExprContext[] ratio;
    AquaParser.ExprContext forvalue;

    public Mix(Identifier assign, Identifier[] identifiers, AquaParser.ExprContext[] ratio, AquaParser.ExprContext forvalue) {
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

    public AquaParser.ExprContext[] getRatio() {
        return ratio;
    }

    public AquaParser.ExprContext getForvalue() {
        return forvalue;
    }
}
