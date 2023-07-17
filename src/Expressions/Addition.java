package Expressions;
import Instructions.InstrException;
import Instructions.Instruction;
import Program.*;

public class Addition extends Expression {
    private Expression expr1;
    private Expression expr2;
    public Addition(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public static Addition of(Expression expr1, Expression expr2) {
        return new Addition(expr1, expr2);
    }

    @Override
    public String toString() {
        return "(" + expr1.toString() + " + " + expr2.toString() + ")";
    }

    @Override
    public int getValue(Program program, Instruction instruction) throws InstrException {
        return expr1.getValue(program, instruction) + expr2.getValue(program, instruction);
    }
}
