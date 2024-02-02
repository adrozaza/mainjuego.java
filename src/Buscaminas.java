import java.util.Random;
import java.util.Scanner;

public class Buscaminas {

    final char[][] tablero;
    final boolean[][] minas;
    private final int filas;
    private final int columnas;
    private final int minasTotales;

        //Aqui se crea el tablero
    public Buscaminas(int filas, int columnas, int minasTotales) {
        this.filas = filas;
        this.columnas = columnas;
        this.minasTotales = minasTotales;
        this.tablero = new char[filas][columnas];
        this.minas = new boolean[filas][columnas];
        inicializarTablero();
        colocarMinas();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = '-';
            }
        }
    }
    //Aqui genero una colocación aleatoria de las minas mediante un randomizador en un void pues no debe ser posible para el usuario saber la colocacion de las mismas
    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;

        while (minasColocadas < minasTotales) {
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);

            if (!minas[fila][columna]) {
                minas[fila][columna] = true;
                minasColocadas++;
            }
        }
    }

    private void mostrarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    //En esta parte se lanza un output que indica cuando se toca una mina
    void revelarCasilla(int fila, int columna) {
        if (minas[fila][columna]) {
            System.out.println("¡Boom! Has perdido.");
        }
        else {
            //Aquí se ejecuta la funcion que calcula las minas que hay alrededor de la casilla seleccionada si en esta no hay ninguna y se muestran en pantalla
            int minasAlrededor = contarMinasAlrededor(fila, columna);
            tablero[fila][columna] = (char) (minasAlrededor + '0');

                // Aquí planeo implementar la función de revelar casillas vacías

        }
    }
    //Función para calcular las minas alrededor de una casilla
    int contarMinasAlrededor(int fila, int columna) {
        int minasAlrededor = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    if (minas[i][j]) {
                        minasAlrededor++;
                    }
                }
            }
        }

        return minasAlrededor;
    }
    //Esta parte del código se encarga de disponer el tablero por pantalla así como los controles
    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;
        //Cabe destacar que inicialmente yo terminaba el bucle con un break cosa que posteriormente cambié pues es una mala práxis
        while (!juegoTerminado) {
            mostrarTablero();
            int fila = obtenerEntradaUsuario("fila", scanner);
            int columna = obtenerEntradaUsuario("columna", scanner);
            revelarCasilla(fila, columna);
            juegoTerminado = verificarFinJuego() || haTocadoMina(fila, columna);
        }

        scanner.close();
    }

    private int obtenerEntradaUsuario(String coordenada, Scanner scanner) {
        System.out.print("Introduce la " + coordenada + ": ");
        return scanner.nextInt();
    }
    private boolean haTocadoMina(int fila, int columna) {
        return minas[fila][columna];
    }
    private boolean verificarFinJuego() {
        if (tableroCompleto()) {
            System.out.println("¡Felicidades! Has ganado.");
            return true;
        }
        return false;
    }
    //Esta funcion determina si se han agotado las casillas sin minas y por ende se ha ganado el juego
    boolean tableroCompleto() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == '-' && !minas[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    //Aqui se pueden modificar tanto el tamaño de la tabla como la cantidad de minas, posteriormente se puede usar esto para introducir un sistema de dificultades
    public static void main(String[] args) {
        Buscaminas buscaminas = new Buscaminas(5, 5, 4);
        buscaminas.jugar();
    }
}
