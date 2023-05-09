package Model;

public class Piece {
    private boolean jump, enterWater, side;
    private String name, fileDir;
    private int rank, row, col;

    public Piece(String name, int rank, int row, int col, boolean jump, boolean enterWater, boolean side,
            String fileDir) {
        this.name = name;
        this.rank = rank;
        this.row = row;
        this.col = col;
        this.jump = jump;
        this.enterWater = enterWater;
        this.side = side;
        this.fileDir = fileDir;
    }

    // special getter
    public String getPieceName() {
        if (!side) {
            String text = String.valueOf(name.charAt(0)) + "0";
            return text;
        } else {
            String text = String.valueOf(name.charAt(0)) + "1";
            return text;
        }
    }

    // getter
    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean getJump() {
        return jump;
    }

    public boolean getEnterWater() {
        return enterWater;
    }

    public String getFileDir() {
        return fileDir;
    }
}
