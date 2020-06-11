package olympiaRL;

import java.awt.*;

public enum Terrain {
    WALL_TEST(TileType.VOID, Color.GRAY, false),
    FLOOR(TileType.VOID, null, true),
    FLOOR_TEMP(TileType.DOTS_STAGGERED, null, true)
    ;
    private TileType type;
    private Color color;
    private boolean passable;
    Terrain(TileType type, Color color, boolean passable){
        this.type = type;
        this.color = color;
        this.passable = passable;
    }

    public TileType getType() {
        return this.type;
    }

    public Color getColor() {
        return this.color;
    }
    public boolean isPassable(){
        return this.passable;
    }
}
