package operation;

import java.util.Arrays;
import static java.lang.Math.abs;
import base.Matrix;

public class MatrixUtil {
    // Gauss : return Matrix after forwardElim;
    public static Matrix gauss(Matrix m, double[] b) {
        Matrix x = augmented(m, b);
        x = forwardElim(x, b);
        return x;
    }

    public static Matrix forwardElim(Matrix m, double[] b) {
        /*
         * Ide : membagi semua elemen kolom pertama agar menjadi sama seperti elemen
         * 0,0; mengubah semua elemen menjadi 0 setelah dikalikan pengali dengan
         * dikurangi elemen baris pertama Lalu, substitusi balik, untuk mendapat solusi
        */                                               
        // Eliminasi
        double t_min = 1e-9;
            
        for (int i = 0; i < m.getRow()-1 ; i++) {
            if (Math.abs(m.getElmt(i,i)) < t_min) {
                for (int j = i+1; j < m.getRow(); j++) {
                    if (Math.abs(m.getElmt(j, i)) > Math.abs(m.getElmt(i, i))) {
                        swapRow(m, i, j);
                        break;
                    }
                }
            }
            for (int j = i + 1; j < m.getRow(); j++) {
                if (m.getElmt(j, i) == 0) {
                    continue;
                }
                double multiplier = m.getElmt(i, i) / m.getElmt(j, i);

                for (int k = i; k < m.getCol(); k++) {
                    double valRowReducted = m.getElmt(i, k) - multiplier * m.getElmt(j, k);
                    m.setElmt(j, k, valRowReducted);
                    m.printMatrix();    
                    System.out.println();
                }
                b[j] = b[i] - multiplier * b[j];
            }
        }

        // Konversi ke leading one
        for (int i = 0; i <= m.getRow() - 1; i++) {
            if (m.getElmt(i, i) != 0 && m.getElmt(i, i) != 1) {
                double pembagi = m.getElmt(i, i);
                for (int j = i; j < m.getCol(); j++) {
                    double rowLeadingOne = m.getElmt(i, j) / pembagi;
                    m.setElmt(i, j, rowLeadingOne);
                }
                b[i] /= pembagi;
            }
        }

        // Swap Row With All Zero
        int countLowerRowZero = 0;
        for (int i=0; i < m.getRow(); i++) {
            if (m.isRowSPLZero(i)) {
                swapRow(m, i, m.getRow()-1-countLowerRowZero);
                countLowerRowZero++;
            }
        }
        return m;
    }

    public static void swapRow(Matrix m, int row1, int row2) {
        double[] temp = new double[m.getCol()];
        int n = m.getCol();
        for (int j=0; j < n; j++) {
            temp[j] = m.getElmt(row1,j);
            m.setElmt(row2,j,m.getElmt(row1,j));
            m.setElmt(row1,j,temp[j]);           
        }
    }

    public static void bSubSol(Matrix m, double[] b, double[] ansArr) {
        // Substitusi Balik : mendapat param Matriks telah di forwardElim
        // Determine possible solution of matrix
        int countZeros = 0;
        int n = b.length;
        for (int j = 0; j < m.getRow(); j++) {
            if (m.getElmt(m.getRow() - 1, j) == 0) {
                countZeros += 1;
            }
        }

        if (countZeros == m.getCol() && b[m.getCol() - 1] != 0) {
            System.out.println("SPL tidak memiliki solusi.");
        } else if (countZeros == m.getCol() && b[m.getCol() - 1] == 0) {
            System.out.println("SPL memiliki tak hingga solusi.");
            // Buat dependensi matrix
            // Matrix depend = new Matrix(m.getCol(), m.getCol());
            // for (int i = 0; i < m.getRow(); i++) {
            // boolean found = false;
            // int elmtKe = -1;
            // for (int j = 0; j < m.getCol(); j++) {
            // if (found == true) {
            // depend.setElmt(elmtKe, j, m.getElmt(i, j));
            // }
            // if (m.getElmt(i, j) != 0.0 && found == false) {
            // found = true;
            // elmtKe = j;
            // }
            // }
            // }

            String[] abjad = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                    "s", "t", "u", "v", "w", "x", "y", "z" };
            int abjadUsed = 0;
            Double[] ansNum = new Double[m.getCol()];
            String[] ansStr = new String[m.getCol()];
            for (int i = 0; i < m.getCol(); i++) {
                ansNum[i] = 0.0;
                ansStr[i] = "";
            }

            for (int i = m.getRow() - 1; i >= 0; i--) {
                if (m.getCountZero(i) == m.getCol()) {
                    ansStr[i] = abjad[abjadUsed];
                    abjadUsed++;
                } else if (m.getCountZero(i) == m.getCol() - 1) {
                    ansNum[i] = b[i];
                } else {
                    ansNum[i] = b[i];
                    for (int j = i + 1; j < m.getCol(); j++) {
                        ansNum[i] = ansNum[i] + (m.getElmt(i, j) * ansNum[j]);
                        if (ansStr[i] == "") {
                            ansStr[i] = ansStr[i] + (m.getElmt(i, j) + ansStr[j]);
                        } else {
                            ansStr[i] = ansStr[i] + "+" + (m.getElmt(i, j) + ansStr[j]);
                        }
                    }
                }
            }

            for (int i = 0; i < m.getCol(); i++) {
                if (ansStr[i] == "") {
                    System.out.printf("%.2f ", ansNum[i]);
                } else if (ansStr[i] != "" && ansNum[i] == 0) {
                    System.out.printf("%s ", ansStr[i]);
                } else {
                    System.out.printf("%.2f%s ", ansNum[i], ansStr[i]);
                }
            }
            System.out.println("");

        } else if ((m.getElmt(m.getRow() - 1, m.getCol() - 1) != 0)) {
            System.out.println("SPL memiliki solusi unik.");

            ansArr[m.getRow() - 1] = b[m.getRow() - 1];
            for (int i = m.getRow() - 2; i >= 0; i--) {
                double sumRow = 0;
                for (int j = i + 1; j < m.getCol(); j++) {
                    sumRow += ansArr[j] * m.getElmt(i, j);
                }
                ansArr[i] = b[i] - sumRow;
            }
            // ansArr terisi jawaban solusi sistem
            for (int i = 0; i < ansArr.length; i++) {
                System.out.printf("%.2f ", ansArr[i]);
            }
        }
    }

    public static Matrix augmented(Matrix m, double[] b) {
        Matrix mNew = new Matrix(m.getRow(), m.getCol()+1);
        for (int i = 0; i < mNew.getRow(); i++) {
            for (int j = 0; j < mNew.getCol(); j++) {
                if (j == mNew.getCol()-1) {
                    mNew.setElmt(i ,j, b[i]);
                } else {
                    mNew.setElmt(i ,j, m.getElmt(i,j));
                }            
            }
        }
        return mNew;
    }

    public static Matrix gaussJ(Matrix m, double[] b) {
        int nRow = m.getRow(), nCol = m.getCol();
        double temp;
        boolean allZero=true;
        // mastiin a(0,0) bukan 0, kalau 0 dituker
        for(int row=0;row<nRow;row++){
            if(m.getElmt(row,0)!=0){
                allZero = false;
                break;
            }
        }
        if(allZero == false){
            for(int i = 0;i<nRow;i++){
                if(m.getElmt(i,0) != 0){
                    for(int j = 0; j<nCol;j++){
                        temp = m.getElmt(0,j);
                        m.setElmt(0, j, m.getElmt(i,j));
                        m.setElmt(i, j, temp);
                    }
                    temp = b[0];
                    b[0] = b[i];
                    b[i] = temp;
                    break;
                }
            }
        }
        

        //mulai ngeelim?
        for(int i = 0; i<nRow;i++){
            int col = i;
            boolean isAllZero = true;
            for(int j = 0; j<nCol;j++){
                if(m.getElmt(i,j)!=0){
                    isAllZero = false;
                }
            }
            if(isAllZero==true){
                break;
            }
            while(m.getElmt(i, col)==0 && col<nCol){
                col++;
            }
            //jadiin 1 yg didepan, bagi sebaris
            if(m.getElmt(i, col)!=1){
                double factor = m.getElmt(i, col);
                for(int j=col;j<nCol;j++){
                    double newval = m.getElmt(i, j)/factor;
                    m.setElmt(i, j, newval);
                    if(m.getElmt(i,j)==(-0)){
                        m.setElmt(i, j, 0);
                    }
                }
                b[i] /= factor;
            }

            //jadiin 00000
            for(int k = 0; k < nRow;k++){
                if(k==i || m.getElmt(k,col) == 0){
                    continue;
                }
                double faktor = m.getElmt(k, col);    
                for(int l=0;l<nCol;l++){
                    double newVal = m.getElmt(k,l) - faktor*m.getElmt(i,l);
                    m.setElmt(k, l, newVal);
                }
                b[k] = b[k] - faktor*b[i];
            } 
        }
        return m;
    }

    public static double[] balikan(Matrix m, double[] b) {
        double[] ansArr = new double[m.getRow()];

        // Lakukan inverse matriks
        Matrix inversed = inverseAdjoin(m);

        // x = inversed*b
        for (int i = 0; i < m.getRow(); i++) {
            ansArr[i] = 0;
            for (int j = 0; j < m.getCol(); j++) {
                ansArr[i] = ansArr[i] + (inversed.getElmt(i, j) * b[i]);
            }
        }

        return ansArr;
    }

    public static double[] cramer(Matrix a, double[] b) {
        Matrix x = new Matrix(a);
        double[] ansArr = new double[x.getCol()];
        double detX = x.getDeterminantCofactor();
        int colB = 0;
        for (int i = 0; i < x.getCol(); i++) {
            Matrix temp = new Matrix(x);
            for (int p = 0; p < x.getCol(); p++) {
                temp.setElmt(p, colB, b[p]);
            }
            ansArr[i] = temp.getDeterminantCofactor() / detX;
            temp.printMatrix();
            colB += 1;
        }
        return ansArr;
    }

    // Inverse Adjoin
    public static void getCofactor(Matrix m, Matrix temp, int i, int j) {
        int rowTemp = 0;
        int colTemp = 0;

        for (int p = 0; p < m.getRow(); p++) {
            for (int q = 0; q < m.getCol(); q++) {
                if (p != i && q != j) {
                    temp.setElmt(rowTemp, colTemp, m.getElmt(p, q));
                    colTemp++;

                    if (colTemp == m.getCol() - 1) {
                        colTemp = 0;
                        rowTemp++;
                    }
                }
            }
        }
    };

    public static Matrix inverseRowReduction(Matrix m) {
        Matrix x = new Matrix(m.getRow(), m.getCol() - 1 + m.getRow());

        // Copy matrix m ke depan matrix x kecuali kolom terakhir
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getCol() - 1; j++) {
                x.setElmt(i, j, m.getElmt(i, j));
            }
        }
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = m.getCol(); j < x.getCol(); j++) {
                if (i == j - m.getCol()) {
                    x.setElmt(i, j, 1);
                } else {
                    x.setElmt(i, j, 0);
                }
            }
        }

        // Kolom terakhir dimasukkan ke variabel b agar bisa diinput ke fungsi
        // gaussJordan
        double[] b = new double[m.getRow()];
        for (int i = 0; i < m.getRow(); i++) {
            b[i] = m.getElmt(i, m.getCol() - 1);
        }

        // Lakukan gauss Jordan
        Matrix reducedMatrix = new Matrix(gaussJ(x, b));

        // Cek apakah punya matrix atau tidak
        for (int i = 0; i < reducedMatrix.getRow(); i++) {
            if (reducedMatrix.getCountZero(i) == reducedMatrix.getCol()) {
                System.out.println("Tidak ada matriks balikan. Akan dikembalikan matriks awal");
                return m;
            }
        }

        // Pisahkan identitas dengan inversedMatrix
        Matrix inversedMatrix = new Matrix(m.getRow(), m.getCol());
        for (int i = 0; i < inversedMatrix.getRow(); i++) {
            for (int j = 0; j < inversedMatrix.getCol(); j++) {
                inversedMatrix.setElmt(i, j, reducedMatrix.getElmt(i, j + x.getRow()));
            }
        }

        return inversedMatrix;
    }

    public static void adj(double[][] toAdjoin, Matrix m) {
        if (m.getRow() == 1 && m.getCol() == 1) {
            toAdjoin[0][0] = 1;
            return;
        }

        Matrix temp = new Matrix(m.getRow() - 1, m.getCol() - 1);
        int plusmin = 1;
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getCol(); j++) {
                getCofactor(m, temp, i, j);
                if ((i + j) % 2 == 0) {
                    plusmin = 1;
                } else {
                    plusmin = -1;
                }
                toAdjoin[j][i] = plusmin * temp.getDeterminantCofactor();
            }
        }
    };

    public static Matrix inverseAdjoin(Matrix m) {
        Matrix x = new Matrix(m);
        Matrix inversedMatrix = new Matrix(x);
        double det = x.getDeterminantCofactor();
        if (det == 0) {
            System.out.println("Matriks adalah matriks singular.");
            return x;
        }
        double[][] adjoin = new double[x.getRow()][x.getCol()];
        adj(adjoin, x);

        for (int i = 0; i < x.getCol(); i++) {
            for (int j = 0; j < x.getCol(); j++) {
                inversedMatrix.setElmt(i, j, (adjoin[i][j] / det));
            }
        }
        return inversedMatrix;
    }

    public static void polynomInterpolation(Matrix m, double[] k) {
        // Array penyimpan y predict
        double[] ypredict = new double[k.length];

        // Buat matrix spl yang berisi matrix persamaan interpolasi polinom
        int n = m.getRow();
        Matrix spl = new Matrix(n, n);
        for (int i = 0; i < spl.getRow(); i++) {
            for (int j = 0; j < spl.getCol(); j++) {
                spl.setElmt(i, j, Math.pow(m.getElmt(i, 0), j));
            }
        }

        // Array untuk menyimpan nilai y titik
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = m.getElmt(i, 1);
        }

        double[] konstanta = new double[spl.getCol()];
        Matrix result = new Matrix(gauss(spl, b));

        konstanta[result.getRow() - 1] = b[result.getRow() - 1];
        for (int i = b.length - 2; i >= 0; i--) {
            double sumRow = 0;
            for (int j = i + 1; j < b.length; j++) {
                sumRow += konstanta[j] * result.getElmt(i, j);
            }
            konstanta[i] = b[i] - sumRow;
        }

        String persamaan = "" + String.format("%.4f", konstanta[0]);
        if (konstanta[1] > 0) {
            persamaan += "+" + String.format("%.4f", konstanta[1]) + "x";
        } else {
            persamaan += String.format("%.4f", konstanta[1]) + "x";
        }
        for (int i = 2; i < konstanta.length; i++) {
            String temp = String.format("%.4f", konstanta[i]);
            if (konstanta[i] > 0) {
                persamaan = persamaan + "+" + temp + "x^" + i;
            } else {
                persamaan = persamaan + temp + "x^" + i;
            }
        }

        System.out.printf("P(%d) = %s\n", n, persamaan);
        for (int i = 0; i < ypredict.length; i++) {
            ypredict[i] = 0;
            for (int j = 0; j < n; j++) {
                ypredict[i] = ypredict[i] + (konstanta[j] * Math.pow(k[i], j));
            }
            System.out.printf("P(%.2f) = %.4f\n", k[i], ypredict[i]);
        }
    }

    public static void regression(Matrix m, double[] y, double[] x) {
        // Matrix spl dengan baris sebanyak k+1, kolom sebanyak k+2 termasuk y
        Matrix combin = new Matrix(m.getCol()+1, m.getCol()+1);
        int n = m.getRow();

        for (int i = 0; i < combin.getRow(); i++) {
            for (int j = i; j < combin.getCol(); j++) {
                if (i == 0 && j == 0) {
                    combin.setElmt(i, j, n);
                } else if (i == j && j != 0) {
                    double sigma = 0.0;
                    for (int k = 0; k < n; k++) {
                        sigma += Math.pow(m.getElmt(k, j - 1), 2);
                    }
                    combin.setElmt(i, j, sigma);
                } else if (i == 0 && j != 0) {
                    double sigma = 0.0;
                    for (int k = 0; k < n; k++) {
                        sigma += m.getElmt(k, j - 1);
                    }
                    combin.setElmt(i, j, sigma);
                    combin.setElmt(j, i, sigma);
                } else if(i!=0){
                    double sigma = 0.0;
                    for (int k = 0; k < n; k++) {
                        sigma += m.getElmt(k, j - 1) * m.getElmt(k, i - 1);
                    }
                    combin.setElmt(i, j, sigma);
                    combin.setElmt(j, i, sigma);
                }
            }
        }

        double[] yspl = new double[combin.getRow()];
        for(int k=0;k<n; k++){
            yspl[0] += y[k];
        }
        
        for(int i=1; i<yspl.length; i++){
            for(int k=0; k<n; k++){
                yspl[i] += m.getElmt(k, i-1)*y[k];
            }
        }

        Matrix eliminated = gauss(combin, yspl);
        double[] ansArr = new double[combin.getCol()];
        bSubSol(eliminated, yspl, ansArr);
        System.out.println("");

        double result = ansArr[0];
        for(int i=1; i<ansArr.length; i++){
            result += ansArr[i]*x[i-1] ;
        }
        System.out.println(result);

        String persamaan = ""+ansArr[0];
        for (int i = 1; i < ansArr.length; i++) {
            if(ansArr[i]>0){
                persamaan += "+"+ansArr[i] + "x["+i+"]";
            } else{
                persamaan += ansArr[i] + "x[" + i + "]";
            }
            
        }
        System.out.println(persamaan);
    }
}