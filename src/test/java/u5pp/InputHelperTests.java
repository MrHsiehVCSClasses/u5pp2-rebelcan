package u5pp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class InputHelperTests {

    InputHelper inputHelper;


    @Test
    public void getChessLocation_allSquares_returnsCorrectRowCol () {
        String input = "";
        for(char c = 'a'; c <= 'h'; c ++) {
            for (int r = 1; r <= 8; r ++) {
                input += "" + (char)c + (int)r + "\n";
            }
        }
        Scanner scanner = new Scanner(input);
        Scanner scanner2 = new Scanner(input);
        inputHelper = new InputHelper(scanner);

        for(int c = 0; c < 8; c++) {
            for (int r = 0; r < 8; r++) {
                int[] output = inputHelper.getChessLocation("test prompt: allowed input");
                String currLocation = scanner2.nextLine();
                assertEquals(r, output[0], String.format("When given %s, row should be %s, not %s", currLocation, r, output[0])); // row
                assertEquals(c, output[1], String.format("When given %s, col should be %s, not %s", currLocation, c, output[1])); // column
            }
        }

        scanner.close();
        scanner2.close();
    }

    @Test
    public void getChessLocation_improperInput_doesNotReturn() {
        String input = "";

        for(char c = 'i'; c <= 'z'; c++) {
            input += "" + c + "1\n"; // i1 to z1
        }

        // various other improper inputs
        input += "a0\n";
        input += "a9\n";
        input += "a10\n";
        input += "2b\n";
        input += "123\n";
        input += "hello\n";
        input += "I'll tell you how I became the prince of a town called Bel-Air\n";

        // proper input to actually end the function
        input += "a1";

        Scanner scanner = new Scanner(input);
        inputHelper = new InputHelper(scanner);

        // if this loops infinitely, you have a problem :)
        int[] output = inputHelper.getChessLocation("test prompt: invalid input");
        assertEquals(0, output[0], "row should be 0 when given a1");
        assertEquals(0, output[1], "col should be 0 when given a1");
        assertFalse(scanner.hasNextLine(), "should ignore all invalid inputs");

        scanner.close();
    }

}
