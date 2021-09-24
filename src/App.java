import operation.MatrixUtil;
import base.Matrix;

public class App {
    public static void main(String[] args) {
        double[][] x = new double[][] {
            {1,2},
            {4,5}
        };
        Matrix m = new Matrix(x);
        double det = m.getDeterminantCofactor();
        System.out.println(det);
    }
}
