package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import main.GamePanel;
import javax.swing.*;
import entity.Player;
import object.*;


public class UI {

    GamePanel gp;
    public int commandNum = 0;
    Graphics2D g2;
    Font arial_40;
    BufferedImage keyimage;
    BufferedImage coinsimage;
    BufferedImage chestimage;
    BufferedImage heart_full, heart_half, heart_blank;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 20);

        ObjectKey key = new ObjectKey();
        keyimage = key.image;

        ObjectCoins coins = new ObjectCoins();
        coinsimage = coins.image;

        ObjectChest chest = new ObjectChest(gp);
        chestimage = chest.image;

        playTime = gp.playTime;

        // create hud object
        SuperObject heart = new ObjectHeart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void draw(Graphics2D g2) {
        this.g2=g2;
        // Titlu
        if (gp.gameState == gp.titleState) {
            drawTitleScreen(g2);
        }

        // Pauza
        else if (gp.gameState == gp.pauseState) {
            drawPauseScreen(g2);
        }
        //end
        if (gp.gameState == gp.endState) {
            drawEndScreen(g2);
        }
        //dead
        if(gp.gameState == gp.deadState)
        {
            drawDeadScreen(g2);
        }

        // playstate
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            //display coins,key..
            int iconSize = gp.tileSize;
            int startX = 2;
            int startY = 1;
            int lineSpacing = 1;
            int textOffsetX = 1;
            int textOffsetY = 28;

            g2.setFont(new Font("Arial", Font.PLAIN, 18));
            g2.setColor(Color.white);

            g2.drawImage(keyimage, startX, startY, iconSize, iconSize, null);
            g2.drawString("x " + gp.player.hasKey, startX + iconSize + textOffsetX, startY + textOffsetY);

// COINS
            int secondY = startY + iconSize + lineSpacing;
            g2.drawImage(coinsimage, startX, secondY, iconSize, iconSize, null);
            g2.drawString("x " + gp.player.hasCoins, startX + iconSize + textOffsetX, secondY + textOffsetY);

// CHESTS
            int thirdY = secondY + iconSize + lineSpacing;
            g2.drawImage(chestimage, startX, thirdY, iconSize, iconSize, null);
            g2.drawString("x " + gp.player.hasChest, startX + iconSize + textOffsetX, thirdY + textOffsetY);


            //timer
            playTime= gp.playTime;
            int minute = (int) playTime / 60;
            int secunde = (int) playTime % 60;
            String timeFormatted = String.format("%02d:%02d", minute, secunde);
            g2.drawString(timeFormatted, gp.tileSize * 15 +25, 30);

            int playTimeInSeconds = (int) playTime;
            int score = +gp.player.hasKey*100 + gp.player.hasCoins*20 - playTimeInSeconds + gp.player.hasChest*50;
            String scoreText = "Scor: " + score;
            g2.drawString(scoreText, gp.tileSize * 15 , 60);

        }
    }

    public void  drawDeadScreen(Graphics2D g2){
        g2.setColor(new Color(76, 0, 153));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        Font titleFont = g2.getFont().deriveFont(Font.BOLD, 50f);
        g2.setFont(titleFont);

        String text = "GAME OVER";
        int x = getXforCenteredText(text, g2), y = gp.tileSize * 3 - 30;

        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);

        g2.setColor(Color.cyan);
        g2.drawString(text, x, y);

        // new game + leave
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

        text = "new game";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize * 4 + 50;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        text = "menu";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }


    }

    public void  drawEndScreen(Graphics2D g2){
        g2.setColor(new Color(76, 0, 153));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        Font titleFont = g2.getFont().deriveFont(Font.BOLD, 50f);
        g2.setFont(titleFont);

        String text = "YOU ESCAPED";
        int x = getXforCenteredText(text, g2), y = gp.tileSize * 3 - 30;

        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);

        g2.setColor(Color.cyan);
        g2.drawString(text, x, y);

        // new game + leave
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

        text = "menu";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize * 4 + 120;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        text = "leave";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        int totalSeconds = (int) gp.playTime;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeFormatted = String.format("Time: %02d:%02d", minutes, seconds);

        int score = gp.player.hasKey * 100 + gp.player.hasCoins * 20 - totalSeconds + gp.player.hasChest * 50;
        String scoreText = "Score: " + score;

        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setColor(Color.cyan);
        int xTime = gp.screenWidth / 2 - 70;
        int yTime = gp.tileSize * 3 + 80;
        int xScore = gp.screenWidth / 2 - 70;
        int yScore = yTime + 40;
        g2.drawString(timeFormatted, xTime, yTime);
        g2.drawString(scoreText, xScore, yScore);



    }
    public void drawTitleScreen(Graphics2D g2) {
        g2.setColor(new Color(76, 0, 153));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        Font titleFont = g2.getFont().deriveFont(Font.BOLD, 60f);
        g2.setFont(titleFont);

        String text = "Cold Escape";
        int x = getXforCenteredText(text, g2), y = gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.cyan);
        g2.drawString(text, x, y);

        // Imaginea
        x = gp.screenWidth / 2 - gp.tileSize;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down2, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // New game + load game + quit
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

        text = "new game";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize * 4;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        text = "load game";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        text = "leave";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }
    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setColor(new Color(76, 0, 153));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        Font titleFont = g2.getFont().deriveFont(Font.BOLD, 50f);
        g2.setFont(titleFont);

        String text = "PAUSE";
        int x = getXforCenteredText(text, g2), y = gp.tileSize * 3 - 48;

        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);

        g2.setColor(Color.cyan);
        g2.drawString(text, x, y);

        // Continue + save game + exit
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

        text = "continue";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize * 4 + 50;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        text = "save game";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }

        text = "exit";
        x = getXforCenteredText(text, g2);
        y += gp.tileSize+30;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.gray);
        g2.drawString(text, x, y);
        g2.setColor(Color.cyan);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize - gp.tileSize, y);
        }
    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawPlayerLife(){
        //inimi goale
        int x = gp.tileSize/2+65;
        int y = gp.tileSize/2-10;
        int i=0;
        while(i< gp.player.maxLife/2)
        {
            g2.drawImage(heart_blank, x, y, null);
            ++i;
            x+=gp.tileSize;
        }
        //reset
        x = gp.tileSize/2+65;
        y = gp.tileSize/2-10;
        i=0;

        //curremt life
        while(i< gp.player.life)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i< gp.player.life)
            {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

}