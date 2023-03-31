package main;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleApp {
    public static Scanner scanner = new Scanner(System.in);
    public static PuzzleAppUtility puzzleUtility = new PuzzleAppUtility();
    public static void main(String[] args) {
        System.out.println("Welcome to the GIC geometry puzzle app");
        System.out.println("[1] Create a custom shape");
        System.out.println("[2] Generate a random shape");

        String userAction;
        do {
            userAction = scanner.nextLine().trim();
            /** Validate user input 1 or 2 only */
            if (!(userAction.equals("1") || userAction.equals("2"))) {
                System.out.println("Please enter 1 or 2");
            } else {
                if (userAction.equals("1")) {
                    createPolygon();
                } else {
                    generateRandomPolygonAndTest();
                }
            }
        } while(!(userAction.equals("1") || userAction.equals("2")));
    }

    /**
     * This function creates polygon according to user input
     * and continues to puzzle according to user input
     * */
    public static void createPolygon() {
        String userInput = "";
        List<Point> listOfPoints = new ArrayList<>();

        do {
            /** First point */
            if (listOfPoints.size() == 0) {
                System.out.println("Please enter coordinates 1 in x y format");
                userInput = scanner.nextLine().trim();
                /** Only validate coordinate format */
                if (!puzzleUtility.validatePoint(listOfPoints, userInput, false)) {
                } else {
                    listOfPoints.add(puzzleUtility.convertUserInputToPoint(userInput));
                }
                /** Polygon is still incomplete */
            } else if (listOfPoints.size() < 3) {
                System.out.println("Your current shape is incomplete");
                int index = 1;
                for(Point pt : listOfPoints) {
                    System.out.println(index++ + ":" + puzzleUtility.convertPointToCoordStr(pt));
                }
                System.out.println("Please enter coordinates " + (listOfPoints.size()+1 + " in x y format"));
                userInput = scanner.nextLine().trim();
                /** Only validate coordinate format */
                if (!puzzleUtility.validatePoint(listOfPoints, userInput, false)) {
                } else {
                    listOfPoints.add(puzzleUtility.convertUserInputToPoint(userInput));
                }
                /** Polygon is complete */
            } else {
                System.out.println("Your current shape is valid and is complete");
                int index = 1;
                for(Point pt : listOfPoints) {
                    System.out.println(index++ + ":" + puzzleUtility.convertPointToCoordStr(pt));
                }
                System.out.println("Please enter # to finalize your shape or enter coordinates " + (listOfPoints.size()+1) + " in x y format");
                userInput = scanner.nextLine().trim();
                /** User wishes to continue adding coordinates */
                if (!userInput.equals("#")) {
                    /** Validate coordinate format and if polygon convex with new point */
                    if (!puzzleUtility.validatePoint(listOfPoints, userInput, false)) {
                    } else {
                        listOfPoints.add(puzzleUtility.convertUserInputToPoint(userInput));
                    }
                    /** User wishes to finalize shape */
                } else {
                    System.out.println("Your finalized shape is");
                    int i = 1;
                    for(Point pt : listOfPoints) {
                        System.out.println(i++ + ":" + puzzleUtility.convertPointToCoordStr(pt));
                    }
                    System.out.println("Please key in test coordinates in x y format or enter # to quit the game");

                    userInput = scanner.nextLine().trim();
                    /** User wishes to test coordinate */
                    if (!userInput.equals("#")) {
                        testPuzzle(listOfPoints, userInput);
                        break;
                        /** User wishes to exit */
                    } else {
                        System.out.println("Thank you for playing GIC geometry puzzle app");
                        System.out.println("Have a nice day!");
                    }
                }
            }
        } while (!userInput.equals("#"));
    }

    /**
     * This function generates a random convex polygon with 3-8 vertices
     * and continues to puzzle according to user input
     * */
    public static void generateRandomPolygonAndTest() {
        List<Point> randomPolygon = generateRandomConvexPolygon();
        System.out.println("Your random shape is");
        int index = 1;
        for(Point pt : randomPolygon) {
            System.out.println(index++ + ":" + puzzleUtility.convertPointToCoordStr(pt));
        }
        System.out.println("Please key in test coordinates in x y format or enter # to quit the game");

        String userInput = scanner.nextLine().trim();
        testPuzzle(randomPolygon, userInput);
    }

    /**
     * This function generates a random convex polygon with 3-8 vertices
     * */
    private static List<Point> generateRandomConvexPolygon() {
        List<Point> polygon = new ArrayList<>();
        Integer numOfPts = puzzleUtility.getRandomNumber(3, 8);

        do {
            Point newRandomPoint = new Point(puzzleUtility.getRandomNumber(-100, 100), puzzleUtility.getRandomNumber(-100,100));
            if (puzzleUtility.validatePoint(polygon, newRandomPoint, true)) {
                polygon.add(newRandomPoint);
            }
        } while (polygon.size() != numOfPts);
        return polygon;
    }

    /**
     * This function tests if a user input point is within finalized polygon
     * @param pts - list of Point(x,y) denoting vertices of polygon
     * @param userInputPt - point to be tested as string
     * */
    private static void testPuzzle(List<Point> pts, String userInputPt) {
        String userInput = userInputPt;

        do {
            Point newPt = puzzleUtility.convertUserInputToPoint(userInput);
            Boolean inPolygon = puzzleUtility.isPtInsideConvexPolygon(pts, newPt);
            System.out.println("Your finalized shape is");
            int i = 1;
            for(Point pt : pts) {
                System.out.println(i++ + ":" + puzzleUtility.convertPointToCoordStr(pt));
            }
            String res = inPolygon ? "Coordinates " + puzzleUtility.convertPointToCoordStr(newPt) + " is within your finalized shape" :
                    "Sorry, coordinates " + puzzleUtility.convertPointToCoordStr(newPt) + " is outside of your finalized shape";
            System.out.println(res);
            System.out.println("Please key in test coordinates in x y format or enter # to quit the game");
            userInput = scanner.nextLine().trim();

            if (userInput.equals("#")) {
                System.out.println("Thank you for playing GIC geometry puzzle app");
                System.out.println("Have a nice day!");
            }
        } while (!userInput.equals("#"));
    }
}