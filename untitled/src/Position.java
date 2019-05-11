/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Position {

    /**
     * enumeration des deplacements de thesee possibles
     */
    public enum Direction {
        NORD(0, -1),
        SUD(0, 1),
        EST(1, 0),
        OUEST(-1, 0);

        /**
         *
         */
        private Position position;

        /**
         *
         * @param x int
         * @param y int
         */
        Direction(int x, int y) {

            this.position = new Position(x, y);
        }

        /**
         *
         * @return Position
         */
        public Position getPosition() {
            return position;
        }
    }

    /**
     *
     */
    private int x, y;

    /**
     *
     * @param x int
     * @param y int
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param direction Direction
     * @return Position
     */
    public Position getNextPosition(Direction direction) {
        Position position = new Position(this.x, this.y);
        Position positionFromDirection = direction.getPosition();
        position.setX(position.getX() + positionFromDirection.getX());
        position.setY(position.getY() + positionFromDirection.getY());
        return position;
        //donne la futur position en fonction du deplacement choisi (nord, sud, est, ouest)
    }

    /**
     *
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        //ici on override le equals pour ne comparer que x ET y non l'instance de l'objet (voir internet)
        Position position = (Position) obj;
        return (this.x == position.getX() && this.y == position.getY());
    }
}
