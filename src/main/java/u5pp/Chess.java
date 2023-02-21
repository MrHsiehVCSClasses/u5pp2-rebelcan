package u5pp;

import java.util.Scanner;

public class Chess {

    private ChessPiece[][] board;
    private int numPlayers;
    private String p1Name = "";
    private String p2Name = "";
    private InputHelper myInputHelper;
    private int turnNumber;

    public Chess() {
    }

    public void play(Scanner scanner) {

        myInputHelper = new InputHelper(scanner);
        // all that gameplay functionality :)

        System.out.println("Welcome to Chess.");
        
        numPlayers = 1;
        p2Name = "Computer";

        // get names for players
        System.out.println("What's your name?");
        p1Name = scanner.nextLine();
        System.out.println(String.format("Hi, %s! Let's play chess!", p1Name));

        boolean stopPlaying = false;
        // while (!shouldQuit) 
        while(!stopPlaying) {
            turnNumber = 0;
            initializeBoard();

            System.out.println("Game start!");
            System.out.println(String.format("%s vs. %s", p1Name, p2Name));

            // while !hasWinner
            while(getWinner(board) == 0) {
                turnNumber++;
                System.out.println("Turn number " + turnNumber);
                // print board 
                System.out.println(boardToString(board));
                // check which player's turn it is
                // if it's 2nd players turn and we are in a 1p game
                if(turnNumber % 2 == 0 && numPlayers == 1) {
                    // do ai turn
                    doComputerMove();
                }
                // else
                else {
                    // do normal player's turn
                    doPlayerMove();
                }
            }

            // here if winner has been found
            // print board
            System.out.println(boardToString(board));
            // declare winner
            int whoWon = getWinner(board);
            if(whoWon > 0) {
                // white win
                System.out.println("White wins!");
            } else if (whoWon < 0) {
                System.out.println("Black wins!");
            }
            // ask if they want to play again
            stopPlaying = !myInputHelper.getYesNoInput("Do you want to play again? (Y/N)");
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * detects if either of white or black has won
     * @param board 
     * @return 1 if white, -1 if black, 0 if no winner or tie
     */
    public static int getWinner(ChessPiece[][] board) {
        boolean whiteKingFound = false;
        boolean blackKingFound = false;
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                ChessPiece currentChessPiece = board[r][c];
                if(currentChessPiece instanceof King) {
                    if(currentChessPiece.getIsWhite()) {
                        whiteKingFound = true;
                    } else {
                        blackKingFound = true;
                    }
                }
            }
        }
        if (whiteKingFound && !blackKingFound) {
            return 1;
        }
        if (!whiteKingFound && blackKingFound) {
            return -1;
        }
        return 0;
    }

    public void initializeBoard() { // possible method
        board = new ChessPiece[8][8];
        
        board[0][4] = new King(board, 0, 4, true);
        board[7][4] = new King(board, 7, 4, false);
        
        board[0][3] = new Queen(board, 0, 3, true);
        board[7][3] = new Queen(board, 7, 3, false);
        
        for(int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(board, 1, i, true);
            board[6][i] = new Pawn(board, 6, i, false);
        }
        
        board[0][2] = new Bishop(board, 0, 2, true);
        board[7][2] = new Bishop(board, 7, 2, false);
        board[0][5] = new Bishop(board, 0, 5, true);
        board[7][5] = new Bishop(board, 7, 5, false);
        
        board[0][0] = new Rook(board, 0, 0, true);
        board[7][0] = new Rook(board, 7, 0, false);
        board[0][7] = new Rook(board, 0, 7, true);
        board[7][7] = new Rook(board, 7, 7, false);
        
        board[0][1] = new Knight(board, 0, 1, true);
        board[7][1] = new Knight(board, 7, 1, false);
        board[0][6] = new Knight(board, 0, 6, true);
        board[7][6] = new Knight(board, 7, 6, false);
    }

    public static String boardToString(ChessPiece[][] board) {

        String output = "";
        output += " ".repeat(3);

        for(int i = 0; i < 8; i++) {
            output += " " + (char)('a' + i) + "  ";
        }
        
        output += "\n";
        output += "  " + "┌───" + "┬───".repeat(7) + "┐";
        output += "\n";
        for(int r = 0; r < board.length; r++) {
            output += (r)+1 + " │";
            for(int c = 0; c < board[r].length; c++) {
                output += " ";
                if(board[r][c] == null) {
                    output += " ";
                } else {
                    output += board[r][c];
                }
                output += " │";
            }
            output += (" " + (r+1));
            output += "\n";
                    
            if(r == board.length-1) {
                output += "  " + "└───" + "┴───".repeat(7) + "┘";
            } else {
                output += "  " + "├───" + "┼───".repeat(7) + "┤";
            }
            output += "\n";
        }
        
        output += " ".repeat(3);

        for(int i = 0; i < 8; i++) {
            output += " " + (char)('a' + i) + "  ";
        }

        return output;
        
    }

    private void doPlayerMove() {
        // while !done
        while(true) {

            // get a proper input
            ChessPiece selectedPiece = null;
            while(selectedPiece == null) {
                int[] input = myInputHelper.getChessLocation("What piece would you like to move?");
                int r = input[0];
                int c = input[1];
                // make sure that proper input points at one of the active player's pieces
                if(board[r][c] != null && board[r][c].getIsWhite()) {
                    selectedPiece = board[r][c];
                } else {
                    System.out.println("Please select one of your pieces");
                }
            }

            // get a proper input
            int[] targetSquare = myInputHelper.getChessLocation("Where would you like to move this piece?");
            // make sure that proper input points at a place the piece can move
            int targetR = targetSquare[0];
            int targetC = targetSquare[1];
            if(selectedPiece.canMoveTo(targetR, targetC)) {
                selectedPiece.moveTo(targetR, targetC);
                break;
            }
            System.out.println("That move is not allowed. Please try again.");
        }
    }

    private void doComputerMove() {
        doComputerMoveRandom();
    }

    private void doComputerMoveRandom() {
        // 1. get randomly ordered list of pieces
        // 1a. create list of all pieces
        int piecesFound = 0;
        ChessPiece[] pieces = new ChessPiece[16];
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                if(board[r][c] != null && !board[r][c].getIsWhite()) {
                    pieces[piecesFound] = board[r][c];
                    piecesFound++;
                }
            }
        }
        // 1b. shuffle list
        arrayShuffle(pieces);


        int[] spaces = new int[64];
        // 2. get random list of all squares
        // > get a list of all 64 squares
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                spaces[r * 8 + c] = r * 8 + c;
            }
        }
        // > shuffle list
        arrayShuffle(spaces);
        
        // pick a legal move for that piece
        // for each piece in our list
        for(ChessPiece piece : pieces) {
            if (piece == null) {
                // skip null pieces
                continue; 
            }

            // try every square for currently selected piece

            // for each possible square
            for(int space : spaces) {
                // decode space into row/col
                int row = space / 8;
                int col = space % 8;
                // if a move works, 
                if(piece.canMoveTo(row, col))
                {
                    piece.moveTo(row, col);
                    return;
                }
            }
        }
    }

    private static void arrayShuffle(ChessPiece[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int swapIndex = (int)(Math.random() * (arr.length - i) + i);
            ChessPiece swap = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = swap;
        }
    }

    private static void arrayShuffle(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int swapIndex = (int)(Math.random() * (arr.length - i) + i);
            int swap = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = swap;
        }
    }

}
