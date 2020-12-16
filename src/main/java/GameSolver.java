public interface GameSolver {
    State solve();
    long getCreatedNodes();
    long getExpandedNodes();
}
