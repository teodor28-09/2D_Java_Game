package object;

import javax.imageio.ImageIO;
import java.io.IOException;

import main.GamePanel;
public class ObjectLever extends SuperObject {
    GamePanel gp;
    public boolean isOpen = false;

    public ObjectLever(GamePanel gp) {
        this.gp = gp;
        name = "Lever";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/manetaRosie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }

    public void openLever() {
        gp.playSE(7);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/manetaVerde.png"));
            isOpen = true;

            // Deschide doar gratiile de pe tile-urile specificate
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] != null && gp.obj[i] instanceof ObjectGratii) {
                    ObjectGratii gratie = (ObjectGratii) gp.obj[i];
                    int col = gratie.worldX / gp.tileSize;
                    int row = gratie.worldY / gp.tileSize;

                    if (
                            (col == 118 && (row == 8 || row == 10)) ||
                                    (col == 117 && (row == 8 || row == 9 || row == 10))
                    ) {
                        gratie.openGratii();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}