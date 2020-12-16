public interface GameSolver {
    State solve();
    int getCreatedNodes();
    int getExpandedNodes();
}
