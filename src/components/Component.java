package components;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 07/06/2017.
 */
public abstract class Component {

    int time;

    public Component() {
        time = 0;
    }

    public int getTime() {
        return time;
    }

    public void addTime(int time) {
        this.time += time;
    }
}
