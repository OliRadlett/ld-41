package com.ld41.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGeneration {

    Random r;
    List<Room> rooms;
    List<hCorridor> hCorridors;
    List<vCorridor> vCorridors;
    int empty = 0;
    int wall = 1;
    int numRooms;

    public MapGeneration() {

        r = new Random();
        rooms = new ArrayList<Room>();
        hCorridors = new ArrayList<hCorridor>();
        vCorridors = new ArrayList<vCorridor>();

    }

    public int[][] GenerateMap(int width, int height, int numRooms) {

        this.numRooms = numRooms;

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

                        }

                    }

                }

            }

        }

        return map;

    }

    public void generateRooms() {

        for (int i = 0; i < numRooms; i++) {

            int width = 10 + r.nextInt(15);
            int height = 10 + r.nextInt(15);
            int x = r.nextInt(100 - width - 1) + 1;
            int y = r.nextInt(100 - height - 1) + 1;

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


