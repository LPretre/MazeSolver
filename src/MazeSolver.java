/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    private Stack<MazeCell> cells = new Stack<>();

    private Queue<MazeCell> cellsQ = new LinkedList<>();

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        //Start at the last cell in the maze
        MazeCell currentCell = maze.getEndCell();
        ArrayList<MazeCell> solution = new ArrayList<>();
        //Using the parent cells we set in each solution, track backwards adding each cell to the
        //start of the arraylist
        while(!currentCell.equals(maze.getStartCell())){
            solution.add(0, currentCell);
            currentCell = currentCell.getParent();
        }
        //Add the starting cell to the front of the list
        solution.add(0, maze.getStartCell());
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        //Push the starting cell onto the stack
        cells.push(maze.getStartCell());
        while(cells.size() > 0){
            //Set the current cell and pop it off the stack
            MazeCell currentCell = cells.pop();
            if (currentCell.equals(maze.getEndCell())){
                break;
            }
            //Check each cell and push them onto the stack if they are valid, in order of NESW
            //If the cell is added, set its parent to the current cell and set its status to explored
            if (maze.isValidCell(currentCell.getRow() - 1,currentCell.getCol())){
                cells.push(maze.getCell(currentCell.getRow() - 1,currentCell.getCol()));
                maze.getCell(currentCell.getRow() - 1,currentCell.getCol()).setParent(currentCell);
                maze.getCell(currentCell.getRow() - 1,currentCell.getCol()).setExplored(true);
            }
            if (maze.isValidCell(currentCell.getRow(),currentCell.getCol() + 1)){
                cells.push(maze.getCell(currentCell.getRow(),currentCell.getCol() + 1));
                maze.getCell(currentCell.getRow(),currentCell.getCol() + 1).setParent(currentCell);
                maze.getCell(currentCell.getRow(),currentCell.getCol() + 1).setExplored(true);
            }
            if (maze.isValidCell(currentCell.getRow() + 1,currentCell.getCol())){
                cells.push(maze.getCell(currentCell.getRow() + 1,currentCell.getCol()));
                maze.getCell(currentCell.getRow() + 1,currentCell.getCol()).setParent(currentCell);
                maze.getCell(currentCell.getRow() + 1,currentCell.getCol()).setExplored(true);
            }
            if (maze.isValidCell(currentCell.getRow(),currentCell.getCol() - 1)){
                cells.push(maze.getCell(currentCell.getRow(),currentCell.getCol() - 1));
                maze.getCell(currentCell.getRow(),currentCell.getCol() - 1).setParent(currentCell);
                maze.getCell(currentCell.getRow(),currentCell.getCol() - 1).setExplored(true);
            }
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        cellsQ.add(maze.getStartCell());
        while(!cellsQ.isEmpty()){
            //Remove the cell you are visiting off the stack
            MazeCell currentCell = cellsQ.remove();

            //Check each cell and add them to the queue if they are valid, in order of NESW
            //If the cell is added, set its parent to the current cell and set its status to explored
            if (maze.isValidCell(currentCell.getRow() - 1,currentCell.getCol())){
                cellsQ.add(maze.getCell(currentCell.getRow() - 1,currentCell.getCol()));
                maze.getCell(currentCell.getRow() - 1,currentCell.getCol()).setParent(currentCell);
                maze.getCell(currentCell.getRow() - 1,currentCell.getCol()).setExplored(true);
            }
            if (maze.isValidCell(currentCell.getRow(),currentCell.getCol() + 1)){
                cellsQ.add(maze.getCell(currentCell.getRow(),currentCell.getCol() + 1));
                maze.getCell(currentCell.getRow(),currentCell.getCol() + 1).setParent(currentCell);
                maze.getCell(currentCell.getRow(),currentCell.getCol() + 1).setExplored(true);
            }
            if (maze.isValidCell(currentCell.getRow() + 1,currentCell.getCol())){
                cellsQ.add(maze.getCell(currentCell.getRow() + 1,currentCell.getCol()));
                maze.getCell(currentCell.getRow() + 1,currentCell.getCol()).setParent(currentCell);
                maze.getCell(currentCell.getRow() + 1,currentCell.getCol()).setExplored(true);
            }
            if (maze.isValidCell(currentCell.getRow(),currentCell.getCol() - 1)){
                cellsQ.add(maze.getCell(currentCell.getRow(),currentCell.getCol() - 1));
                maze.getCell(currentCell.getRow(),currentCell.getCol() - 1).setParent(currentCell);
                maze.getCell(currentCell.getRow(),currentCell.getCol() - 1).setExplored(true);
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze2.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();
        System.out.println("\n~~~~~~~~~~~~~~~\n");

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
