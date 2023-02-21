package u5pp;

public class Knight extends ChessPiece {
    public Knight(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int r, int c) {
        if(!super.canMoveTo(r, c)) {
            return false;
        }

        // can only move to spaces that are an L shape away, exactly 3 spaces
        int rDiff = Math.abs(r - getRow());
        int cDiff = Math.abs(c - getColumn());

        if(rDiff + cDiff != 3) {
            return false;
        }
        if(rDiff <= 0 || cDiff <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return getIsWhite() ? "N" : "n";
    }
}
