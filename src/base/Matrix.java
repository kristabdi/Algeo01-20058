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
    // I/O
    public void printMatrix() {
        for (int i =0; i < row; i++) {
            for (int j=0; j < col; j++) {
                if (j==row-1) {
                    System.out.printf("%.2f", elements[i][j]);
                } else {
                    System.out.printf("%.2f ", elements[i][j]);
                }
            }
            System.out.println();
        }
    }
    // Sifat
    public boolean isSquare() {
        return (row==col);
    }

    public boolean isRowSPLZero(int rw) {
        for(int j=0; j<getCol(); j++){
            if(getElmt(rw, j)!=0.0){
                return false;
            }
        }
        return true;
    }
    // Method
    public int getCountZero(int rw){
        int zero = 0;

        for(int j=0; j<getCol(); j++){
            if(getElmt(rw, j)==0.0){
                zero++;
            }
        }

        return zero;
    }
    public double getDeterminantCofactor() {
        if (isSquare()) {
            double det = 0.0;
            int rowSmallMtr = 0;
            int colSmallMtr = 0;

            if (row == 1) {
                return getElmt(0,0);
            } else if (row == 2) {
                return ((getElmt(0,0)*getElmt(1,1)) - (getElmt(1,0)*getElmt(0,1)));
            } else {
                for (int j=0; j<row; j++) {
                    Matrix smallMtr = new Matrix(row-1,col-1);
                    rowSmallMtr = 0;
                    colSmallMtr = 0;
                    for (int p=1; p<row; p++) {
                        for (int q=0; q<col; q++) {
                            if (q!=j) {
                                smallMtr.setElmt(rowSmallMtr, colSmallMtr, getElmt(q,p));
                                rowSmallMtr+=1;
                            }
                        }
                        colSmallMtr += 1;
                        rowSmallMtr = 0;
                    }
                    if (j%2==0) {
                        det += getElmt(j,0) * smallMtr.getDeterminantCofactor();
                    } else {
                        det += getElmt(j,0) * smallMtr.getDeterminantCofactor()  * -1;
                    }
                }
            }
            return det;
        } else {
            return -9999.000;
        }
    }
    public double getDeterminantOBE() {
        return 0.0;
    }
}
