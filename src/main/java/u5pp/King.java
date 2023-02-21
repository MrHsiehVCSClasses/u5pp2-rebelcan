package u5pp;

import static java.lang.Math.abs;

public class King extends ChessPiece
{
    public King(ChessPiece[][] board, int row, int col, boolean isWhite)
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
            return "K";
        else
            return "k";
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

        //Check Opposite King
        for(int i=0;i<8;i++)
        {
            int next_row=row+posx[i];
            int next_col=col+posy[i];

            if(next_row<0||next_row>=8||next_col<0||next_col>=8)
                continue;

            if(board[next_row][next_col]!=null)
            {
                if(board[next_row][next_col] instanceof King&&board[next_row][next_col].getIsWhite()!=board[this.row][this.column].getIsWhite())
                    return false;
            }
        }

        int xDiff=abs(this.column-col);
        int yDiff=abs(this.row-row);
        check = (0 <= xDiff && xDiff <= 1) && (0 <= yDiff && yDiff <= 1);

        return check;
    }

}

