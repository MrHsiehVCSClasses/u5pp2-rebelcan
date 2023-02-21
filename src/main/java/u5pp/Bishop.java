package u5pp;

import static java.lang.Math.abs;

public class Bishop extends ChessPiece
{
    public Bishop(ChessPiece[][] board, int row, int col, boolean isWhite)
    {
        this.row=row;
        this.column=col;
        this.is_white=isWhite;
        this.board=board;
        this.is_blank=false;
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
            return "B";
        else
            return "b";
    }
    public boolean canMoveTo(int row, int col)
    {
        boolean check=true;

        //out of board
        if(row<0||row>=8||col<0||col>=8)
            return false;

        //same color at destination
        if(is_blocked(row,col))
            if(board[row][col].getIsWhite()==board[this.row][this.column].getIsWhite())
                return false;

        int x = this.column;
        int y = this.row;
        int targetX = col;
        int targetY = row;
        int xDiff = targetX - x;
        int yDiff = targetY - y;
        int xDiffAndYDiff = xDiff + yDiff;
        boolean isRange = abs(xDiff) - abs(yDiff) == 0;
        if(!isRange)
            return false;

        if(xDiffAndYDiff == 0)
        {
            if(xDiff>yDiff)
            {
                for(int i=1;i<xDiff;i++)
                {
                    if(is_blocked(y-i,x+i))
                    {
                        return false;
                    }
                }
            }
            else
            {
                for(int i=1;i<yDiff;i++)
                {
                    if(is_blocked(y+i,x-i))
                    {
                        return false;
                    }
                }
            }
        }
        else if(xDiffAndYDiff > 0)
        {
            for(int i=1;i<xDiff;i++)
            {
                if(is_blocked(y+i,x+i))
                {
                    return false;
                }
            }
        }
        else
        {
            for(int i=1;i<abs(xDiff);i++)
            {
                if(is_blocked(y-i,x-i))
                {
                    return false;
                }
            }
        }


        return check;
    }

}
