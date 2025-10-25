package Enviroment;

import main.GamePanel;

import java.awt.*;

public class EnviromentManager {
    GamePanel gp;
    Lighting lighting;

    public EnviromentManager(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setup()
    {
        lighting = new Lighting(gp, 400);
    }

    public void draw(Graphics2D g2)
    {
        lighting.draw(g2);
    }
}
