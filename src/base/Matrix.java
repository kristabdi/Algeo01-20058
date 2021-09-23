package base;

public class Matrix {
    private double[][] elements;
    private int row, col;
    //Konstruktor
    public Matrix(int rows, int cols) {
        row = rows;
        col = cols;
        elements = new double[row][col];
    
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                elements[i][j] = 0;
            }
        }
    }
    public Matrix(double[][] m1) {
        row = m1.length;
        col = m1[0].length;

        elements = new double[row][col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                elements[i][j] = m1[i][j];
            }
        }
    }
    public Matrix(Matrix m1) {
        row = m1.getRow();
        col = m1.getCol();
        elements = new double[row][col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                setElmt(i,j,m1.getElmt(i,j));
            }
        }
    }
    //Getter Setter
    public void setElmt(int row, int col, double val) {
        elements[row][col] = val;
    }
    public double getElmt(int row, int col) {
        return elements[row][col];
    }
    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    // Method
    public double getDeterminantCofactor() {
        elements[0][0] += 1;
        return elements[0][0];
    }
    public double getDeterminantOBE() {
        return 0.0;
    }
}
