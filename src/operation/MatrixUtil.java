package operation;

import base.Matrix;

public class MatrixUtil {
    public static Matrix gauss(Matrix m) {
        Matrix x = new Matrix(m);
        //Operasi x
        return x;
    }

    public static double[] cramer(Matrix a, double[] b) {
        Matrix x = new Matrix(a);
        double[] ansArr = new double[x.getCol()];
        double detX = x.getDeterminantCofactor();
        int colB = 0;
        for (int i=0; i < x.getCol(); i++) {
            Matrix temp = new Matrix(x);
            for (int p=0; p < x.getCol(); p++){
                    temp.setElmt(p,colB,b[p]);
            }
            ansArr[i] = temp.getDeterminantCofactor()/detX;
            temp.printMatrix();
            colB+=1;
        } 
        return ansArr;
    }
}