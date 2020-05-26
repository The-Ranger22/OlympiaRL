
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;


public class AssetLoader {
    static final int TILE_SIZE = 64;
    static final int NUM_OF_TILE_TYPES = 16;
    private Image tile_floor, tile_blocker;
    private Image tile_fourway, tile_hall_h, tile_hall_v;
    private Image tile_tjunct0, tile_tjunct1, tile_tjunct2, tile_tjunct3;
    private Image tile_hallcap0, tile_hallcap1, tile_hallcap2, tile_hallcap3;
    private Image tile_hallbend0, tile_hallbend1, tile_hallbend2, tile_hallbend3;

    private Image token_player;

    AssetLoader(){}



    public static Image loadPlayerToken(){
        return new Image("file:assets/ts128px/tokens/token_player.png");
    }
    public void loadDungeonTileset(){
        loadTileset("dungeon_tileset");
    }
    public void loadTownTileset(){
        loadTileset("town_tileset");
    }
    private void loadTileset(String tileset){
        tile_floor = new Image("file:assets/ts128px/"+tileset+"/tile_floor.png");
        tile_blocker = new Image("file:assets/ts128px/"+tileset+"/tile_blocker.png");
        tile_fourway = new Image("file:assets/ts128px/"+tileset+"/tile_fourway.png");
        tile_hall_h = new Image("file:assets/ts128px/"+tileset+"/tile_hall_h.png");
        tile_hall_v = new Image("file:assets/ts128px/"+tileset+"/tile_hall_v.png");
        tile_tjunct0 = new Image("file:assets/ts128px/"+tileset+"/tile_tjunct0.png");
        tile_tjunct1 = new Image("file:assets/ts128px/"+tileset+"/tile_tjunct1.png");
        tile_tjunct2 = new Image("file:assets/ts128px/"+tileset+"/tile_tjunct2.png");
        tile_tjunct3 = new Image("file:assets/ts128px/"+tileset+"/tile_tjunct3.png");
        tile_hallcap0 = new Image("file:assets/ts128px/"+tileset+"/tile_hallcap0.png");
        tile_hallcap1 = new Image("file:assets/ts128px/"+tileset+"/tile_hallcap1.png");
        tile_hallcap2 = new Image("file:assets/ts128px/"+tileset+"/tile_hallcap2.png");
        tile_hallcap3 = new Image("file:assets/ts128px/"+tileset+"/tile_hallcap3.png");
        tile_hallbend0 = new Image("file:assets/ts128px/"+tileset+"/tile_hallbend0.png");
        tile_hallbend1 = new Image("file:assets/ts128px/"+tileset+"/tile_hallbend1.png");
        tile_hallbend2 = new Image("file:assets/ts128px/"+tileset+"/tile_hallbend2.png");
        tile_hallbend3 = new Image("file:assets/ts128px/"+tileset+"/tile_hallbend3.png");
        token_player = new Image("file:assets/ts128px/tokens/token_player.png");
    }
    public void drawMapLayer(GridPane root, int[][] keyMap, PlayerCharacter player){
        int gridY = 0;
        for(int yPos = player.getPosY() - 7; yPos < player.getPosY() + 8; yPos++){
            int gridX = 0;
            for (int xPos = player.getPosX() - 7; xPos < player.getPosX() + 8; xPos++){

                Text coordinates = new Text();

                ImageView imageView = new ImageView();
                imageView.setFitHeight(48);
                imageView.setFitWidth(48);

                if(yPos <= -1 || yPos >= keyMap.length){ imageView.setImage(tile_blocker); coordinates.setText("null");}
                else if(xPos <= -1 || xPos >= keyMap[yPos].length){ imageView.setImage(tile_blocker); coordinates.setText("null");}
                else {
                    coordinates.setText("[" + xPos + ", " + yPos + "]");
                    switch (keyMap[yPos][xPos]) {
                        case 0:
                            imageView.setImage(tile_blocker);
                            break;
                        case 1:
                            imageView.setImage(tile_fourway);
                            break;
                        case 2:
                            imageView.setImage(tile_hall_h);
                            break;
                        case 3:
                            imageView.setImage(tile_hall_v);
                            break;
                        case 4:
                            imageView.setImage(tile_tjunct0);
                            break;
                        case 5:
                            imageView.setImage(tile_tjunct1);
                            break;
                        case 6:
                            imageView.setImage(tile_tjunct2);
                            break;
                        case 7:
                            imageView.setImage(tile_tjunct3);
                            break;
                        case 8:
                            imageView.setImage(tile_hallcap0);
                            break;
                        case 9:
                            imageView.setImage(tile_hallcap1);
                            break;
                        case 10:
                            imageView.setImage(tile_hallcap2);
                            break;
                        case 11:
                            imageView.setImage(tile_hallcap3);
                            break;
                        case 12:
                            imageView.setImage(tile_hallbend0);
                            break;
                        case 13:
                            imageView.setImage(tile_hallbend1);
                            break;
                        case 14:
                            imageView.setImage(tile_hallbend2);
                            break;
                        case 15:
                            imageView.setImage(tile_hallbend3);
                            break;
                        case 16:
                            imageView.setImage(tile_floor);
                            break;
                        default:
                            imageView.setImage(tile_blocker);
                    }
                }
                if(gridX == 7 && gridY == 7) coordinates.setFill(Color.WHITE);
                root.add(imageView, gridX, gridY);
                root.add(coordinates, gridX, gridY);
                gridX++;
            }
            gridY++;
        }
    }
    public void drawObjectLayer(GridPane root, PlayerCharacter player, GameCharacter[][] arr){
        WritableImage filler = new WritableImage(1, 1);
        filler.getPixelWriter().setColor(0, 0, Color.TRANSPARENT);
        //set map
        for(int rY = 0; rY < Display.DISPLAY_HEIGHT; rY++){
            for(int rX = 0; rX < Display.DISPLAY_WIDTH; rX++){
                ImageView imageView = new ImageView();
                imageView.setFitHeight(48);
                imageView.setFitWidth(48);
                imageView.setImage(filler);
                root.add(imageView, rX, rY);
            }
        }
        //draw enemies//
        int gridY = 0;
        for(int yIndex = player.getPosY() - 7; yIndex < player.getPosY() + 8; yIndex++){
            int gridX = 0;
            for(int xIndex = player.getPosX() - 7; xIndex < player.getPosX() + 8; xIndex++){
                ImageView imageView = new ImageView();
                imageView.setFitHeight(48);
                imageView.setFitWidth(48);
                if(yIndex <= -1 || yIndex >= arr.length){ imageView.setImage(filler);}
                else if(xIndex <= -1 || xIndex >= arr[yIndex].length){ imageView.setImage(filler);}
                else {
                    if(arr[yIndex][xIndex] != null) imageView.setImage(arr[yIndex][xIndex].getCharacterImage());
                    else imageView.setImage(filler);
                }
                root.add(imageView, gridX, gridY);
                gridX++;
            }
            gridY++;
        }

        //draw player
        ImageView imageView = new ImageView();
        imageView.setFitHeight(48);
        imageView.setFitWidth(48);
        imageView.setImage(player.getCharacterImage());
        root.add(imageView, 7, 7);
    }
}
