package operation;

import base.Matrix;

public class MatrixUtil {
    public static Matrix gauss(Matrix m) {
        Matrix x = new Matrix(m);
        //Operasi x
        return x;
    }

    public static Matrix cramer(Matrix a, double[] b) {
        Matrix x = new Matrix(a);
        Matrix temp = new Matrix(x.getRow(), x.getCol());
        double[] detArr = new double[x.getCol()];
        for (int i=0; i < x.getCol(); i++) {
            for (int q=0; q < x.getCol(); q++) {
                for (int p=0; p < x.getCol(); p++){
                }
            }
        } 
        //Operasi x
        return x;
    }
}