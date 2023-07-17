# Macchiato Programming Language Simulator

This repository is a simulator and debugger for the Macchiato programming language and its enhanced version, featuring additional language functionalities and improvements in its ecosystem. With this simulator, you can create, execute, and debug Macchiato programs more conveniently.

## Features

### Program Execution
The simulator can execute Macchiato programs, outputting the final state of all variables in the main program block. 

### Debugging
In addition to regular execution, the simulator also supports step-by-step debugging. Debugging sessions can be controlled using commands provided via standard input.

### Procedures
In the enhanced version of Macchiato, you can now declare and call procedures, which are similar to void functions that take zero or more integer parameters. The declaration of a procedure includes a header that provides its name and parameters, and a body that defines the series of instructions that will be executed when the procedure is called.

### Enhanced Debugging Commands
The debugger now includes a new command, `m(emory dump) <filepath>`, which creates a memory dump of the program to a specified file. The memory dump includes visible procedure declarations (names and parameters, but no contents) and the current state of all variables (like `d 0` command).

## Debugging Commands

Here are the available debugger commands:

- `c(ontinue)` : Continue execution until the end of the program. If the program has already finished, this command will only output a relevant message.
- `s(tep) <number>` : Execute a certain number of steps in the program. One step is defined as the execution of a single instruction. After the specified number of steps, the next instruction (possibly a complex one) to be executed will be output. If the program ends before the specified number of steps, a relevant message is output and the program ends normally.
- `d(isplay) <number>` : Display the current state of variables. The parameter specifies the level of the outer block to be displayed. For example, `d 0` displays the current level. If the specified number exceeds the level of nesting of the current instruction, only a relevant message will be displayed.
- `e(xit)` : Terminate the program execution and end the debugging session. The final state of the variables is not displayed.
- `m(emory dump) <filepath>` : Dump the memory state of the program to a file. The memory dump includes visible procedure declarations and the current state of all variables.

## Convenient Macchiato Program Creation in Java
Creating Macchiato programs is now more convenient with the new set of classes provided by this project, which acts as a small SDK for Macchiato. It allows for the construction of programs and their individual parts in a builder-like fashion, adding declarations and instructions one by one through appropriate method calls. Expressions can be created using clear, static functions.

## Usage
To use this simulator, construct your Macchiato program in Java using the classes provided by this project. You can then execute it or debug it using the functionalities provided by the simulator. 

## Contributing
Contributions are always welcome. Feel free to submit pull requests or open issues to improve this project.

## License
This project is licensed under the MIT License.
