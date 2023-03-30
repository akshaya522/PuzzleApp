package test;

import main.PuzzleApp;
import main.PuzzleAppUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleAppTest {

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;
    private List<Point> mockPolygon = Arrays.asList(new Point(1, 1), new Point(5, 1), new Point(5, 5));
    private static Point mockPt = new Point(5,1);
    private static String mockUserStr = "5 1";

    PuzzleApp puzzleApp = new PuzzleApp();
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void convertUserInputToCoordStr() {
        assertEquals("(5,1)", PuzzleAppUtility.convertUserInputToCoordStr(mockUserStr));
    }

}