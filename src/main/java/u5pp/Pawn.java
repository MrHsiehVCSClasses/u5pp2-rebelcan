package u5pp;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece
{

    public Pawn(ChessPiece[][] board, int row, int col, boolean isWhite)
    {
        this.row=row;
        this.column=col;
        this.is_white=isWhite;
        this.board=board;
        this.is_blank=false;
        first_move=true;
    }
    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public boolean getIsWhite()
    {
        return is_white;
    }
    public String toString()
    {
        if(is_white)
            return "P";
        else
            return "p";
    }
    public boolean canMoveTo(int row, int col)
    {
        boolean check=true;

        //out of board
        if(row<0||row>=8||col<0||col>=8)
            return false;

        int xDiff = col - this.column;
        int yDiff = row - this.row;

        if(!this.is_white&&yDiff>0)
            return false;
        else if(this.is_white&&yDiff<0)
            return false;

        if(abs(xDiff)==0)//go forward
        {
            if(yDiff==0)//current square
                return false;
            if(first_move)
            {
                if(abs(yDiff)==2)
                {
                    if(is_blocked(this.row+yDiff,col)||is_blocked(row,col))
                        return false;
                }
                else
                {
                    if(is_blocked(row,col)&&(abs(yDiff) == 1))
                        return false;
                }
            }
            else
            {
                if(is_blocked(row,col)&&(abs(yDiff) == 1))
                    return true;
                else //move two spaces on their second turn
                    return false;
            }
        }
        else//attack
        {
            if((abs(xDiff) == 1) && (abs(yDiff) == 1))//diagonal
            {
                if(board[row][col]==null||board[row][col].is_blank)//attack on blank
                    return false;
                else
                {
                    if(board[this.row][this.column].getIsWhite()==board[row][col].getIsWhite())//same color
                        return false;
                }
            }
            else
                return false;
        }

        return check;
    }

}
