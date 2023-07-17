package Instructions;

import Program.Program;

public class Declaration extends Instruction {
    private char variableName;
    private int value;

    public Declaration(char variableName, int value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Declaration " + variableName + " = " + value;
    }

    @Override
    public void execute(Program program) throws InstrException {
        if (program.VarDeclaredInCurrentBlock(variableName)) {
            throw new InstrException(program, this, "Nie można deklarować, jednej zmiennej" +
                    " dwa razy w tym samym bloku.");
        }
        program.setVariable(variableName, value);
    }

    @Override
    void debug(Program program) throws InstrException {
        if (program.VarDeclaredInCurrentBlock(variableName)) {
            throw new InstrException(program, this, "Nie można deklarować, jednej zmiennej" +
                    " dwa razy w tym samym bloku.");
        }
        program.setVariable(variableName, value);
        Program.debuggerStepCounter++;
    }
}
