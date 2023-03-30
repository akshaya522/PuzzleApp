package main;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleApp {
    public static Scanner scanner = new Scanner(System.in);
    public static PuzzleAppHelper puzzleHelper = new PuzzleAppHelper();
    public static void main(String[] args) {

        System.out.println("Welcome to the GIC Geometry Puzzle App");
        System.out.println("[1] Create a custom shape");
        System.out.println("[2] Generate a random shape");

        String userAction;

        do {
            userAction = scanner.nextLine().trim();
            if (!(userAction.equals("1") || userAction.equals("2"))) {
                System.out.println("Please enter 1 or 2");
            } else {
                if (userAction.equals("1")) {
                    createShape();
                } else {
                    generateRandomShape();
                }
            }
            } while(!(userAction.equals("1") || userAction.equals("2")));
    }

    private static void generateRandomShape() {
        List<Point> randomPolygon = generateRandomConvexShape();
        System.out.println("Your random shape is");
        int index = 1;
        for(Point pt : randomPolygon) {
            System.out.println(index++ + ":" + puzzleHelper.convertPointToCoordStr(pt));
        }
        System.out.println("Please key in test coordinates in x y format or enter # to quit the game");

        String userInput = scanner.nextLine().trim();
        testPuzzle(randomPolygon, userInput);
    }

    private static void createShape() {
        String userInput = "";
        ArrayList<Point> listOfPoints = new ArrayList<Point>();

        do {
            if (listOfPoints.size() == 0) {
                System.out.println("Please enter coordinates 1 in x y format");
                userInput = scanner.nextLine().trim();
                if (!puzzleHelper.validatePoint(listOfPoints, userInput, false)) {
                } else {
                    listOfPoints.add(puzzleHelper.convertUserInputToPoint(userInput));
                }
            } else if (listOfPoints.size() < 3) {
                System.out.println("Your current shape is incomplete");
                int index = 1;
                for(Point pt : listOfPoints) {
                    System.out.println(index++ + ":" + puzzleHelper.convertPointToCoordStr(pt));
                }
                System.out.println("Please enter coordinates " + (listOfPoints.size()+1 + " in x y format"));
                userInput = scanner.nextLine().trim();
                if (!puzzleHelper.validatePoint(listOfPoints, userInput, false)) {
                } else {
                    listOfPoints.add(puzzleHelper.convertUserInputToPoint(userInput));
                }
            } else {
                System.out.println("Your current shape is valid and is complete");
                int index = 1;
                for(Point pt : listOfPoints) {
                    System.out.println(index++ + ":" + puzzleHelper.convertPointToCoordStr(pt));
                }
                System.out.println("Please enter # to finalize your shape or enter coordinates " + (listOfPoints.size()+1) + " in x y format");
                userInput = scanner.nextLine().trim();
                if (!userInput.equals("#")) {
                    if (!puzzleHelper.validatePoint(listOfPoints, userInput, false)) {
                    } else {
                        listOfPoints.add(puzzleHelper.convertUserInputToPoint(userInput));
                    }
                } else {
                    System.out.println("Your finalized shape is");
                    int i = 1;
                    for(Point pt : listOfPoints) {
                        System.out.println(i++ + ":" + puzzleHelper.convertPointToCoordStr(pt));
                    }
                    System.out.println("Please key in test coordinates in x y format or enter # to quit the game");

                    userInput = scanner.nextLine().trim();
                    if (!userInput.equals("#")) {
                        testPuzzle(listOfPoints, userInput);
                        break;
                    } else {
                        System.out.println("Thank you for playing GIC geometry puzzle app");
                        System.out.println("Have a nice day! :-) ");
                    }
                }
            }
        } while (!userInput.equals("#"));
    }

    private static void testPuzzle(List<Point> pts, String userInputPt) {
        String userInput = userInputPt;

        do {
            Point newPt = puzzleHelper.convertUserInputToPoint(userInput);
            Boolean inPolygon = puzzleHelper.isPtInsideConvexPolygon(pts, newPt);
            System.out.println("Your finalized shape is");
            int i = 1;
            for(Point pt : pts) {
                System.out.println(i++ + ":" + puzzleHelper.convertPointToCoordStr(pt));
            }
            String res = inPolygon ? "Coordinates " + puzzleHelper.convertPointToCoordStr(newPt) + " is within your finalized shape" :
                    "Sorry, coordinates " + puzzleHelper.convertPointToCoordStr(newPt) + " is outside of your finalized shape";
            System.out.println(res);
            System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
            userInput = scanner.nextLine().trim();

            if (userInput.equals("#")) {
                System.out.println("Thank you for playing GIC geometry puzzle app");
                System.out.println("Have a nice day! :-) ");
            }
        } while (!userInput.equals("#"));
    }

    private static List<Point> generateRandomConvexShape() {
        List<Point> polygon = new ArrayList<>();
        Integer numOfPts = puzzleHelper.getRandomNumber(3, 8);

        do {
           Point newRandomPoint = new Point(puzzleHelper.getRandomNumber(0, 100), puzzleHelper.getRandomNumber(1,100));
           if (puzzleHelper.validatePoint(polygon, newRandomPoint, true)) {
                polygon.add(newRandomPoint);
           }
        } while (polygon.size() != numOfPts);
        return polygon;
    }
}