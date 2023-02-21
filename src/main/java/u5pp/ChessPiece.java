package u5pp;

public class ChessPiece {

    protected ChessPiece[][] board;
    protected boolean is_white;
    protected int row=0;
    protected int column=0;
    protected boolean is_blank;
    protected boolean first_move;

    protected int []posx={-1,-1,0,1,1,1,0,-1};
    protected int []posy={0,-1,-1,-1,0,1,1,1};

    public ChessPiece()
    {

    }

    public ChessPiece(ChessPiece[][] board, int row, int col, boolean isWhite)
    {
        this.board=board;
        this.row=row;
        this.column=col;
        this.is_white=isWhite;
        this.is_blank=true;
    }

    public boolean is_canSelect(ChessPiece[][] board, int row, int col,boolean is_white)
    {
        this.board=board;
        if(!board[row][col].is_blank && board[row][col].getIsWhite()==is_white)
            return true;
        else
            return false;
    }
    public ChessPiece[][] getBoard()
    {
        return board.clone();
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
    public boolean canMoveTo(int row, int col)
    {
        return true;
    }
    public void moveTo(int row, int col)
    {
        //move
        board[row][col]= board[this.row][this.column];

        //remove previous board
        board[getRow()][getColumn()]=null;
        board[row][col].column=col;
        board[row][col].row=row;
        board[row][col].first_move=false;
    }

    public boolean is_blocked(int row, int col)
    {
        if(board[row][col]==null)
            return false;

        return !board[row][col].is_blank;
    }

    public String toString()
    {
        return " ";
    }
}


