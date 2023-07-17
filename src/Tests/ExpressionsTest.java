package Tests;
import static org.junit.Assert.*;

import Program.Program;
import org.junit.Test;
import Expressions.*;
import Program.*;
import Instructions.*;

public class ExpressionsTest {

    @Test
    public void testAdditionExpression() throws InstrException {
        Addition expr = Addition.of(Int.of(2), Int.of(3));
        assertEquals(5, expr.getValue(new Program(), new Block()));
    }

    @Test
    public void testSubtractionExpression() throws InstrException {
        Substraction expr = Substraction.of(Int.of(5), Int.of(7));
        assertEquals(-2, expr.getValue(new Program(), new Block()));
    }

    @Test
    public void testDivisionExpression() throws InstrException {
        Division expr = Division.of(Int.of(21), Int.of(7));
        assertEquals(3, expr.getValue(new Program(), new Block()));
    }

    @Test
    public void testIntExpression() throws InstrException {
        Int expr = Int.of(5);
        assertEquals(5, expr.getValue(new Program(), new Block()));
    }

    @Test
    public void testModuloExpression() throws InstrException {
        Modulo expr = Modulo.of(Int.of(3), Int.of(3));
        assertEquals(0, expr.getValue(new Program(), new Block()));
    }

    @Test
    public void testMultiplicationExpression() throws InstrException {
        Multiplication expr = Multiplication.of(Int.of(3), Int.of(3));
        assertEquals(9, expr.getValue(new Program(), new Block()));
    }
}
