ACO: MAX–MIN Ant Colony Optimization for TSP
=======================================================

This project implements the MAX–MIN Ant Colony Optimization algorithm to solve
the Travelling Salesman Problem (TSP).

The goal is always to find the shortest Hamiltonian cycle through all cities.

-------------------------------------------------------
Project files:
-------------------------------------------------------

- Main.java
- MaxMinACO.java
- Ant.java
- DistanceMatrix.java
- TSPLibReader.java
- resources
  - berlin52.tsp

-------------------------------------------------------
Algorithm summary:
-------------------------------------------------------

Each ant constructs a tour using a probabilistic rule combining:
- pheromone information
- heuristic visibility (1/distance)

After all ants complete tours, pheromones evaporate,
and only the best tour globally reinforces pheromone trails (MAX–MIN).

Pheromone intensities are bounded:
tauMin ≤ tau ≤ tauMax

This prevents stagnation and encourages exploration.

-------------------------------------------------------
Distance calculation:
-------------------------------------------------------

TSP datasets contain (x,y) coordinates or special distance encoding.
Typical Euclidean distance:

    distance = sqrt((x2−x1)^2 + (y2−y1)^2)

For datasets with geographic coordinates, other metrics may be used.

-------------------------------------------------------
Execution:
-------------------------------------------------------

Compile:

    javac *.java

Run:

    java Main

Example:

    java Main

Example output:

    Iteration: 299 best length: 8220.30469
    === FINAL BEST TOUR ===
    47 23 4 14 5 ...
    Total length: 8220.30469

Meaning:
- The numbers in FINAL BEST TOUR are the city IDs in the visiting order.
- The best length is the total distance of the complete tour.
