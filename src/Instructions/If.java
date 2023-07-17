package Instructions;

import Expressions.Expression;
import Program.Program;

import java.util.ArrayList;
import java.util.List;

public class If extends Instruction {
    private List<Instruction> instructions;
    private Expression expr1;
    private Expression expr2;
    private String operator;

    public If(Expression expr1, String operator, Expression expr2) {
        this.instructions = new ArrayList<>();
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "If " + "(" + expr1.toString() + " " + operator + " " + expr2.toString() + ")";
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    @Override
    void execute(Program program) throws InstrException {
        int num1 = expr1.getValue(program, this);
        int num2 = expr2.getValue(program, this);
        boolean condition = switch (operator) {
            case ">" -> num1 > num2;
            case ">=" -> num1 >= num2;
            case "<" -> num1 < num2;
            case "<=" -> num1 <= num2;
            case "<>" -> num1 != num2;
            case "=" -> num1 == num2;
            default -> throw new InstrException(program, this, "Niepoprawny operator");
        };
        if (condition) {
            for (Instruction inst: instructions) {
                inst.execute(program);
            }
        }
    }

    @Override
    void debug(Program program) throws InstrException {
        Program.debuggerStepCounter++;
        int num1 = expr1.getValue(program, this);
        int num2 = expr2.getValue(program, this);
        boolean condition = switch (operator) {
            case ">" -> num1 > num2;
            case ">=" -> num1 >= num2;
            case "<" -> num1 < num2;
            case "<=" -> num1 <= num2;
            case "<>" -> num1 != num2;
            case "=" -> num1 == num2;
            default -> throw new InstrException(program, this, "Niepoprawny operator");
        };
        if (condition) {
            for (Instruction inst: instructions) {
                if (Program.debuggerStepCounter == Program.maxCount) {
                    System.out.println(inst.toString());
                    program.debugHelper();
                }
                inst.debug(program);
            }
        }
    }
}
