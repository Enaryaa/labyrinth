public class Position {

    public enum Direction {
        NORD(0, -1),
        SUD(0, 1),
        EST(1, 0),
        OUEST(-1, 0);

        //enumeration des deplacements de thesees possibles

        private Position position;

        Direction(int x, int y) {
            this.position = new Position(x, y);
        }

        public Position getPosition() {
            return position;
        }
    }

    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getNextPosition(Direction direction) {
        Position position = new Position(this.x, this.y);
        Position positionFromDirection = direction.getPosition();
        position.setX(position.getX() + positionFromDirection.getX());
        position.setY(position.getY() + positionFromDirection.getY());
        return position;
        //donne la futur position en fonction du deplacement choisi (nord, sud, est, ouest)
    }

    @Override
    public boolean equals(Object obj) {
        //ici on override le equals pour ne comparer que x ET y non l'instance de l'objet (voir internet)
        Position position = (Position) obj;
        return (this.x == position.getX() && this.y == position.getY());
    }
}
