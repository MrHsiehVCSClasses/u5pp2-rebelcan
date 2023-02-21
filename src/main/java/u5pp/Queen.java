package u5pp;

import static java.lang.Math.abs;

public class Queen extends ChessPiece
{
    public Queen(ChessPiece[][] board, int row, int col, boolean isWhite)
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
            return "Q";
        else
            return "q";
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

        int x = this.column;
        int y = this.row;
        int targetX = col;
        int targetY = row;
        int xDiff = targetX - x;
        int yDiff = targetY - y;
        int xDiffAndYDiff = xDiff + yDiff;

        //if it's not diagonal
        if(y!=targetY&&x!=targetX)
        {
            if(abs(xDiff)!=abs(yDiff))
                return false;
        }

        if(xDiffAndYDiff == 0)//right top to left bottom
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
        else if(xDiffAndYDiff > 0)//to left top
        {
            for(int i=1;i<xDiff;i++)
            {
                if(is_blocked(y+i,x+i))
                {
                    return false;
                }
            }
        }
        else//to right bottom
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
