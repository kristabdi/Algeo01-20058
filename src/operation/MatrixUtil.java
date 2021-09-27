package operation;

import base.Matrix;

public class MatrixUtil {
    // Gauss
    public static void gauss(Matrix m, double[] b) {
        Matrix x = new Matrix(m);
        double[] ansArr = new double[x.getCol()];
        OBE(x, b, ansArr);
        return; 
    }

    public static void OBE(Matrix m, double[] b, double[] ansArr) {
        /* Ide : membagi semua elemen kolom pertama agar menjadi sama seperti elemen 0,0;
            mengubah semua elemen menjadi 0 setelah dikalikan pengali dengan dikurangi elemen baris pertama
            Lalu, substitusi balik, untuk mendapat solusi
        */
        int n = b.length;
        // Eliminasi
        for (int i=0; i <= n-2; i++) {
	        for (int j=i+1; j < n; j++) {

                if (m.getElmt(j,i) == 0) {
                    continue;
                }
		        double multiplier = m.getElmt(i,i)/m.getElmt(j,i);

		        for (int k=i; k < n; k++) {
                    double valRowReducted = m.getElmt(i,k) - multiplier*m.getElmt(j,k);
			        m.setElmt(j, k, valRowReducted);
		        }
                b[j] = b[i] - multiplier*b[j];
	        }
        }

        //Substitusi Balik
        for (int j=0; j<m.getRow(); j++) {
            if (m.getElmt(m.getRow()-1, j)==0) {
                countZeros+=1;
            }
        }
        if (countZeros==col-1 && (m.getElmt(m.getRow()-1, m.getCol()-1) !=0)) {
            System.out.println("SPL tidak memiliki solusi.");
        } else if (countZeros==col) {
            System.out.println("SPL memiliki tak hingga solusi.");
            // Parametrik
        } else if ((m.getElmt(m.getRow()-1, m.getCol()-2) !=0)) {
            System.out.println("SPL memiliki solusi unik.");
        }

        ansArr[m.getRow()-1] = b[m.getRow()-1] / m.getElmt(m.getRow()-1, m.getCol()-1);
        for (int i=b.length-2; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                sumRow += ansArr[j] * m.getElmt(i,j);
            }
            ansArr[i] = (b[i]-sumRow)/m.getElmt(i,i);
        }
        // ansArr terisi jawaban solusi sistem
    }

    // Cramer
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
    // Inverse Adjoin
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
    //
}