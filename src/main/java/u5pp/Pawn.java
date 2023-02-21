package u5pp;

public class Pawn extends ChessPiece {
    private boolean firstMove = true;

    public Pawn(ChessPiece[][] board, int row, int col, boolean isWhite) {
        super(board, row, col, isWhite);
    }

    @Override
    public boolean canMoveTo(int r, int c) {
        if(super.canMoveTo(r, c) == false) {
            return false;
        }

        int direction = this.getIsWhite() ? 1 : -1;

        // allow taking if moving diagonal
        if(r == this.getRow() + direction &&
           Math.abs(c - getColumn()) == 1 &&
           getBoard()[r][c] != null && 
           getBoard()[r][c].getIsWhite() != getIsWhite()) {
            return true;
        }

        if(firstMove) {
            // allow moving 2 moves 
            if( c == this.getColumn() && 
                r == this.getRow() + direction * 2 &&
                getBoard()[r][c] == null ) {
                return true;
            }
        }

        if( c == this.getColumn() && 
            r == this.getRow() + direction &&
            getBoard()[r][c] == null) {
            return true;
        }

        return false;



    }

    @Override
    public void moveTo(int r, int c) {
        firstMove = false;
        super.moveTo(r,c);
    }

    @Override
    public String toString() {
        return getIsWhite() ? "P" : "p";
    }
}
