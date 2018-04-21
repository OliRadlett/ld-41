package com.ld41.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class hCorridor {

    List<Integer> x;
    int y;

    public hCorridor(int x1, int x2, int y) {

        System.out.println(x1 + " and " + x2 + " and " + y);

        this.x = new ArrayList<Integer>();

        IntStream.range(Math.min(x1, x2), Math.max(x1, x2) + 1).forEachOrdered(n -> {

            this.x.add(n);
            this.y = y;

        });

    }

}
