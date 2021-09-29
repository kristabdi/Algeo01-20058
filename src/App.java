import operation.MatrixUtil;
import base.Matrix;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TulisMenuAwal();
        Matrix a = new Matrix(1,1);
        int con = sc.nextInt(); // pilihan menu awal
        while(con < 6){
            if(con>=1 && con <= 3){
                if(con == 1){
                    //Sistem Persamaan Linier
                    TulisSubMenuSPL();
                    int metSPL = sc.nextInt();
                    while(!(isValid(metSPL, 1, 4))){
                        System.out.println(("Isi ulang!"));
                        metSPL = sc.nextInt();
                    }
                    int input = PilihInput(); // cara input
                    while(!(isValid(input, 1, 2))){
                        input = PilihInput();
                    }
                    if(input==1){
                        //baca input keyboard
                        System.out.println("Masukkan jumlah baris matriks : ");
                        int m = sc.nextInt(); // row
                        System.out.println("Masukkan jumlah kolom matriks : ");
                        int n = sc.nextInt(); // col
                        // inisialisasi matrix
                        System.out.println("Masukkan elemen matriks a!");
                        a = inputKeyboard(m, n);
                        double b[] = new double[]{5, 3, 1};
                        //System.out.println("Masukkan elemen matriks b!");
                        //for(int i = 0; i<m;i++){
                        //    double val = sc.nextDouble();
                        //    b[i] = val;
                        //}
                        if(metSPL == 1){
                            //SPL - Gauss
                            MatrixUtil.gauss(a, b);
                        }
                        else if(metSPL == 2){
                            //SPL - Gauss Jordan
                            MatrixUtil.gaussJ(a, b);
                        }
                        else if(metSPL == 3){
                            //SPL - Matriks Balikan
                            MatrixUtil.balikan(a, b);
                        }
                        else if(metSPL == 4){
                            //SPL - Cramer
                            MatrixUtil.cramer(a, b);
                        }
                    }
                    else{
                        // baca matrix dari file
                        System.out.println("Masukkan nama file berisi matriks augmented : ");
                        String nama = sc.nextLine();
                        a = inputFile(nama); // matrix input
                        double b[] = new double[a.getRow()]; // matrix b
                        Matrix a1 = new Matrix(a.getRow(), a.getCol()-1); // matrix a
                        for(int i = 0; i < a1.getRow();i++){
                            for(int j = 0; j < a1.getCol(); j++){
                                double val = a.getElmt(i, j);
                                a1.setElmt(i, j, val);
                            }
                        }
                        for(int k=0;k<a.getRow();k++){
                            double elm = a.getElmt(k, a.getCol()-1);
                            b[k] = elm;
                        }
                        if(metSPL == 1){
                            //SPL - Gauss
                            MatrixUtil.gauss(a1, b);
                        }
                        else if(metSPL == 2){
                            //SPL - Gauss Jordan
                            MatrixUtil.gaussJ(a1, b);
                        }
                        else if(metSPL == 3){
                            //SPL - Matriks Balikan
                            MatrixUtil.balikan(a1, b);
                        }
                        else if(metSPL == 4){
                            //SPL - Cramer
                            MatrixUtil.cramer(a1, b);
                        }
                    }
                }
                else if(con == 2){
                    //determinan
                    TulisSubMenuDet();
                    int metDet = sc.nextInt();
                    while(!(isValid(metDet, 1, 2))){
                        System.out.println(("Isi ulang!"));
                        metDet = sc.nextInt();
                    }
                    int input = PilihInput(); // cara input
                    while(!(isValid(input, 1, 2))){
                        input = PilihInput();
                    }
                    if(input == 1){
                        System.out.println("Masukkan jumlah baris dan kolom matriks : ");
                        int n = sc.nextInt();
                        System.out.println("Masukkan elemen matriks!");
                        a = inputKeyboard(n, n);
                        if(metDet==1){
                            //reduksi baris
                        }else{
                            //ekspansi kofaktor
                        }
                    }else{
                        System.out.println("Masukkan nama file berisi matriks augmented : ");
                        String nama = sc.nextLine();
                        a = inputFile(nama);
                        if(metDet==1){
                            //reduksi baris
                        }else{
                            //ekspansi kofaktor
                        }
                    }
                }
                else if(con == 3){
                    //matriks balikan
                    TulisSubMenuBalikan();
                    int metBal = sc.nextInt();
                    while(!(isValid(metBal, 1, 2))){
                        System.out.println(("Isi ulang!"));
                        metBal = sc.nextInt();
                    }
                    int input = PilihInput(); // cara input
                    while(!(isValid(input, 1, 2))){
                        input = PilihInput();
                    }
                    if(input==1){
                        System.out.println("Masukkan jumlah baris dan kolom matriks : ");
                        int n = sc.nextInt();
                        System.out.println("Masukkan elemen matriks!");
                        a = inputKeyboard(n, n);
                        if(metBal==1){
                            //reduksi baris
                        }else{
                            a = MatrixUtil.inverseAdjoin(a);
                        }
                    }else{
                        System.out.println("Masukkan nama file berisi matriks augmented : ");
                        String nama = sc.nextLine();
                        a = inputFile(nama);
                        if(metBal==1){
                            //reduksi baris
                        }else{
                            a = MatrixUtil.inverseAdjoin(a);
                        }
                    }
                }
            }
            else{
                if(con == 4){
                    //interpolasi
                    
                    int input = 0;
                    input = PilihInput(); // cara input
                    while(!(isValid(input, 1, 2))){
                        input = PilihInput();
                    }
                    if(input==1){
                        //System.out.println("Masukkan jumlah titik interpolasi!");
                        //int n = sc.nextInt();
                        //System.out.println("Masukkan titik-titik interpolasi!");
                        //a = inputKeyboard(n, 2); 
                        //System.out.println("Masukkan jumlah x yang akan ditaksir nilai fungsinya.");
                        //int nX = sc.nextInt();
                        //double TestX[] = new double[nX];
                        //for(int i =0;i<nX;i++){
                        //    TestX[i] = sc.nextDouble();
                        //}

                        // double[][] m1 = new double[][]{
                        //     {0.1, 0.003},
                        //     {0.3, 0.067},
                        //     {0.5, 0.148},
                        //     {0.7, 0.248},
                        //     {0.9, 0.370},
                        //     {1.1, 0.518},
                        //     {1.3, 0.697}
                        // };
                        double[][] m1 = new double[][] {
                            {8.0, 2.0794},
                            {9.0, 2.1972},
                            {9.5, 2.2513}
                        };
                        // double[] TestX = new double[]{0.2,0.55,0.85,1.28};
                        double[] TestX = new double[]{9.2};
       
                        Matrix m = new Matrix(m1);
                        //fungsi interpolasi
                        MatrixUtil.polynomInterpolation(m, TestX);
                    }else{
                        System.out.println("Masukkan nama file berisi titik interpolasi : ");
                        String nama = sc.nextLine();
                        a = inputFile(nama);
                        //fungsi interpolasi
                    }
                }
                else if(con == 5){
                    //regresi
                    int input = PilihInput(); // cara input
                    while(!(isValid(input, 1, 2))){
                        input = PilihInput();
                    }
                    if(input==1){
                        // System.out.println("Masukkan jumlah baris x : ");
                        // int i = sc.nextInt();
                        // System.out.println("Masukkan jumlah peubah x : ");
                        // int n = sc.nextInt();
                        // System.out.println("Masukkan nilai x : ");
                        // Matrix xn = inputKeyboard(i, n); // nilai x1i, x2i, ...xni
                        // System.out.println("Masukkan nilai y : ");
                        // double y[] = new double[i];
                        // for(int j=0;j<n;j++){ //isi array of yn
                        //     y[j] = sc.nextDouble();
                        // }
                        // int k = n-1;
                        // System.out.println("Masukkan nilai Xk : ");
                        // Matrix xk = inputKeyboard(i, k);

                        double[][] m1 = new double[][] {
                            {72.4, 76.3, 29.18},
                            {41.6, 70.3, 29.35},
                            {34.3, 77.1, 29.24},
                            {35.1, 68.0, 29.27},
                            {10.7, 79.0, 29.78},
                            {12.9, 67.4, 29.39},
                            {8.3, 66.8, 29.69},
                            {20.1, 76.9, 29.48},
                            {72.2, 77.7, 29.09},
                            {24.0, 67.7, 29.60},
                            {23.2, 76.8, 29.38},
                            {47.4, 86.6, 29.35},
                            {31.5, 76.9, 29.63},
                            {10.6, 86.3, 29.56},
                            {11.2, 86.0, 29.48},
                            {73.3, 76.3, 29.40},
                            {75.4, 77.9, 29.28},
                            {96.6, 78.7, 29.29},
                            {107.4, 86.8, 29.03},
                            {54.9, 70.9, 29.37}
                        };
                        Matrix m = new Matrix(m1);
                        double[] y = new double[] { 0.90, 0.91, 0.96, 0.89, 1.00, 1.10, 1.15, 1.03, 0.77, 1.07, 1.07, 0.94, 1.10, 1.10, 1.10, 0.91, 0.87, 0.78, 0.82, 0.95 };
                        double[] x = new double[] {50,76,29.30};
                        MatrixUtil.regression(m, y, x);
                    }else{
                        System.out.println("Masukkan nama file berisi titik untuk di regresi : ");
                        String nama = sc.nextLine();
                        a = inputFile(nama);
                    }
                }
                else{
                    System.out.println("Masukkan angka dari menu yang tersedia!");
                }
            }
            TulisMenuAwal();
            con = sc.nextInt();
        }

        // Contoh Penggunaan Cramer
        // double[][] x = new double[][] {
        // {5, -2, 2, 7},
        // {1, 0, 0, 3},
        // {-3, 1, 5, 0},
        // {3, -1, -9, 4}
        // };
        // Matrix m = new Matrix(x);
        // double[] b = new double[] {-4,-3};
        // double[] ans = MatrixUtil.cramer(m,b);
        // for (int i=0; i<ans.length; i++) {
        // System.out.println(ans[i]);
        // }

        // Contoh Penggunaan Inverse
        // double[][] x = new double[][] {
        // {5, -2, 2, 7},
        // {1, 0, 0, 3},
        // {-3, 1, 5, 0},
        // {3, -1, -9, 4}
        // };
        // Matrix m = new Matrix(x);
        // Matrix a = MatrixUtil.inverseAdjoin(m);
        // a.printMatrix();

        //Contoh Penggunaan Gauss
        //double[][] x = new double[][] { 
        //    {3, -2, 5, 0},
        //    {4, 5, 8, 1},
        //    {1, 1, 2, 1},
        //    {2, 7, 6, 5}
        //};
        //double[] b = new double[] {2,4,5,7
        //};
        //Matrix m = new Matrix(x);
        //Matrix a = MatrixUtil.gauss(m, b);
        //a.printMatrix();
    }

    public static void TulisMenuAwal() {
        System.out.println("Menu tersedia : ");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println("Pilih menu : ");
    }

    public static void TulisSubMenuSPL() {
        System.out.println("Pilih sub-menu (metode) : ");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("Pilih sub-menu : ");
    }

    public static void TulisSubMenuDet() {
        System.out.println("Pilih sub-menu (metode) : ");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode ekspansi kofaktor");
        System.out.println("Pilih sub-menu : ");
    }

    public static void TulisSubMenuBalikan() {
        System.out.println("Pilih sub-menu (metode) : ");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode adjoin");
        System.out.println("Pilih sub-menu : ");
    }

    public static int PilihInput() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Cara input : ");
        System.out.println("1. Dengan Keyboard");
        System.out.println("2. Dari file (augmented matriks)");
        System.out.println("Pilih : ");
        int x = scn.nextInt();
        return x;
    }

    public static boolean isValid(int val, int awal, int akhir) {
        boolean valid = false;
        if (val >= awal && val <= akhir) {
            valid = true;
        }
        return valid;
    }

    public static Matrix inputKeyboard(int m, int n) {
        Matrix m1 = new Matrix(m, n);
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                double val = sc.nextDouble();
                m1.setElmt(i, j, val);
            }
        }
        sc.close();
        return m1;
    }

    public static Matrix inputFile(String nama) {
        // Buat matriks dari matriks augmented file
        Matrix mNot = new Matrix(1, 1);
        try {
            File myFile = new File(nama);
            Scanner sc = new Scanner(myFile);
            int m = 0; // row
            int n = 0; // cols

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String arrStr[] = data.split(" ");
                n = 0;
                for (String i : arrStr) {
                    n++;
                }
                m++;
            }
            File myFile2 = new File(nama);
            Scanner sc2 = new Scanner(myFile2);
            Matrix m1 = new Matrix(m, n);
            int i = 0;
            while (sc2.hasNextLine()) {
                String data1 = sc2.nextLine();
                String arrStr1[] = data1.split(" ");
                for (int j = 0; j < n; j++) {
                    Double val = Double.parseDouble(arrStr1[j]);
                    m1.setElmt(i, j, val);
                }
                i++;
            }
            sc.close();
            sc2.close();
            return m1;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return mNot;
        }
    }
}
