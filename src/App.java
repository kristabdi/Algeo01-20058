import operation.MatrixUtil;
import base.Matrix;

public class App {
    public static void main(String[] args) {

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
        // Matrix a = MatrixUtil.inverse(m);
        // a.printMatrix();
    }
}
