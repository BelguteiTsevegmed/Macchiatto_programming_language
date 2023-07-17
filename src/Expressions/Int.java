package Expressions;
import Instructions.Instruction;
import Program.*;

public class Int extends Expression {
    private int value;

    public Int(int value) {
        this.value = value;
    }
    public static Int of(int value) {
        return new Int(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int getValue(Program program, Instruction instruction) {
        return value;
    }
}
