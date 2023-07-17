package Instructions;
import Expressions.*;
import Program.Program;

public class Assign extends Instruction {
    private char variableName;
    private Expression expr;

    public Assign(char variableName, Expression expr) {
        this.variableName = variableName;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "Assign " + variableName + " " + expr.toString();
    }

    @Override
    public void execute(Program program) throws InstrException {
        if (!program.isDeclared(variableName)) {
            throw new InstrException(program, this, "Taka zmienna nie została zadeklarowana, " +
                    "więc nie można jej przypisać wartości.");
        }
        program.assignVariable(variableName, expr.getValue(program, this));
    }

    @Override
    public void debug(Program program) throws InstrException {
        if (!program.isDeclared(variableName)) {
            throw new InstrException(program, this, "Taka zmienna nie została zadeklarowana, " +
                    "więc nie można jej przypisać wartości.");
        }
        int value = expr.getValue(program, this);
        Program.listOfBlocks.get(Program.listOfBlocks.toArray().length - 1).setVariable(variableName, value, program);
        Program.debuggerStepCounter++;
    }
}
