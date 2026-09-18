import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class Percolation {
    int[][] grid;
    public Percolation(int n){
        this.grid = new int[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                grid[i][j]=0;
            }
        }
    }
    public void open(int row, int col){
        try {
            this.grid[row-1][col-1] = 1;
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
        if (row == 1){
            return true;
        } else {
    }
    public boolean percolates(){

    }
    public static void main(String[] args) {

    }
}
