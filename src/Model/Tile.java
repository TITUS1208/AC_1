package Model;

public class Tile {
    private boolean water, den, trap, side;
    private String name;
    private Piece piece;
    private boolean hasPiece = false;

    public Tile(String name, boolean water, boolean den, boolean trap, boolean side) {
        this.name = name;
        // this.piece = piece;
        this.water = water;
        this.den = den;
        this.trap = trap;
    }

    // setter
    public void setWater() {
        water = true;
        name = "W";
    }

    public void setDen(boolean side) {
        den = true;
        name = "D";
        this.side = side;
    }

    public void setTrap(boolean side) {
        trap = true;
        name = "T";
        this.side = side;
    }

    // getter
    public Piece getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }

    public boolean isWater() {
        return water;
    }

    public boolean isDen() {
        return den;
    }

    public boolean isTrap() {
        return trap;
    }

    public String toString() {
        return name;
    }

    public boolean getSide() {
        return side;
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
        hasPiece = true;
    }

    public void removePiece() {
        piece = null;
        hasPiece = false;
    }

    public boolean occupied() {
        return hasPiece;
    }
}
