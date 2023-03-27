import java.awt.*;

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
}
