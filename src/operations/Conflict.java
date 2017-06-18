package operations;

/**
 * Created by Jesper on 15/03/2017.
 */
public class Conflict extends Declaration {
    private String identifier;
    private String follows_identifier;
    private String wash_identifier;

    public Conflict(String identifier, String follows_identifier, String wash_identifier) {
        this.identifier = identifier;
        this.follows_identifier = follows_identifier;
        this.wash_identifier = wash_identifier;
    }

    public String getWash_identifier() {
        return wash_identifier;
    }

    public String getFollows_identifier() {
        return follows_identifier;
    }
}
