import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class matriz {
    public static void main(String[] args) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe el nombre del archivo (ej. matriz.txt):");
            String nombreArchivo = entrada.readLine();

            String ruta = "C:\\Archivos\\" + nombreArchivo;
            BufferedReader br = new BufferedReader(new FileReader(ruta));

            String linea;
            StringBuilder contenido = new StringBuilder();
            int filas = 0;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
                filas++;
            }
            br.close();

            String[] lineas = contenido.toString().split("\n");
            int columnas = lineas[0].trim().split("\\s+").length;
            if (filas != columnas) {
                System.out.println("La matriz no es cuadrada.");
                return;
            }
            double[][] matriz = new double[filas][columnas];
            for (int i = 0; i < filas; i++) {
                String[] valores = lineas[i].trim().split("\\s+");
                for (int j = 0; j < columnas; j++) {
                    matriz[i][j] = Double.parseDouble(valores[j]);
                }
            }
            double[][] inversa = invertirMatriz(matriz);
            if (inversa == null) {
                System.out.println("La matriz no tiene inversa (determinante = 0).");
            } else {
                System.out.println("Matriz inversa:");
                for (double[] fila : inversa) {
                    for (double val : fila) {
                        System.out.printf("%.3f ", val);
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static double[][] invertirMatriz(double[][] A) {
        int n = A.length;
        double[][] I = new double[n][n];
        double[][] copia = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copia[i][j] = A[i][j];
                I[i][j] = (i == j) ? 1 : 0;
            }
        }
        for (int i = 0; i < n; i++) {
            double pivote = copia[i][i];
            if (pivote == 0) return null;

            for (int j = 0; j < n; j++) {
                copia[i][j] /= pivote;
                I[i][j] /= pivote;
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = copia[k][i];
                    for (int j = 0; j < n; j++) {
                        copia[k][j] -= factor * copia[i][j];
                        I[k][j] -= factor * I[i][j];
                    }
                }
            }
        }
        return I;
    }
}

