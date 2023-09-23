public class Puzzle {
    public int[] board;
    public int blankSpot;
    public int depth;

    public Puzzle(int[] board, int depth) {
        this.board = board.clone();
        this.depth = depth;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 9) {
                this.blankSpot = i;
            }
        }
    }

    public Puzzle swapRight() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot % 3 != 0) {
            newPuzzle.board[this.blankSpot] = newPuzzle.board[this.blankSpot - 1];
            newPuzzle.board[this.blankSpot - 1] = 9;
            newPuzzle.blankSpot--;
            return newPuzzle;
        } else {
            System.out.println("Error: Cannot Swap Right");
            return null;
        }
    }

    public Puzzle swapLeft() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot % 3 != 2) {
            newPuzzle.board[blankSpot] = newPuzzle.board[this.blankSpot + 1];
            newPuzzle.board[this.blankSpot + 1] = 9;
            newPuzzle.blankSpot++;
            return newPuzzle;
        } else {
            System.out.println("Error: Cannot Swap Left");
            return null;
        }
    }

    public Puzzle swapDown() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot >= 3) {
            newPuzzle.board[this.blankSpot] = newPuzzle.board[this.blankSpot - 3];
            newPuzzle.board[this.blankSpot - 3] = 9;
            newPuzzle.blankSpot -= 3;
            return newPuzzle;
        } else {
            System.out.println("Error: Cannot Swap Down");
            return null;
        }
    }

    public Puzzle swapUp() {
        Puzzle newPuzzle = new Puzzle(this.board, this.depth + 1);
        if (this.blankSpot <= 5) {
            newPuzzle.board[blankSpot] = newPuzzle.board[blankSpot + 3];
            newPuzzle.board[blankSpot + 3] = 9;
            newPuzzle.blankSpot += 3;
            return newPuzzle;
        } else {
            System.out.println("Error: Cannot Swap Up");
            return null;
        }
    }
}

// % 3 != 0 --> right
// % 3 != 2 ---> left
// <= 5 --> down
// >= 3 --> up