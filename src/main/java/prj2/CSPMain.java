package prj2;

public class CSPMain {
    public static void main(String[] args) {
        State initialState = IOParser.parseInput();
        System.out.println("Initial state:");
        System.out.println(initialState);
        System.out.println("--------------");

//        GameSolver gameSolver = new AStarSolver(initialState);
//        State solved = gameSolver.solve();
//        IOParser.printOutput(solved, gameSolver);
    }
}
