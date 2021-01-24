package prj1;

public class AStarMain {
    public static void main(String[] args) {
        State initialState = IOParser.parseInput();

        GameSolver gameSolver = new AStarSolver(initialState);
        State solved = gameSolver.solve();
        IOParser.printOutput(solved, gameSolver);
    }
}
