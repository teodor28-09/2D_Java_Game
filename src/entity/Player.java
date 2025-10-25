package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;
    public int commandNum = 0;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int hasCoins , hasChest = 0;
    public int life;
    public boolean isHit;
    public boolean invincible = false;
    public int invincibleCounter = 0;


    public Player(main.GamePanel gp, main.KeyHandler keyH){

        super(gp);

        this.keyH = keyH;
        this.life=6;
        this.isHit=false;

        screenX=gp.screenWidth/2 - gp.tileSize/2;
        screenY=gp.screenHeight/2 - gp.tileSize/2;

        solidArea=new Rectangle(20,15,6,17);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.y = 0;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = 1*gp.tileSize;
        worldY = 8*gp.tileSize;
        speed = 3;
        direction = "down";

        //player status
        maxLife =6;
        life = maxLife;
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_right2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true ||keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            }
            //check tile colision
            collisionOn=false;
            gp.check.checkTile(this);

            //check object colision
            int objIndex = gp.check.checkObject(this,true);
            PickObject(objIndex);


            //check NPC colision
            int npcIndex = gp.check.checkEntity(this, gp.npc);
            interactNPC(npcIndex);


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
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (gp.keyH.fPressed) {
            interactWithObject();
            gp.keyH.fPressed = false;
        }

        if (invincible)
        {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(life <=0)
        {
            gp.gameState=gp.deadState;
        }

        int playerTileX = worldX / gp.tileSize;
        int playerTileY = worldY / gp.tileSize;

        if(playerTileX == 119 && playerTileY == 8)
        {
            gp.gameState = gp.endState;
        }



    }

    public void PickObject(int i)
    {
        if(i != 999)
        {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.playSE(3);
                    break;
                case "Coins":
                    hasCoins++;
                    gp.obj[i] = null;
                    gp.playSE(4);
                    break;
                case "DoorLevel":
                     if((hasKey == 2 && hasChest==1)|| (hasKey==4 && hasChest==2)|| (hasKey == 6 && hasChest == 4) )
                     {
                        gp.obj[i] = null;
                     }
                    break;
                case "Chest":
//                    hasChest++;
                    break;

                case "Gratii":
                    if (!(gp.lever != null && gp.lever.isOpen)) {
                    takeDamage(1);
                }
            }
        }
    }

    public void interactNPC(int i){
        if (i != 999)
        {
            // respawn + o inimioara in minus
        }
    }


    public void draw(Graphics2D g2){
        BufferedImage image = null;
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
        g2.drawImage(image,screenX,screenY,gp.tileSize*3/2,gp.tileSize*3/2,null);
        //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Draw player
        //g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
    public void interactWithObject() {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                int objLeftWorldX = gp.obj[i].worldX;
                int objRightWorldX = gp.obj[i].worldX + gp.tileSize;
                int objTopWorldY = gp.obj[i].worldY;
                int objBottomWorldY = gp.obj[i].worldY + gp.tileSize;

                int playerLeft = worldX;
                int playerRight = worldX + solidArea.width;
                int playerTop = worldY;
                int playerBottom = worldY + solidArea.height;

                boolean isTouching = playerRight > objLeftWorldX &&
                        playerLeft < objRightWorldX &&
                        playerBottom > objTopWorldY &&
                        playerTop < objBottomWorldY;

                if (isTouching && gp.obj[i].name.equals("Chest")) {
                    if (gp.obj[i] instanceof object.ObjectChest chest && !chest.isOpen) {
                        chest.openChest();
                        hasChest++;
                    }
                }
                if (isTouching && gp.obj[i].name.equals("Lever")) {
                    if (gp.obj[i] instanceof object.ObjectLever lever && !lever.isOpen) {
                        lever.openLever();
                    }
                }

            }
        }
    }
    @Override
    public void takeDamage(int amount) {
        if (!invincible) {
            life -= amount;
            invincible = true;
            invincibleCounter = 0;
            gp.playSE(5);
        }

    }




}
