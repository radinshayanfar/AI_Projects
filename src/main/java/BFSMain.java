import java.util.Arrays;

public class BFSMain {
    public static void main(String[] args) {
        State initialState = IOParser.parseInput();

        GameSolver gameSolver = new BFSSolver(initialState);
        State solved = gameSolver.solve();
        IOParser.printOutput(solved, gameSolver);
    }
}
