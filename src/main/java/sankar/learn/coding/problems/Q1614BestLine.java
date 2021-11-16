package sankar.learn.coding.problems;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        //coords.add(Coordinate.from(1.0f, 1.0f));
        //coords.add(Coordinate.from(3.0f, 7.2f));
        //coords.add(Coordinate.from(3.3f, -3.4f));
         coords.add(Coordinate.from(6.2f, 1.0f));
        //coords.add(Coordinate.from(2.0f, -3.0f));
         coords.add(Coordinate.from(2.0f, -4.0f));
        //coords.add(Coordinate.from(8.4f, 1.0f));
        //coords.add(Coordinate.from(9.0f, 2.34f));
        //Line l = Line.from(Coordinate.from(1.0f, 1.0f), Coordinate.from(3.3f, -3.4f));
        //System.out.println(getCountOfPointsInLine(l, coords));
        //TODO: mathematical problem: a line should have two points, but the above two points (6.2,1.0) and (2.0, -4.0) has only one point crossing
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
        private BigDecimal yIntercept;
        private BigDecimal slope;
        private BigDecimal xIntercept;
        private boolean isVertical;
        private Coordinate c1;
        private Coordinate c2;
        private static final MathContext context = new MathContext(5, RoundingMode.HALF_EVEN);

        public static Line from(Coordinate c1, Coordinate c2) {
            return new Line(c1, c2);
        }

        private Line(Coordinate c1, Coordinate c2) {
            this.c1 = c1;
            this.c2 = c2;

            if (c2.getX().compareTo(c1.getX()) == 0) {
                /**
                 * This problem gives raise to a specific problem handling division by zero. what if we have infinite slope,
                 * We won't have a y-intercept. vertical lines are represented by x intercept x = x1
                 */
                xIntercept = c1.getX();
                isVertical = true;
            } else {
                //slope = (y2 - y1)/(x2- x1)
                slope = c2.getY().subtract(c1.getY()).divide(c2.getX().subtract(c1.getX()), context);
                //y-intercept => y=mx+b => b = y-(mx)
                yIntercept = c1.getY().subtract(slope.multiply(c1.getX()));
            }
        }

        public BigDecimal getyIntercept() {
            return yIntercept;
        }

        public BigDecimal getSlope() {
            return slope;
        }

        public BigDecimal getxIntercept() {
            return xIntercept;
        }

        public boolean isVerticalLine() {
            return isVertical;
        }

        public boolean crossesCoordinate(Coordinate c) {
            //y = mx + b;
            if (isVertical) {
                return c.getX().compareTo(xIntercept) == 0;
            } else {
                BigDecimal y = slope.multiply(c.getX(), context).add(yIntercept);
                return y.compareTo(c.getY()) == 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;
            Line line = (Line) o;
            return isVertical ? ((Line) o).getxIntercept().compareTo(xIntercept) == 0 : line.getyIntercept().compareTo(getyIntercept()) == 0 && line.getSlope().compareTo(getSlope()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getyIntercept(), getSlope(), getxIntercept(), isVertical);
        }

        @Override
        public String toString() {
            return "Line{" +
                    "yIntercept=" + yIntercept +
                    ", slope=" + slope +
                    ", xIntercept=" + xIntercept +
                    ", isVertical=" + isVertical +
                    ", c1=" + c1 +
                    ", c2=" + c2 +
                    '}';
        }
    }

    static final class Coordinate {
        private BigDecimal x;
        private BigDecimal y;

        private Coordinate(float x, float y) {
            this.x = new BigDecimal(Float.toString(x));
            this.y = new BigDecimal(Float.toString(y));
        }

        public static Coordinate from(float x, float y) {
            return new Coordinate(x, y);
        }

        public BigDecimal getX() {
            return x;
        }

        public BigDecimal getY() {
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
