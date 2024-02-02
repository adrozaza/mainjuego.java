import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BuscaminasTest {


    @Test
    public void testContarMinasAlrededor() {
        Buscaminas buscaminas = new Buscaminas(5, 5, 0);
        buscaminas.minas[0][1] = true; // Colocar una mina en la casilla adyacente
        assertEquals(1, buscaminas.contarMinasAlrededor(0, 0)); // Asegura que se cuenten correctamente las minas adyacentes
    }

    @Test
    public void testTableroCompleto() {
        Buscaminas buscaminas = new Buscaminas(2, 2, 1);
        assertFalse(buscaminas.tableroCompleto()); // Asegura que el tablero no esté completo al inicio
        buscaminas.tablero[0][0] = '1'; // Simula la revelación de todas las casillas sin minas
        buscaminas.tablero[0][1] = '1';
        buscaminas.tablero[1][0] = '1';
        buscaminas.tablero[1][1] = '1';
        assertTrue(buscaminas.tableroCompleto()); // Asegura que el tablero esté completo después de revelar todas las casillas sin minas
    }


}
