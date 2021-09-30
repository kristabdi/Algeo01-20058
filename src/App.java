import operation.MatrixUtil;
import base.Matrix;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int menupilihan, submenu, input;
        String nama, answer;
        char jawab;
        int m, n;
        double[] b, ans;
        Matrix a, aug;
        Scanner scn = new Scanner(System.in);
        System.out.println("Selamat datang di aplikasi slonongboy!\nSilakan pilih salah satu menu dibawah ini!");
        TulisMenuAwal();
        menupilihan = scn.nextInt();
        while(menupilihan!=6){
            if(menupilihan==1){
                TulisSubMenuSPL();
                submenu = scn.nextInt();
                input = PilihInput();
                if(input==1){
                    System.out.println("Masukkan baris matriks A ");
                    m = scn.nextInt();
                    System.out.println("Masukkan kolom matriks A ");
                    n = scn.nextInt();
                    a = inputMatKeyboard(m, n);
                    b = inputArrKeyboard(m);
                }else{
                    System.out.println("Masukkan nama file ");
                    nama = sc.nextLine();
                    aug = inputFile(nama);
                    a = matAugmented(aug);
                    b = arrAugmented(aug);
                }

                if(submenu==1){
                    a = MatrixUtil.gauss(a, b);
                    answer = makeString(a);
                    System.out.println("Apakah ingin save ke file? (Y/N)");
                    jawab = scn.next().charAt(0);
                    if(jawab=='Y'){
                        WriteToFile(answer);
                    }
                }
                else if(submenu==2){
                    a = MatrixUtil.gaussJ(a, b);
                    answer = makeString(a);
                    a.printMatrix();
                    for(int i = 0; i<a.getRow();i++){
                        System.out.println(b[i]);
                    }
                    System.out.println("Apakah ingin save ke file? (Y/N)");
                    jawab = scn.next().charAt(0);
                    if(jawab=='Y'){
                        WriteToFile(answer);
                    }
                }
                else if(submenu==3){
                    ans = MatrixUtil.balikan(a, b);
                    answer = ans.toString();
                    System.out.println("Apakah ingin save ke file? (Y/N)");
                    jawab = scn.next().charAt(0);
                    if(jawab=='Y'){
                        WriteToFile(answer);
                    }
                }
                else{
                    ans = MatrixUtil.cramer(a, b);
                    answer = ans.toString();
                    System.out.println("Apakah ingin save ke file? (Y/N)");
                    jawab = scn.next().charAt(0);
                    if(jawab=='Y'){
                        WriteToFile(answer);
                    }
                }
                System.out.println("===============================");

            }
            else if(menupilihan==2){
                TulisSubMenuDet();
                submenu = scn.nextInt();

                System.out.println("Determinan");
            }
            else if(menupilihan==3){
                System.out.println("Matriks Balikan");
            }
            else if(menupilihan==4){
                System.out.println("Interpolasi Polinom");
            }
            else if(menupilihan==5){
                System.out.println("Regresi Linier Berganda");
            }
            TulisMenuAwal();
            menupilihan = scn.nextInt();
        }
    }

    public static String makeString(Matrix m){
        String mat = "";
        for(int i = 0; i < m.getRow(); i++){
            mat += "[";
            for(int j = 0; j < m.getCol();j++){
                if(j!=m.getCol()-1){
                    mat += " ";
                    mat += String.valueOf(m.getElmt(i, j));
                }
                else{
                    mat += " ";
                    mat += String.valueOf(m.getElmt(i, j));
                    mat += ",";
                }
            }
            mat += "]\n";
        }
        return mat;
    }

    public static void WriteToFile(String m) {
        try {
            System.out.println("Masukkan nama baru file : ");
            String nama = sc.nextLine();
            FileWriter myWriter = new FileWriter(nama);
            myWriter.write(m);
            myWriter.close();
            System.out.println("Berhasil menulis ke "+nama);
        } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
        }
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
        System.out.println("Cara input : ");
        System.out.println("1. Dengan Keyboard");
        System.out.println("2. Dari file (augmented matriks)");
        System.out.println("Pilih : ");
        int x = sc.nextInt();
        return x;
    }

    public static double[] arrAugmented(Matrix m){
        double[] b = new double[m.getRow()];
        for(int row=0;row<m.getRow();row++){
            b[row] = m.getElmt(row,m.getCol()-1);
        }
        return b;
    }

    public static Matrix matAugmented(Matrix m){
        Matrix mnew = new Matrix(m.getRow(), m.getCol()-1);
        for(int row=0;row<m.getRow();row++){
            for(int col=0;col<m.getCol()-1;col++){
                mnew.setElmt(row, col, m.getElmt(row,col));
            }
        }
        return mnew;
    }
    public static double[] inputArrKeyboard(int n){
        double[] b = new double[n];
        System.out.println("Input array!");
        for(int i = 0; i<n; i++){
            double newone = sc.nextDouble();
            b[i] = newone;
        }
        return b;
    }
    public static Matrix inputMatKeyboard(int m, int n) {
        Matrix m1 = new Matrix(m, n);
        System.out.println("Input matrix!");
        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                double val = sc.nextDouble();
                m1.setElmt(i, j, val);
            }
        }
        return m1;
    }

    public static Matrix inputFile(String nama) {
        // Buat matriks dari matriks augmented file
        Matrix mNot = new Matrix(1, 1);
        try {
            File myFile = new File(nama);
            Scanner scn = new Scanner(myFile);
            int m = 0; // row
            int n = 0; // cols

            while (scn.hasNextLine()) {
                String data = scn.nextLine();
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
            scn.close();
            sc2.close();
            return m1;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return mNot;
        }
    }
}