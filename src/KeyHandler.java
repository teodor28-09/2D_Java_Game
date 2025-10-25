package  main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {

    main.GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean fPressed;

    public KeyHandler(main.GamePanel gp)
    {
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //dead state
        if(gp.gameState == gp.deadState){
            if(code == KeyEvent.VK_W){
                gp.playSE(1);
                if(gp.ui.commandNum>0) {
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.playSE(1);
                if(gp.ui.commandNum<1) {
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(1);
                if(gp.ui.commandNum==0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum==1){
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum =0;
                }
            }
        }

        //pause state
         else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_W){
                gp.playSE(1);
                if(gp.ui.commandNum>0) {
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.playSE(1);
                if(gp.ui.commandNum<2) {
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(1);
                if(gp.ui.commandNum==0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum==1){
                    gp.saveManager.saveGameToDatabase();
                    gp.ui.commandNum =0;
                }
                if(gp.ui.commandNum==2){
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum =0;
                }
            }
        }

        //title state
        else if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){
                gp.playSE(1);
                if(gp.ui.commandNum>0) {
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.playSE(1);
                if(gp.ui.commandNum<2) {
                    gp.ui.commandNum++;
                }
            }
            if(code ==KeyEvent.VK_ENTER){
                gp.playSE(1);
                if(gp.ui.commandNum==0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum==1){
                    gp.saveManager.loadGameFromDatabase();
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum==2){
                    System.exit(0);
                }
            }
        }
        //end state
        if(gp.gameState == gp.endState){
            if(code == KeyEvent.VK_W){
                gp.playSE(1);
                if(gp.ui.commandNum>0) {
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.playSE(1);
                if(gp.ui.commandNum<1) {
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(1);
                if(gp.ui.commandNum==0){
                    gp.gameState = gp.titleState;
                }
                if(gp.ui.commandNum==1){
                    System.exit(0);
                }
            }
        }
        //
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
//        if(code == KeyEvent.VK_P){
//            if(gp.gameState == gp.playState)
//                gp.gameState = gp.pauseState;
//            else if (gp.gameState == gp.pauseState)
//                gp.gameState = gp.playState;
//        }
        if(gp.gameState==gp.playState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.playSE(1);
                gp.gameState = gp.pauseState;
            }
        }
        if (code == KeyEvent.VK_F) {
            fPressed = true;
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_F) {
            fPressed = false;
        }

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
