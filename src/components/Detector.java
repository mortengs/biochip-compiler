package components;

import model.IntPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 07/06/2017.
 */
public class Detector extends Component {

    private List<IntPair> timeSchedule;

    public Detector() {
        timeSchedule = new ArrayList<>();
    }

    public List<IntPair> getTimeSchedule() {
        return timeSchedule;
    }

    public void addTime(IntPair time) {
        timeSchedule.add(time);
    }
}
