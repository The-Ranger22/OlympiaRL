package olympiaRL;

import java.awt.*;

public class PlayerCharacter extends GenericCharacter{

    public PlayerCharacter(){
        this.setToken(TileType.AT_SYMBOL);
        this.setColor(Color.YELLOW);
    }

    //move player up
    public void moveUp(){
        this.getLocalMap().moveCharacter(this, 'U');
    }
    //move player down
    public void moveDown(){
        this.getLocalMap().moveCharacter(this, 'D');
    }
    //move player right
    public void moveRight(){
        this.getLocalMap().moveCharacter(this, 'R');
    }
    //move player left
    public void moveLeft(){
        this.getLocalMap().moveCharacter(this, 'L');
    }



}
