import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    Percolation[] percolations;
    int trials;
    int n;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.trials = trials;
        this.percolations = new Percolation[trials];

        for (int i = 0; i < trials; i++) {
            this.percolations[i] = new Percolation(n);
            while (!this.percolations[i].percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (!this.percolations[i].isOpen(row, col)) {
                    this.percolations[i].open(row, col);
                }
            }
        }
    }

    public double mean() {
        double sum = 0;
        for (int i = 0; i < trials; i++) {
            sum += (double) this.percolations[i].numberOfOpenSites() / (n * n);
        }
        return sum / trials;
    }

    public double stddev() {
        double sum = 0;
        double mean = mean();
        for (int i = 0; i < trials; i++) {
            double threshold = (double) this.percolations[i].numberOfOpenSites() / (n * n);
            sum += Math.pow(threshold - mean, 2);
        }
        return Math.pow(sum / (trials - 1), 0.5);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo()
                                   + ", " + stats.confidenceHi() + "]");
    }
}
