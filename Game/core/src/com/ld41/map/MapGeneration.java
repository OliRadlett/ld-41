package com.ld41.map;

import java.util.Random;

public class MapGeneration {

    Random r;
    int up = 0;
    int down = 1;
    int left = 2;
    int right = 3;
    int direction;
    int empty = 0;
    int wall = 1;

    public MapGeneration() {

        r = new Random();

    }

    public int[][] GenerateMap(int width, int height, int complexity) {

        int[][] map = new int[width][height];

        // Fill map in completely
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                map[i][j] = wall;

            }

        }

        // Dig out a room in the middle
        int roomX = Math.round(width / 2);
        int roomY = Math.round(height / 2);
        int roomWidth = 10;
        int roomHeight = 10;

        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                if (i >= roomX && i <= roomX + roomWidth && j >= roomY && j <= roomY + roomHeight) {

                    map[i][j] = 0;

                }

            }

        }

        // Pick random wall
        boolean foundWall = false;
        int tryX = 0;
        int tryY = 0;


        while (!foundWall) {

            tryX = r.nextInt(width);
            tryY = r.nextInt(height);

            int square = map[tryX][tryY];

            if  (square == empty) {

                if (map[tryX - 1][tryY] == wall) { // Check left

                    foundWall = true;
                    direction = left;

                } else if (map[tryX + 1][tryY] == wall) { // Check right

                    foundWall = true;
                    direction = right;

                } else if (map[tryX][tryY - 1] == wall) { // Check bellow

                    foundWall = true;
                    direction = down;

                } else if (map[tryX][tryY + 1] == wall) { // Check above

                    foundWall = true;
                    direction = up;

                }

            }

        }

        // Add corridor
        int corridorWidth = r.nextInt(3) + 1;
        int corridorLength = r.nextInt(10) + 1;

        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                if (direction == right) {

                    if (i >= tryX && i <= tryX + corridorLength && j >= tryY && j <= tryY + corridorWidth) {

                        map[i][j] = empty;

                    }

                }

                if (direction == up) {

                    if (i >= tryX && i <= corridorWidth + tryY && j >= tryY && j <= tryY + corridorLength) {

                        map[i][j] = empty;

                    }

                }

            }

        }

        return map;

    }

}
