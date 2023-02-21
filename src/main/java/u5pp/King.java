package u5pp;

public class King extends ChessPiece {
    public King(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int r, int c) {
        if(!super.canMoveTo(r, c)) {
            return false;
        }

        int rDiff = Math.abs(r - getRow());
        int cDiff = Math.abs(c - getColumn());

        if(rDiff > 1 || cDiff > 1) {
            return false; // can only move 1 in each direction
        }

        // can't move next to king of other color
        ChessPiece otherKing = getKingOfOtherColor();
        rDiff = Math.abs(r - otherKing.getRow());
        cDiff = Math.abs(c - otherKing.getColumn());
        if(rDiff <=1 && cDiff <=1) {
            return false;
        }

        return true;
    }

    // assumes there is only 1 king of each color
    // assumes the other king exists on board
    private ChessPiece getKingOfOtherColor() {
        ChessPiece[][] board = this.getBoard();
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                if(board[r][c] != null && 
                board[r][c] instanceof King &&
                board[r][c].getIsWhite() != this.getIsWhite()) {
                    return board[r][c];
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getIsWhite() ? "K" : "k";
    }
}
