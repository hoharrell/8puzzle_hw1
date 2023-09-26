import java.util.*;

public class Main {
    // A* functions go here
    public static void main(String[] args) {
        int[] arraysAtDepths = new int[12];
        // ArrayList<int[]> solutionList = new ArrayList<int[]>();
        // for (int i = 0; i < 12; i++) {
        // arraysAtDepths[i] = 0;
        // }
        int goodDepths = 0;
        while (goodDepths < 9) { // set to 8 because ignoring 2, 4, 6, and 8
            int[] board = convertStringToArray("123456789");
            BoardFunctions.shuffle(board, 9);

            // ensure goal state is reachable and edit board if not

            if (!BoardFunctions.checkBoard(board)) {
                if (board[0] == 9) {
                    int temp = board[1];
                    board[1] = board[2];
                    board[2] = temp;
                } else if (board[1] == 9) {
                    int temp = board[0];
                    board[0] = board[2];
                    board[2] = temp;
                } else {
                    int temp = board[0];
                    board[0] = board[1];
                    board[1] = temp;
                }
            }

            ArrayList<Integer> puzzleBoard = new ArrayList<Integer>();
            for (int i = 0; i < board.length; i++) {
                puzzleBoard.add(board[i]);
            }
            // A*
            // *******************************************************************************************

            // int[] solution = findSolution(new Puzzle(puzzleBoard, 0));
            // int depth = solution[2];
            // if (depth % 2 == 0 && depth <= 24 && depth != 0) {
            // if (arraysAtDepths[depth / 2 - 1] <= 99) {
            // if (arraysAtDepths[depth / 2 - 1] == 99) {
            // goodDepths++;
            // }
            // arraysAtDepths[depth / 2 - 1]++;
            // System.out.println(String.format("INSERT INTO h2Puzzle VALUES ('%s', '%d',
            // '%d', '%d');",
            // translateBoard(puzzleBoard), solution[0],
            // solution[1], solution[2]));
            // }
            // }

            // IDS
            // *******************************************************************************************

            int depthLimit = 12;
            int N_1 = 0;
            int N_2 = 0;
            for (int i = 1; i <= depthLimit; i++) {
                int[] solution = findSolutionIDS(new Puzzle(puzzleBoard, 0), i);
                N_1 += solution[0];
                N_2 += solution[1];
                int depth = solution[2];
                if (depth > 0 && depth % 2 == 0 && depth <= 24) {
                    if (arraysAtDepths[depth / 2 - 1] <= 99) {
                        if (arraysAtDepths[depth / 2 - 1] == 99) {
                            goodDepths++;
                        }
                        arraysAtDepths[depth / 2 - 1]++;
                        System.out.println(String.format("INSERT INTO IDSPuzzle VALUES ('%s', '%d', '%d', '%d');",
                                translateBoard(puzzleBoard), N_1,
                                N_2, solution[2]));
                        break;
                    }
                }
            }
        }
    }

    public static void solveAll(int n, int[] elements) {

        if (n == 1) {
            // solve with elements
        } else {
            for (int i = 0; i < n - 1; i++) {
                solveAll(n - 1, elements);
                if (n % 2 == 0) {
                    int tmp = elements[i];
                    elements[i] = elements[n - 1];
                    elements[n - 1] = tmp;
                } else {
                    int tmp = elements[0];
                    elements[0] = elements[n - 1];
                    elements[n - 1] = tmp;
                }
            }
            solveAll(n - 1, elements);
        }
    }

    public static int[] convertStringToArray(String arrayString) {
        int[] array = new int[arrayString.length()];
        for (int i = 0; i < arrayString.length(); i++) {
            array[i] = arrayString.charAt(i) - '0';
        }
        return array;
    }

    public static int findSuccessors(Puzzle board, PriorityQueue<Puzzle> puzzleStates) {
        int N_2 = 0;
        N_2 += addToQueue(board.swapUp(), puzzleStates);
        N_2 += addToQueue(board.swapRight(), puzzleStates);
        N_2 += addToQueue(board.swapDown(), puzzleStates);
        N_2 += addToQueue(board.swapLeft(), puzzleStates);
        return N_2;
    }

    public static int addToQueue(Puzzle board, PriorityQueue<Puzzle> puzzleStates) {
        int N_2 = 0;
        if (board != null) {
            puzzleStates.add(board);
            N_2++;
        }
        return N_2;
    }

    public static int[] findSolution(Puzzle initialBoard) {
        Set<ArrayList<Integer>> visitedStates = new HashSet<ArrayList<Integer>>();
        PriorityQueue<Puzzle> puzzleStates = new PriorityQueue<>(new CustomComparator());
        int N_1 = 1;
        int N_2 = 1;

        // add initial board to priority queue
        puzzleStates.add(initialBoard);

        while (puzzleStates.size() > 0) {
            // pop node from priority queue only keep going if its new

            Puzzle currentBoard = puzzleStates.poll();

            if (checkGoal(currentBoard.board)) {
                int[] success = { N_1, N_2, currentBoard.depth };
                // System.out.println(Arrays.toString(success));
                return success;
            }
            if (!visitedStates.contains(currentBoard.board)) {
                N_1++;
                // find successors & increment number of nodes
                N_2 += findSuccessors(currentBoard, puzzleStates);
                // for each successsor
                // depth = g of parent ++
                // Can add all nodes to pq without worrying about if priority is better
                // if goal state, return g (depth) & number of nodes
                // add node to pq
            }
            visitedStates.add(currentBoard.board);
        }
        int[] fail = { N_1, -N_2, -1 };
        return fail;
    }

    public static int[] findSolutionIDS(Puzzle initialBoard, int depthLimit) {
        Set<ArrayList<Integer>> visitedStates = new HashSet<ArrayList<Integer>>();
        Stack<Puzzle> puzzleStates = new Stack<>();
        int N_1 = 1;
        int N_2 = 1;

        // add initial board to priority queue
        puzzleStates.add(initialBoard);
        Puzzle currentBoard = initialBoard;
        while (puzzleStates.size() > 0) {
            // pop node from priority queue only keep going if its new

            currentBoard = puzzleStates.pop();

            if (checkGoal(currentBoard.board)) {
                int[] success = { N_1, N_2, currentBoard.depth };
                // System.out.println(Arrays.toString(success));
                return success;
            }
            if (!visitedStates.contains(currentBoard.board) && currentBoard.depth < depthLimit) {
                N_1++;
                // find successors & increment number of nodes
                N_2 += findSuccessorsIDS(currentBoard, puzzleStates);
                // for each successsor
                // depth = g of parent ++
                // Can add all nodes to pq without worrying about if priority is better
                // if goal state, return g (depth) & number of nodes
                // add node to pq
            }
            visitedStates.add(currentBoard.board);
        }
        int[] fail = { N_1, N_2, -1 };
        return fail;
    }

    public static int findSuccessorsIDS(Puzzle board, Stack<Puzzle> puzzleStates) {
        int N_2 = 0;
        N_2 += addToStackIDS(board.swapUp(), puzzleStates);
        N_2 += addToStackIDS(board.swapRight(), puzzleStates);
        N_2 += addToStackIDS(board.swapDown(), puzzleStates);
        N_2 += addToStackIDS(board.swapLeft(), puzzleStates);
        return N_2;
    }

    public static int addToStackIDS(Puzzle board, Stack<Puzzle> puzzleStates) {
        int N_2 = 0;
        if (board != null) {
            puzzleStates.add(board);
            N_2++;
        }
        return N_2;
    }

    public static boolean checkGoal(ArrayList<Integer> board) {
        for (int i = 1; i < 9; i++) {
            if (board.get(i) < board.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String translateBoard(ArrayList<Integer> board) {
        String translation = "";
        for (int i = 0; i < 9; i++) {
            String addOn = Integer.toString(board.get(i));
            translation += addOn;
        }
        return translation;
    }
}

class CustomComparator implements Comparator<Puzzle> {

    @Override
    public int compare(Puzzle board1, Puzzle board2) {

        // queue is ordered by f(n) = g(n) + h(n) = current path depth + result of
        // heuristic function
        int value1 = board1.depth + BoardFunctions.h1Calculator(board1.board); // change between h1Calculator and
                                                                               // h2Calculator
        int value2 = board2.depth + BoardFunctions.h1Calculator(board2.board); // change between h1Calculator and
        // h2Calculator

        if (value1 > value2) {
            return 1;
        } else if (value2 > value1) {
            return -1;
        } else {
            return 0;
        }
    }
}

class IDSBoardList {
    int[][] boardList;
}