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
    AquaParser.ExprContext[] ratioExpr;
    AquaParser.ExprContext timeExpr;
    Integer[] ratio;
    Integer time;

    public Mix(Identifier assign, Identifier[] identifiers, AquaParser.ExprContext[] ratioExpr, AquaParser.ExprContext timeExpr) {
        this.assign = assign;
        this.identifiers = identifiers;
        this.ratioExpr = ratioExpr;
        this.timeExpr = timeExpr;
    }

    public Mix(Identifier assign, Identifier[] identifiers, Integer[] ratio, Integer time) {
        this.assign = assign;
        this.identifiers = identifiers;
        this.ratio = ratio;
        this.time = time;
    }

    public Identifier getAssign() {
        return assign;
    }

    public Identifier[] getIdentifiers() {
        return identifiers;
    }

    public AquaParser.ExprContext[] getRatioExpr() {
        return ratioExpr;
    }

    public AquaParser.ExprContext getTimeExpr() {
        return timeExpr;
    }

    public Integer[] getRatio() {
        return ratio;
    }

    public Integer getTime() {
        return time;
    }

}
