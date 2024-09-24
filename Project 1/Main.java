import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Integer;

public class Main {

    public static int[] findStart(char[][] maze) {
        int[] curr = { 0, 0 };
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                // if we see a + than we are at the start
                if (maze[row][col] == '+') {
                    maze[row][col] = ' ';
                    curr[0] = row;
                    curr[1] = col;
                }
            }
        }
        return curr;
    }

    public static boolean Solve(char[][] maze, int row, int col) {
        boolean solved = false;
        if(row >= maze.length || col >= maze[0].length || row < 0 || col < 0) {
            return false;
        }
        //If we found the exit
        if(maze[row][col] == '-') {
            solved = true;
            System.out.println("Maze is solved");
            return solved;
        }
        //If we backtrack into a +
        if(maze[row][col] == '+') {
            return false;
        }
        //If we hit a wall
        if(maze[row][col] == 'X' || maze[row][col] == '.') {
            return false;
        }

        //Place a + everytime we go in a direction
        maze[row][col] = '+';        

        if(!solved) {
            //north
            solved = Solve(maze, row - 1, col);
            if(solved) {
                return true;
            }   
        }
        if(!solved) {
            //west
            solved = Solve(maze, row, col + 1);
            if(solved) {
                return true;
            }   
        }
        if(!solved) {
            //south
            solved = Solve(maze, row + 1, col);
            if(solved) {
                return true;
            }           
        }
        if(!solved) {
            //east
            solved = Solve(maze, row, col - 1);
            if(solved) {
                return true;
            }   
        }
        //backtrack
        if(!solved) {
            maze[row][col] = '.';
            return false;
        }
        return false;
    }
    public static void main(String[] args) {

        File in = new File("maze.dat");
        File out = new File("copy.txt");

        try (Scanner scan = new Scanner(in);
                PrintWriter pw = new PrintWriter(out)) {
            char[][] maze;
            // Dimensions
            String dimension = scan.nextLine();
            // Delimeter to read the length and the height
            String[] dim_split = dimension.split(" ");
            int width = Integer.parseInt(dim_split[1]);
            int height = Integer.parseInt(dim_split[0]);

            maze = new char[height][width];
            String line = scan.nextLine();
            // instantiate maze and returning character at given index
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    maze[row][col] = line.charAt(col);
                }
                
                if(scan.hasNext()) {
                    line = scan.nextLine();
                }
            }
            //find the start
            int[] start = findStart(maze);
            Solve(maze, start[0], start[1]);

            //Save maze to copy.txt if you use pw.print
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    System.out.print(maze[row][col]);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}