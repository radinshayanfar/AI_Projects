package prj2;

public class CSPMain {
    public static void main(String[] args) {
        State initialState = IOParser.parseInput();

        CSPSolver cspSolver = new CSPSolver(initialState);
        State solved = cspSolver.solve();
        System.out.println("============");
        if (solved != null)
            System.out.println(solved.outputString());
        else
            System.out.println("No solution!");
    }
}
