package Program;

import Expressions.Expression;
import Instructions.*;

import java.util.List;

public class ProgramBuilder {
    private Block block;

    public ProgramBuilder() {
        this.block = new Block();
    }

    public ProgramBuilder addBlock (Block block1) {
        block.addInstruction(block1);
        return this;
    }

    public ProgramBuilder declareVariable(char name, int num) {
        block.addInstruction(new Declaration(name, num));
        return this;
    }

    public ProgramBuilder declareProcedure(String name, List<Character> params, Block newBlock) {
        Procedure proc = new Procedure(name, params, newBlock.getInstructionList());
        block.addInstruction(proc);
        return this;
    }

    public ProgramBuilder assign(char name, Expression expression) {
        block.addInstruction(new Assign(name, expression));
        return this;
    }

    public ProgramBuilder invoke(String name, List<Expression> arguments) {
        block.addInstruction(new CallProcedure(name, arguments));
        return this;
    }

    public ProgramBuilder addFor(char index, Expression expression, Block block) {
        For for1 = new For(index, expression);
        for (Instruction inst: block.getInstructionList()){
            for1.addInstruction(inst);
        }
        block.addInstruction(for1);
        return this;
    }

    public ProgramBuilder addIf(Expression expr1, String operator, Expression expr2, Block block) {
        If if1 = new If(expr1, operator, expr2);
        for (Instruction inst: block.getInstructionList()){
            if1.addInstruction(inst);
        }
        block.addInstruction(if1);
        return this;
    }

    public ProgramBuilder print(Expression expression) {
        block.addInstruction(new Print(expression));
        return this;
    }

    public Block build() {
        return block;
    }

    public void execute() throws Exception {
        Program program = new Program();
        program.execute(block);
    }
    public void debug() throws Exception {
        Program program = new Program();
        program.debug(block);
    }
}
