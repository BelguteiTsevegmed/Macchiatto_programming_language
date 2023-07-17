package Program;
import Instructions.*;
import Expressions.*;

import java.util.List;


public class BlockBuilder {
    private Block block;

    public BlockBuilder() {
        this.block = new Block();
    }

    public BlockBuilder addBlock (Block block1) {
        block.addInstruction(block1);
        return this;
    }

    public BlockBuilder declareVariable(char name, int num) {
        block.addInstruction(new Declaration(name, num));
        return this;
    }

    public BlockBuilder declareProcedure(String name, List<Character> params, Block newBlock) {
        Procedure proc = new Procedure(name, params, newBlock.getInstructionList());
        block.addInstruction(proc);
        return this;
    }

    public BlockBuilder assign(char name, Expression expression) {
        block.addInstruction(new Assign(name, expression));
        return this;
    }

    public BlockBuilder invoke(String name, List<Expression> arguments) {
        block.addInstruction(new CallProcedure(name, arguments));
        return this;
    }

    public BlockBuilder addFor(char index, Expression expression, Block block) {
        For for1 = new For(index, expression);
        for (Instruction inst: block.getInstructionList()){
            for1.addInstruction(inst);
        }
        block.addInstruction(for1);
        return this;
    }

    public BlockBuilder addIf(Expression expr1, String operator, Expression expr2, Block block) {
        If if1 = new If(expr1, operator, expr2);
        for (Instruction inst: block.getInstructionList()){
            if1.addInstruction(inst);
        }
        block.addInstruction(if1);
        return this;
    }

    public BlockBuilder print(Expression expression) {
        block.addInstruction(new Print(expression));
        return this;
    }

    public Block build() {
        return block;
    }
}
