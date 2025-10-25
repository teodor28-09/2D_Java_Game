package object;

import javax.imageio.ImageIO;
import java.io.IOException;

import main.GamePanel;
public class ObjectChest extends SuperObject {
    GamePanel gp;
    public boolean isOpen = false;

    public ObjectChest(GamePanel gp) {
        this.gp = gp;
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cufar_inchis.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }

    public void openChest() {
        gp.playSE(2);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cufar_deschis.png"));
            isOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

