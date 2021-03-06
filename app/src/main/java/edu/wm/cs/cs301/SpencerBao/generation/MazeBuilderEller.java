package edu.wm.cs.cs301.SpencerBao.generation;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class MazeBuilderEller extends MazeBuilder implements Runnable{

    public MazeBuilderEller() {
        super();
        System.out.println("MazeBuilderEller uses Eller's algorithm to generate maze.");
    }

    @Override
    protected void generatePathways() {
        // assign each cell in a row to a number
        // make a 2d array corresponding to each cell
        // have a set counter
        int[][] set = new int[height][width]; // [rows, col]
        int set_counter = 1;
        for (int i = 0; i < width; i++) {
            set[0][i] = set_counter;
            set_counter++;
        }

        // join adjacent cells, they merge into the same set
        Random random = new Random();
        int x = 0;
        int y = 0;

        CardinalDirection cd = CardinalDirection.East;
        Wallboard wallboard = new Wallboard(x, y, cd);
        Dictionary<Integer, Boolean> dict = new Hashtable();

        for (int i = 0; i < height - 1; i++) {
            cd = CardinalDirection.East;
            for (int j = 0; j < width - 1; j++) { // if not a load-bearing wall
                wallboard.setLocationDirection(x, y, cd);
                if (random.nextInt(2) == 0) { // randomly delete wallboards
                    floorplan.deleteWallboard(wallboard);
                    set[y][x+1] = set[y][x]; // merged cells belong to same set
                }
                dict.put(set[y][x], true);
                x++;
            }
            y++; // to next row

            // random vertical connections, at least 1 per set, assign them to the set they connect to
            // flesh out next row, assigning remaining cells to its own set
            cd = CardinalDirection.North;
            while (!dict.isEmpty()) {
                x = 0;
                for (int j = 0; j < width; j++) {
                    wallboard.setLocationDirection(x, y, cd);
                    if (set[y][x] != set[y-1][x]) { //if the set above does not have a vertical merge yet
                        if (random.nextInt(2) == 0) {
                            floorplan.deleteWallboard(wallboard);
                            set[y][x] = set[y-1][x];
                            dict.remove(set[y-1][x]);
                        } else {
                            set[y][x] = set_counter;
                            set_counter++;
                        }
                    }
                    x++;
                }
            }
            x = 0;

        }
        cd = CardinalDirection.East;

        for (int j = 0; j < width - 1; j++) {
            wallboard.setLocationDirection(x, y, cd);
            floorplan.deleteWallboard(wallboard);
            x++;
        }



    }
}
