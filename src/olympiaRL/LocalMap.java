package olympiaRL;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class LocalMap extends GenericMap {
    private int height;
    private int width;

    private PlayerCharacter player;

    private Terrain[][] mapLayer;

    private HashMap<Integer, ArrayList<GameEntity>> actors; //TODO: Create tailored HashMap for <Coordinates, List<GameEntity>> | Map of maps? -> Map<Coordinates, Map<GameEntities>



    public LocalMap(int mapWidth, int mapHeight) {
        width = mapWidth;
        height = mapHeight;
        mapLayer = new Terrain[height][width];
        actors = new HashMap<>();

        player = new PlayerCharacter();
        player.setLocalMap(this);
        addCharacter(player, width/2, height/2);
    }


    public void addCharacter(GameEntity entity, int xPos, int yPos) {
        entity.setCoordinates(xPos, yPos);
        int xyKey = generateKey(xPos, yPos);
        if (!actors.containsKey(xyKey)) {
            actors.put(xyKey, new ArrayList<>());
        }
        actors.get(xyKey).add(entity);
    }

    public boolean removeCharacter(GameEntity entity, int x, int y){
        int key = generateKey(x, y);
        return actors.get(key).remove(entity);
    }


    public GameEntity getCharacter(int x, int y){
        int key = generateKey(x, y);
        if(!actors.containsKey(key)){
            return null;
        }
        return actors.get(key).get(0);
    }


    public ArrayList<GameEntity> getCharacters(int x, int y) {
        if(!actors.containsKey(generateKey(x, y))){
            return null;
        }
        return actors.get(generateKey(x, y));
    }

    public boolean isCharacterPresent(int x, int y){
        return actors.containsKey(generateKey(x, y));
    }
    public int getHeight() {
        return height;
    }

    public void moveCharacter(GameEntity entity, char direction){
        int key = generateKey(entity.getX(), entity.getY());
        actors.get(key).remove(entity); //Removes the GameEntity from the list it currently inhabits
        if(actors.get(key).isEmpty()){
            actors.remove(key);
        }
        switch(direction){
            case 'U':{
                if(isPassible(entity.getX(), entity.getY() - 1)) entity.getCoordinates().decreaseYCoord();

                break;
            }
            case 'D':{
                if(isPassible(entity.getX(), entity.getY() + 1)) entity.getCoordinates().increaseYCoord();
                break;
            }
            case 'R':{
                if(isPassible(entity.getX() + 1, entity.getY())) entity.getCoordinates().increaseXCoord();
                break;
            }
            case 'L':{
                if(isPassible(entity.getX() - 1, entity.getY())) entity.getCoordinates().decreaseXCoord();
                break;
            }
        }

        key = generateKey(entity.getX(), entity.getY());

        if (!actors.containsKey(key)) {
            actors.put(key, new ArrayList<>());
        }
        actors.get(key).add(entity);

    }

    public int getWidth() {
        return width;
    }

    public PlayerCharacter getPlayer(){
        return player;
    }

    public Terrain[][] getTerrain() {
        return mapLayer;
    }

    private static int generateKey(int x, int y) {
        return (y * GameWindow.VIEW_X) + x;
    }
    private boolean isPassible(int x, int y){
        System.out.println(mapLayer[y][x]);
        return mapLayer[y][x].isPassable();
    }

    public void testMap(){
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(x == 0 || y == 0 || x == width - 1 || y == height - 1){
                    mapLayer[y][x] = Terrain.WALL_TEST;
                } else {

                    mapLayer[y][x] = (((y + x) % 2 == 0) ? Terrain.FLOOR : Terrain.FLOOR_TEMP);
                }
            }
        }
    }

}
