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
            "[CTRL+X]a fluffy white cloud drifts [CTRL+V][CTRL+C] across the sky, [CTRL+V]"};

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
        String result = challenge1.runChallenge("first copy[CTRL+C] paste here [CTRL+V] copy[CTRL+C] last paste [CTRL+V]");
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
        String result = challenge1.runChallenge(testStrings[0]);
        assertEquals("the big red fox jumps over the big red lazy dog.", result);
    }

    @Test
    public void shouldManipulateSecondString() {
        String result = challenge1.runChallenge(testStrings[1]);
        assertEquals("the tall oak tree towers over the lush green meadow.", result);
    }

    @Test
    public void shouldManipulateThirdString() {
        String result = challenge1.runChallenge(testStrings[2]);
        assertEquals("the sun shines down on the sun shines down the busy the sun shines down on the sun shines down.",
                result);
    }

    @Test
    public void shouldManipulateFourthString() {
        String result = challenge1.runChallenge(testStrings[3]);
        assertEquals("a majestic lion searches for a majestic lion in the tall grass.", result);
    }

    @Test
    public void shouldManipulateFifthString() {
        String result = challenge1.runChallenge(testStrings[4]);
        assertEquals("Twinkling in the dark, the shimmering star shines bright.", result);
    }

    @Test
    public void shouldManipulateSixthString() {
        String result = challenge1.runChallenge(testStrings[5]);
        assertEquals("a fluffy white cloud drifts  across the sky, a fluffy white cloud drifts ", result);
    }
}
