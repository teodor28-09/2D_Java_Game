package object;
import tile.TileManager;
import main.GamePanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class ObjectSpike extends SuperObject {

    private BufferedImage spikeDownImage;
    private BufferedImage spikeUpImage;
    public int spikeType;
    private boolean raised = false;
    private GamePanel gp;

    public ObjectSpike(GamePanel gp, int spikeType) {
        this.gp = gp;
        this.spikeType = spikeType;

        name = "Spike";

        collision = false;

        try {
            spikeDownImage = ImageIO.read(getClass().getResourceAsStream("/objects/spike1.png"));
            spikeUpImage = ImageIO.read(getClass().getResourceAsStream("/objects/spike2.png"));

            if (spikeType == 1) {
                image = spikeDownImage;
            } else if (spikeType == 2) {
                image = spikeUpImage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        solidArea = new Rectangle(0, 0, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setSpikeRaised(boolean raised) {
        this.raised = raised;

        try
        {
            if (raised)
            {
                image = spikeUpImage;
                collision = false;
            }
            else
            {
                image = spikeDownImage;
                collision = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean isSpikeRaised() {
        return raised;
    }

}
