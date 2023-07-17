package Tests;
import static org.junit.Assert.*;
import org.junit.Test;
import Program.*;
import Expressions.*;
import Program.*;
import Instructions.*;

import java.util.List;

public class InstructionsTest {

    @Test
    public void testAssign() throws Exception {
        Program program = new Program();
        program.makeVariableMap();
        program.setVariable('x', 10);
        Assign assign = new Assign('x', Int.of(20));
        assign.execute(program);
        assertEquals(20, program.getVariable('x'));
    }

    @Test
    public void testDeclaration() throws Exception {
        Program program = new Program();
        program.makeVariableMap();
        Declaration declaration = new Declaration('x', 10);
        declaration.execute(program);
        assertEquals(10, program.getVariable('x'));
    }

    @Test
    public void testPrint() throws Exception {
        Program program = new Program();
        Print print = new Print(Int.of(10));
        print.execute(program);
    }

    @Test
    public void testBlock() throws Exception {
        Program program = new Program();
        Block block = new Block();
        Declaration declaration = new Declaration('x', 10);
        block.addInstruction(declaration);
        Print print = new Print(Variable.named('x'));
        block.addInstruction(print);
        program.execute(block);
    }

    @Test
    public void testCallProcedureAndProcedure() throws Exception {
        Program program = new Program();
        Block block = new Block();
        block.addInstruction(new Procedure("test",
                                            List.of('a'),
                                            List.of(new Declaration('x', 10),
                                                    new Print(Addition.of(Variable.named('a'),
                                                                            Variable.named('x'))))));
        block.addInstruction(new CallProcedure("test", List.of(Int.of(5))));
        program.execute(block);
    }
}

