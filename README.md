# u5pp - Chess

## Overview

### Files

- `Chess.java` - The main game loop, various game logic, UI
- `ChessPiece.java` - Parent class of all the chess pieces
  - `King.java`
  - `Queen.java`
  - `Bishop.java`
  - `Knight.java`
  - `Rook.java`
  - `Pawn.java`
- `InputHelper.java` - you will augment this class to take in chess notation input

## Chess

You will be implementing a simplified version of chess. The rules are given in the sections below.  

If you already know chess rules, our version has the following differences:

- no check/checkmate/stalemate
- no en passant
- no castling
- no pawn promotion.
- Always viewed with white at top, and black at bottom
- Games end when one side has no kings

The `Chess` class has multiple helper functions already made for you. Use these to implement the main game loop in `Chess.play()`.  

## General Chess Rules

*Italicized* rules are different from normal chess.

- Piece cannot move out of bounds
- pieces cannot move through other pieces (except the knight).
- piece cannot move onto a piece of the same color
- piece moving onto a piece of the other color is a 'take', and removes that other piece from the game
- *game is decided when one color does not have a king on board. Winner is the one with at least 1 king left*
- White pieces are represented with capital letters, black pieces with lower case
- rows are labeled 1 to 8
- columns are labeled a-h
- White pieces start in rows 1 and 2. Black pieces start in rows 7 and 8.
- *White pieces are denoted by uppercase letters, black by lowercase letters*

### Starting board arrangement

``` ASCII_ART
    a   b   c   d   e   f   g   h  
  ┌───┬───┬───┬───┬───┬───┬───┬───┐
1 │ R │ N │ B │ Q │ K │ B │ N │ R │ 1
  ├───┼───┼───┼───┼───┼───┼───┼───┤
2 │ P │ P │ P │ P │ P │ P │ P │ P │ 2
  ├───┼───┼───┼───┼───┼───┼───┼───┤
3 │   │   │   │   │   │   │   │   │ 3
  ├───┼───┼───┼───┼───┼───┼───┼───┤
4 │   │   │   │   │   │   │   │   │ 4
  ├───┼───┼───┼───┼───┼───┼───┼───┤
5 │   │   │   │   │   │   │   │   │ 5
  ├───┼───┼───┼───┼───┼───┼───┼───┤
6 │   │   │   │   │   │   │   │   │ 6
  ├───┼───┼───┼───┼───┼───┼───┼───┤
7 │ p │ p │ p │ p │ p │ p │ p │ p │ 7
  ├───┼───┼───┼───┼───┼───┼───┼───┤
8 │ r │ n │ b │ q │ k │ b │ n │ r │ 8
  └───┴───┴───┴───┴───┴───┴───┴───┘
    a   b   c   d   e   f   g   h  
```

## Piece Movement Rules

``` ASCII_ART
┌───┬───┬───┬───┬───┐ ┌───┬───┬───┬───┬───┐ ┌───┬───┬───┬───┬───┐┌───┬───┬───┬───┬───┐ 
│   │   │   │   │   │ │   │ x │   │ x │   │ │   │   │   │   │   ││   │   │   │   │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤├───┼───┼───┼───┼───┤
│   │ x │ x │ x │   │ │ x │   │   │   │ x │ │   │   │   │   │   ││   │ * │ o │ * │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤├───┼───┼───┼───┼───┤
│   │ x │ K │ x │   │ │   │   │ N │   │   │ │   │   │ P │   │   ││   │   │ p │   │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤├───┼───┼───┼───┼───┤
│   │ x │ x │ x │   │ │ x │   │   │   │ x │ │   │ * │ o │ * │   ││   │   │   │   │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤├───┼───┼───┼───┼───┤
│   │   │   │   │   │ │   │ x │   │ x │   │ │   │   │   │   │   ││   │   │   │   │   │
└───┴───┴───┴───┴───┘ └───┴───┴───┴───┴───┘ └───┴───┴───┴───┴───┘└───┴───┴───┴───┴───┘
┌───┬───┬───┬───┬───┐ ┌───┬───┬───┬───┬───┐ ┌───┬───┬───┬───┬───┐ 
│ x │   │ x │   │ x │ │ x │   │   │   │ x │ │   │   │ x │   │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤
│   │ x │ x │ x │   │ │   │ x │   │ x │   │ │   │   │ x │   │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤
│ x │ x │ Q │ x │ x │ │   │   │ B │   │   │ │ x │ x │ R │ x │ x │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤
│   │ x │ x │ x │   │ │   │ x │   │ x │   │ │   │   │ x │   │   │
├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤ ├───┼───┼───┼───┼───┤
│ x │   │ x │   │ x │ │ x │   │   │   │ x │ │   │   │ x │   │   │
└───┴───┴───┴───┴───┘ └───┴───┴───┴───┴───┘ └───┴───┴───┴───┴───┘
┌───┐
│ x │ is a valid move anytime
└───┘
┌───┐
│ * │ is a valid move only when taking
└───┘
┌───┐
│ o │ is a valid move only when not taking
└───┘
```

- King
  - represented with a `K` or a `k`
  - can only move 1 square in any of the 8 directions
  - **cannot move to a square next to another king**
- Pawn:
  - represented with a `P` or `p`
  - can move straight forward 1 space, and cannot take when doing so
  - can move forward diagonally, only when taking
  - forward is defined as away from the side of the board where it started
    - black pawns only move to lower number rows
    - white pawns only move to higher number rows
  - **if a pawn has never taken a move, it can move 2 squares directly forward** (cannot take with this move)
- Rook
  - represented with a `R` or a `r`
  - can move in any of the 4 orthogonal directions (right, left, up, down) any number of squares
- Bishop
  - represented with a `B` or a `b`
  - can move in any diagonal direction any number of squares
- Queen
  - represented with a `Q` or a `q`
  - can move in a straight line in any of the 8 directions (rook + bishop), any number of squares
- Knight
  - represented with a `N` or an `n` (because `k` is taken by the king)
  - **can jump over pieces**
  - can move 2 squares in any orthogonal direction, then one square at a 90 degree angle from the first move. See example above.

## Implementation

### `Chess.java`

In the `Chess` class, you will implement the following `public` methods:

- `void play(Scanner scanner)` - the main game loop. Example output in `ChessExample.txt`. You are allowed to make it a 2 player or 1 player game (vs. computer). Ideas for simple chess AI are at the bottom of this README.
- `int getWinner(ChessPiece[][] board)` - a `static` method that returns `1` if white has won, returns `-1` if black has won, and `0` otherwise. A side wins if the other side does not have a king. If both sides have the same number of kings, the function should return `0`.

### `ChessPiece.java`

You will create a `ChessPiece.java`. All the other pieces will inherit from this class.  
Any functionality that is shared between most of the types of pieces should be placed in this class.  
The class must include the following methods:

- `public ChessPiece(ChessPiece[][] board, int row, int col, boolean isWhite)`
- `public ChessPiece[][] getBoard()`
- `public int getRow()`
- `public int getColumn()`
- `public boolean getIsWhite()`
- `public boolean canMoveTo(int row, int col)` which returns true if the indicated space is a valid move for this piece, false otherwise. Does not alter the board.
- `public void moveTo(int row, int col)` which moves the piece to the indicated space.
- `public String toString()` to be implemented by each child class, based on how they are to be represented

### Specific Chess Pieces

You will also implement all the pieces as child classes of `ChessPiece`.  
Each child class should have a constructor, and override `canMoveTo()` and `toString()`.
You might also have to override `toMove()`.

### `InputHelper.java`

You will add the following method to the `InputHelper` class:

- `public int[] getChessLocation(String prompt)` - repeats the prompt until the user has inputted a correctly formatted chess location, such as 'a1' or 'h8'. Returns an `int` array of size 2, where the first element is the row (0-7) and the second element is the col (0-7).
  - `a1` should return `[0,0]`
  - `a8` should return `[7,0]`
  - `h1` should return `[0,7]`

### Recommended implementation order

1. Fix the project so it compiles. There are couple ways to do this.
     - Dummy version of every class, with nearly empty version of every method, just so that everything compiles
     - Comment out the tests that you won't be doing yet. Uncomment as you go.
2. `ChessPiece.java` (until `mvn package` is success for all related tests)
3. InputHelper `getChessLocation`
4. basic version of `Chess.play()` to check your `InputHelper.getChessLocation()`, initializing the board, and moving pieces around
     - keep using this function to test in between/during all future steps
5. `toString()` for all the piece classes
6. `Chess.getWinner()`
7. `Chess.play()` (this can be completely finished even if your pieces aren't 100% done)
8. All the other pieces

### Hints

- the `play()` method is pretty large. Try method decomposition (making smaller methods) to break up the work, and to make it easier to read/write.
  - Example helper methods: `initializeBoard()`, `doPlayerMove()`, `doComputerMove()`.
  - Mr. Hsieh was able to create a `play()` method that was ~50 lines long, by using the helper methods above.
- Test as frequently as possible - every time you finish a segment of code that you can test (a line or two), just throw in a `println()` as a quick sanity check. Managing complexity this way will save **so** much debugging time in the long run.
  
## Extra Credit - Skipped chess rules

Extra credit for each additional rule you implement.
Sorted roughly from easiest to hardest.  

- castling
- en passant
- pawn promotion
- Rules that require calculating the future (check, checkmate, stalemate, & related rules)
  - king may not move into check, and check must be ended during your turn
    - Calculating check can be done by precomputing all possible moves for all pieces 1 turn into the future
    - Can also be done by keeping a "heat map" of what spaces are currently dangerous to each player. Heat map is updated every time a piece moves.
  - Game is a draw when there is a stalemate
    - Stalemate occurs when one side has no more legal moves
    - requires calculating all possible moves for a player
  - Game ends when there is a checkmate
    - Calculating checkmate requires precomputing all possible moves for all pieces, 2 turns into the future
- Rules the require keeping a record of the past
  - stalemate when the 3 same moves are repeated on both sides. Need to remember the state of the board for the past ~5 turns

## Extra Credit - Chess AI

Some ideas for a chess AI, sorted roughly from simplest to hardest

- Randomly select a piece, and randomly select a square. Keep trying until it works.
- Check if there is a way to win in 1 move (take the king) do this if possible
- Prioritize taking enemy pieces, from most valuable to least (king, queen, rook, bishop/knight, pawn)
- Prioritize moving pieces that would be taken. Move the most important pieces first.
- Prioritize moving pieces to squares that are safe.
- check if there is a way to win in 2 moves. Then, try this if possible

### Grading Breakdown

- Formatting/Indentation: 2 points
- All Code is DRY: 2 points
- All Code Properly JavaDoc'ed: 2 points
- no `public` methods/attributes besides the ones specified: 2 point
- Passes All Test Cases: 9 points (1 pt per class)
- Manual testing of chess game loop: 1 points
- UX/UI: 2 point

Total: 20 points
