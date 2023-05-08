package Model;

// import org.w3c.dom.ls.LSOutput;

public class Board {
    public Board() {
        Tile[][] board = new Tile[9][7];

        // initialize board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Tile("0", false, false, false, false);
            }
        }
        // initialize water
        for (int i = 3; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                if (j != 3) {
                    board[i][j].setWater();
                }
            }
        }
        // set trap
        // top side traps
        board[0][2].setTrap(false);
        board[0][4].setTrap(false);
        board[1][3].setTrap(false);
        // bottom side traps
        board[8][2].setTrap(true);
        board[8][4].setTrap(true);
        board[7][3].setTrap(true);

        // set top den
        board[0][3].setDen(false);
        // set bottom den
        board[8][3].setDen(true);

        Piece rat0 = new Piece("Rat", 1, 2, 0, false, true, false, "temp");
        Piece cat0 = new Piece("Cat", 2, 1, 5, false, false, false, "temp");
        Piece dog0 = new Piece("Dog", 3, 1, 1, false, false, false, "temp");
        Piece wolf0 = new Piece("Wolf", 4, 2, 4, false, false, false, "temp");
        Piece leopard0 = new Piece("Leopard", 5, 2, 2, false, false, false, "temp");
        Piece tiger0 = new Piece("Tiger", 6, 0, 6, true, false, false, "temp");
        Piece lion0 = new Piece("Lion", 7, 0, 0, true, false, false, "temp");
        Piece elephant0 = new Piece("Elephant", 8, 2, 6, false, true, false, "temp");

        Piece rat1 = new Piece("Rat", 1, 6, 6, false, true, true, "temp");
        Piece cat1 = new Piece("Cat", 2, 7, 1, false, false, true, "temp");
        Piece dog1 = new Piece("Dog", 3, 7, 5, false, false, true, "temp");
        Piece wolf1 = new Piece("Wolf", 4, 6, 2, false, false, true, "temp");
        Piece leopard1 = new Piece("Leopard", 5, 6, 4, false, false, true, "temp");
        Piece tiger1 = new Piece("Tiger", 6, 8, 0, true, false, true, "temp");
        Piece lion1 = new Piece("Lion", 7, 8, 6, true, false, true, "temp");
        Piece elephant1 = new Piece("Elephant", 8, 6, 0, false, true, true, "temp");

        Piece[][] pieces = { { rat0, cat0, dog0, wolf0, leopard0, tiger0, lion0, elephant0 },
                { rat1, cat1, dog1, wolf1, leopard1, tiger1, lion1, elephant1 } };

        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                board[piece.getRow()][piece.getCol()].addPiece(piece);
                // System.out.println(board[piece.getRow()][piece.getCol()].getPiece().getName());
            }
        }

        printBoard(board);
        System.out.println();
        printAnimal(board);
    }

    public static void printBoard(Tile[][] board) {
        for (Tile[] i : board) {
            for (Tile j : i) {
                System.out.print(j + "  ");
            }
            System.out.println("");
        }
    }

    public static void printAnimal(Tile[][] board) {
        for (Tile[] i : board) {
            for (Tile j : i) {
                if (j.occupied()) {
                    System.out.print(j.getPiece().getName().charAt(0) + "  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println("");
        }
    }

}
