import java.util.Scanner;
import java.util.Stack;

abstract public class IOParser {

    private static Scanner sc = new Scanner(System.in);

    private IOParser() {
    }

    public static State parseInput() {
        State.BATCHES_K = sc.nextInt();
        Batch.COLOR_M = sc.nextInt();
        Batch.BATCH_N = sc.nextInt();
        sc.nextLine();

        Batch[] batches = new Batch[State.BATCHES_K];
        for (int i = 0; i < State.BATCHES_K; i++) {
            batches[i] = new Batch();

            String line = sc.nextLine().trim();
            if (line.length() == 1 && line.charAt(0) == '#')
                continue;

            String[] cardsStr = line.split(" ");
            for (int j = 0; j < cardsStr.length; j++) {
                Card newCard = new Card(cardsStr[j].charAt(cardsStr[j].length() - 1),
                        Integer.parseInt(cardsStr[j].substring(0, cardsStr[j].length() - 1)));
                batches[i].addTop(newCard);
            }
        }

        return new State(batches);
    }

    public static void printOutput(State solved, GameSolver gameSolver) {
        StringBuilder out = solved.outputString();
        out.append("========\n");
        out.append("Depth: ").append(solved.getCost()).append("\n");
        out.append("Created nodes: ").append(gameSolver.getCreatedNodes()).append("\n");
        out.append("Expanded nodes: ").append(gameSolver.getExpandedNodes()).append("\n");
        out.append("========\n");
        out.append("Actions:\n");

        Stack<State> path = new Stack<>();
        State current = solved;
        while (current.getParent() != null) {
            path.push(current);
            current = current.getParent();
        }
        while (!path.isEmpty()) {
            current = path.pop();
            out.append(current.getTransition().getFrom() + 1).append(" => ").append(current.getTransition().getTo() + 1).append("\n");
        }

        System.out.print(out);
    }
}
