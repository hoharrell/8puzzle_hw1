public class Puzzle {
    private int[] board;
    private int blankSpot;

    public Puzzle(int[] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 9) {
                this.blankSpot = i;
            }
        }
    }

    public void swapRight() {
        if (this.blankSpot % 3 != 0) {
            this.board[this.blankSpot] = this.board[this.blankSpot - 1];
            this.board[this.blankSpot - 1] = 9;
            this.blankSpot--;
        } else {
            System.out.println("Error: Cannot Swap Right");
        }
    }

    public void swapLeft() {
        if (this.blankSpot % 3 != 2) {
            this.board[blankSpot] = this.board[this.blankSpot + 1];
            this.board[this.blankSpot + 1] = 9;
            this.blankSpot++;
        } else {
            System.out.println("Error: Cannot Swap Left");
        }
    }

    public void swapDown() {
        if (this.blankSpot >= 3) {
            this.board[this.blankSpot] = this.board[this.blankSpot - 3];
            this.board[this.blankSpot - 3] = 9;
            blankSpot -= 3;
        } else {
            System.out.println("Error: Cannot Swap Down");
        }
    }

    public void swapUp() {
        if (this.blankSpot <= 5) {
            this.board[blankSpot] = this.board[blankSpot + 3];
            this.board[blankSpot + 3] = 9;
            blankSpot += 3;
        } else {
            System.out.println("Error: Cannot Swap Up");
        }
    }
}

// slightly off because not starting at 0
// % 3 != 0 --> right
// % 3 != 2 ---> left
// <= 5 --> down
// >= 3 --> up