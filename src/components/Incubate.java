package components;

/**
 * Created by Jesper on 07/06/2017.
 */
public class Incubate extends Component {
    private Integer temperature;
    private Integer time;

    public Incubate(Integer temperature, Integer time) {
        this.temperature = temperature;
        this.time = time;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public Integer getTime() {
        return time;
    }
}
