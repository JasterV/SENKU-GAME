import acm.graphics.GDimension;
import acm.graphics.GPoint;

public class Geometry {

    private final int windowWidth;
    private final int windowHeight;
    private final int numCols;
    private final int numRows;
    private final double boardPadding;
    private final double cellPadding;

    public Geometry(int windowWidth, int windowHeight, int numCols, int numRows, double boardPadding, double cellPadding) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.numCols = numCols;
        this.numRows = numRows;
        this.boardPadding = boardPadding;
        this.cellPadding = cellPadding;
    }

    public int getRows() {
        return numRows;
    }

    public int getColumns() {
        return numCols;
    }

    public GDimension boardDimension() {
        return new GDimension(
                windowWidth * (1 - boardPadding * 2),
                windowHeight * (1 - boardPadding * 2));
    }

    public GPoint boardTopLeft() {
        return new GPoint(
                windowWidth * boardPadding,
                windowHeight * boardPadding);
    }

    public GDimension cellDimension() {
        GDimension boardDimension = boardDimension();
        return new GDimension(
                boardDimension.getWidth() / numCols,
                boardDimension.getHeight() / numRows);
    }

    public GPoint cellTopLeft(int x, int y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new GPoint(
                boardTopLeft.getX() + cellDimension.getWidth() * x,
                boardTopLeft.getY() + cellDimension.getHeight() * y);
    }

    public GDimension tokenDimension() {
        GDimension cellDimension = cellDimension();
        return new GDimension(
                cellDimension.getWidth() * (1 - cellPadding * 2),
                cellDimension.getHeight() * (1 - cellPadding * 2));
    }

    public GPoint tokenTopLeft(int x, int y) {
        GPoint cellTopLeft = cellTopLeft(x, y);
        GDimension cellDimension = cellDimension();
        return new GPoint(
                cellTopLeft.getX() + cellDimension.getWidth() * cellPadding,
                cellTopLeft.getY() + cellDimension.getHeight() * cellPadding);
    }

    public GPoint centerAt(int x, int y) {
        GDimension tokenDimension = tokenDimension();
        GPoint tokenTopLeft = tokenTopLeft(x, y);
        return new GPoint(
                tokenTopLeft.getX() + tokenDimension.getWidth() / 2,
                tokenTopLeft.getY() + tokenDimension.getHeight() / 2);
    }

    public Position xyToCell(double x, double y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new Position(
                (int) ((x - boardTopLeft.getX()) / cellDimension.getWidth()),
                (int) ((y - boardTopLeft.getY()) / cellDimension.getHeight()));
    }
}
