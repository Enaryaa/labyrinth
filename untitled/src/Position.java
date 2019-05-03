public class Position {
    private int x;
    private int y;

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

    @Override
    public boolean equals(Object obj) {
        //ici on override le equals pour ne comparer que x ET y non l'instance de l'objet (voir detail sur internet)
        Position position = (Position) obj;
        return (this.x == position.getX() && this.y == position.getY());
    }
}
