

package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    main.GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager (main.GamePanel gp){
        this.gp = gp;
        tile = new Tile[99];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try{
            for (int i = 0; i < tile.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/1.png")));
            }
            tile[61] = new Tile();
            tile[61].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete.png")));
            tile[61].collision=true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/2.png")));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/3.png")));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/4.png")));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/2.png")));

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/6.png")));

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/7.png")));

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/3.png")));

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/4.png")));

            tile[60] = new Tile();
            tile[60].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/usa.jpg")));

            tile[62] = new Tile();
            tile[62].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/doorOpen.png")));

            tile[57] = new Tile();
            tile[57].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/butoi.png")));
            tile[57].collision=true;

            tile[58] = new Tile();
            tile[58].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cutie.png")));
            tile[58].collision=true;

            tile[59] = new Tile();
            tile[59].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/flacara.jpg")));
            tile[59].collision=true;

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){

        try{
            InputStream is = getClass().getResourceAsStream("/maps/mapa_full.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers [] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }

            }
            br.close();
        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];


            int worldX=worldCol * gp.tileSize;
            int worldY=worldRow * gp.tileSize;
            int screenX=worldX - gp.player.worldX + gp.player.screenX;
            int screenY=worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY )
            {
                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }
    }
}