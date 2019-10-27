/**
 * Author: Ross Baldwin
 *
 * Program: ConnectN
 *
 * Description: This program lets users play ConnectN. ConnectN is
 * like Connect4, except any number of rows, columns, and
 * amount of connected chips needed to win can be specified.
*/
package connectn;

/**
 * The ConnectN class defines a game of ConnectN. The current game board state and the position
 * of the last played chip are stored. The number of columns and rows can be determined by a user or
 * set to a default value. A win can be detected for, which depends on winningNumber.
 */
public class ConnectN {
    private int[][]	gameState;
    private int	lastColumn;
    private int	lastRow;
    private int	numberOfColumns;
    private int	numberOfRows;
    private int	playerTurn;
    private int	winningNumber;

    /**
     * This method creates a game of ConnectN with 6 rows, 7 columns, and a
     * winningNumber of 4.
     */
    public ConnectN()
    {
        numberOfRows = 6;
        numberOfColumns = 7;
        winningNumber = 4;
        //Initialize gameState to all zeroes
        gameState = new int[numberOfRows][numberOfColumns];
        lastRow = -1;
        lastColumn = -1;
    }

    /**
     * This method creates a game of ConnectN with a specified number of rows,
     * columns, and winningNumber.
     *
     * @param numberOfRows: the number of rows of the board
     * @param numberOfColumns: the number of columns of the board
     * @param winningNumber: the amount of consecutive chips in a line (horizontally,
     *                     vertically, or diagonally) needed to win the game
     */
    public ConnectN(int numberOfRows, int numberOfColumns, int winningNumber)
    {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.winningNumber = winningNumber;
        gameState = new int[numberOfRows][numberOfColumns];
        lastRow = -1;
        lastColumn = -1;
    }

    /**
     * This method checks for a win given the current state of the board.
     * It checks for a winningNumber amount of chips in a horizontal, vertical,
     * or diagonal direction.
     *
     * @return the number of the player that won the game or -1 if there is no win
     */
    public int	detectWin()
    {
        int winningPlayer = -1;

        winningPlayer = detectWinRows();
        if (winningPlayer != -1)
            return winningPlayer;

        winningPlayer = detectWinColumns();
        if (winningPlayer != -1)
            return winningPlayer;

        winningPlayer = detectWinDiagonals();
        if (winningPlayer != -1)
            return winningPlayer;

        return -1;
    }

    /**
     * This method detects for a win in a horizontal direction. It checks to the left
     * then to the right.
     *
     * @return the number of the player that won the game or -1 if there is no win
     */
    private int	detectWinColumns()
    {
        int connectedChips = 1;
        boolean sameChip = true;
        int count = 1;

        while(sameChip)
        {
            if ((lastColumn - count) < 0)
                sameChip = false;
            else if (gameState[lastRow][lastColumn - count] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if (connectedChips >= winningNumber)
            return playerTurn;

        sameChip = true;
        count = 1;

        while(sameChip)
        {
            if((lastColumn + count) >= numberOfColumns)
                sameChip = false;
            else if(gameState[lastRow][lastColumn + count] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if (connectedChips >= winningNumber)
            return playerTurn;
        else
            return -1;
    }

    /**
     * This method checks for a win in all diagonal directions. It first
     * checks for a downwards slope win then an upwards slope win.
     *
     * @return the number of the player that won the game or -1 if there is no win
     */
    private int	detectWinDiagonals()
    {
        int connectedChips = 1;
        boolean sameChip = true;
        int count = 1;

        //Check downwards slope
        while(sameChip)
        {
            if((lastRow - count) < 0 || (lastColumn - count) < 0)
                sameChip = false;
            else if(gameState[lastRow - count][lastColumn - count] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if(connectedChips >= winningNumber)
            return playerTurn;

        sameChip = true;
        count = 1;

        while(sameChip)
        {
            if((lastRow + count) >= numberOfRows || (lastColumn + count) >= numberOfColumns)
                sameChip = false;
            else if(gameState[lastRow + count][lastColumn + count] != playerTurn)
                sameChip = false;
            else
        {
            connectedChips++;
            count++;
        }
        }

        if(connectedChips >= winningNumber)
            return playerTurn;

        //Check upwards slope
        connectedChips = 1;
        sameChip = true;
        count = 1;

        while(sameChip)
        {
            if((lastRow + count) >= numberOfRows || (lastColumn - count) < 0)
                sameChip = false;
            else if(gameState[lastRow + count][lastColumn - count] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if(connectedChips >= winningNumber)
            return playerTurn;

        sameChip = true;
        count = 1;

        while(sameChip)
        {
            if((lastRow - count) < 0 || (lastColumn + count) >= numberOfColumns)
                sameChip = false;
            else if(gameState[lastRow - count][lastColumn + count] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if(connectedChips >= winningNumber)
            return playerTurn;
        else
            return -1;
    }

    /**
     * This method detects for a win in a vertical direction. It first checks up
     * then down.
     *
     * @return the number of the player that won the game or -1 if there is no win
     */
    private int	detectWinRows()
    {
        int connectedChips = 1;
        boolean sameChip = true;
        int count = 1;

        while(sameChip)
        {
            if((lastRow - count) < 0)
                sameChip = false;
            else if(gameState[lastRow - count][lastColumn] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if (connectedChips >= winningNumber)
            return playerTurn;

        sameChip = true;
        count = 1;

        while(sameChip)
        {
            if((lastRow + count) >= numberOfRows)
                sameChip = false;
            else if(gameState[lastRow + count][lastColumn] != playerTurn)
                sameChip = false;
            else
            {
                connectedChips++;
                count++;
            }
        }

        if (connectedChips >= winningNumber)
            return playerTurn;
        else
            return -1;
    }

    /**
     * This method returns the current state of board, including dimensions
     * and chips played.
     *
     * @return the state of the game board
     */
    public int[][]	getGameState()
    {
        return gameState;
    }

    /**
     * This method returns the number of columns on the board.
     *
     * @return number of columns
     */
    public int	getNumberOfColumns()
    {
        return numberOfColumns;
    }

    /**
     * This method returns the number of rows on the board.
     *
     * @return number of rows
     */
    public int	getNumberOfRows()
    {
        return numberOfRows;
    }

    /**
     * This method returns what player's turn it is.
     *
     * @return the number of the current player
     */
    public int	getPlayerTurn()
    {
        return playerTurn;
    }

    /**
     * This method returns the amount of consecutive chips needed to win the game.
     *
     * @return required number of connected chips
     */
    public int	getWinningNumber()
    {
        return winningNumber;
    }

    /**
     * This method lets a player insert a chip into a specified column.
     *
     * @param columnNumber number of column to insert chip in
     * @return true if the chip was inserted or false if the column is full
     */
    public boolean insertChip(int columnNumber)
    {
        if (gameState[0][columnNumber] != 0)
            return false;

        //Find what row the chip is placed in
        for (int i = 0; i < numberOfRows; i++)
        {
            if(gameState[i][columnNumber] != 0) {
                gameState[i - 1][columnNumber] = playerTurn;
                lastRow = i - 1;
                lastColumn = columnNumber;
                return true;
            }
        }

        gameState[numberOfRows - 1][columnNumber] = playerTurn;
        lastRow = numberOfRows - 1;
        lastColumn = columnNumber;
        return true;
    }

    /**
     * This method checks if every space on the board is filled with a chip.
     *
     * @return true if the board is full or false if there are open spaces
     */
    public boolean	isGameFull()
    {
        for (int i = 0; i < numberOfColumns; i++)
        {
            if (gameState[0][i] == 0)
                return false;
        }

        return true;
    }

    /**
     * This method sets every space in the game board to empty or "0".
     */
    public void	resetGame()
    {
        for (int i = 0; i < numberOfRows; i++)
        {
            for (int j = 0; j < numberOfColumns; j++)
                gameState[i][j] = 0;
        }

        playerTurn = 1;
        lastRow = -1;
        lastColumn = -1;
    }

    /**
     * This method switches the turn of the current player.
     */
    public void	switchTurn()
    {
        if (playerTurn == 1)
            playerTurn = 2;
        else
            playerTurn = 1;
    }

    /**
     * This method returns a string to display the state of the board. Empty spaces
     * are represented by "0", Player1 chips by "1", and Player2 chips by "2".
     *
     * @return the state of the board in string form
     */
    @Override
    public java.lang.String	toString()
    {
        String game = "";

        for (int i = 0; i < numberOfRows; i++)
        {
            for (int j = 0; j <= numberOfColumns; j++)
            {
                if (j == numberOfColumns)
                    game += "\n";
                else
                    game += gameState[i][j];
            }
        }

        return game;
    }

}
