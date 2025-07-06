package game2048;


public class Board
{
    public Tile[][] board;
    int grids = 4;
    int border = 0;
    public int score = 0;

  
    public Board()
    {
        board = new Tile[4][4];
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = new Tile();
            }
        }
    }

    public Board(int grids)
    {
        this.grids = grids;
        board = new Tile[grids][grids];
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = new Tile();
            }
        }
    }

    
    public Tile[][] getBoard()
    {
        return board;
    }

    
    public int getScore()
    {
        return score;
    }

    
    public int getHighTile()
    {
        int high = board[0][0].getValue();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getValue() > high)
                {
                    high = board[i][j].getValue();
                }
            }
        }
        return high;
    }

   // randomly spawn tiles
    public void spawn()
    {
        boolean empty = true;
        while (empty)
        {
            int row = (int)(Math.random() * 4);
            int col = (int)(Math.random() * 4);
            double x = Math.random();
            if (board[row][col].getValue() == 0)
            {
                if (x < 0.2)
                {
                    board[row][col] = new Tile(4);
                    empty = false;
                }
                else
                {
                    board[row][col] = new Tile(2);
                    empty = false;
                }
            }
            
            // Safety check to prevent infinite loop if board is full
            if (blackOut()) {
                return;
            }
        }
    }

    
    public boolean blackOut()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getValue() == 0)
                {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells found
    }

    
    public boolean gameOver()
    {
        // Check if there are any empty cells
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() == 0) {
                    return false; // Found an empty cell, game not over
                }
            }
        }
        
        // Check if any horizontal merges are possible
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j].getValue() == board[i][j+1].getValue()) {
                    return false; // Found a possible horizontal merge
                }
            }
        }
        
        // Check if any vertical merges are possible
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() == board[i+1][j].getValue()) {
                    return false; // Found a possible vertical merge
                }
            }
        }
        
        // If we get here, the game is over (no empty cells and no possible merges)
        return true;
    }

    
    public void up()
    {
        for (int i = 0; i < grids; i++)
        {
            border = 0;
            for (int j = 0; j < grids; j++)
            {
                if (board[j][i].getValue() != 0)
                {
                    if (border <= j)
                    {
                        verticalMove(j, i, "up");
                    }
                }
            }
        }
    }

    
    public void down()
    {
        for (int i = 0; i < grids; i++)
        {
            border = (grids - 1);
            for (int j = grids - 1; j >= 0; j--)
            {
                if (board[j][i].getValue() != 0)
                {
                    if (border >= j)
                    {
                        verticalMove(j, i, "down");
                    }
                }
            }
        }
    }

   
    private void verticalMove(int row, int col, String direction)
    {
        Tile initial = board[border][col];
        Tile compare = board[row][col];
        if (initial.getValue() == 0 || initial.getValue() == compare.getValue())
        {
            if (row > border || (direction.equals("down") && (row < border)))
            {
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0)
                {
                    score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        }
        else
        {
            if (direction.equals("down"))
            {
                border--;
            }
            else
            {
                border++;
            }
            verticalMove(row, col, direction);
        }
    }

    
    public void left()
    {
        for (int i = 0; i < grids; i++)
        {
            border = 0;
            for (int j = 0; j < grids; j++)
            {
                if (board[i][j].getValue() != 0)
                {
                    if (border <= j)
                    {
                        horizontalMove(i, j, "left");
                    }
                }
            }
        }
    }

    
    public void right()
    {
        for (int i = 0; i < grids; i++)
        {
            border = (grids - 1);
            for (int j = (grids - 1); j >= 0; j--)
            {
                if (board[i][j].getValue() != 0)
                {
                    if (border >= j)
                    {
                        horizontalMove(i, j, "right");
                    }
                }
            }
        }
    }

   
    private void horizontalMove(int row, int col, String direction)
    {
        Tile initial = board[row][border];
        Tile compare = board[row][col];
        if (initial.getValue() == 0 || initial.getValue() == compare.getValue())
        {
            if (col > border || (direction.equals("right") && (col < border)))
            {
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0)
                {
                    score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        }
        else
        {
            if (direction.equals("right"))
            {
                border--;
            }
            else
            {
                border++;
            }
            horizontalMove(row, col, direction);
        }
    }
    public boolean hasWon() {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (board[i][j].getValue() == 2048) {
                return true;
            }
        }
    }
    return false;
}

}
