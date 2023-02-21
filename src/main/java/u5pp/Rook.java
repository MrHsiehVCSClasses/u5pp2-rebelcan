package u5pp;

public class Rook extends ChessPiece {
    public Rook(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    @Override 
    public boolean canMoveTo(int r, int c) {
        if(!super.canMoveTo(r, c)) {
            return false;
        }

        int rDiff = Math.abs(r - getRow());
        int cDiff = Math.abs(c - getColumn());

        // must be orthogonal
        if (rDiff != 0 && cDiff != 0) {
            return false;
        }

        // must not go through another piece
        if(rDiff > 0) {
            // row move only
            int minRow = Math.min(r, getRow());
            for(int rr = minRow +1 ; rr < minRow + rDiff; rr++) {
                if(getBoard()[rr][c] != null) {
                    return false;
                }
            }
        } else {
            // col move only
            int minCol = Math.min(c, getColumn());
            for(int cc = minCol +1 ; cc < minCol + cDiff; cc++) {
                if(getBoard()[r][cc] != null) {
                    return false;
                }
            }
        }
        return true;
        
    }

    @Override
    public String toString() {
        return getIsWhite() ? "R" : "r";
    }
}
