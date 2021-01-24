package prj2;

public class CSPMain {
    public static void main(String[] args) {
        State initialState = IOParser.parseInput();
        System.out.println("Initial state:");
        System.out.println(initialState);
        System.out.println("--------------");

        CSPSolver cspSolver = new CSPSolver(initialState);
        State solved = cspSolver.solve();
//        IOParser.printOutput(solved, gameSolver);
        System.out.println("Solved state:");
        System.out.println(solved.outputString());
    }
}
