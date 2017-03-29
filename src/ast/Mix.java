package ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Mix extends Statement {

    String[] identifiers;

    public Mix(String[] identifiers) {
        this.identifiers = identifiers;
    }
}
