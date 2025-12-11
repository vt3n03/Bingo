# Bingo Simulation Program

This program creates Bingo cards, tracks drawn numbers, checks for winners, and simulates full Bingo sequences. It includes classes for individual Bingo cards, a simulator to manage many cards at once, and a test suite that verifies key behaviours of the program.

# Features

Each Bingo card stores a 5 by 5 layout of numbers separated into B, I, N, G, and O columns. Numbers are marked as taken when drawn. The center of the card is always a free space.

The simulator keeps track of many Bingo cards at the same time. It updates each card as numbers are drawn and stops when any card wins.

Supported behaviour

- Validates Bingo cards  
- Marks numbers that have been drawn  
- Resets cards to their original state  
- Calculates how many more numbers are needed to win  
- Detects winning rows, columns, and diagonals  
- Simulates full Bingo draws and prints progress  
- Shows cards with a certain minimum distance from winning  

# How the Program Works

A BingoCard stores its numbers and a matching grid that tracks which positions have been taken. When a number is drawn, the card compares it against every position and marks the match if found. The free space in the center is always treated as taken.

The card checks for a winner by confirming whether any row, column, or diagonal is fully taken. The minToWin method counts how many squares remain unmarked in each possible winning line and returns the smallest number.

The BingoSim class handles many Bingo cards at once. Its simulate method goes through a full sequence of draws, prints the call with its letter prefix, updates each card, shows how close each card is to winning, and stops as soon as a winner appears.

```vbnet
Simulating ...
1. G-50 3 4 4 4 3 4 4 4 
2. N-32 3 3 3 4 3 4 4 4 
3. B-11 2 3 3 4 3 4 4 3 
...
24. N-44 2 2 1 2 1 2 1 2 
25. I-24 1 2 1 2 1 2 1 2 
26. I-26 1 2 1 2 0 2 1 2 
```

When the simulator prints the card grid, taken numbers appear inside brackets. The free space is marked as `[FR]`.

# Bingo Card Display Example

```vbnet
 B    I    N    G    O   
  4   25   36   50   74  
  8   27   33   59   71  
  5   22  [FR]  48   73  
  2   26   34   58   70  
 13   18   44   46   66  
```

Cards also report how many squares away they are from winning. The simulator keeps this count updated during each draw.

