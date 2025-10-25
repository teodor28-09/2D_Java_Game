package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject{

    public ObjectKey()
    {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cheie_noua.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        solidArea.x = 5;
    }
}
