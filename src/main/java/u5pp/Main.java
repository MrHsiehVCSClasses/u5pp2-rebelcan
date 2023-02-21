package u5pp;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello! :)\n" + 
        "Welcome to the unit 5 programming project!\n"+
        "Take a look at the README, and the txt files to start\n" + 
        "Good luck!");

        Scanner scanner = new Scanner(System.in);
        Chess chess = new Chess();
        chess.play(scanner);
    }
}