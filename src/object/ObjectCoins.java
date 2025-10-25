package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectCoins extends SuperObject{
    public ObjectCoins()
    {
        name = "Coins";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coins.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
