package com.labyrinthe.algorithme;

/**
 * la classe <code>Position</code> contient également l'enumeration <code>Direction</code>
 * elle permet de gérer la position de Thésée et la prochaine cellule en fonction
 * de la direction choisie
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Position {

    /**
     * enumeration des deplacements de thesee possibles
     * en fonction des directions
     */
    public enum Direction {
        NORD(0, -1),
        SUD(0, 1),
        EST(1, 0),
        OUEST(-1, 0);

        /**
         *composante Position
         */
        private Position position;

        /**
         *Constructeur Direction qui instancie
         * Position
         * @param x int
         * @param y int
         */
        Direction(int x, int y) {

            this.position = new Position(x, y);
        }

        /**
         *retourne la position d'une cellule
         * @return Position
         */
        public Position getPosition() {
            return position;
        }
    }

    /**
     *composante horizontale et verticale  des directions
     */
    private int x, y;

    /**
     *Constructeur qui rend publique la composante horizontale et verticale
     * @param x int
     * @param y int
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *permet de récupérer la composante horizontale
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     *permet de set la composante horizontale
     * @param x int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *permet de récupérer la composante verticale
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     *permet de set la composante verticale
     * @param y int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *Permet de récupérer la position de la prochaine cellule où Thésee va se rendre
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

    @Override
    public boolean equals(Object obj) {
        Position position = (Position) obj;
        return (this.x == position.getX() && this.y == position.getY());
    }
}
