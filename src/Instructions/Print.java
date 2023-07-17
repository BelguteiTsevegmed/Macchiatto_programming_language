package Instructions;
import Expressions.*;
import Program.*;

public class Print extends Instruction {
    private Expression expr;

    public Print(Expression expression) {
        expr = expression;
    }

    @Override
    public String toString() {
        return "Print " + expr.toString();
    }

    @Override
    public void execute(Program program) throws InstrException {
        System.out.println(expr.getValue(program, this));
    }

    @Override
    void debug(Program program) throws InstrException {
        System.out.println(expr.getValue(program, this));
        Program.debuggerStepCounter++;
    }
}
