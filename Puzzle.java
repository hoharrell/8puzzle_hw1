public class Puzzle {
    private int[] board;
    private int blankSpot;

    public Puzzle(int[] board, int blankSpot) {
        this.board = board;
        this.blankSpot = blankSpot;
    }

    public void swapRight() {
        this.board[this.blankSpot] = this.board[this.blankSpot - 1];
        this.board[this.blankSpot - 1] = 9;
        this.blankSpot--;
    }

    public void swapLeft() {
        this.board[blankSpot] = this.board[this.blankSpot + 1];
        this.board[this.blankSpot + 1] = 9;
        this.blankSpot++;
    }

    public void swapDown() {
        this.board[this.blankSpot] = this.board[this.blankSpot - 3];
        this.board[this.blankSpot - 3] = 9;
        blankSpot -= 3;
    }

    public void swapUp() {
        this.board[blankSpot] = this.board[blankSpot + 3];
        this.board[blankSpot + 3] = 9;
        blankSpot += 3;
    }
    // boardid finder
}

// slightly off because not starting at 0
// % 3 != 0 --> right
// % 3 != 2 ---> left
// <= 5 --> down
// >= 3 --> up