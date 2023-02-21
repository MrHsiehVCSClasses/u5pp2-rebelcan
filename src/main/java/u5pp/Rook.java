package u5pp;

public class Rook extends ChessPiece
{
    public Rook(ChessPiece[][] board, int row, int col, boolean isWhite)
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
            return "R";
        else
            return "r";
    }
    public boolean canMoveTo(int row, int col)
    {
        boolean check=true;

        // check out of board
        if(row<0||row>=8||col<0||col>=8)
            return false;

        //same color at destination
        if(is_blocked(row,col))
            if(board[row][col].getIsWhite()==board[this.row][this.column].getIsWhite())
                return false;

        if(this.row==row||this.column==col)
        {
            if(this.row==row)//move X axis
            {
                if(this.column>col)//left
                {
                    for(int x=column-1;x>col;x--)
                    {
                        if(is_blocked(row,x))
                        {
                            return false;
                        }
                    }
                }
                else//right
                {
                    for(int x=column+1;x<col;x++)
                    {
                        if(is_blocked(row,x))
                        {
                            return false;
                        }
                    }
                }
            }
            else if(this.row>row)//move Y axis - lower
            {
                for(int y=this.row-1;y>row;y--)
                {
                    if(is_blocked(y,col))
                    {
                        return false;
                    }
                }
            }
            else//move Y axis - upper
            {
                for(int y=this.row+1;y<row;y++)
                {
                    if(is_blocked(y,col))
                    {
                        return false;
                    }
                }
            }
        }
        else
            check=false;

        return check;
    }

}
