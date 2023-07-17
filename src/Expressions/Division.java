package Expressions;

import Instructions.Assign;
import Instructions.InstrException;
import Instructions.Instruction;
import Program.Program;

public class Division extends Expression {
    private Expression expr1;
    private Expression expr2;

    public Division(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public static Division of(Expression expr1, Expression expr2) {
        return new Division(expr1, expr2);
    }

    @Override
    public String toString() {
        return "(" + expr1.toString() + "/" + expr2.toString() + ")";
    }

    @Override
    public int getValue(Program program, Instruction instruction) throws InstrException {
        int num2 = expr2.getValue(program, instruction);
        if (num2 == 0) {
            Assign assign = null;
            throw new InstrException(program, assign, "Wyrażenie drugie," +
                    " czyli dzielnik, nie może być zerem.");
        }
        return expr1.getValue(program, instruction) / num2;
    }
}
