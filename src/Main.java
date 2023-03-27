import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Welcome to the GIC Geometry Puzzle App");
        System.out.println("[1] Create a custom shape");
        System.out.println("[2] Generate a random shape");

        Integer userName = Integer.valueOf(scanner.nextLine());

        if (userName == 1) {
            createShape();
        } else if (userName == 2) {

        } else {
            System.out.println("Please enter 1 or 2");
        }
    }

    private static void createShape() {
        System.out.println("Please enter coordinates 1 in x y format");
        ArrayList<Point> listOfPoints = new ArrayList<Point>();
        String coords = scanner.nextLine();
        Integer x = Integer.valueOf(coords.substring(0,1));
        Integer y = Integer.valueOf(coords.substring(2,3));
        Point p = new Point(x, y);
        listOfPoints.add(p);

        while (listOfPoints.size() < 3) {
            System.out.println("Your current shape is incomplete");
            int index = 1;
            for(Point pt : listOfPoints) {
                System.out.println(index++ + ":(" + pt.x + "," + pt.y + ")");
            }

            System.out.println("Please enter coordinates " + (listOfPoints.size()+1 + " in x y format"));
            String newCoords = scanner.nextLine();
            Integer newX = Integer.valueOf(newCoords.substring(0,1));
            Integer newY = Integer.valueOf(newCoords.substring(2,3));
            Point newP = new Point(newX, newY);
            listOfPoints.add(newP);
        }

        String userInput = "";
        while (!userInput.equals("#")) {
            System.out.println("Your current shape is valid and is complete");
            int index = 1;
            for(Point pt : listOfPoints) {
                System.out.println(index++ + ":(" + pt.x + "," + pt.y + ")");
            }
            System.out.println("Please enter # to finalize your shape or enter coordinates " + (listOfPoints.size()+1) + " in x y format");
            userInput = scanner.nextLine().trim();
            if (!userInput.equals("#")) {
                Integer newX = Integer.valueOf(userInput.substring(0,1));
                Integer newY = Integer.valueOf(userInput.substring(2,3));
                Point newP = new Point(newX, newY);
                listOfPoints.add(newP);
            }
        }
    }
}