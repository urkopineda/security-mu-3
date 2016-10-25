package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by urko on 15/02/16.
 */
public class Message {

    private String type;
    private long x, y;

    private final String regex = "([a-z]{3})[(]([0-9]*)[,,]([0-9]*)[)][$]";

    public Message(String message) {
        if (message == null) {
            return;
        } else if (message.equals("null")) {
            return;
        }
        Matcher matcher = createPattern().matcher(message);
        if (matcher.matches()) {
            type = matcher.group(1);
            x = Long.parseLong(matcher.group(2));
            y = Long.parseLong(matcher.group(3));
        }
    }

    public Message(String type, long x, long y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    private Pattern createPattern() {
        return Pattern.compile(regex);
    }

    public String getType() {
        return type;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

}
