package u5pp;

import static java.lang.Math.abs;

public class Knight extends ChessPiece
{
    public Knight(ChessPiece[][] board, int row, int col, boolean isWhite)
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
            return "N";
        else
            return "n";
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

        int xDiff=abs(this.column-col);
        int yDiff=abs(this.row-row);

        //if(0<xDiff&&xDiff<=2&&0<yDiff&&yDiff<=2)
        if(xDiff+yDiff==3&&Math.abs(xDiff-yDiff)==1)
            check=true;
        else
            check=false;

        return check;
    }

}
