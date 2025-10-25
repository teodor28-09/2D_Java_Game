package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Boss extends Entity
{
    private List<int[]> path;
    private int currentTarget;

    public Boss(GamePanel gp, List <int[]> path)
    {
        super(gp);
        onPath=true;
        startCol = 83;
        startRow = 11;
        goalCol = 91;
        goalRow = 8;

        direction = "down";
        this.path=path;
        currentTarget = 0;
        speed = 1;
        solidArea = new Rectangle(8, 16, 16, 16);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getBossImage();
    }



    private void followPlayer() {
        int targetX = gp.player.worldX;
        int targetY = gp.player.worldY;

        int tolerance = 2;
        boolean moved = false;

        // Încearcă să te miști pe axa X
        if (Math.abs(worldX - targetX) > tolerance) {
            direction = (worldX < targetX) ? "right" : "left";
            gp.check.checkTile(this);
            if (!collisionOn) {
                worldX += (direction.equals("right") ? speed : -speed);
                moved = true;
            }
        }

        // Dacă nu s-a mișcat pe axa X, încearcă pe axa Y
        if (!moved && Math.abs(worldY - targetY) > tolerance) {
            direction = (worldY < targetY) ? "down" : "up";
            gp.check.checkTile(this);
            if (!collisionOn) {
                worldY += (direction.equals("down") ? speed : -speed);
            }
        }
    }


    public void getBossImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Boss/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Boss/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Boss/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Boss/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Boss/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Boss/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Boss/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Boss/right2.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        //collisionOn = false;
        //gp.check.checkPlayer(this);

        if (followPlayer == false) {
            if (onPath) searchPath(goalCol,goalRow);
            else searchPath(startCol,startRow);
        }else {
            int goalCol= (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow= (gp.player.worldY+ gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol,goalRow);

        }

        if (!collisionOn)
        {

            if(collisionOn== false)
            {
                switch(direction)
                {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX +=speed;
                        break;

                }
            }        }
    }


    @Override
    public void update() {
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;
        if (tileDistance < 5 && followPlayer == false) {
            // aggressive enemy only in 50% chance
            int i = new Random().nextInt(100) + 1;
            if (i > 50) followPlayer = true;
        } else if (tileDistance > 12 && followPlayer)  followPlayer = false;
        setAction();

        collisionOn = false;

        gp.check.checkTile(this);
        gp.check.checkObject(this, false);
        gp.check.checkPlayer(this);



        spriteCounter++;
        if (spriteCounter > 12)
        {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }



}

