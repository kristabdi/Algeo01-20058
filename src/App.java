import operation.MatrixUtil;
import base.Matrix;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int menupilihan, submenu, input;
        String nama, answer;
        char jawab;
        int m, n;
        double det;
        double[] b, ans, y, x;
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
                    System.out.println("Masukkan nama file :");
                    nama = scn.nextLine();
                    nama = scn.nextLine();
                    aug = inputFile(nama);
                    a = matAugmented(aug);
                    b = arrAugmented(aug);
                }
                String names ="default.txt";
                nama = (Path.of("../test", names)).toString();
                File namef = new File(nama);
                try{
                    PrintStream o = new PrintStream(nama);
                    if(submenu==1){ //gauss
                        a = MatrixUtil.gauss(a, b);
                        ans = new double[a.getCol()];
                        MatrixUtil.bSubSol(a, b, ans, o);
                        o.close();
                        System.out.println("Apakah ingin save ke file? (Y/N)");
                        jawab = scn.next().charAt(0);
                        if(jawab=='Y'){
                            System.out.println("Tulis nama file!");
                            names = sc.nextLine();
                            names = sc.nextLine();
                            Path source = Paths.get("../test/default.txt");
                            Files.move(source, source.resolveSibling(names));
                        }else{
                            Path path = Paths.get("../test/default.txt");
                            Files.delete(path);
                        }
                    }
                    else if(submenu==2){ //gauss-jordan
                        a = MatrixUtil.gaussJ(a, b);
                        ans = new double[a.getCol()];
                        MatrixUtil.bSubSol(a, b, ans, o);
                        o.close();
                        System.out.println("Apakah ingin save ke file? (Y/N)");
                        jawab = scn.next().charAt(0);
                        if(jawab=='Y'){
                            System.out.println("Tulis nama file!");
                            names = sc.nextLine();
                            names = sc.nextLine();
                            Path source = Paths.get("../test/default.txt");
                            Files.move(source, source.resolveSibling(names));
                        }else{
                            Path path = Paths.get("../test/default.txt");
                            Files.delete(path);
                        }
                    }
                    else if(submenu==3){
                        if(a.isSquare()){
                            MatrixUtil.balikan(a, b, o);
                            o.close();
                            System.out.println("Apakah ingin save ke file? (Y/N)");
                            jawab = scn.next().charAt(0);
                            if(jawab=='Y'){
                                System.out.println("Tulis nama file!");
                                names = sc.nextLine();
                                names = sc.nextLine();
                                Path source = Paths.get("../test/default.txt");
                                Files.move(source, source.resolveSibling(names));
                            }else{
                                Path path = Paths.get("../test/default.txt");
                                Files.delete(path);
                            }
                        }else{
                            System.out.println("Matriks bukan persegi. Silakan gunakan cara lain!");
                        }
    
                    }
                    else{
                        if(a.isSquare()){
                            ans = MatrixUtil.cramer(a, b, o);
                            o.close();
                            System.out.println("Apakah ingin save ke file? (Y/N)");
                            jawab = scn.next().charAt(0);
                            if(jawab=='Y'){
                                System.out.println("Tulis nama file!");
                                names = sc.nextLine();
                                names = sc.nextLine();
                                Path source = Paths.get("../test/default.txt");
                                Files.move(source, source.resolveSibling(names));
                            }else{
                                Path path = Paths.get("../test/default.txt");
                                Files.delete(path);
                            }
                        }else{
                            System.out.println("Matriks bukan persegi. Silakan gunakan cara lain!");
                        }
                    }
                }catch(IOException e){
                    System.out.println("An Error occured.");
                }
                System.out.println("===============================");

            }
            else if(menupilihan==2){
                TulisSubMenuDet();
                submenu = scn.nextInt();
                input = PilihInput();
                if(input == 1){
                    System.out.println("Masukkan baris dan kolom matriks");
                    m = scn.nextInt();
                    a = inputMatKeyboard(m, m);
                }
                else{
                    System.out.println("Masukkan nama file ");
                    nama = scn.nextLine();
                    nama = scn.nextLine();
                    aug = inputFile(nama);
                    a = aug;
                }
                if(submenu==1){
                    if(a.isSquare()){
                        det = MatrixUtil.DetRowRed(a);
                        System.out.println("Determinan matriks adalah "+det);
                        answer = String.format("%.2f", det);
                        System.out.println("Apakah ingin save ke file? (Y/N)");
                        jawab = scn.next().charAt(0);
                        if(jawab=='Y'){
                            WriteToFile(answer);
                        }
                    }
                    else{
                        System.out.println("Matriks bukan persegi. Tidak ada determinan");
                    }
                }else{
                    if(a.isSquare()){
                        det = a.getDeterminantCofactor();
                        System.out.println("Determinan matriks adalah "+det);
                        answer = String.format("%.2f", det);
                        System.out.println("Apakah ingin save ke file? (Y/N)");
                        jawab = scn.next().charAt(0);
                        if(jawab=='Y'){
                            WriteToFile(answer);
                        }
                    }
                    else{
                        System.out.println("Matriks bukan persegi. Tidak ada determinan");
                    }
                }
                System.out.println("===============================");
            }
            else if(menupilihan==3){
                TulisSubMenuBalikan();
                submenu = scn.nextInt();
                input = PilihInput();
                if(input == 1){
                    System.out.println("Masukkan baris matriks");
                    m = scn.nextInt();
                    System.out.println("Masukkan kolom matriks");
                    n = scn.nextInt();
                    a = inputMatKeyboard(m, n);
                }
                else{
                    System.out.println("Masukkan nama file ");
                    nama = scn.nextLine();
                    nama = scn.nextLine();
                    aug = inputFile(nama);
                    a = aug;
                }
                if(submenu==1){
                    //reduksi baris
                    if(a.isSquare() && MatrixUtil.DetRowRed(a)!=-9999){
                        a = MatrixUtil.inverseRowReduction(a);
                        answer = makeString(a);
                        System.out.println("Apakah ingin save ke file? (Y/N)");
                        jawab = scn.next().charAt(0);
                        if(jawab=='Y'){
                            WriteToFile(answer);
                        }
                    }
                    else{
                        System.out.println("Matriks bukan persegi atau tidak ada determinan. Tidak dapat di-inverse");
                    }
                }else{
                    //adjoin
                    if(a.isSquare()&&MatrixUtil.DetRowRed(a)!=-9999){
                        a = MatrixUtil.inverseAdjoin(a);
                        answer = makeString(a);
                        System.out.println("Apakah ingin save ke file? (Y/N)");
                        jawab = scn.next().charAt(0);
                        if(jawab=='Y'){
                            WriteToFile(answer);
                        }
                    }
                    else{
                        System.out.println("Matriks bukan persegi atau tidak ada determinan. Tidak dapat di-inverse");
                    }
                }
                System.out.println("===============================");
            }
            else if(menupilihan==4){
                input = PilihInput();
                if(input == 1){
                    System.out.println("Masukkan jumlah titik");
                    m = scn.nextInt();
                    a = inputMatKeyboard(m, 2);
                }
                else{
                    System.out.println("Masukkan nama file ");
                    nama = scn.nextLine();
                    nama = scn.nextLine();
                    aug = inputFile(nama);
                    a = aug;
                }
                System.out.println("Masukkan jumlah titik yang akan ditaksir nilai fungsinya");
                n = scn.nextInt();
                System.out.println("Masukkan titik-titik yang akan ditaksir nilai fungsinya");
                double[] xk = new double[n];
                for(int i = 0;i<n;i++){
                    xk[i] = scn.nextDouble();
                }
                String names ="default.txt";
                nama = (Path.of("../test", names)).toString();
                File namef = new File(nama);
                try{
                    PrintStream o = new PrintStream(nama);
                    MatrixUtil.polynomInterpolation(a, xk, o);
                    o.close();
                    System.out.println("Apakah ingin save ke file? (Y/N)");
                    jawab = scn.next().charAt(0);
                    if(jawab=='Y'){
                        System.out.println("Tulis nama file!");
                        names = sc.nextLine();
                        names = sc.nextLine();
                        Path source = Paths.get("../test/default.txt");
                        Files.move(source, source.resolveSibling(names));
                    }else{
                        Path path = Paths.get("../test/default.txt");
                        Files.delete(path);
                    }
                }catch(IOException e){
                    System.out.println("An Error occured.");
                }
                System.out.println("===============================");
            }
            else if(menupilihan==5){
                input = PilihInput();
                if(input == 1){
                    System.out.println("Masukkan jumlah peubah x");
                    n = scn.nextInt();
                    System.out.println("Masukkan jumlah variabel");
                    m = scn.nextInt();
                    a = inputMatKeyboard(m, n);
                    System.out.println("Masukkan nilai y!");
                    y = new double[m];
                    for(int i = 0; i < m;i++){
                        y[i] = scn.nextDouble();
                    }
                    System.out.println("Masukkan jumlah x yang akan ditaksir!");
                    int aaa = sc.nextInt();
                    System.out.println("Masukkan nilai x yang akan ditaksir!");
                    x = new double[aaa];
                    for(int i = 0; i < aaa;i++){
                        x[i] = scn.nextDouble();
                    }
                }
                else{
                    System.out.println("Masukkan nama file ");
                    nama = scn.nextLine();
                    nama = scn.nextLine();
                    aug = inputFile(nama);
                    a = matAugmented(aug);
                    y = arrAugmented(aug);
                    System.out.println("Masukkan jumlah x yang akan ditaksir!");
                    int aaa = sc.nextInt();
                    System.out.println("Masukkan nilai x yang akan ditaksir!");
                    x = new double[aaa];
                    for(int i = 0; i < aaa;i++){
                        x[i] = scn.nextDouble();
                    }
                }
                String names ="default.txt";
                nama = (Path.of("../test", names)).toString();
                File namef = new File(nama);
                try{
                    PrintStream o = new PrintStream(nama);
                    MatrixUtil.regression(a, y, x, o);
                    o.close();
                    System.out.println("Apakah ingin save ke file? (Y/N)");
                    jawab = scn.next().charAt(0);
                    if(jawab=='Y'){
                        System.out.println("Tulis nama file!");
                        names = sc.nextLine();
                        names = sc.nextLine();
                        Path source = Paths.get("../test/default.txt");
                        Files.move(source, source.resolveSibling(names));
                    }else{
                        Path path = Paths.get("../test/default.txt");
                        Files.delete(path);
                    }
                }catch(IOException e){
                    System.out.println("An Error occured.");
                }
                System.out.println("===============================");
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
            System.out.println("Tulis nama file!");
            String name = sc.nextLine();
            name = sc.nextLine();
            Path currentPath = Paths.get(System.getProperty("user.dir"));
            Path filePath = Paths.get(currentPath.toString(), name);
            FileWriter myWriter = new FileWriter(filePath.toString());
            myWriter.write(m);
            myWriter.close();
            System.out.println("Successfully wrote to the "+ filePath.toString());
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
            nama = (Path.of("../test", nama)).toString();
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
            return mNot;
        }
    }
}