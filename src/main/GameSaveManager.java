package main;// GameSaveManager.java
import main.GamePanel;

import java.sql.*;

public class GameSaveManager {
    GamePanel gp;

    public GameSaveManager(GamePanel gp) {
        this.gp = gp;
    }

    public Connection connect() {
        try {
            String url = "jdbc:sqlite:savegame.db";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveGameToDatabase() {
        String sql = "INSERT INTO game_save(player_x, player_y, coins, keys, play_time) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gp.player.worldX);
            pstmt.setInt(2, gp.player.worldY);
            pstmt.setInt(3, gp.player.hasCoins);
            pstmt.setInt(4, gp.player.hasKey);
            pstmt.setDouble(5, gp.playTime);
            //pstmt.setInt(6, gp.player.score);

            pstmt.executeUpdate();
            System.out.println("Joc salvat în baza de date.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadGameFromDatabase() {
        String sql = "SELECT * FROM game_save ORDER BY id DESC LIMIT 1";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                gp.player.worldX = rs.getInt("player_x");
                gp.player.worldY = rs.getInt("player_y");
                gp.player.hasCoins = rs.getInt("coins");
                gp.player.hasKey = rs.getInt("keys");
                gp.playTime = rs.getDouble("play_time");
                //gp.player.score = rs.getInt("score");

                System.out.println("Joc încărcat din baza de date.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

