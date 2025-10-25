package main;
import Boss_AI.PathFinder;
import Enviroment.EnviromentManager;
import entity.Entity;
import entity.Player;
import object.ObjectSpike;
import object.SuperObject;

import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 32;
    final int scale = 1;
    public final int  tileSize = originalTileSize * scale;
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 16;
    public final int  screenWidth = tileSize * maxScreenCol;
    public final int  screenHeight = tileSize * maxScreenRow;

    //world settings
    public final int maxWorldCol =120;
    public final int maxWorldRow =20;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize* maxWorldRow;


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int deadState = 3 ;
    public final int endState = 4;
    //
    public GameSaveManager saveManager;
    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public UI ui = new UI(this);
    public main.KeyHandler keyH;

    EnviromentManager eManager= new EnviromentManager(this);

    Thread gameThread;
    public ColisionCheck check=new ColisionCheck(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[333];
    public AssetSetter assetSetter = new AssetSetter(this);
    public Entity npc[] = new Entity[10];
    public PathFinder pFinder = new PathFinder(maxWorldCol,maxWorldRow, this);
    public double playTime=0;
    public EventHandler eHandler = new EventHandler(this);
    public object.ObjectLever lever;



    //tepi
    long spikeToggleTimer = System.currentTimeMillis();
    boolean spikeRaisedVisible = false;
    final long spikeOnDuration = 2000;  // 2 secunde ON
    final long spikeOffDuration = 3000; // 3 secunde OFF
    final long spikeCycleDuration = spikeOnDuration + spikeOffDuration;


    public TileManager getTileManager() {
        return tileM;
    }

    Sound sound = new Sound();
    //usa
    class Door {
        int col, row;
        boolean soundPlayed;

        Door(int col, int row) {
            this.col = col;
            this.row = row;
            this.soundPlayed = false;
        }
    }
    java.util.List<Door> doors = new java.util.ArrayList<>();





    public GamePanel(){
        keyH = new main.KeyHandler(this);
        player = new Player(this, keyH);
        saveManager = new GameSaveManager(this);
        UI ui;

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        playMusic(0);
        //gameState = playState;
        assetSetter.setObject();
        if (obj[200] instanceof object.ObjectLever) {
            lever = (object.ObjectLever) obj[200];
        }
        eManager.setup();

        assetSetter.setNPC();
        assetSetter.setBoss();
        //usile
        doors.add(new Door(5, 6));
        doors.add(new Door(8, 16));
        doors.add(new Door(39, 10));
        doors.add(new Door(71, 3));
        doors.add(new Door(71, 16));
        doors.add(new Door(79, 10));
        doors.add(new Door(83, 4));
        doors.add(new Door(104, 17));
        doors.add(new Door(111, 2));
        doors.add(new Door(119, 9));
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextdrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null)
        {

//            System.out.println("merge");
            update();
            repaint();

            try{
                double remainingTime = nextdrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
             Thread.sleep((long)remainingTime);
                nextdrawTime += drawInterval;
        }catch(InterruptedException e){
            e.printStackTrace();}
        }
    }

    public void update() {
        if(gameState == playState) {
            //timer
            playTime += 1.0 / 60.0;
            //PLAYER
            player.update();

            // coleziune - spike ridicat
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null && obj[i] instanceof ObjectSpike spike)
                {
                    if (spike.isSpikeRaised())
                    {  // verifică dacă spike-ul este ridicat
                        Rectangle spikeArea = new Rectangle(
                                spike.worldX + spike.solidArea.x,
                                spike.worldY + spike.solidArea.y,
                                spike.solidArea.width,
                                spike.solidArea.height
                        );

                        Rectangle playerArea = new Rectangle(
                                player.worldX + player.solidArea.x,
                                player.worldY + player.solidArea.y,
                                player.solidArea.width,
                                player.solidArea.height
                        );

                        if (playerArea.intersects(spikeArea)) {
                            player.takeDamage(1);
                        }
                    }
                }
            }
            //NPC
            for (int i=0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }

            updateDoor();

            // Toggle spikes on/off
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - spikeToggleTimer;

            if (elapsed >= spikeCycleDuration) {
                spikeToggleTimer = currentTime;
                spikeRaisedVisible = false;
                removeRaisedSpikes();
            } else if (elapsed >= spikeOffDuration && !spikeRaisedVisible) {
                spikeRaisedVisible = true;
                addRaisedSpikes();
            }
        }
        if(gameState == pauseState) {
            // nu face nimic in pauza
        }
    }

    public void updateDoor() {
        int playerCenterX = player.worldX + tileSize / 2;
        int playerCenterY = player.worldY + tileSize / 2;

        int playerCol = playerCenterX / tileSize;
        int playerRow = playerCenterY / tileSize;

        for (Door door : doors) {
            if (playerCol == door.col && playerRow == door.row) {
                tileM.mapTileNum[door.col][door.row] = 62;  // ușa deschisă
                if (!door.soundPlayed) {
                    playSE(6);
                    door.soundPlayed = true;
                }
            } else {
                tileM.mapTileNum[door.col][door.row] = 60;  // ușa închisă
                door.soundPlayed = false;
            }
        }
    }


    public void addRaisedSpikes() {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null && obj[i] instanceof ObjectSpike) {
                obj[i].collision = true; // tepii sunt activi
                ((ObjectSpike)obj[i]).setSpikeRaised(true);
            }
        }
    }

    public void removeRaisedSpikes() {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null && obj[i] instanceof ObjectSpike) {
                obj[i].collision = false; // tepii nu mai rănesc
                ((ObjectSpike)obj[i]).setSpikeRaised(false);
            }
        }
    }




    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == titleState){
            ui.draw(g2);
        }
        else if (gameState==pauseState)
        {
           ui.draw(g2);
        }
        else if (gameState==deadState)
        {
            ui.draw(g2);
        }
        else if (gameState==endState)
        {
            ui.draw(g2);
        }
        else {
            //TILE
            tileM.draw(g2);

            //OBJECT
            for(int i = 0; i< obj.length;i++)
                if(obj[i] != null)
                {
                    obj[i].draw(g2,this);
                }
            //NPC
            for(int i=0; i < npc.length; i++)
                if(npc[i] != null)
                {
                    npc[i].draw(g2);
                }
            //PLAYER
            player.draw(g2);

            //ENVIROMENT
            eManager.draw(g2);
            //UI
            ui.draw(g2);
        }
        g2.dispose();

    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.setVolume(-5.0f);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }



}
