package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class OrcaNPC extends Entity{
    private List<int[]> path;
    private int currentTarget;

    public OrcaNPC(GamePanel gp, List <int[]> path)
    {
        super(gp);
        direction = "down";
        this.path=path;
        currentTarget = 0;
        speed = 1;
        solidArea = new Rectangle(8, 16, 16, 16);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getNPCImage();
    }



    private void moveToNextPoint() {
        if (path == null || path.isEmpty()) return;

        if (currentTarget >= path.size())
        {
            currentTarget = 0;
        }


        int[] target = path.get(currentTarget);
        int targetX = target[0] * gp.tileSize-8;
        int targetY = target[1] * gp.tileSize-10;

        int tolerance = 2;

        if (Math.abs(worldX - targetX) > tolerance)
        {
            if (worldX < targetX)
            {
                worldX += speed;
                direction = "right";
            } else if (worldX > targetX)
            {
                worldX -= speed;
                direction = "left";
            }
            return;
        }

        // Mișcare pe axa Y (sus/jos)
        if (Math.abs(worldY - targetY) > tolerance)
        {
            if (worldY < targetY)
            {
                worldY += speed;
                direction = "down";
            } else if (worldY > targetY)
            {
                worldY -= speed;
                direction = "up";
            }
            return;
        }


        if (Math.abs(worldX - targetX) <= tolerance && Math.abs(worldY - targetY) <= tolerance)
        {
            currentTarget++;
        }
    }



    public void getNPCImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/NPC/orca_right2.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        collisionOn = false;
        gp.check.checkPlayer(this);

        if (!collisionOn)
        {
            moveToNextPoint();
        }
    }

    @Override
    public void update() {
        collisionOn = false;

        gp.check.checkTile(this);
        gp.check.checkObject(this, false);
        gp.check.checkPlayer(this);

        setAction();

        spriteCounter++;
        if (spriteCounter > 12)
        {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }



}
