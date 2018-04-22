package com.ld41.map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGeneration {

    Random r;
    List<Room> rooms;
    List<hCorridor> hCorridors;
    List<vCorridor> vCorridors;
    public List<Rectangle> collisions;
    public List<Spider> spiders;
    public int empty = 0;
    public int wall = 1;
    public int chest = 2;
    int numRooms;
    int roomWidth;
    int roomHeight;
    public DemoCharacter character;
    public Rectangle chestRect;


    public MapGeneration() {

        r = new Random();
        rooms = new ArrayList<Room>();
        hCorridors = new ArrayList<hCorridor>();
        vCorridors = new ArrayList<vCorridor>();
        collisions = new ArrayList<Rectangle>();
        spiders = new ArrayList<Spider>();

    }

    public int[][] GenerateMap(int width, int height, int numRooms) {

        this.numRooms = numRooms;
        this.roomWidth = width;
        this.roomHeight = height;

        hCorridors.clear();
        vCorridors.clear();
        rooms.clear();

        int[][] map = new int[width][height];

        // Fill map in completely
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                map[i][j] = wall;

            }

        }

        generateRooms();

        for (Room room : rooms) {

            if (rooms.indexOf(room) == 0) {

                character = new DemoCharacter(room.centreX * 32, room.centreY * 32, this);

            }

            for (int i = 0; i < width; i++) {

                for (int j = 0; j < height; j++) {

                    if (i >= room.x1 && i <= room.x2 && j >= room.y1 && j <= room.y2) {

                        map[i][j] = empty;

                    }

                }

            }

        }

        for (hCorridor corridor : hCorridors) {

            for (int i = 0; i < width; i++) {

                for (int j = 0; j < height; j++) {

                    for (int x : corridor.x) {

                        if (x == i && corridor.y == j) {

                            map[i][j] = empty;
                            map[i][j + 1] = empty;
                            map[i][j - 1] = empty;

                        }

                    }

                }

            }

        }

        for (vCorridor corridor : vCorridors) {

            for (int i = 0; i < width; i++) {

                for (int j = 0; j < height; j++) {

                    for (int y : corridor.y) {

                        if (corridor.x == i && y == j) {

                            map[i][j] = empty;
                            map[i + 1][j] = empty;
                            map[i - 1][j] = empty;

                        }

                    }

                }

            }

        }


        // Edges
        //top and bottom
        for (int i = 0; i < width; i++) {

            map[i][0] = wall;
            map[i][height - 1] = wall;

        }

        //left and right
        for (int j = 0; j < height; j++) {

            map[0][j] = wall;
            map[width - 1][j] = wall;

        }

        // Add collisions
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                if (map[i][j] == wall) {

                    collisions.add(new Rectangle(i * 32, j * 32, 32, 32));

                }

            }

        }

        int numMonsters = 0;

        while (numMonsters < 16) {

            int monsterX = r.nextInt(width);
            int monsterY = r.nextInt(height);

            if (map[monsterX][monsterY] == empty) {

                if ((monsterX * 32 != character.getX() && monsterY * 32 != character.getY())) {

                    spiders.add(new Spider(monsterX * 32, monsterY * 32));
                    numMonsters++;

                }

            }

        }

        boolean chestPlaced = false;
        int distFromPlayer;
        Vector2 playerPos = new Vector2(character.x, character.y);

        while (!chestPlaced) {

            int chestPosX = r.nextInt(width);
            int chestPosY = r.nextInt(height);

            if (map[chestPosX][chestPosY] == empty) {

                Vector2 chestPos = new Vector2(chestPosX, chestPosY);
                distFromPlayer = (int) chestPos.dst(playerPos);

                if (distFromPlayer >= 500) {

                    map[chestPosX][chestPosY] = chest;
                    chestRect = new Rectangle(chestPosX * 32, chestPosY * 32, 32, 32);
                    chestPlaced = true;

                }

            }

        }

        return map;

    }

    public void generateRooms() {

        for (int i = 0; i < numRooms; i++) {

            int width = 5 + r.nextInt(10);
            int height = 5 + r.nextInt(10);
            int x = r.nextInt(roomWidth - width - 1) + 1;
            int y = r.nextInt(roomHeight- height - 1) + 1;

            Room room = new Room(x, y, width, height);

            boolean failed = false;

            for (Room r : rooms) {

                if (room.intersects(r)) {

                    failed = true;
                    i--;
                    break;

                }

            }

            if (!failed) {

                // centreX and prevCentreX are the same for some reason.

                int centreX = room.centreX;
                int centreY = room.centreY;

                if (rooms.size() != 0) {

                    int prevCentreX = rooms.get(rooms.size() - 1).centreX;
                    int prevCentreY = rooms.get(rooms.size() - 1).centreY;

                    int dir = r.nextInt(2);
                    // horizontal
                    if (dir == 0) {

                        System.out.println("horizontal first");

                        hCorridors.add(new hCorridor(prevCentreX, centreX, prevCentreY));
                        vCorridors.add(new vCorridor(prevCentreY, centreY, centreX));

                    }

                    // vertical
                    if (dir == 1) {

                        System.out.println("vertical first");

                        vCorridors.add(new vCorridor(prevCentreY, centreY, prevCentreX));
                        hCorridors.add(new hCorridor(prevCentreX, centreX, centreY));

                    }

                }

            }

            if (!failed) {

                rooms.add(room);

            }

        }

    }

}


