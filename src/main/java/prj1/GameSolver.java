package prj1;

public interface GameSolver {
    State solve();
    long getCreatedNodes();
    long getExpandedNodes();
}
