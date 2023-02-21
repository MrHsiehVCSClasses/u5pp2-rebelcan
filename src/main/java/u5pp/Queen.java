package u5pp;

public class Queen extends ChessPiece {
    public Queen(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int r, int c) {
        if (super.canMoveTo(r, c) == false) {
            return false;
        }

        boolean rookMove = false;
        if (r == this.getRow() || c == this.getColumn()) {
            rookMove = true;
        }

        boolean bishopMove = false;
        int rDiff = Math.abs(r - this.getRow());
        int cDiff = Math.abs(c - this.getColumn());
        if (rDiff == cDiff) {
            bishopMove = true;
        }

        if (!rookMove && !bishopMove) {
            return false;
        }

        if (rookMove) {
            // must not go through another piece
            if (rDiff > 0) {
                // row move only
                int minRow = Math.min(r, getRow());
                for (int rr = minRow + 1; rr < minRow + rDiff; rr++) {
                    if (getBoard()[rr][c] != null) {
                        return false;
                    }
                }
            } else {
                // col move only
                int minCol = Math.min(c, getColumn());
                for (int cc = minCol + 1; cc < minCol + cDiff; cc++) {
                    if (getBoard()[r][cc] != null) {
                        return false;
                    }
                }
            }
            return true;
        }

        if (bishopMove) {
            // must not go through another piece
            int rMin = Math.min(r, getRow());
            int cMin = Math.min(c, getColumn());
            int cMax = Math.max(c, getColumn());
            for (int i = 1; i < rDiff; i++) {
                int rCheck = rMin + i;
                int cCheck;
                if (r > getRow() == c > getColumn()) {
                    cCheck = cMin + i;
                } else {
                    cCheck = cMax - i;
                }
                if (getBoard()[rCheck][cCheck] != null) {
                    return false;
                }
            }
            return true;
        }

        // should never get to this point
        return false;
    }

    @Override
    public String toString() {
        return getIsWhite() ? "Q" : "q";
    }
}
