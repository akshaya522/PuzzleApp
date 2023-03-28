import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleAppHelper {

    String convertUserInputToCoordStr(String userInput) {
        return convertPointToCoordStr(convertUserInputToPoint(userInput));
    }

    Point convertUserInputToPoint(String userInput) {
        String[] xy = userInput.split(" ");
        Integer x = Integer.valueOf(xy[0]);
        Integer y = Integer.valueOf(xy[1]);
        return new Point(x, y);
    }

    String convertPointToCoordStr(Point point) {
        return "(" + point.x + "," + point.y + ")";
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public boolean validateSamePlane(List<Point> pts, Point newPt) {
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

    private Integer crossPdt(List<Point> pts) {
        Integer x1 = pts.get(1).x - pts.get(0).x;
        Integer y1 = pts.get(1).y - pts.get(0).y;
        Integer x2 = pts.get(2).x - pts.get(0).x;
        Integer y2 = pts.get(2).y - pts.get(2).y;

        return ((x1*y2) - (y1*x2));
    }

    public boolean validateNewPtIsConvex(List<Point> pts, Point newPt) {
        // make deep copy
        List<Point> tempPts = new ArrayList<>(pts);
        tempPts.add(newPt);

        Integer numOfEdges = tempPts.size();
        Integer prev = 0;
        Integer curr = 0;

        for (int i = 0; i< numOfEdges; i++) {
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
}
