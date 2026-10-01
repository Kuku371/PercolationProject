import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[][] grid;
    WeightedQuickUnionUF uf;
    WeightedQuickUnionUF ufFull;
    int n;
    int openSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.grid = new int[n][n];
        this.n = n;
        this.openSites = 0;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.ufFull = new WeightedQuickUnionUF(n * n + 1);

        for (int i = 1; i <= n; i++) {
            this.uf.union(0, i);
            this.ufFull.union(0, i);
            this.uf.union(n * n + 1, n * n + 1 - i);
        }
    }

    public void open(int row, int col) {
        validate(row, col);
        if (grid[row - 1][col - 1] == 1) {
            return;
        }

        grid[row - 1][col - 1] = 1;
        openSites++;
        int site = index(row, col);

        if (row > 1 && isOpen(row - 1, col)) {
            unionBoth(site, index(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            unionBoth(site, index(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            unionBoth(site, index(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            unionBoth(site, index(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && ufFull.find(0) == ufFull.find(index(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if (n == 1) {
            return isOpen(1, 1);
        }
        return uf.find(0) == uf.find(n * n + 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Out of bounds");
        }
    }

    private int index(int row, int col) {
        return (row - 1) * n + col;
    }

    private void unionBoth(int p, int q) {
        uf.union(p, q);
        ufFull.union(p, q);
    }

    public static void main(String[] args) {
    }
}
