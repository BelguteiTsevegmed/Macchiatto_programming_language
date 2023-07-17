import Expressions.*;
import Instructions.*;
import Program.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ProgramBuilder program = new ProgramBuilder()
                .declareVariable('x', 101)
                .declareVariable('y', 1)
                .declareProcedure("out", List.of('a'), new BlockBuilder()
                        .print(Addition.of(Variable.named('a'), Variable.named('x')))
                        .build()
                )
                .assign('x', Substraction.of(Variable.named('x'), Variable.named('y')))
                .invoke("out", List.of(Variable.named('x')))
                .invoke("out", List.of(Int.of(100)))
                .addBlock(new BlockBuilder()
                        .declareVariable('x', 10)
                        .invoke("out", List.of(Int.of(100)))
                        .build()
                );
        program.execute();
        program.debug();

//        begin block
//        var x 101
//        var y 1
//        proc out(a)
//                print a+x
//        end proc
//        x := x - y
//        out(x)
//        out(100)
//        begin block
//        var x 10
//        out(100)
//        end block
//        end block
    }
}