import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Random;

public class PercolationStats {
    Percolation[] percolations; int trials; int n;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
            this.n=n; this.trials=trials;
            percolations = new Percolation[trials];
            for (int i=0;i<trials; i++){
                percolations[i]=new Percolation(n);
                while (percolations[i].percolates()==false){
                    int row = StdRandom.uniformInt(1, n + 1);
                    int col = StdRandom.uniformInt(1, n + 1);

                    if (percolations[i].isOpen(row, col)==false) {
                        percolations[i].open(row, col);
                    }
                }
            }
    }

    // sample mean of percolation threshold
    public double mean(){
        double sum=0;
        for (int i=0; i<this.trials; i++){
            sum+=percolations[i].numberOfOpenSites()/(n*n);
        }
        return (sum/this.trials);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return 0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return 0;
    }

    public static void main(String[] args) {

    }

}
