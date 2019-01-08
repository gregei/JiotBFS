package com.gregei.jiobfs;

/**
 * Class to represent a Node which holds the distance from the origin and a point.
 */
public class Node {
    Point point;
    int distance;

    public Node(Point point, int distance) {
        this.point = point;
        this.distance = distance;
    }
}
