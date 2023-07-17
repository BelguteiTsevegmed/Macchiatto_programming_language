package Instructions;
import java.util.List;
import Program.Program;
import Expressions.*;

public class CallProcedure extends Instruction {
    // The name of the procedure to be called
    private String name;
    // The arguments to be passed to the procedure when it's called
    private List<Expression> arguments;

    public CallProcedure(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Procedure Call " + name + arguments;
    }

    // The execute method for this instruction. It gets the procedure from the program,
    // checks it and its parameters for validity, then executes the procedure.
    @Override
    void execute(Program program) throws InstrException {
        // Fetch the procedure using the name
        Procedure procedure = program.getProcedure(name);

        // If the procedure does not exist in the program, throw an exception
        if (procedure == null) {
            throw new InstrException(program, this,"Procedura " + name + " nie zdefiniowana.");
        }

        // If the number of arguments provided does not match the procedure's parameters, throw an exception
        if (procedure.getParameters().size() != arguments.size()) {
            throw new InstrException(program, this, "Nieprawidłowa liczba argumentów w " + name + ".");
        }

        // Before executing, make a new layer of environment for the variables and procedures
        program.makeVariableMap();
        program.makeProcedureMap();

        // Set the values of the procedure's parameters to the values of the provided arguments
        for (int i = 0; i < procedure.getParameters().size(); i++) {
            program.setVariable(procedure.getParameters().get(i), arguments.get(i).getValue(program, this));
        }

        // If the procedure has instructions, execute them
        if (!procedure.getInstructions().isEmpty()) {
            for(Instruction inst: procedure.getInstructions()){
                inst.execute(program);
            }
        }

        // After executing, remove the new layer of environment of the program variables and procedures
        program.removeVariableMap();
        program.removeProcedureMap();
    }

    // Similar to execute method but with debugging support.
    @Override
    void debug(Program program) throws InstrException {
        Procedure procedure = program.getProcedure(name);

        if (procedure == null) {
            throw new InstrException(program, this,"Procedura " + name + " nie zdefiniowana.");
        }

        if (procedure.getParameters().size() != arguments.size()) {
            throw new InstrException(program, this, "Nieprawidłowa liczba argumentów w " + name + ".");
        }

        // Increase debuggerStepCounter to control stepping through the program while debugging
        Program.debuggerStepCounter++;

        program.makeVariableMap();
        program.makeProcedureMap();

        for (int i = 0; i < procedure.getParameters().size(); i++) {
            program.setVariable(procedure.getParameters().get(i), arguments.get(i).getValue(program, this));
        }

        // If the procedure has instructions, execute them, pausing and
        // printing information if the debug step count matches maxCount
        if (!procedure.getInstructions().isEmpty()) {
            for (Instruction inst: procedure.getInstructions()) {
                if (Program.debuggerStepCounter == Program.maxCount) {
                    System.out.println(inst.toString());
                    program.debugHelper();
                }
                inst.debug(program);
            }
        }

        program.removeVariableMap();
        program.removeProcedureMap();
    }
}
