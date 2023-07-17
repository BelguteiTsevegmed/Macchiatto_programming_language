package Instructions;
import java.util.List;
import Program.Program;

// Procedure is an Instruction type that represents the action of declaring a new procedure.
public class Procedure extends Instruction {
    // The name of the procedure
    private String name;
    // The parameters of the procedure
    private List<Character> parameters;
    // The instructions contained within the procedure
    private List<Instruction> instructions;

    public Procedure(String name, List<Character> parameters, List<Instruction> instructions) {
        this.name = name;
        this.parameters = parameters;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Procedure Declaration " + name + parameters;
    }

    public String getName() {
        return name;
    }

    public List<Character> getParameters() {
        return parameters;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    // The execute method for this instruction. It checks if the procedure has already been declared in the current block,
    // and if not, adds this procedure to the program's list of procedures.
    @Override
    void execute(Program program) throws InstrException {
        if (program.ProcDeclaredInCurrentBlock(name)) {
            throw new InstrException(program, this, "Nie można deklarować tej samej procedury" +
                    " dwa razy w tym samym bloku.");
        }
        program.setProcedure(this);
    }

    // Similar to execute method but with debugging support.
    @Override
    void debug(Program program) throws InstrException {
        if (program.ProcDeclaredInCurrentBlock(name)) {
            throw new InstrException(program, this, "Nie można deklarować tej samej procedury" +
                    " dwa razy w tym samym bloku.");
        }
        program.setProcedure(this);
        // Increase debuggerStepCounter to control stepping through the program while debugging
        Program.debuggerStepCounter++;
    }
}
