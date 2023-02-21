package u5pp;

import java.util.Scanner;

public class InputHelper {

    private Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    // get number input

    public boolean getYesNoInput(String prompt) {
        while(true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            input = input.replaceAll("\\s", "").toLowerCase();
            char firstLetter = input.charAt(0);
            if(firstLetter == 'y') {
                return true;
            }
            if(firstLetter == 'n') {
                return false;
            }
            System.out.println("Sorry, invalid input. Please answer with either \'yes\'' or \'no\'.");
        }
    }


    // get chess location input
    // returns an int[], {row, col} where row & col are 0-7
    public int[] getChessLocation(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            input = input.replaceAll("\\s", "").toLowerCase();

            if (input.length() != 2 ||
                    input.charAt(0) < 'a' ||
                    input.charAt(0) > 'h' ||
                    input.charAt(1) < '1' ||
                    input.charAt(1) > '8') {

                System.out.println("Sorry, invalid input.\n"
                        + "Please input a letter a-h followed by a number 1-8");
                continue;
            }

            int col = input.charAt(0) - 'a';
            int row = input.charAt(1) - '1';
            int[] output = new int[2];
            output[0] = row;
            output[1] = col;
            return output;
        }
    }
}
