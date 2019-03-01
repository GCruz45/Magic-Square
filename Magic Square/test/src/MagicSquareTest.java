import junit.framework.TestCase;
import junit.swingui.TestRunner;
import model.IllegalOrderException;
import model.MagicSquare;

public class MagicSquareTest extends TestCase {

    TestRunner runner = new TestRunner();
    private MagicSquare magicSquare;


    public void testGenerateSquare() {
        magicSquare = new MagicSquare();
        int order;

        //Comprueba que el cuadrado sea magico. Compara la suma de cada fila, cada columna y las dos diagonales principales del cuadrado.
        order = 11;
        try {
            magicSquare.generateSquare(order, "North", "NW");
        } catch (IllegalOrderException e) {
            e.printStackTrace();
        }

        int isMagical = 0;
        int tempSum = 0;
        for (int j = 0; j < order; j++) {//Revisa la suma de las filas
            int currentTempSum = 0;

            for (int i = 0; i < order; i++) {
                tempSum += magicSquare.getNumber(i, j);
            }
            if (currentTempSum == tempSum) {
                tempSum = currentTempSum;
            } else {
                isMagical = 1;
                break;
            }
        }

        for (int i = 0; i < order; i++) {//Revisa la suma de las columnas
            int currentTempSum = 0;

            for (int j = 0; j < order; j++) {
                tempSum += magicSquare.getNumber(i, j);
            }
            if (currentTempSum == tempSum) {
                tempSum = currentTempSum;
            } else {
                isMagical = 1;
                break;
            }
        }

        int currentTempSum = 0;
        for (int i = 0; i < order; i++) {//Revisa la suma de la diagonal principal
            currentTempSum += magicSquare.getNumber(i, i);
        }
        if (currentTempSum != tempSum) {
            isMagical = 1;
        }

        currentTempSum = 0;
        for (int i = order - 1; i > -1; i--) {//Revisa la suma de la otra diagonal
            currentTempSum += magicSquare.getNumber(i, i);
        }
        if (currentTempSum != tempSum) {
            isMagical = 1;
        }
        assertEquals("Square is not magic.", 1, isMagical);


        //Verifica que el cuadrado se haya llenado por completo, al no encontrar ceros.
        order = 9;
        try {
            magicSquare.generateSquare(order, "West", "SW");
        } catch (IllegalOrderException e) {
            e.printStackTrace();
        }

        boolean noZeroes = true;
        for (int i = 0; i < order; i++) {//Revisa casilla por casilla si alguna es cero
            for (int j = 0; j < order; j++) {
                if (magicSquare.getNumber(i, j) == 0) {
                    noZeroes = false;
                }
            }
        }
        assertFalse("Square was not filled correctly.", !noZeroes);

    }

}
