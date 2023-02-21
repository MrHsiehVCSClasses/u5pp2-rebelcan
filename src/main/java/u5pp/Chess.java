package u5pp;

import java.util.Scanner;



    /**
     * Used to print a chess board out.
     */



public class Chess {

    private ChessPiece[][] board;
    private InputHelper inputHelper;
    private int GameType;

    Scanner scanner;

    public Chess()
    {
    }

    public void Play(Scanner scanner)
    {
        int winner=0;
        this.scanner=scanner;
        System.out.println("Chess game start.");
        inputHelper = new InputHelper(scanner);

        board=new ChessPiece[8][8];

        initializeBoard();

        System.out.println("Choose Game Mode 1: 2 Players, 2: 1 Player vs Computer ");
        while(true)
        {
            GameType = scanner.nextInt();
            System.out.println(boardToString(board));

            if(GameType==1)//2 Players
            {
                while(true)
                {
                    doPlayerMove();
                    System.out.println(boardToString(board));
                    winner=getWinner(board);
                    if(winner!=0)
                        break;

                    doComputerMove();
                    System.out.println(boardToString(board));
                    winner=getWinner(board);
                    if(winner!=0)
                        break;
                }
                break;
            }
            else if(GameType==2)//vs AI
            {
                break;
            }
            else
            {
                System.out.println("Wrong input, try again.");
            }

        }

        if(winner==1)
            System.out.println("White won the game.");
        else
            System.out.println("Black won the game.");
    }

    private void doPlayerMove()
    {
        //2 Players mode
        if(GameType==1)
            System.out.println("Please input Player 1 move.");
        //AI mode
        else
            System.out.println("Please input Player move.");

        int[]first_input=new int[2];
        int[]second_input=new int[2];
        boolean check_move;

        boolean is_move=false;
        while(!is_move)
        {
            first_input=inputHelper.getChessLocation(scanner.next());
            if(first_input[0]==-1||first_input[1]==-1)
            {
                System.out.println("not allowed input. Please input again.");
                is_move=false;
                continue;
            }
            check_move=board[first_input[1]][first_input[0]].is_canSelect(board,first_input[1],first_input[0],true);
            if(!check_move)
            {
                System.out.println("not allowed input. Please input again.");
                is_move=false;
                continue;
            }
            System.out.println("Please input where to move.");
            second_input=inputHelper.getChessLocation(scanner.next());
            if(second_input[0]==-1||second_input[1]==-1)
            {
                System.out.println("not allowed input. Please input again.");
                is_move=false;
                continue;
            }
            check_move=board[first_input[1]][first_input[0]].canMoveTo(second_input[1],second_input[0]);

            if(!check_move)
            {
                System.out.println("not allowed input.");
                is_move=false;
            }
            else
                is_move=true;
        }
        board[first_input[1]][first_input[0]].moveTo(second_input[1],second_input[0]);

    }

    private void doComputerMove()
    {
        if(GameType==1)//2 Players mode
        {
            System.out.println("Please input Player 2 move.");

            int[]first_input=new int[2];
            int[]second_input=new int[2];
            boolean check_move;

            boolean is_move=false;
            while(!is_move)
            {
                first_input=inputHelper.getChessLocation(scanner.next());
                if(first_input[0]==-1||first_input[1]==-1)
                {
                    System.out.println("not allowed input. Please input again.");
                    is_move=false;
                    continue;
                }
                check_move=board[first_input[1]][first_input[0]].is_canSelect(board,first_input[1],first_input[0],false);
                if(!check_move)
                {
                    System.out.println("not allowed input. Please input again.");
                    continue;
                }
                System.out.println("Please input where to move.");
                second_input=inputHelper.getChessLocation(scanner.next());
                if(second_input[0]==-1||second_input[1]==-1)
                {
                    System.out.println("not allowed input. Please input again.");
                    is_move=false;
                    continue;
                }
                check_move=board[first_input[1]][first_input[0]].canMoveTo(second_input[1],second_input[0]);

                if(!check_move)
                {
                    System.out.println("not allowed input. Please input again.");
                    is_move=false;
                }
                else
                    is_move=true;
            }
            board[first_input[1]][first_input[0]].moveTo(second_input[1],second_input[0]);
        }
        else//AI mode
        {

        }
    }

    //check King count
    static int getWinner(ChessPiece[][]board)
    {
        int white_king_cnt=0;
        int black_king_cnt=0;

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(board[i][j]==null)
                    continue;

                if(board[i][j].toString()=="K")
                    white_king_cnt++;
                else if(board[i][j].toString()=="k")
                    black_king_cnt++;
            }
        }
        if(white_king_cnt>black_king_cnt)
            return 1;
        else if(white_king_cnt<black_king_cnt)
            return -1;
        else
            return 0;
    }

    private void initializeBoard()
    {
        for(int i=0;i<8;i++)
        {
            if(i==0)
            {
                board[i][0]=new Rook(board,0,0,true);
                board[i][1]=new Knight(board,0,1,true);
                board[i][2]=new Bishop(board,0,2,true);
                board[i][3]=new Queen(board,0,3,true);
                board[i][4]=new King(board,0,4,true);
                board[i][5]=new Bishop(board,0,5,true);
                board[i][6]=new Knight(board,0,6,true);
                board[i][7]=new Rook(board,0,7,true);
            }
            else if(i==1)
            {
                for(int j=0;j<8;j++)
                    board[i][j]=new Pawn(board,i,j,true);
            }
            else if(i==6)
            {
                for(int j=0;j<8;j++)
                    board[i][j]=new Pawn(board,i,j,false);
            }
            else if(i==7)
            {
                board[i][0]=new Rook(board,7,0,false);
                board[i][1]=new Knight(board,7,1,false);
                board[i][2]=new Bishop(board,7,2,false);
                board[i][3]=new Queen(board,7,3,false);
                board[i][4]=new King(board,7,4,false);
                board[i][5]=new Bishop(board,7,5,false);
                board[i][6]=new Knight(board,7,6,false);
                board[i][7]=new Rook(board,7,7,false);
            }
            else
            {
                for(int j=0;j<8;j++)
                    board[i][j]=new ChessPiece(board,i,j,false);
            }
        }
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

}
