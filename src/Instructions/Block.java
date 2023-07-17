package Instructions;
import Program.*;
import java.util.*;

public class Block extends Instruction {
    private List<Instruction> instructions;
    private int forLoopIteration;

    public Block () {
        this.instructions = new ArrayList<>();
        this.forLoopIteration = 0;
    }

    @Override
    public String toString() {
        return "Block";
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public List<Instruction> getInstructionList() {
        return instructions;
    }

    public void setVariable(char variableName, int number, Program program) throws InstrException {
        if (program.VarDeclaredInCurrentBlock(variableName) && forLoopIteration <= 1) {
            throw new InstrException(program, this, "Nie można deklarować, jednej zmiennej" +
                    " dwa razy w tym samym bloku.");
        }
        program.setVariable(variableName, number);
    }

    public void setProcedure(Procedure procedure, Program program) throws InstrException{
        if (program.ProcDeclaredInCurrentBlock(procedure.getName()) && forLoopIteration <= 1) {
            throw new InstrException(program, this, "Nie można deklarować, jednej zmiennej" +
                    " dwa razy w tym samym bloku.");
        }
        program.setProcedure(procedure);
    }

    @Override
    public void execute(Program program) throws InstrException {
        forLoopIteration++;
        program.makeProcedureMap();
        program.makeVariableMap();
        if (!instructions.isEmpty()) {
            for(Instruction inst: instructions){
                inst.execute(program);
            }
        }
        program.removeVariableMap();
        program.removeProcedureMap();
    }

    @Override
    public void debug(Program program) throws InstrException {
        forLoopIteration++;
        Program.debuggerStepCounter++;
        Program.listOfBlocks.add(this);
        program.makeVariableMap();
        program.makeProcedureMap();
        if (!instructions.isEmpty()) {
            for (Instruction inst: instructions) {
                if (Program.debuggerStepCounter == Program.maxCount) {
                    System.out.println(inst.toString());
                    program.debugHelper();
                }
                inst.debug(program);
            }
        }
        program.removeVariableMap();
        program.removeProcedureMap();
        Program.listOfBlocks.remove(this);
    }
}
