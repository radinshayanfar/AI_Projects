# AI Projects

AUT Principles and Applications of Artificial Intelligence course (Fall 2020) projects.



## Table of Contents

1. [Simple Card Game! Search problem using BFS, IDS, and A* algorithms](#1-simple-card-game-search-problem-using-bfs-ids-and-a-algorithms)
2. Colorful Sudoku: CSP problem using Backtrack algorithm with Forward Checking and MRV and Degree heuristics
3. Poet Recognition: NLP problem using Unigram, Bigram, and Backoff models



### 1. [Simple Card Game! Search problem using BFS, IDS, and A* algorithms](https://github.com/radinshayanfar/AI_Projects/tree/master/PRJ1_PRJ2/src/main/java/prj1)

In this exciting game, there are multiple cards with different numbers and colors. We have `M` colors for cards, and for each color, there are `N` cards numbered from 1 to `N`. The cards could be placed in any of `K` available columns, and all of them could be seen at any time. **The goal is to order the columns so that all cards in every column should have the same color and ordered descending**. Also, there is a rule for moving the cards. You can only pick a card from the top of each column, and it should be placed on a card with a greater number.

For instance, an initial state can be as follows:

![initial state](./images/prj1_initial.svg)

In the above example, a possible move is to pick the yellow-2 card from the second column and put it on the fourth column. So the game state would be:

![example state](./images/prj1_example.svg)

Other possible destinations for yellow-2 are the first column and fifth (empty) column. But notice that it cannot be moved to the third column (as the card underneath it will not be greater than that).

So in this project, given the initial state, a goal state could be found using BFS (BFSMain class), IDS (IDSMain class), or A* (AStarMain class) search algorithm.

#### Input

The first line contains `K M N`, number of columns, colors, and cards for each color, respectively.

Each next `K` lines inputs each column card. Cards are separated by space and characterized by a character (defining card color) followed by a number (defining card number). `#` determines an empty column.

**Sample input:**

The above example initial state is as follows:

```
5 3 5
5g 5r 4y
2g 4r 3y 3g 2y
1y 4g 1r
1g 2r 5y 3r
#
```

#### Output

The goal state, problem statistics such as goal depth and created noes, and steps to reproduce the goal state will be printed.

A possible answer to the above input using A* algorithm is:

```
5g 4g 3g 1g
2g
4r
5r 3r 2r 1r
5y 4y 3y 2y 1y
========
Depth: 22
Created nodes: 2066067
Expanded nodes: 427019
========
Actions:
3 => 2
4 => 3
4 => 5
4 => 3
1 => 5
4 => 5
1 => 4
3 => 1
3 => 4
1 => 4
2 => 4
3 => 1
3 => 1
2 => 3
1 => 3
2 => 1
5 => 1
2 => 5
3 => 2
3 => 5
2 => 5
2 => 3
```

which is illustrated in the image below:

![final state](./images/prj1_final.svg)



#### Full description

Read the full description (in Persian) [here](https://github.com/radinshayanfar/AI_Projects/blob/master/instructions/AI_P1.pdf).

The report for this project (also in Persian) is [here](https://github.com/radinshayanfar/AI_Projects/blob/master/report/prj1/report.pdf).