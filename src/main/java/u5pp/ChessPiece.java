package u5pp;

public class ChessPiece {

    private ChessPiece[][] board;
    private int row, col;
    private boolean isWhite;

    public ChessPiece(ChessPiece[][] board, int row, int col, boolean isWhite) {
        this.board = board;
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public boolean canMoveTo(int row, int col) {
        if(row == this.row && col == this.col) {
            // cannot move onto self
            return false;
        }
        if(row < 0 || row >= this.board.length || col < 0 || col >= this.board.length) {
            // cannot move out of bounds
            return false;
        }
        if(board[row][col] != null && board[row][col].getIsWhite() == this.isWhite) {
            // cannot move onto own team
            return false;
        }
        
        return true;
    }

    public void moveTo(int row, int col) {
        int oldRow = this.row;
        int oldCol = this.col;
        board[row][col] = this;
        board[oldRow][oldCol] = null;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return isWhite ? "X" : "x";
    }
}
