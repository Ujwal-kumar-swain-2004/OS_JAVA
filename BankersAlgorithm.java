public class BankersAlgorithm {
    public static void main(String[] args) {

        int n = 5;
        int m = 3;

        int[][] alloc = {
                {0, 1, 0},
                {2, 0, 0},
                {3, 0, 2},
                {2, 1, 1},
                {0, 0, 2}
        };

        int[][] max = {
                {7, 5, 3},
                {3, 2, 2},
                {9, 0, 2},
                {2, 2, 2},
                {4, 3, 3}
        };

        int[] avail = {3, 3, 2};

        int[][] need = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        boolean[] done = new boolean[n];
        int[] safe = new int[n];
        int idx = 0;

        for (int count = 0; count < n; count++) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!done[i]) {
                    boolean canRun = true;

                    for (int j = 0; j < m; j++) {
                        if (need[i][j] > avail[j]) {
                            canRun = false;
                            break;
                        }
                    }

                    if (canRun) {
                        for (int j = 0; j < m; j++) {
                            avail[j] += alloc[i][j];
                        }

                        safe[idx++] = i;
                        done[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is not in safe state");
                return;
            }
        }

        System.out.println("System is in safe state");
        System.out.print("Safe sequence: ");

        for (int i = 0; i < n; i++) {
            System.out.print("P" + safe[i] + " ");
        }
    }
}