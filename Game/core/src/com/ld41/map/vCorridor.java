package com.ld41.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class vCorridor {

    int x;
    List<Integer> y;

    public vCorridor(int y1, int y2, int x) {

        this.y = new ArrayList<Integer>();
        IntStream.range(Math.min(y1, y2), Math.max(y1, y2) + 1).forEachOrdered(n -> {

            this.x = x;
            this.y.add(n);

        });

    }

}
