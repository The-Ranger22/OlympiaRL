package olympiaRL;

import java.awt.*;

public abstract class GameEntity{
    private LocalMap localMap;
    private Coordinates coordinates;
    private TileType token;
    private Color color;
    public int getX() {
        return this.coordinates.xCoord;
    }
    public int getY(){
        return this.coordinates.yCoord;
    }


    Coordinates getCoordinates(){
        return this.coordinates;
    }

    void setCoordinates(int x, int y) {

        this.coordinates = new Coordinates(x, y);
    }

    public TileType getToken() {
        return token;
    }

    public void setToken(TileType token) {
        this.token = token;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LocalMap getLocalMap() {
        return localMap;
    }

    public void setLocalMap(LocalMap localMap) {
        this.localMap = localMap;
    }

    class Coordinates{
        private int xCoord;
        private int yCoord;
        //TODO: private int zCoord;

        protected Coordinates(){
            this.xCoord = 0;
            this.yCoord = 0;
        }

        Coordinates(int xCoord, int yCoord){
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }

        int getxCoord(){
            return this.xCoord;
        }
        int getyCoord(){
            return this.yCoord;
        }

        void increaseXCoord(){
            this.xCoord++;
        }
        void decreaseXCoord(){
            this.xCoord--;
        }
        void increaseYCoord(){
            this.yCoord++;
        }
        void decreaseYCoord(){
            this.yCoord--;
        }

    }
}
