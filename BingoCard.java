public class BingoCard
{
    private int[][] card;
    private boolean[][] taken;

    public static final int[] MY_WINNER = {7, 12, 15, 14, 13, 16, 29, 17, 23, 20, 37, 38, 36, 44, 49, 46, 48, 47, 52, 66, 73, 74, 75, 72};

    public BingoCard(int[] data)
    {
        card = new int[5][5];
        taken = new boolean[5][5];

        int z = 0;

        for (int j = 0; j < 5; j++)
        {
            for (int i = 0; i < 5; i++)
            {
                if (i == 2 && j == 2)
                {
                    card[2][2] = 0;
                    taken[2][2] = true;
                } else
                {
                    card[i][j] = data[z];
                    z++;
                    taken[i][j] = false;
                }
            }
        }
    }

    public boolean isValid()
    {

        for (int row = 0; row < 5; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                if (!(row == 2 && col == 2))
                {
                    if (card[row][col] < ((col * 15) + 1) || card[row][col] > ((col + 1) * 15))
                    {
                        return false;
                    } else if (isDuplicate(card[row][col], row, col))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isDuplicate(int number, int row, int col)
    {

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (!(i == 2 && j == 2))
                {
                    if (!(i == row && j == col))
                    {
                        if (card[i][j] == number)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public void drawn(int number)
    {

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (!(i == 2 && j == 2))
                {
                    if (card[i][j] == number)
                    {
                        taken[i][j] = true;
                        return;
                    }
                }
            }
        }
    }


    public void drawn(int[] numbers)
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (!(i == 2 && j == 2))
                {
                    for (int num : numbers)
                    {
                        if (card[i][j] == num)
                        {
                            taken[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void reset()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (i == 2 && j == 2)
                {
                    taken[2][2] = true;
                } else
                {
                    taken[i][j] = false;
                }
            }
        }
    }

    //TODO: diagonal remaining
    public boolean isAWinner()
    {

        for (int i = 0; i < 5; i++)
        {
            boolean checkRow = true;
            boolean checkCol = true;

            for (int j = 0; j < 5; j++)
            {
                if (!(i == 2 && j == 2))
                {
                    if (!taken[i][j])
                    {
                        checkRow = false;
                    }
                    if (!taken[j][i])
                    {
                        checkCol = false;
                    }
                }
            }
            if (checkRow || checkCol)
            {
                return true;
            }
        }

        if (taken[0][0] && taken[1][1] && taken[3][3] && taken[4][4])
        {
            return true;
        } else if (taken[0][1] && taken[1][2] && taken[2][3] && taken[3][4])
        {
            return true;
        } else return taken[1][0] && taken[2][1] && taken[3][2] && taken[4][3];
    }

    //TODO: remaining
    public int minToWin() {

        int min = 5;
        for (int i = 0; i < 5; i++)
        {
            int rowMin = 0;
            int colMin = 0;

            for (int j = 0; j < 5; j++)
            {
                if (!(i == 2 && j == 2))
                {
                    if (!taken[i][j])
                    {
                        rowMin++;
                    }
                    if (!taken[j][i])
                    {
                        colMin++;
                    }
                }
            }
            if (rowMin < min)
            {
                min = rowMin;
            }
            if (colMin < min)
            {
                min = colMin;
            }
        }

        int diagonal1 = 0, diagonal2 = 0, diagonal3 = 0;

        for (int i = 0; i < 5; i++)
        {
            if (!taken[i][i])
            {
                diagonal1++;
            }
        }

        int j = 1;
        for (int i = 0; i < 4; i++)
        {
            if (!taken[i][j])
            {
                diagonal2++;
            }
            if (!taken[j][i])
            {
                diagonal3++;
            }
            j++;
        }

        if (diagonal1 < min)
        {
            min = diagonal1;
        }

        if (diagonal2 < min)
        {
            min = diagonal2;
        }

        if (diagonal3 < min)
        {
            min = diagonal3;
        }

        return min;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(" B    I    N    G    O   ");
        builder.append("\n");

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (i == 2 && j == 2)
                {
                    builder.append("[FR] ");
                } else if (taken[i][j])
                {
                    builder.append(String.format("[%2d] ", card[i][j]));
                } else
                {
                    builder.append(String.format(" %2d  ", card[i][j]));
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}