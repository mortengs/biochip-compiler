package ast;

import parser.AquaParser;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Incubate extends Statement {
    //incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;
    Identifier identifier;
    Identifier assign;
    AquaParser.ExprContext temperatureExpr;
    AquaParser.ExprContext timeExpr;
    Integer temperature;
    Integer time;


    public Incubate(Identifier assign, Identifier identifier, AquaParser.ExprContext temperatureExpr, AquaParser.ExprContext timeExpr) {
        this.assign = assign;
        this.identifier = identifier;
        this.temperatureExpr = temperatureExpr;
        this.timeExpr = timeExpr;
    }

    public Incubate(Identifier assign, Identifier identifier, Integer temperature, Integer time) {
        this.assign = assign;
        this.identifier = identifier;
        this.temperature = temperature;
        this.time = time;
    }

    public Identifier getAssign() {
        return assign;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public AquaParser.ExprContext getTemperatureExpr() {
        return temperatureExpr;
    }

    public AquaParser.ExprContext getTimeExpr() {
        return timeExpr;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getTime() {
        return time;
    }

}
