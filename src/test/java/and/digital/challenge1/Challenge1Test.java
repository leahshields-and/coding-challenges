package and.digital.challenge1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Challenge1Test {
    String[] testStrings = { "the big red[CTRL+C] fox jumps over [CTRL+V] lazy dog.",
            "[CTRL+V]the tall oak tree towers over the lush green meadow.",
            "the sun shines down[CTRL+C] on [CTRL+V][CTRL+C] the busy [CTRL+V].",
            "a majestic lion[CTRL+C] searches for [CTRL+V] in the tall grass.",
            "the shimmering star[CTRL+X]Twinkling in the dark, [CTRL+V] shines bright.",
            "[CTRL+X]a fluffy white cloud drifts [CTRL+V][CTRL+C] across the sky, [CTRL+V]" };

    Challenge1 challenge1;

    @BeforeEach
    public void before() {
        challenge1 = new Challenge1();
    }

    @Test
    public void shouldCopyStringToClipboard() {
        assertEquals("", challenge1.getClipboard());
        challenge1.runCommand("C", 11, "the big red[CTRL+C]");
        assertEquals("the big red", challenge1.getClipboard());
    }

    @Test
    public void shouldRemoveCommandAfterCopy() {
        String result = challenge1.runChallenge("the big red[CTRL+C] fox");
        assertEquals("the big red fox", result);
    }

    @Test
    public void shouldPasteStringFromClipboard() {
        challenge1.setClipboard("clipboard");
        String result = challenge1.runCommand("V", 11, "pasted text from [CTRL+V]");
        assertEquals("pasted text from clipboard", result);
    }

    @Test
    public void shouldRemoveCommandAfterPaste() {
        String result = challenge1.runChallenge("the big red[CTRL+V] fox");
        assertEquals("the big red fox", result);
    }

    @Test
    public void shouldOnlyRemoveFirstPaste() {
        challenge1.setClipboard("clipboard");
        String result = challenge1.runCommand("V", 11, "pasted text from [CTRL+V] [CTRL+V]");
        assertEquals("pasted text from clipboard [CTRL+V]", result);
    }

    @Test
    public void shouldCopyAndPasteTwice() {
        String result = challenge1
                .runChallenge("first copy[CTRL+C] paste here [CTRL+V] copy[CTRL+C] last paste [CTRL+V]");
        assertEquals("first copy paste here first copy copy last paste first copy paste here first copy copy", result);
    }

    @Test
    public void shouldCopyStringToClipboardOnCut() {
        assertEquals("", challenge1.getClipboard());
        challenge1.runCommand("X", 11, "the big red[CTRL+X]");
        assertEquals("the big red", challenge1.getClipboard());
    }

    @Test
    public void shouldRemoveCommandAndStringOnCut() {
        String result = challenge1.runChallenge("the big red[CTRL+X] fox");
        assertEquals(" fox", result);
    }

    @Test
    public void shouldManipulateFirstString() {
        runOnTestString(testStrings[0], "the big red fox jumps over the big red lazy dog.");
    }

    @Test
    public void shouldManipulateSecondString() {
        runOnTestString(testStrings[1], "the tall oak tree towers over the lush green meadow.");
    }

    @Test
    public void shouldManipulateThirdString() {
        runOnTestString(testStrings[2],
                "the sun shines down on the sun shines down the busy the sun shines down on the sun shines down.");
    }

    @Test
    public void shouldManipulateFourthString() {
        runOnTestString(testStrings[3], "a majestic lion searches for a majestic lion in the tall grass.");
    }

    @Test
    public void shouldManipulateFifthString() {
        runOnTestString(testStrings[4], "Twinkling in the dark, the shimmering star shines bright.");
    }

    @Test
    public void shouldManipulateSixthString() {
        runOnTestString(testStrings[5], "a fluffy white cloud drifts  across the sky, a fluffy white cloud drifts ");
    }

    private void runOnTestString(String testInput, String expectedOutput) {
        System.out.println("-----------------------");
        System.out.println("Running challenge on input:");
        System.out.println(testInput);
        String result = challenge1.runChallenge(testInput);
        System.out.println("RESULT:");
        System.out.println(result);
        System.out.println("-----------------------");
        assertEquals(expectedOutput, result);
    }
}
