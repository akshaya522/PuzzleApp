package main;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleAppHelper {

    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";

    public static String convertUserInputToCoordStr(String userInput) {
        return convertPointToCoordStr(convertUserInputToPoint(userInput));
    }

    public static Point convertUserInputToPoint(String userInput) {
        String[] xy = userInput.split(" ");
        Integer x = Integer.valueOf(xy[0]);
        Integer y = Integer.valueOf(xy[1]);
        return new Point(x, y);
    }

    public static String convertPointToCoordStr(Point point) {
        return "(" + point.x + "," + point.y + ")";
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static boolean validateSamePlane(List<Point> pts, Point newPt) {
        // Check area of triangle formed by three points to check if on the same plane
        Integer areaOfTriangle = Math.multiplyExact(pts.get(0).x, Math.subtractExact(pts.get(1).y, newPt.y)) +
                Math.multiplyExact(pts.get(1).x, Math.subtractExact(newPt.y, pts.get(0).y)) +
                Math.multiplyExact(newPt.x, Math.subtractExact(pts.get(0).y, pts.get(1).y));

        if (areaOfTriangle == 0) {
            return false;
        } else {
            return true;
        }
    }
    private static Integer crossPdt(List<Point> pts) {
        Integer x1 = pts.get(1).x - pts.get(0).x;
        Integer y1 = pts.get(1).y - pts.get(0).y;
        Integer x2 = pts.get(2).x - pts.get(0).x;
        Integer y2 = pts.get(2).y - pts.get(0).y;

        return ((x1*y2) - (y1*x2));
    }

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

    public static boolean isPtInsideConvexPolygon(List<Point> pts, Point newPt) {

        if (pts.contains(newPt)) {
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
            Point affinePt = getSubPt(newPt, a);
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

    public static boolean validatePoint(List<Point> points, String userPt, Boolean forRandomGen) {
        /** Check if coordinate format is valid */
        if (userPt.matches("^[0-9]+\s[0-9]+$")) {
            Point newPt = convertUserInputToPoint(userPt);
            return validatePoint(points, newPt, forRandomGen);
        } else {
            System.out.println("Incorrect format!");
            System.out.println("Not adding new coordinates to current shape. \n");
            return false;
        }
    }


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
}
