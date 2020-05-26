public abstract class GameMap {
    private int mapWidth;
    private int mapHeight;

    private int getMapWidth(){
        return this.mapWidth;
    }
    private int getMapHeight(){
        return this.mapHeight;
    }

    private void setMapWidth(int mapWidth){this.mapWidth = mapWidth;}
    private void setMapHeight(int mapHeight){this.mapHeight = mapHeight;}
}