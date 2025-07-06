/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game2048;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game2048 extends JPanel implements KeyListener
{
    Board game = new Board();
    static JFrame frame = new JFrame("2048");
    
  
    public static void setUpGUI(Game2048 newGame)
    {
        frame.addKeyListener(newGame);
        frame.getContentPane().add(newGame);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocus(); // Ensure the frame has focus to receive key events
    }

  
    @Override
    public void keyPressed(KeyEvent e)
    {
        // Special case for Enter key 
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            // Reset the game
            this.game = new Board();
            this.game.spawn();
            this.game.spawn();
            frame.repaint();
            return; // Process Enter key and exit
        }
        
        // For movement keys, check if game is over first
        if (game.gameOver()) {
            return;  // Prevent movement if game is over
        }

        // Process movement keys
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.game.up();
            this.game.spawn();
            frame.repaint();
        }
        else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.game.down();
            this.game.spawn();
            frame.repaint();
        }
        else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.game.left();
            this.game.spawn();
            frame.repaint();
        }
        else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            this.game.right();
            this.game.spawn();
            frame.repaint();
        }
    }

   @Override
public void keyReleased(KeyEvent e) {}
@Override
public void keyTyped(KeyEvent e) {}

   
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("2048", 250, 20);
        g2.drawString("Score: " + game.getScore(),
                200 - 4 * String.valueOf(game.getScore()).length(),
                40);
        g2.drawString("Highest Tile: " + game.getHighTile(),
                280 - 4 * String.valueOf(game.getHighTile()).length(),
                40);
        g2.drawString("Press 'Enter' to Start", 210, 315);
        g2.drawString("Use 'wasd' or Arrow Keys to move", 180, 335);
        g2.drawString("Press 'Enter' to restart", 200, 355);

        // Draw the gray board background
        g2.setColor(Color.gray);
        g2.fillRect(140, 50, 250, 250);
        
        // Draw the tiles
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                drawTiles(g, game.board[i][j], j * 60 + 150, i * 60 + 60);
            }
        }

        // Display game over screen if the game is over
        if (game.gameOver())
        {
            // Draw semi-transparent overlay
            g2.setColor(new Color(150, 150, 150, 150));
            g2.fillRect(140, 50, 250, 250);
            
            // Draw game over tiles
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    g2.setColor(Color.RED);
                    g2.fillRoundRect(j * 60 + 150, i * 60 + 60, 50, 50, 5, 5);
                    g2.setColor(Color.BLACK);
                    g.drawString("GAME", j * 60 + 160, i * 60 + 75);
                    g.drawString("OVER", j * 60 + 160, i * 60 + 95);
                }
            }
            if (game.hasWon())
{
    g2.setColor(new Color(255, 215, 0, 180)); // gold translucent overlay
    g2.fillRect(140, 50, 250, 250);

    g2.setColor(Color.BLACK);
    g2.drawString("YOU", 240, 180);
    g2.drawString("WIN!", 240, 200);
}

            // Emphasize the restart message
            g2.setColor(Color.BLACK);
            g2.drawString("Press 'Enter' to restart", 200, 355);
        }
    }

    
    public void drawTiles(Graphics g, Tile tile, int x, int y)
    {
        int tileValue = tile.getValue();
        int length = String.valueOf(tileValue).length();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        g2.setColor(Color.black);
        if (tileValue > 0)
        {
            g2.setColor(tile.getColor());
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black);
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }
    }

   
    public static void main(String[] args)
    {
        Game2048 newGame = new Game2048();  // Create the Game2048 instance
        newGame.game.spawn();
        newGame.game.spawn();
        setUpGUI(newGame);  // Pass the instance to the setup method
    }
}