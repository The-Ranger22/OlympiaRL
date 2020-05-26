import javafx.scene.image.Image;

public abstract class GameCharacter extends GameObject {
    private Image characterImage;
    private int posX, posY;
    private int currentTile;
    private int xBoundary;
    private int yBoundary;
    int health, mana, experience;

    public int getPosX(){
        return this.posX;
    }
    public int  getPosY(){
        return this.posY;
    }
    public int getCurrentTile(){return this.currentTile;}
    public Image getCharacterImage(){return this.characterImage;}
    public int getxBoundary(){
        return this.xBoundary;
    }
    public int getyBoundary(){
        return this.yBoundary;
    }

    public void setPosX(int newX){
        this.posX = newX;
    }
    public void setPosY(int newY){
        this.posY = newY;
    }
    public void setCurrentTile(int tile){this.currentTile = tile;}
    public void setCharacterImage(Image image){
        this.characterImage = image;
    }
    public void setxBoundary(int xBoundary){this.xBoundary = xBoundary;}
    public void setyBoundary(int yBoundary){this.yBoundary = yBoundary;}

    public void increaseX(){
        this.posX++;
    }
    public void decreaseX(){
        this.posX--;
    }

    public void increaseY(){
        this.posY++;
    }
    public void decreaseY(){
        this.posY--;
    }








}
