public class BingoSim
{
    private int numCards;
    private boolean[] taken;
    private BingoCard[] cards;

    public BingoSim(int max)
    {
        cards = new BingoCard[max];
        taken = new boolean[75];
        for (int i = 0; i < 75; i++)
        {
            taken[i] = false;
        }
        numCards = 0;
    }


    public void addCard(BingoCard b)
    {
        if (numCards < cards.length)
        {
            cards[numCards] = b;
            numCards++;
        }
    }


    public String simulate(int[] sequence)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Simulating ...\n");

        for (int i = 0; i < sequence.length; i++)
        {

            taken[sequence[i] - 1] = true;

            builder.append(i + 1).append(". ");
            if (sequence[i] > 0 && sequence[i] <= 15)
            {
                builder.append("B-");
            } else if (sequence[i] > 15 && sequence[i] <= 30)
            {
                builder.append("I-");
            } else if (sequence[i] > 31 && sequence[i] <= 45)
            {
                builder.append("N-");
            } else if (sequence[i] > 45 && sequence[i] <= 60)
            {
                builder.append("G-");
            } else if (sequence[i] > 60 && sequence[i] <= 75)
            {
                builder.append("I-");
            }

            builder.append(sequence[i]).append(" ");

            for (int j = 0; j < numCards; j++)
            {
                cards[j].drawn(sequence[i]);
                builder.append(cards[j].minToWin()).append(" ");
            }
            builder.append("\n");


            for (int j = 0; j < numCards; j++)
            {
                if (cards[j].isAWinner())
                {
                    return builder.toString();
                }
            }
        }

        return builder.toString();
    }

    public String showCardsWithMin(int min)
    {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < numCards; j++)
        {
            if (cards[j].minToWin() == min)
            {
                builder.append(cards[j]);
            }
        }
        return builder.toString();
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(" B    I    N    G    O   ");
        builder.append("\n");

        int drawn = 0;
        for (int i = 1; i <= 15; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                int ind = (j * 15) + i;
                if (taken[ind - 1])
                {
                    drawn++;
                    builder.append(String.format("[%2d] ", ind));
                } else
                {
                    builder.append(String.format(" %2d  ", ind));
                }
            }
            builder.append("\n");
        }

        builder.append("# Drawn: ").append(drawn);
        builder.append("\n");

        builder.append("mins:");
        for (int j = 0; j < numCards; j++)
        {
            builder.append(cards[j].minToWin()).append(" ");
        }
        builder.append("\n");

        int winner = 0;
        for (int j = 0; j < numCards; j++)
        {
            if (cards[j].isAWinner())
            {
                winner++;
            }
        }
        builder.append("# Winners: ").append(winner).append(" out of ").append(numCards);

        return builder.toString();
    }
}
