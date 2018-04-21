package com.ld41.map;

public class Room {

    int x1;
    int x2;
    int y1;
    int y2;
    int width;
    int height;
    int centreX;
    int centreY;

    public Room(int x, int y, int width, int height) {

        this.x1 = x;
        this.x2 = x + width;
        this.y1 = y;
        this.y2 = y + height;
        this.centreX = (int) Math.floor((x1 + x2) / 2);
        this.centreY = (int) Math.floor((y1 + y2) / 2);

    }

    public boolean intersects(Room room) {

        return (x1 <= room.x2 && x2 >= room.x1 && y1 <= room.y2 && room.y2 >= room.y1);

    }

}
