package sankar.learn.coding.problems;

import java.math.BigDecimal;
import java.util.*;

public class Q1614BestLine {
    /**
     * Given a two dimension graph with points on it, find the line which passes the most number of points ?
     * HInts: #591,#520, #529, #563
     */
    /**
     * Logic to solve the problem:
     * create a map with with Line as key and the number of points it hits as value
     * Find all possible lines be made with the given set of points in graph.
     * For each line, find the number of points it hits. and record the same in the map
     * Once the map is populated for all possible lines, find the line which hits the most points.
     *
     * High Level Design:
     * Point can represented by x and y coordinates
     * Line can be represented by slope and y-intercept y=mx+b. m = slope, b = y-intercept
     * Logic to find if a given point is present in line should be part of Line
     * Any two points can make a line, Given two coordinates c1 and c2 Line(c1->c2) is same as Line(c2-> c1). Use this while constructing possible lines.
     */

    public static void main(String[] args) {
        List<Coordinate> coords = new ArrayList<>();
        coords.add(Coordinate.from(1.0f, 1.0f));
        coords.add(Coordinate.from(3.0f, 7.2f));
        coords.add(Coordinate.from(3.3f, -3.4f));
        coords.add(Coordinate.from(6.2f, 1.0f));
        coords.add(Coordinate.from(2.0f, -3.0f));
        coords.add(Coordinate.from(2.0f, -4.0f));
        coords.add(Coordinate.from(8.4f, 1.0f));
        coords.add(Coordinate.from(9.0f, 2.34f));
        //Line l = Line.from(Coordinate.from(1.0f, 1.0f), Coordinate.from(3.3f, -3.4f));
        //System.out.println(getCountOfPointsInLine(l, coords));
        solve(coords);
    }

    public static void solve(List<Coordinate> coordinates) {
        int length = coordinates.size();
        Map<Line, Integer> pointsInLine = new HashMap<>();
        //create line with possible coordinates
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                Line l = Line.from(coordinates.get(i), coordinates.get(j));
                int hits = getCountOfPointsInLine(l, coordinates);
                pointsInLine.put(l, hits);
            }
        }
        //get line with max count, but what if all multiple lines has same hit count, return all lines
        System.out.println(pointsInLine);
    }

    public static int getCountOfPointsInLine(Line l, List<Coordinate> coordinates) {
        int count = 0;
        for (Coordinate c : coordinates) {
            if (l.crossesCoordinate(c)) {
                ++count;
            }
        }
        return count;
    }

    static final class Line {
        private float yIntercept;
        private float slope;
        private Coordinate c1;
        private Coordinate c2;

        public static Line from(Coordinate c1, Coordinate c2) {
            return new Line(c1, c2);
        }

        private Line(Coordinate c1, Coordinate c2) {
            this.c1 = c1;
            this.c2 = c2;
            //slope = (y2 - y1)/(x2- x1)
            slope = (c2.getY() - c1.getY())/(c2.getX() - c1.getX());
            //y-intercept => y=mx+b => b = y-(mx)
            yIntercept = c1.getY() - (slope * c1.getX());
        }

        public float getyIntercept() {
            return yIntercept;
        }

        public float getSlope() {
            return slope;
        }

        public boolean crossesCoordinate(Coordinate c) {
            //y = mx + b;
            float y = (slope * c.getX()) + yIntercept;
            return (Math.round(y*1000) == Math.round(c.getY()*1000)) ? true : false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;
            Line line = (Line) o;
            return Float.compare(line.getyIntercept(), getyIntercept()) == 0 && Float.compare(line.getSlope(), getSlope()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getyIntercept(), getSlope());
        }

        @Override
        public String toString() {
            return "Line{" +
                    ", c1=" + c1 +
                    ", c2=" + c2 +
                    '}';
        }
    }

    static final class Coordinate {
        private float x;
        private float y;

        private Coordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate from(float x, float y) {
            return new Coordinate(x, y);
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
