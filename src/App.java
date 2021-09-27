import operation.MatrixUtil;
import base.Matrix;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.text.DefaultStyledDocument.ElementSpec;


public class App {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // TulisMenuAwal();
        // Matrix a = new Matrix(1,1);
        // int con = sc.nextInt(); // pilihan menu awal
        // while(con < 6){
        //     if(con>=1 && con <= 3){
        //         if(con == 1){
        //             //Sistem Persamaan Linier
        //             TulisSubMenuSPL();
        //             int metSPL = sc.nextInt();
        //             while(!(isValid(metSPL, 1, 4))){
        //                 System.out.println(("Isi ulang!"));
        //                 metSPL = sc.nextInt();
        //             }
        //             int input = PilihInput(); // cara input
        //             while(!(isValid(input, 1, 2))){
        //                 PilihInput();
        //             }
        //             if(input==1){
        //                 //baca input keyboard
        //                 System.out.println("Masukkan jumlah baris matriks : ");
        //                 int m = sc.nextInt(); // row
        //                 System.out.println("Masukkan jumlah kolom matriks : ");
        //                 int n = sc.nextInt(); // col
        //                 // inisialisasi matrix
        //                 a = inputKeyboard(m, n);
        //                 double b[] = new double[m];
        //                 System.out.println("Masukkan elemen matriks b!");
        //                 for(int i = 0; i<m;i++){
        //                     double val = sc.nextDouble();
        //                     b[i] = val;
        //                 }
        //                 if(metSPL == 1){
        //                     //SPL - Gauss
        //                     MatrixUtil.gauss(a, b);
        //                 }
        //                 else if(metSPL == 2){
        //                     //SPL - Gauss Jordan
        //                     MatrixUtil.gaussJ(a, b);
        //                 }
        //                 else if(metSPL == 3){
        //                     //SPL - Matriks Balikan
        //                     MatrixUtil.balikan(a, b);
        //                 }
        //                 else if(metSPL == 4){
        //                     //SPL - Cramer
        //                     MatrixUtil.cramer(a, b);
        //                 }
        //             }
        //             else{
        //                 // baca matrix dari file
        //                 System.out.println("Masukkan nama file berisi matriks augmented : ");
        //                 String nama = sc.nextLine();
        //                 a = inputFile(nama); // matrix input
        //                 double b[] = new double[a.getRow()]; // matrix b
        //                 Matrix a1 = new Matrix(a.getRow(), a.getCol()-1); // matrix a
        //                 for(int i = 0; i < a1.getRow();i++){
        //                     for(int j = 0; j < a1.getCol(); j++){
        //                         double val = a.getElmt(i, j);
        //                         a1.setElmt(i, j, val);
        //                     }
        //                 }
        //                 for(int k=0;k<a.getRow();k++){
        //                     double elm = a.getElmt(k, a.getCol()-1);
        //                     b[k] = elm;
        //                 }
        //                 if(metSPL == 1){
        //                     //SPL - Gauss
        //                     MatrixUtil.gauss(a1, b);
        //                 }
        //                 else if(metSPL == 2){
        //                     //SPL - Gauss Jordan
        //                     MatrixUtil.gaussJ(a1, b);
        //                 }
        //                 else if(metSPL == 3){
        //                     //SPL - Matriks Balikan
        //                     MatrixUtil.balikan(a1, b);
        //                 }
        //                 else if(metSPL == 4){
        //                     //SPL - Cramer
        //                     MatrixUtil.cramer(a1, b);
        //                 }
        //             }
        //         }
        //         else if(con == 2){
        //             //determinan
        //             TulisSubMenuDet();
        //             int metDet = sc.nextInt();
        //             while(!(isValid(metDet, 1, 2))){
        //                 System.out.println(("Isi ulang!"));
        //                 metDet = sc.nextInt();
        //             }
        //             int input = PilihInput(); // cara input
        //             while(!(isValid(input, 1, 2))){
        //                 PilihInput();
        //             }
        //             if(input == 1){
        //                 System.out.println("Masukkan jumlah baris dan kolom matriks : ");
        //                 int n = sc.nextInt();
        //                 a = inputKeyboard(n, n);
        //                 if(metDet==1){
        //                     //reduksi baris
        //                 }else{
        //                     //ekspansi kofaktor
        //                 }
        //             }else{
        //                 System.out.println("Masukkan nama file berisi matriks augmented : ");
        //                 String nama = sc.nextLine();
        //                 a = inputFile(nama);
        //                 if(metDet==1){
        //                     //reduksi baris
        //                 }else{
        //                     //ekspansi kofaktor
        //                 }
        //             }
        //         }
        //         else if(con == 3){
        //             //matriks balikan
        //             TulisSubMenuBalikan();
        //             int metBal = sc.nextInt();
        //             while(!(isValid(metBal, 1, 2))){
        //                 System.out.println(("Isi ulang!"));
        //                 metBal = sc.nextInt();
        //             }
        //             int input = PilihInput(); // cara input
        //             while(!(isValid(input, 1, 2))){
        //                 PilihInput();
        //             }
        //             if(input==1){
        //                 System.out.println("Masukkan jumlah baris dan kolom matriks : ");
        //                 int n = sc.nextInt();
        //                 a = inputKeyboard(n, n);
        //                 if(metBal==1){
        //                     //reduksi baris
        //                 }else{
        //                     //adjoin
        //                 }
        //             }else{
        //                 System.out.println("Masukkan nama file berisi matriks augmented : ");
        //                 String nama = sc.nextLine();
        //                 a = inputFile(nama);
        //                 if(metBal==1){
        //                     //reduksi baris
        //                 }else{
        //                     //adjoin
        //                 }
        //             }
        //         }
        //     }
        //     else{
        //         if(con == 4){
        //             //interpolasi
        //         }
        //         else if(con == 5){
        //             //regresi
        //         }
        //         else{
        //             System.out.println("Masukkan angka dari menu yang tersedia!");
        //         }
        //     }
        //     TulisMenuAwal();
        //     con = sc.nextInt();
        // }

        // Contoh Penggunaan Cramer
        // double[][] x = new double[][] { 
        //     {5, -2, 2, 7},
        //     {1, 0, 0, 3},
        //     {-3, 1, 5, 0},
        //     {3, -1, -9, 4}
        // };
        // Matrix m = new Matrix(x);
        // double[] b = new double[] {-4,-3};
        // double[] ans = MatrixUtil.cramer(m,b);
        // for (int i=0; i<ans.length; i++) {
        //     System.out.println(ans[i]);
        // }

        //Contoh Penggunaan Inverse
        // double[][] x = new double[][] { 
        //     {5, -2, 2, 7},
        //     {1, 0, 0, 3},
        //     {-3, 1, 5, 0},
        //     {3, -1, -9, 4}
        // };
        // Matrix m = new Matrix(x);
        // Matrix a = MatrixUtil.inverseAdjoin(m);
        // a.printMatrix();

        //Contoh Penggunaan Gauss
        double[][] x = new double[][] { 
            {3, -2, 5, 0},
            {4, 5, 8, 1},
            {1, 1, 2, 1},
            {2, 7, 6, 5}
        };
        double[] b = new double[] {2,4,5,7
        };
        Matrix m = new Matrix(x);
        Matrix a = MatrixUtil.gauss(m, b);
        a.printMatrix();
    }

    public static void TulisMenuAwal(){
        System.out.println("Menu tersedia : ");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println("Pilih menu : ");
    }

    public static void TulisSubMenuSPL(){
        System.out.println("Pilih sub-menu (metode) : ");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("Pilih sub-menu : ");   
    }

    public static void TulisSubMenuDet(){
        System.out.println("Pilih sub-menu (metode) : ");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode ekspansi kofaktor");
        System.out.println("Pilih sub-menu : ");   
    }

    public static void TulisSubMenuBalikan(){
        System.out.println("Pilih sub-menu (metode) : ");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode adjoin");
        System.out.println("Pilih sub-menu : ");   
    }

    public static int PilihInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Cara input : ");
        System.out.println("1. Dengan Keyboard");
        System.out.println("2. Dari file (augmented matriks)");
        System.out.println("Pilih : ");
        int x = sc.nextInt();
        sc.close();
        return x;
    }
    public static boolean isValid(int val, int awal, int akhir){
        boolean valid = false;
        if(val >= awal && val <= akhir){
            valid = true;
        }
        return valid;
    }

    public static Matrix inputKeyboard(int m, int n){
        Matrix m1 = new Matrix(m, n);
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan elemen matriks a!");
        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                double val = sc.nextDouble();
                m1.setElmt(i, j, val);
            }
        }
        sc.close();
        return m1;
    }

    public static Matrix inputFile(String nama){
        // Buat matriks dari matriks augmented file
        Matrix mNot = new Matrix(1,1);
        try{
            File myFile = new File(nama);
            Scanner sc = new Scanner(myFile);
            int m = 0; // row
            int n = 0; // cols
            
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                String arrStr[] = data.split(" ");
                n = 0;
                for(String i : arrStr){
                    n++; 
                }
                m++;
            }
            File myFile2 = new File(nama);
            Scanner sc2 = new Scanner(myFile2);
            Matrix m1 = new Matrix(m, n);
            int i=0;
            while(sc2.hasNextLine()){
                String data1 = sc2.nextLine();
                String arrStr1[] = data1.split(" ");
                System.out.println("Baris dgn index ke-" + i);
                for(int j=0;j<n;j++){
                    Double val = Double.parseDouble(arrStr1[j]);
                    m1.setElmt(i, j, val);
                }
                i++;
            }
            sc.close();
            sc2.close();
            return m1;
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
            return mNot;
        }
    }
}
