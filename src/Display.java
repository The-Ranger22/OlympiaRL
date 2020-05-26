import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

//
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Display implements Initializable {
    public AnchorPane anchor;
    public GridPane mapLayer;
    public GridPane objectLayer;
    private AssetLoader aLoader;
    private AreaMap map;
    private PlayerCharacter player;
    final static int DISPLAY_WIDTH = 15;
    final static int DISPLAY_HEIGHT = 15;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new AreaMap();
        aLoader = new AssetLoader();
        aLoader.loadDungeonTileset();
        //map.loadRandomMap(21, 21);
        map.loadTestMap();
        map.addEnemy(new EnemyCharacter(10, 10));
        createPlayer();
        updateMap();
    }
    @FXML
    public void handleKeyEvent(KeyEvent keyEvent) {
        String key = keyEvent.getCode().toString();
        if(key.equals("A") && player.getPosX() - 1 > -1){
            if(MapCollision.leftTileAccessible(player.getCurrentTile(), map.getTileLayer()[player.getPosY()][player.getPosX() - 1])){
                player.moveLeft();
            }
        }
        else if (key.equals("W") && player.getPosY() - 1 > 0){
            if(MapCollision.aboveTileAccessible(player.getCurrentTile(), map.getTileLayer()[player.getPosY() - 1][player.getPosX()])){
                player.moveUp();
            }
        }
        else if (key.equals("D") && player.getPosX() + 1 < map.getTileLayer()[player.getPosY()].length){
            if(MapCollision.rightTileAccessible(player.getCurrentTile(), map.getTileLayer()[player.getPosY()][player.getPosX() + 1])){
                player.moveRight();
            }
        }
        else if (key.equals("S") && player.getPosY() + 1 < map.getTileLayer().length){
            if(MapCollision.belowTileAccessible(player.getCurrentTile(), map.getTileLayer()[player.getPosY() + 1][player.getPosX()])){
                player.moveDown();
            }
        }
        try{
            player.setCurrentTile(map.getTileLayer()[player.getPosY()][player.getPosX()]);
            System.out.println(player.getCurrentTile());
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Outside of map!");
        }
        updateMap();
    }
    @FXML
    public void handleButtonPress(ActionEvent ev){
        System.out.println("BEEP");
        System.out.println(ev.getSource().toString());
    }

    //Methods for creating player, enemy and map objects

    //Create PlayerCharacter
    private void createPlayer(){
        player = new PlayerCharacter(map.getStartX(), map.getStartY()); //Add one to counter offset when drawing map
        player.setCurrentTile(map.getTileLayer()[map.getStartY()][map.getStartX()]);
        player.setxBoundary(map.xBoundary());
        player.setyBoundary(map.yBoundary());
    }

    private void createEnemy(){

    }

    private void updateMap(){
        clearMapLayer();
        clearObjectLayer();
        aLoader.drawMapLayer(mapLayer, map.getTileLayer(), player);
        aLoader.drawObjectLayer(objectLayer, player, map.getCharacterLayer());
    }



    private void clearMapLayer(){mapLayer.getChildren().clear();}
    private void clearObjectLayer(){objectLayer.getChildren().clear();}



}
