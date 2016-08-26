import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sarasumit on 8/24/2016.
 */
public class ReadThetaValue {
    public static void main(String[] args) throws IOException {
        String theta1 = "Theta1.txt";
        String theta2 = "Theta2.txt";
        FileReader theta1File = new FileReader(theta1);
        FileReader theta2File = new FileReader(theta2);
        BufferedReader theta1Reader = new BufferedReader(theta1File);
        BufferedReader theta2Reader = new BufferedReader(theta2File);
        String theta1Line;
        String theta2Line;
        double[][] theta1DoubleArray = new double[300][3];
        for(int i=0;i<300;i++){
            theta1Line = theta1Reader.readLine();
            String[] theta1values = theta1Line.split(" ");
            for (int j=0;j<3;j++){
                theta1DoubleArray[i][j] = Double.parseDouble(theta1values[j]);
            }
        }

        double[][] theta2DoubleArray = new double[3][301];
        for(int i=0;i<3;i++){
            theta2Line = theta2Reader.readLine();
            String[] theta2values = theta2Line.split(" ");
            for (int j=0;j<301;j++){
                theta2DoubleArray[i][j] = Double.parseDouble(theta2values[j]);
            }
        }

        MatrixMultiplication m = new MatrixMultiplication();
        double [][] oneMatrix = m.matrixMultiply(theta1DoubleArray, theta2DoubleArray);
        System.out.println(oneMatrix.length);
        System.out.println("-----------------------HERE----------------------------------");
        theta1Reader.close();
        theta2Reader.close();

        double [][] input = {{1,0.01,0.01}};
        System.out.println(input.length);
        System.out.println(theta1DoubleArray.length);
        double [][] transposeTheta1Matrix = new double[3][300];
                //Transpose of a matrix
        for(int i=0;i<300;i++){
            for(int j=0;j<3;j++)
            {
                transposeTheta1Matrix[j][i] = theta1DoubleArray[i][j];
            }
        }

        double [][] firstResult = new double[input.length][transposeTheta1Matrix[0].length];
        for(int i=0;i<input.length;i++)
        {
            for(int j=0;j<transposeTheta1Matrix.length;j++){
                for(int k=0;k<input[0].length;k++) {
                    firstResult[i][j] = input[i][k] * transposeTheta1Matrix[k][j];
                }
            }
        }

        System.out.println(firstResult.length + "x" + firstResult[0].length);
        System.out.println(transposeTheta1Matrix.length + "x" + transposeTheta1Matrix[0].length);
        System.out.println(input.length + "x" + theta2DoubleArray[0].length);
        double  [][] firstModifiedResult = new double[input.length][theta2DoubleArray[0].length];
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < theta2DoubleArray.length; j++){
                firstModifiedResult [i][j]= firstResult[i][j] * (1 / (1 + Math.pow(Math.E, (-1 * firstResult[i][j]))));
                System.out.println(firstModifiedResult[i][j]);
            }
        }

        System.out.println("UNITEDDDDDDDDDDDDDD");

        double [][] firstResultAfterBias = new double[input.length][transposeTheta1Matrix[0].length + 1];
        firstResultAfterBias[0][1] = 1;
        for(int i=0;i<input.length;i++)
        {
            for(int j=1;j<transposeTheta1Matrix.length + 1;j++){
                for(int k=0;k<input[0].length;k++) {
                    firstResultAfterBias[i][j] = firstModifiedResult[i][j - 1];
                }
            }
        }
        System.out.println(firstResultAfterBias.length + "x" + firstResultAfterBias[0].length);

        double [][] theta2Transpose = new double[301][3];
        System.out.println("here==============");
        //Transpose of a matrix
        for(int i=0;i<3;i++){
            for(int j=0;j<301;j++)
            {
                theta2Transpose[j][i] = theta2DoubleArray[i][j];
            }
        }
        System.out.println("OSOSOS");
        double [][] finalResult = new double[firstResultAfterBias.length][theta2Transpose[0].length];
        System.out.println(+firstResultAfterBias.length);
        System.out.println(theta2Transpose[0].length);
        System.out.println(firstResultAfterBias[0].length);
        for(int i=0;i<firstResultAfterBias.length;i++)
        {
            for(int j=0;j<theta2Transpose[0].length;j++){
                for(int k=0;k<firstResultAfterBias[0].length;k++) {
                    finalResult[i][j] = firstResultAfterBias[i][k] * theta2Transpose[k][j];
                }
            }
        }
        System.out.println("--------------RESULT--------------------");
        for (int i=0;i<finalResult.length;i++){
            for(int j=0;j<finalResult[0].length;j++){
                System.out.println(Math.abs(finalResult[i][j]));
            }
        }
    }
}
