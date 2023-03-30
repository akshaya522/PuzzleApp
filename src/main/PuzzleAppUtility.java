package main;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PuzzleAppUtility {

    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";

    /**
     * This utility function converts user entered pt string to coordinate string
     * @param userInput - point string
     * @return coordStr - point in coordinate format
     * */
    public static String convertUserInputToCoordStr(String userInput) {
        return convertPointToCoordStr(convertUserInputToPoint(userInput));
    }

    /**
     * This utility function converts user entered pt string to a Point(x,y)
     * @param userInput - point string
     * @return point - Point entered by user
     * */
    public static Point convertUserInputToPoint(String userInput) {
        String[] xy = userInput.split(" ");
        Integer x = Integer.valueOf(xy[0]);
        Integer y = Integer.valueOf(xy[1]);
        return new Point(x, y);
    }

    /**
     * This utility function converts a Point to a coordinate string format
     * @param point - point to be converted
     * @return coordStr - point in coordinate format
     * */
    public static String convertPointToCoordStr(Point point) {
        return "(" + point.x + "," + point.y + ")";
    }

    /**
     * This utility function generated a random number between range
     * @param min - lower limit of range inclusive
     * @param max - higher limit of range inclusive
     * @return random number generated within and including range
     * */
    public static Integer getRandomNumber(Integer min, Integer max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    private static Integer crossPdt(List<Point> pts) {
        Integer x1 = pts.get(1).x - pts.get(0).x;
        Integer y1 = pts.get(1).y - pts.get(0).y;
        Integer x2 = pts.get(2).x - pts.get(0).x;
        Integer y2 = pts.get(2).y - pts.get(0).y;

        return ((x1*y2) - (y1*x2));
    }

    /**
     * This utility function validates if a new point would keep the polygon convex
     * @param pts - existing list of vertices of polygon
     * @param newPt - new point entered to be added to polygon
     * @return boolean denoting if polygon would remain convex
     * */
    public static boolean validateNewPtIsConvex(List<Point> pts, Point newPt) {
        // make deep copy
        List<Point> tempPts = new ArrayList<>(pts);
        tempPts.add(newPt);

        Integer numOfEdges = tempPts.size();
        Integer prev = 0;
        Integer curr = 0;

        for (int i = 0; i < numOfEdges; i++) {
            List<Point> temp = new ArrayList<>();
            temp.add(tempPts.get(i));
            temp.add(tempPts.get((i+1) % numOfEdges));
            temp.add(tempPts.get((i+2) % numOfEdges));

            curr = crossPdt(temp);

            if (curr != 0) {
                if (curr * prev < 0) {
                    return false;
                } else {
                    prev = curr;
                }
            }
        }
        return true;
    }

    /**
     * This utility function validates if a point is inside a convex polygon, inclusive of border of
     * @param pts - existing list of vertices of polygon
     * @param testPt - new point to be tested in polygon
     * @return boolean denoting if testPt is within polygon
     * */
    public static boolean isPtInsideConvexPolygon(List<Point> pts, Point testPt) {
        if (pts.contains(testPt)) {
            return true;
        } else {

        }
        Integer numOfPoints = pts.size();
        String prevSide = null;

        Integer index = 0;
        for (Point pt : pts) {
            Point a = pts.get(index);
            Point b = pts.get((index + 1) % numOfPoints);
            index++;

            Point affineSeg = getSubPt(b, a);
            Point affinePt = getSubPt(testPt, a);
            String currentSide = getSide(affineSeg, affinePt);

            if (currentSide == null) {
                return false;
            } else if (prevSide == null) {
                prevSide = currentSide;
            } else if (prevSide != currentSide) {
                return false;
            }
        }

        return true;
    }

    /**
     * This utility function validates if a point entered is of correct "x y" format
     * and carries on to carry other furthur validation
     * @param points - existing list of vertices of polygon
     * @param userPt - new point to be validated
     * @param forRandomGen - boolean denoting if point is added by user or randomly generated
     * @return boolean denoting if point is valid
     * */
    public static boolean validatePoint(List<Point> points, String userPt, Boolean forRandomGen) {
        /** Check if coordinate format is valid */
        if (userPt.matches("^[-]?[0-9]+\s[-]?[0-9]+$")) {
            Point newPt = convertUserInputToPoint(userPt);
            return validatePoint(points, newPt, forRandomGen);
        } else {
            System.out.println("Incorrect format!");
            System.out.println("Not adding new coordinates to current shape. \n");
            return false;
        }
    }

    /**
     * This utility function validates if a point
     * [1] is not a duplicate point - if only 1 existing point in polygon
     * [2] is not making polygon concave - if 2 or more existing points in polygon
     * @param points - existing list of vertices of polygon
     * @param newPt - new point to be validated
     * @param forRandomGen - boolean denoting if point is added by user or randomly generated
     * @return boolean denoting if point is valid
     * */
    public static boolean validatePoint(List<Point> points, Point newPt, Boolean forRandomGen) {
        /** Check if duplicate point */
        if (points.contains(newPt)) {
            if (!forRandomGen) {
                printErrorMsg(newPt);
            }
            return false;
        } else {
            /** Check if new point maintains polygon as convex */
            if (points.size() > 1 && !validateNewPtIsConvex(points, newPt)) {
                if (!forRandomGen) {
                    printErrorMsg(newPt);
                }
                return false;
            } else {
                return true;
            }
        }
    }

    public static void printErrorMsg(Point newPt) {
        System.out.println("New coordinates" + convertPointToCoordStr(newPt) + " is invalid!!!");
        System.out.println("Not adding new coordinates to the current shape. \n");
    }

    private static Integer getCosineSign(Point a, Point b) {
        return Math.subtractExact( Math.multiplyExact(a.x, b.y),Math.multiplyExact(a.y, b.x));
    }

    private static Point getSubPt(Point a, Point b) {
        return new Point(Math.subtractExact(a.x, b.x), Math.subtractExact(a.y, b.y));
    }

    private static String getSide(Point a, Point b) {
        Integer val = getCosineSign(a, b);
        if (val < 0) {
            return LEFT;
        } else if (val > 0) {
            return RIGHT;
        } else {
            return null;
        }
    }
}
