package main.java.com.codingcon.util;

import java.util.*;
public class TourFinderUtil {

    private final int N, start;
    private final int[][] weightMatrix;
    private List<Integer> tour = new ArrayList<>();
    private int minTourCost = Integer.MAX_VALUE;
    private boolean ranSolver = false;

    public TourFinderUtil(int start, int[][] weightMatrix) {
        N = weightMatrix.length;
        if (N > 32) throw new IllegalArgumentException("Too many destinations");
        this.start = start;
        this.weightMatrix = weightMatrix;
    }

    public List<Integer> getTour() {
        if (!ranSolver) solve();
        return tour;
    }

    public int getTourCost() {
        if (!ranSolver) solve();
        return minTourCost;
    }

    public void solve() {

        if (ranSolver) return;

        final int END_STATE = (1 << N) - 1;
        int[][] memo = new int[N][1 << N];

        for (int end = 0; end < N; end++) {
            if (end == start) continue;
            memo[end][(1 << start) | (1 << end)] = weightMatrix[start][end];
        }

        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(start, subset)) continue;
                for (int next = 0; next < N; next++) {
                    if (next == start || notIn(next, subset)) continue;
                    int subsetWithoutNext = subset ^ (1 << next);
                    int minDist = Integer.MAX_VALUE;
                    for (int end = 0; end < N; end++) {
                        if (end == start || end == next || notIn(end, subset)) continue;
                        int newDistance = memo[end][subsetWithoutNext] + weightMatrix[end][next];
                        if (newDistance < minDist) {
                            minDist = newDistance;
                        }
                    }
                    memo[next][subset] = minDist;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (i == start) continue;
            int tourCost = memo[i][END_STATE] + weightMatrix[i][start];
            if (tourCost < minTourCost) {
                minTourCost = tourCost;
            }
        }

        int lastIndex = start;
        int state = END_STATE;
        tour.add(start);

        for (int i = 1; i < N; i++) {

            int index = -1;
            for (int j = 0; j < N; j++) {
                if (j == start || notIn(j, state)) continue;
                if (index == -1) index = j;
                int prevDist = memo[index][state] + weightMatrix[index][lastIndex];
                int newDist = memo[j][state] + weightMatrix[j][lastIndex];
                if (newDist < prevDist) {
                    index = j;
                }
            }

            tour.add(index);
            state = state ^ (1 << index);
            lastIndex = index;
        }

        tour.add(start);
        Collections.reverse(tour);

        ranSolver = true;
    }

    private static boolean notIn(int elem, int subset) {
        return ((1 << elem) & subset) == 0;
    }

    public static List<Integer> combinations(int r, int n) {
        List<Integer> subsets = new ArrayList<>();
        combinations(0, 0, r, n, subsets);
        return subsets;
    }

    private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {

        int elementsLeftToPick = n - at;
        if (elementsLeftToPick < r) return;

        if (r == 0) {
            subsets.add(set);
        } else {
            for (int i = at; i < n; i++) {
                set ^= (1 << i);

                combinations(set, i + 1, r - 1, n, subsets);

                set ^= (1 << i);
            }
        }
    }
}