package Instructions;

import Program.Program;

public class InstrException extends Exception {
    public InstrException(Program program, Instruction instruction, String statement) {
        super(statement);
        System.out.println("Błąd wystąpił w instrukcji: " + instruction.toString());
        System.out.print("Zmienne widoczne w bloku, w którym wystąpił błąd: ");
        program.printVariables();
    }
}
