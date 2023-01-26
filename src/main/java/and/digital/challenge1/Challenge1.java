package and.digital.challenge1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge1 {
    private String clipboard = "";
    private Pattern pattern = Pattern.compile("\\[CTRL+(?:$|\\W)([A-Z])\\]");

    private void copyPrecedingStringToClipboard(int startLocation, String input) {
        setClipboard(input.substring(0, startLocation));
    }

    protected String runCommand(String command, int startLocation, String input) {
        if (command.equals("C")) {
            copyPrecedingStringToClipboard(startLocation, input);
            return input.replaceFirst(pattern.pattern(), "");
        }
        if (command.equals("V")) {
            return input.replaceFirst(pattern.pattern(), getClipboard());
        }
        if (command.equals("X")) {
            copyPrecedingStringToClipboard(startLocation, input);
            input = input.replaceFirst(pattern.pattern(), "");
            return input.substring(startLocation);
        }
        return input;
    }

    private String findMatch(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            input = runCommand(matcher.group(1), matcher.start(), input);
            matcher = pattern.matcher(input);
            input = findMatch(input);
        }
        return input;
    }

    public String runChallenge(String input) {
        return findMatch(input);
    }

    public String getClipboard() {
        return clipboard;
    }

    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }

}
