package Expressions;
import Instructions.InstrException;
import Instructions.Instruction;
import Program.*;

public abstract class Expression {
    public abstract int getValue(Program program, Instruction instruction) throws InstrException;
}

