import java.util.StringTokenizer;

// Only a rectangle of cells. Do not know game rules.

public class Board {

    private final int width;
    private final int height;
    private final Cell[][] cells;

    public Board(int width, int height, String board) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
        initializeCells(board);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isForbidden(Position pos) {
        return outOfBounds(pos) ||
                cells[pos.getY()][pos.getX()].isForbidden();
    }

    public boolean isFilled(Position pos) {
        return !outOfBounds(pos) &&
                cells[pos.getY()][pos.getX()].isFilled();
    }

    public boolean isEmpty(Position pos) {
        return !outOfBounds(pos) &&
                cells[pos.getY()][pos.getX()].isEmpty();
    }

    public void fillPosition(Position pos) {
        if (!isFilled(pos)) {
            cells[pos.getY()][pos.getX()] = Cell.FILLED;
        }
    }

    public void emptyPosition(Position pos) {
        if (!isEmpty(pos)) {
            cells[pos.getY()][pos.getX()] = Cell.EMPTY;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                sb.append(cells[y][x].toString());
            }
            if (y != height - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private void initializeCells(String board) {
        StringTokenizer boardLine = new StringTokenizer(board, "\n");
        for (int row = 0; boardLine.hasMoreTokens() && row < cells.length; ++row) {
            addCells(boardLine.nextToken(), row);
        }
    }

    private void addCells(String token, int row) {
        for (int col = 0; col < token.length() && col < cells[row].length; ++col) {
            char cellType = token.charAt(col);
            cells[row][col] = Cell.fromChar(cellType);
        }
    }

    private boolean outOfBounds(Position pos) {
        return pos.getX() < 0 || pos.getX() >= width ||
                pos.getY() < 0 || pos.getY() >= height;
    }
}
