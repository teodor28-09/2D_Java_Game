package object;


import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import main.GamePanel;


public class ObjectHeart extends SuperObject{

    GamePanel gp;

    static UtilityTool uTool = new UtilityTool();

    public ObjectHeart(GamePanel gp)
    {
        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/inima_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/inima_jumate.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/inima_goala.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = false;
    }
}