import java.awt.*;
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
            userAction = scanner.nextLine();
            if (!(userAction.equals("1") || userAction.equals("2"))) {
                System.out.println("Please enter 1 or 2");
            } else {
                if (userAction.equals("1")) {
                    createShape();
                } else {
                    // generate random
                }
            }
            } while(!(userAction.equals("1") || userAction.equals("2")));
    }

    private static void createShape() {
        String userInput = "";
        ArrayList<Point> listOfPoints = new ArrayList<Point>();

        do {
            if (listOfPoints.size() == 0) {
                System.out.println("Please enter coordinates 1 in x y format");
                userInput = scanner.nextLine();
                if (!validatePoint(listOfPoints, userInput)) {
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
                userInput = scanner.nextLine();
                if (!validatePoint(listOfPoints, userInput)) {
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
                userInput = scanner.nextLine();
                if (!userInput.equals("#")) {
                    if (!validatePoint(listOfPoints, userInput)) {
                    } else {
                        listOfPoints.add(puzzleHelper.convertUserInputToPoint(userInput));
                    }
                } else {
                    System.out.println("Your finalized shape is");
                    int i = 1;
                    for(Point pt : listOfPoints) {
                        System.out.println(i++ + ":" + puzzleHelper.convertPointToCoordStr(pt));
                    }
                    System.out.println("\n");
                    System.out.println("Please key in test coordinates in x y format or enter # to quit the game");

                    userInput = scanner.nextLine();
                    if (!userInput.equals("#")) {
                        otherfunction();
                        break;
                    } else {
                        System.out.println("Thank you for playing GIC geometry puzzle app");
                        System.out.println("Have a nice day! :-) ");
                    }
                }
            }
        } while (!userInput.equals("#"));
    }

    private static void otherfunction() {
        System.out.println("TEST!");
        String userInput = scanner.nextLine();
    }


    private static boolean validatePoint(ArrayList<Point> points, String userInput) {
        if (userInput.matches("^[0-9]+\s[0-9]+$")) {
            Point newPt = puzzleHelper.convertUserInputToPoint(userInput);
            if (points.contains(newPt)) {
                System.out.println("New coordinates" + puzzleHelper.convertUserInputToCoordStr(userInput) + " is invalid!!!");
                System.out.println("Not adding new coordinates to the current shape. \n");
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println("Incorrect format!");
            System.out.println("Not adding new coordinates to current shape. \n");
            return false;
        }
    }
}