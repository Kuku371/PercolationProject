import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class Percolation {
    int[][] grid; WeightedQuickUnionUF uf; int n;
    public Percolation(int n){
        this.grid = new int[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++) {
                grid[i][j] = 0;
            }
        }
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n*n+2);
        for (int i=1; i<=n; i++){
            this.uf.union(0, i);
            this.uf.union(n*n+1, n*n+1-i);
        }
    }
    public void open(int row, int col){
        try {
            this.grid[row-1][col-1] = 1;
            if (row != 1){
                if (this.grid[row-2][col-1]==1) {
                    this.uf.union((row - 1) * (col - 1) + 1, (row - 2) * (col - 1) + 1);
                }
            } if (row != n){
                if (this.grid[row][col-1]==1) {
                    this.uf.union((row - 1) * (col - 1) + 1, (row) * (col - 1) + 1);
                }
            } if (col != 1){
                if (this.grid[row-1][col-2]==1) {this.uf.union((row-1)*(col-1)+1,(row-1)*(col-2)+1);}
            } if (col != n){
                if (this.grid[row-1][col]==1) { this.uf.union((row-1)*(col-1)+1,(row-1)*(col)+1);}
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("Out of bounds");
        }
    }
    public boolean isOpen(int row, int col){
        try {
            return (this.grid[row-1][col-1]==1);
        } catch(Exception e) {
            throw new IllegalArgumentException("Out of bounds");
        }
    }
    public boolean isFull(int row, int col){
        try {
            return (this.uf.connected(0, (row-1)*(col-1)+1) && this.isOpen(row, col));
        } catch(Exception e){
            throw new IllegalArgumentException("Out of bounds");
        }
    }
    public boolean percolates(){
        return this.uf.connected(0, n*n+1);
    }
    public static void main(String[] args) {
    }
}
