// It knows the game rules

public class Game {

    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    public boolean isValidFrom(Position from) {
        if (board.isFilled(from)) {
            boolean[] possibleMoves = possibleDirectionsTo(from); // To Up, Down, Right, Left
            for (int i = 0; i < possibleMoves.length; ++i) {
                if (possibleMoves[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Assumes validFrom is a valid starting position
    public boolean isValidTo(Position validFrom, Position to) {
        if (board.isEmpty(to)) {
            return validFrom.colinear(to) && validFrom.distance(to) == 2;
        }
        return false;
    }

    // Assumes both positions are valid
    public Position move(Position validFrom, Position validTo) {
        board.emptyPosition(validFrom);
        board.fillPosition(validTo);
        board.emptyPosition(validFrom.middle(validTo));
        return validFrom.middle(validTo);
    }

    public boolean hasValidMovesFrom() {
        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                if (isValidFrom(new Position(j, i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countValidMovesFrom() {
        int validMovesFrom = 0;
        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                if (isValidFrom(new Position(j, i))) {
                    validMovesFrom += 1;
                }
            }
        }
        return validMovesFrom;
    }

    // Assumes validFrom is a valid starting position
    public int countValidMovesTo(Position validFrom) {
        int validMovesTo = 0;
        for (int i = 0; i < Direction.ALL.length; ++i) {
            Position validTo = Direction.ALL[i].apply2Times(validFrom);
            if (isValidTo(validFrom, validTo)) {
                validMovesTo += 1;
            }
        }
        return validMovesTo;
    }

    private boolean[] possibleDirectionsTo(Position pos) {
        boolean[] directions = new boolean[Direction.ALL.length];
        for (int i = 0; i < Direction.ALL.length; i++) {
            setValidDirections(directions, i, pos);
        }
        return directions;
    }

    private void setValidDirections(boolean[] directions, int index, Position pos) {
        Position posToKill = Direction.ALL[index].apply(pos);
        Position posToJump = Direction.ALL[index].apply2Times(pos);
        directions[index] = board.isFilled(posToKill) &&
                board.isEmpty(posToJump);
    }
}
