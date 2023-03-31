package test;

import main.PuzzleApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleAppTest {

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    private final InputStream originIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originIn);
    }

    @Test
    void validate_randomPolygon_EndsWithMsg() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1 1\n5 1\n5 5\n#").getBytes());
        System.setIn(in);
        PuzzleApp.generateRandomPolygonAndTest();
        assertTrue(outContent.toString().endsWith("Thank you for playing GIC geometry puzzle app\r\n" +
                "Have a nice day!\r\n"));
    }

    @Test
    void validate_randomPolygon_startsWithPolygonGeneration() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1 1\n5 1\n5 5\n#").getBytes());
        System.setIn(in);
        PuzzleApp.generateRandomPolygonAndTest();
        assertTrue(outContent.toString().startsWith("Your random shape is\r\n"));
    }

    @Test
    void validate_wrongCoordinateInput_throwIncorrectFormat() {
        ByteArrayInputStream in = new ByteArrayInputStream(("3\n1\n1 1\n5 1\n5 5\n#\n#").getBytes());
        System.setIn(in);
        PuzzleApp.createPolygon();
        String result = "Please enter coordinates 1 in x y format\r\n" +
                "Incorrect format!\r\n" +
                "Not adding new coordinates to current shape.\n" +
                "\r\n" +
                "Please enter coordinates 1 in x y format\r\n" +
                "Incorrect format!\r\n" +
                "Not adding new coordinates to current shape.\n" +
                "\r\n" +
                "Please enter coordinates 1 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "Please enter coordinates 3 in x y format\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Thank you for playing GIC geometry puzzle app\r\n" +
                "Have a nice day!\r\n";
        assertEquals(result.toString(), outContent.toString());
    }

    @Test
    void validate_validPolygonCoords_NoErrMsg() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1 1\n1 1\n5 1\n5 5\n#\n#").getBytes());
        System.setIn(in);
        PuzzleApp.createPolygon();
        String result = "Please enter coordinates 1 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "New coordinates(1,1) is invalid!!!\r\n" +
                "Not adding new coordinates to the current shape.\n" +
                "\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "Please enter coordinates 3 in x y format\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Thank you for playing GIC geometry puzzle app\r\n" +
                "Have a nice day!\r\n";
        assertEquals(result.toString(), outContent.toString());
    }

    @Test
    void validate_ConcavePolygon_InvalidCoordMsg() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1 1\n1 1\n5 1\n5 5\n4 0\n5 5\n#\n#").getBytes());
        System.setIn(in);
        PuzzleApp.createPolygon();
        String result = "Please enter coordinates 1 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "New coordinates(1,1) is invalid!!!\r\n" +
                "Not adding new coordinates to the current shape.\n" +
                "\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "Please enter coordinates 3 in x y format\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "New coordinates(4,0) is invalid!!!\r\n" +
                "Not adding new coordinates to the current shape.\n" +
                "\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "New coordinates(5,5) is invalid!!!\r\n" +
                "Not adding new coordinates to the current shape.\n" +
                "\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Thank you for playing GIC geometry puzzle app\r\n" +
                "Have a nice day!\r\n";
        assertEquals(result.toString(), outContent.toString());
    }

    @Test
    void validate_puzzleValidPoint_WithinPolygonMsg() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1 1\n1 1\n5 1\n5 5\n#\n3 2\n#").getBytes());
        System.setIn(in);
        PuzzleApp.createPolygon();
        String result = "Please enter coordinates 1 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "New coordinates(1,1) is invalid!!!\r\n" +
                "Not adding new coordinates to the current shape.\n" +
                "\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "Please enter coordinates 3 in x y format\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Coordinates (3,2) is within your finalized shape\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Thank you for playing GIC geometry puzzle app\r\n" +
                "Have a nice day!\r\n";
        assertEquals(result.toString(), outContent.toString());
    }

    @Test
    void validate_puzzleInValidPoint_OutsidePolygonMsg() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1 1\n1 1\n5 1\n5 5\n#\n4 0\n#").getBytes());
        System.setIn(in);
        PuzzleApp.createPolygon();
        String result = "Please enter coordinates 1 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "New coordinates(1,1) is invalid!!!\r\n" +
                "Not adding new coordinates to the current shape.\n" +
                "\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "Please enter coordinates 2 in x y format\r\n" +
                "Your current shape is incomplete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "Please enter coordinates 3 in x y format\r\n" +
                "Your current shape is valid and is complete\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please enter # to finalize your shape or enter coordinates 4 in x y format\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Your finalized shape is\r\n" +
                "1:(1,1)\r\n" +
                "2:(5,1)\r\n" +
                "3:(5,5)\r\n" +
                "Sorry, coordinates (4,0) is outside of your finalized shape\r\n" +
                "Please key in test coordinates in x y format or enter # to quit the game\r\n" +
                "Thank you for playing GIC geometry puzzle app\r\n" +
                "Have a nice day!\r\n";
        assertEquals(result.toString(), outContent.toString());
    }
}