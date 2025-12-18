#include <stdio.h>

#define N 5

int G[N][N] = {
    {0, 1, 1, 0, 1},
    {1, 0, 1, 1, 0},
    {1, 1, 0, 1, 1},
    {0, 1, 1, 0, 1},
    {1, 0, 1, 1, 0}
};

int x[N]; // stores current path
int n = N;

// Function to print current path
void printX(int k) {
    printf("x = [ ");
    for (int i = 0; i < n; i++)
        printf("%d ", x[i]);
    printf("]  <-- after k = %d\n", k);
}

// Function to find next possible vertex
void NextValue(int k) {
    while (1) {
        x[k] = (x[k] + 1) % (n + 1); // increase vertex number
        if (x[k] == 0) return;       // no more vertices to try

        // Check if there is an edge between previous and current vertex
        if (G[x[k - 1] - 1][x[k] - 1] != 0) {
            int j;
            // Check if vertex already included
            for (j = 0; j < k; j++) {
                if (x[j] == x[k]) break;
            }

            // If not duplicate vertex
            if (j == k) {
                // If last vertex, check for cycle closure
                if (k < n - 1 || (k == n - 1 && G[x[k] - 1][x[0] - 1] != 0))
                    return;
            }
        }
    }
}

// Backtracking function
void Hamiltonian(int k) {
    while (1) {
        NextValue(k);
        printX(k);  // show current state of x
        if (x[k] == 0)
            return; // no vertex possible

        if (k == n - 1) {
            printf("\n Hamiltonian Cycle Found: ");
            for (int i = 0; i < n; i++)
                printf("%d ", x[i]);
            printf("%d\n\n", x[0]); // back to start
        } else {
            Hamiltonian(k + 1); // recursion
        }
    }
}

int main() {
    for (int i = 0; i < n; i++)
        x[i] = 0;

    x[0] = 1; // start vertex fixed

    printf("Tracing Hamiltonian Cycle Steps:\n\n");
    Hamiltonian(1);

    return 0;
}
