import javafx.scene.image.Image;

public class PlayerCharacter extends GameCharacter {

    PlayerCharacter(){}

    PlayerCharacter(int startingX, int startingY){
        this.setPosX(startingX);
        this.setPosY(startingY);
        this.setCharacterImage(AssetLoader.loadPlayerToken());
    }

    public void moveUp(){
        if(this.getPosY() > 0) {
            this.decreaseY();

        }
    }
    public void moveDown(){
        if(this.getPosY() < this.getyBoundary() - 1) {
            this.increaseY();

        }
    }
    public void moveLeft(){
        if(this.getPosX() > 0) {
            this.decreaseX();

        }
    }
    public void moveRight(){
        if(this.getPosX() < this.getxBoundary() - 1) {
            this.increaseX();

        }
    }

    public void printCoordinates(){
        System.out.println("[" + this.getPosX() + "," + this.getPosY() + "]");
    }

    @Override
    String ConvertToString() {
        return null;
    }
}