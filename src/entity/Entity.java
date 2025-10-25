package entity;

import Boss_AI.PathFinder;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn=false;
    public int actionLockCounter =0;

    //status caracter
    public int maxLife, life;

    public Entity(GamePanel gp)
    {

        this.gp = gp;
    }
    public void setAction(){}
    public void update() {
        collisionOn = false;

        gp.check.checkTile(this);
        gp.check.checkObject(this, false);
        gp.check.checkPlayer(this);

        setAction();

        if (!collisionOn) {
            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }


        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }


    public void takeDamage(int amount)
    {
        life -= amount;
        if (life < 0) life = 0;
    }




    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX=worldX - gp.player.worldX + gp.player.screenX;
        int screenY=worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY )
        {
            switch (direction){
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY,gp.tileSize * 3/2,gp.tileSize*3/2,null);
        }

    }
    protected int startCol, startRow, goalCol, goalRow;
    protected boolean onPath = false;
    protected boolean followPlayer = false;

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
//        if (Game.debugState && this instanceof Chupacabra) {
//            SworldYstem.out.println("startCol = " + startCol + ", startRow = " + startRow);
//        }

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        if (gp.pFinder.search()) {

            // Next x and worldY coordinates
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // entity position
            int entLeftX = worldX + solidArea.x;
            int entRightX = worldX + solidArea.x + solidArea.width;
            int entTopY = worldY + solidArea.y;
            int entBottomY = worldY + solidArea.y + solidArea.height;

            if (entTopY > nextY && entLeftX >= nextX && entRightX < nextX + gp.tileSize) {
                direction = "up";
            } else
            if (entTopY < nextY && entLeftX >= nextX && entRightX < nextX + gp.tileSize) {
                direction = "down";
            } else
            if (entTopY >= nextY && entBottomY < nextY + gp.tileSize) {
                // left or right
                if (entLeftX > nextX) {
                    direction = "left";
                }
                if (entLeftX < nextX) {
                    direction = "right";
                }
            } else if (entTopY > nextY && entLeftX > nextX) {
                // up or left
                direction = "up";
                gp.check.checkTile(this);
                gp.check.checkPlayer(this);
                if (collisionOn) {
                    direction = "left";
                }
            } else if (entTopY > nextY && entLeftX < nextX) {
                // up or right
                direction = "up";
                gp.check.checkTile(this);
                gp.check.checkPlayer(this);
                if (collisionOn) {
                    direction = "right";
                }
            } else if (entTopY < nextY && entLeftX > nextX) {
                // down or left
                direction = "down";
                gp.check.checkTile(this);
                gp.check.checkPlayer(this);
                if (collisionOn) {
                    direction = "left";
                }
            } else if (entTopY < nextY && entLeftX < nextX) {
                // down or right
                direction = "down";
                gp.check.checkTile(this);
                gp.check.checkPlayer(this);
                if (collisionOn) {
                    direction = "right";
                }
            }

            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;

            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = !onPath;
            }
        }
    }



}
