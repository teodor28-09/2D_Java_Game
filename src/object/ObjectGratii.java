package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ObjectGratii extends SuperObject {

    public ObjectGratii() {
        name = "Gratii";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gratii.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        solidArea = new Rectangle(0, 0, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void openGratii() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/spike1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
