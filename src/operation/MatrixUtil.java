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

    public static void getCofactor(Matrix m, Matrix temp, int i, int j) {
        int rowTemp = 0;
        int colTemp = 0;

        for (int p = 0; p < m.getRow(); p++) {
            for (int q = 0; q < m.getCol(); q++) {
                if (p!=i && q!=j) {
                    temp.setElmt(rowTemp,colTemp,m.getElmt(p,q));
                    colTemp++;

                    if (colTemp == m.getCol()-1) {
                        colTemp =0;
                        rowTemp++;
                    }
                }
            }
        }        
    };
    
    public static void adj(double[][] toAdjoin, Matrix m) {
        if (m.getRow() == 1 && m.getCol() == 1) {
            toAdjoin[0][0] = 1;
            return;
        }

        Matrix temp = new Matrix(m.getRow()-1, m.getCol()-1);
        int plusmin = 1;
        for (int i=0; i<m.getRow(); i++) {
            for (int j=0; j<m.getCol(); j++) {
                getCofactor(m, temp, i ,j);
                if ((i+j)%2==0) {
                    plusmin = 1;
                } else {
                    plusmin = -1;
                }
                toAdjoin[j][i] = plusmin * temp.getDeterminantCofactor();
            }
        }
    };

    public static Matrix inverse(Matrix m) {
        Matrix x = new Matrix(m);
        Matrix inversedMatrix = new Matrix(x); 
        double det = x.getDeterminantCofactor();
        if (det==0) {
            System.out.println("Matriks adalah matriks singular.");
            return x;
        }
        double[][] adjoin = new double[x.getRow()][x.getCol()];
        adj(adjoin, x);

        for (int i=0; i < x.getCol(); i++) {
            for (int j=0; j < x.getCol(); j++) {
                inversedMatrix.setElmt(i,j,(adjoin[i][j]/det));
            }
        }
        return inversedMatrix;
    }
}