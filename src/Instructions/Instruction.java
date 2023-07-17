package Instructions;
import Program.*;

public abstract class Instruction {
    abstract void execute(Program program) throws InstrException;

    abstract void debug(Program program) throws InstrException;
}
