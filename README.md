# PuzzleAppGeometry 

1. Environment required: Windows 
2. Assumptions
  •	Coordinates for random polygon generation are within range (-100, -100) inclusive
  •	Randomly generated polygon can have 3-8 vertices inclusive
  •	The program will exit after the final user action (“#”)
  •	Points on the boundary/vertices of polygon are not considered to be within the polygon

3. Design
For the custom shape generation, there are 3 validations for the user input string. For all points of the polygon, the coordinate string format is validated. For incomplete polygons, duplicate point validation is carried out. For points added creating a complete polygon, 3 or more points in polygon, validation to check if addition of the polygon would maintain the polygon as a convex polygon. For the 3rd validation, the new point is validated by checking if the polygon created by adding the new point would be convex. This is done by traversing the list of points and checking if direction of the cross product of any 2 adjacent sides are the same. Checking the cross product, which is the vector perpendicular to the 2 points would ensure that the new point is convex (all internal angles less than 180).

For the random shape generation, a random number within 3-8 (inclusive) is generated to decide the number of vertices in the random polygon. For the number of vertices generated, random (x,y) points within (-100,-100) is generated. These random points are validated according to the 3 validations mentioned above and valid points are added to the random polygon. 

4. Steps to run 
  1.	Unzip folder and open folder using an IDE (Intellij/Eclipse/VSC)
  2.	Run PuzzleApp.main() in PuzzleApp/src/main 
  3.	Enter user inputs according to prompts in console 
Alternatively, if you have JDK installed and configured to system environment variables, 
  1.	Open cmd in PuzzleApp/src/main path 
  2.	Run “javac PuzzleApp.java”

