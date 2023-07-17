package Expressions;
import Instructions.Assign;
import Instructions.Instruction;
import Program.*;
import Instructions.InstrException;

public class Variable extends Expression {
    private char variableName;

    public Variable(char variableName) {
        this.variableName = variableName;
    }

    public static Variable named(char variableName) {
        return new Variable(variableName);
    }

    @Override
    public String toString() {
        return String.valueOf(variableName);
    }

    @Override
    public int getValue(Program program, Instruction instruction) throws InstrException {
        if (!program.isDeclared(variableName)) {
            throw new InstrException(program, instruction, "Ta zmienna nie została wcześniej zadeklarowana.");
        }
        return program.getVariable(variableName);
    }
}
