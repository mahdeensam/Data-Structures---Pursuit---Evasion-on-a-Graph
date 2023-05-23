/*
Mahdeen Ahmed Khan Sameer
Project 7
For our Exploration!
Exploration1 Class: The purpose of the Exploration1 class is to perform a series of trials to explore the success rate of a dummy maze search algorithm in finding a path from the start cell to the target cell in mazes of different obstacle densities.
*/

import java.util.LinkedList;

public class Exploration1 {
    public static void main(String[] args) {
        int mazeSize = 20;  // Size of the maze (20x20)
        int numTrials = 1000;  // Number of trials to run for each obstacle density

        // Iterate over obstacle densities from 0 to 1 (inclusive) in increments of 0.05
        for (double obstacleDensity = 0; obstacleDensity <= 1; obstacleDensity += 0.05) {
            int numSuccesses = 0;  // Number of successful searches

            // Run the trials
            for (int i = 0; i < numTrials; i++) {
                // Create a maze with the current obstacle density
                Maze maze = new Maze(mazeSize, mazeSize, obstacleDensity);

                // Create a dummy maze search
                DummyMazeSearch search = new DummyMazeSearch(maze);

                // Set start and target cells
                Cell start = maze.get(0, 0);
                Cell target = maze.get(mazeSize - 1, mazeSize - 1);
                search.setStart(start);
                search.setTarget(target);

                // Run the search and check if it's successful
                LinkedList<Cell> path = null;
                try {
                    path = search.search(start, target, false, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (path != null && path.contains(target)) {
                    numSuccesses++;
                }
            }

            // Compute and print the success rate
            double successRate = (double) numSuccesses / numTrials;
            System.out.printf("Obstacle Density: %.2f, Success Rate: %.2f%n", obstacleDensity, successRate);
        }
    }
}
