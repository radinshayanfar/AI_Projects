package prj1;

public class IDSMain {
    public static void main(String[] args) {
        State initialState = IOParser.parseInput();

        GameSolver gameSolver = new IDSSolver(initialState);
        State solved = gameSolver.solve();
        IOParser.printOutput(solved, gameSolver);
    }
}
