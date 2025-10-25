package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoorLevel extends SuperObject{

    public ObjectDoorLevel()
    {
        name = "DoorLevel";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/usa_level.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}