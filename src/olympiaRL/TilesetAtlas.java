package olympiaRL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TilesetAtlas {
    private static final String TILESET_PATH = "././assets/terminal.png";
    private static final Dimension IMAGE_DIM = new Dimension(8, 8);
    private static final int IMAGE_SCALED_DIM = 16;
    private BufferedImage tileset;
    private HashMap<String, ImageIcon> buffer;

    public TilesetAtlas() {
        try {
            tileset = initTileset();
        } catch (IOException e){
            System.out.println("Failed to load tileset");
            System.exit(-5);
        }
        buffer = new HashMap<>();
    }

    public ImageIcon drawTile(TileType tileType){
        return drawTile(tileType, null);
    }

    public ImageIcon drawTile(TileType tileType, Color color){
        //Form key using the name of the tileType and the RGB of the color
        String key = (tileType.name() + ((color != null) ? color.getRGB() : "_DEFAULT"));
        //If the requested tile is already in the buffer, return it
        if(!buffer.containsKey(key)){
            BufferedImage newTile = new BufferedImage(8, 8, tileset.getType());
            Graphics2D gr = newTile.createGraphics();
            gr.drawImage(tileset, 0, 0, IMAGE_DIM.width, IMAGE_DIM.height, tileType.getxStart(), tileType.getyStart(), tileType.getxStart() + IMAGE_DIM.width, tileType.getyStart() + IMAGE_DIM.height, null);
            gr.dispose();

            if(color != null){
                for (int y = 0; y < newTile.getHeight(); y++){
                    for(int x = 0; x < newTile.getWidth(); x++){
                        if(newTile.getRGB(x, y) == ((tileType == TileType.VOID) ? Color.BLACK.getRGB() : Color.WHITE.getRGB())){
                            newTile.setRGB(x, y, color.getRGB());
                        }
                    }
                }
            }

            buffer.put(key, new ImageIcon(newTile.getScaledInstance(IMAGE_SCALED_DIM, IMAGE_SCALED_DIM, Image.SCALE_DEFAULT)));

        }

        return buffer.get(key);

    }
    private static BufferedImage initTileset() throws IOException {
        FileInputStream tilesetSource = new FileInputStream(new File(TILESET_PATH));
        return ImageIO.read(tilesetSource);
    }




}
