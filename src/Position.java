public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean colinear(Position other) {
        return x == other.x ||
                y == other.y;
    }

    public int distance(Position other) {
        return Math.abs(x - other.x) +
                Math.abs(y - other.y);
    }

    public Position middle(Position other) {

        return new Position((x + other.x) / 2,
                            (y + other.y) / 2);
    }

    // Needed for testing

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
