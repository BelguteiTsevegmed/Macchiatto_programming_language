package Instructions;

import Expressions.Expression;
import Program.Program;

import java.util.ArrayList;
import java.util.List;

public class For extends Instruction {
    private List<Instruction> instructions;
    private int loopCounter;
    private Expression expr;
    private char variableName;

    public For(char variableName, Expression expr) {
        this.instructions = new ArrayList<>();
        this.loopCounter = 0;
        this.expr = expr;
        this.variableName = variableName;
    }

    @Override
    public String toString() {
        return "For " + variableName + " " +  expr.toString();
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    @Override
    public void execute(Program program) throws InstrException{
        int maxCount = expr.getValue(program, this);
        if (maxCount <= 0) return;
        program.setVariable(variableName, 0);
        for (;loopCounter < maxCount; loopCounter++) {
            program.setVariable(variableName, loopCounter);
            for (Instruction inst: instructions) {
                inst.execute(program);
            }
        }
        program.removeVariable(variableName);
    }

    @Override
    public void debug(Program program) throws InstrException {
        Program.debuggerStepCounter++;
        int loopMaxCount = expr.getValue(program, this);
        if (loopMaxCount <= 0) return;
        program.setVariable(variableName, 0);
        for (;loopCounter < loopMaxCount; loopCounter++) {
            Program.listOfBlocks.get(Program.listOfBlocks.toArray().length - 1).setVariable(variableName, loopCounter, program);
            for (Instruction inst: instructions) {
                if (Program.debuggerStepCounter == Program.maxCount) {
                    System.out.println(inst.toString());
                    program.debugHelper();
                }
                inst.debug(program);
            }
        }
        program.removeVariable(variableName);
    }
}
