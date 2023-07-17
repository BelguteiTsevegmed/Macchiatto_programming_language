package Program;
import Expressions.Int;
import Instructions.Block;
import Instructions.Procedure;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Program {
    private ArrayList<Map<Character, Integer>> variables;
    private ArrayList<Map<String, Procedure>> procedures;
    public static int debuggerStepCounter;
    public static int maxCount;
    public static List<Block> listOfBlocks;

    public Program() {
        this.variables = new ArrayList<>();
        this.procedures = new ArrayList<>();
    }

    public void makeVariableMap() {
        variables.add(new HashMap<>());
    }

    public void removeVariableMap() {
        variables.remove(variables.size() - 1);
    }

    public int getVariable(char variableName) {
        for (int i = variables.size() - 1; i >= 0; i--) {
            if (variables.get(i).containsKey(variableName)) {
                return variables.get(i).get(variableName);
            }
        }
        return 0;
    }

    public void removeVariable(char variableName) {
        for (int i = variables.size() - 1; i >= 0; i--) {
            if (variables.get(i).containsKey(variableName)) {
                variables.get(i).remove(variableName);
            }
        }
    }

    public void setVariable(char variableName, int number) {
        variables.get(variables.size() - 1).put(variableName, number);
    }

    public void assignVariable(char variableName, int num) {
        for (int i = variables.size() - 1; i >= 0; i--) {
            if (variables.get(i).containsKey(variableName)) {
                variables.get(i).put(variableName, num);
                return;
            }
        }
    }


    public Boolean VarDeclaredInCurrentBlock(char variableName) {
        return variables.get(variables.size() - 1).containsKey(variableName);
    }

    public void makeProcedureMap() {
        procedures.add(new HashMap<>());
    }

    public void removeProcedureMap() {
        procedures.remove(procedures.size() - 1);
    }

    public void setProcedure(Procedure procedure) {
        procedures.get(procedures.size() - 1).put(procedure.getName(), procedure);
    }

    public Procedure getProcedure(String name) {
        for (int i = procedures.size() - 1; i >= 0; i--) {
            if (procedures.get(i).containsKey(name)) {
                return procedures.get(i).get(name);
            }
        }
        return null;
    }

    public Boolean ProcDeclaredInCurrentBlock(String name) {
        return procedures.get(procedures.size() - 1).containsKey(name);
    }

    public void printVariables() {
        Map<Character, Integer> allVariables = new HashMap<>();
        for (int i = variables.size() - 1; i >= 0; i--) {
            for (char var: variables.get(i).keySet()) {
                if (!allVariables.containsKey(var)) {
                    allVariables.put(var, variables.get(i).get(var));
                }
            }
        }
        for (char var: allVariables.keySet()) {
            System.out.println(var + allVariables.get(var));
        }
    }

    public boolean isDeclared(char variableName) {
        for (int i = variables.size() - 1; i >= 0; i--) {
            if (variables.get(i).containsKey(variableName)) {
                return true;
            }
        }
        return false;
    }

    // Helper function for the "m" switch debugger
    public void dumpMemory(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);

            // Save the procedure definitions visible at a given point
            Map<String, Procedure> allProcedures = new HashMap<>();
            for (int i = procedures.size() - 1; i >= 0; i--) {
                for (String name: procedures.get(i).keySet()) {
                    if (!allProcedures.containsKey(name)) {
                        allProcedures.put(name, procedures.get(i).get(name));
                    }
                }
            }
            // Write procedure declarations to file
            writer.write("Deklaracje procedur:\n");
            for (String name: allProcedures.keySet()) {
                writer.write(name + " " + allProcedures.get(name).getParameters() + "\n");
            }

            // Save variable values visible at a given point
            Map<Character, Integer> allVariables = new HashMap<>();
            for (int i = variables.size() - 1; i >= 0; i--) {
                for (char var: variables.get(i).keySet()) {
                    if (!allVariables.containsKey(var)) {
                        allVariables.put(var, variables.get(i).get(var));
                    }
                }
            }
            // Write variable values to file
            writer.write("Wartości zmiennych:\n");
            for (char var: allVariables.keySet()) {
                writer.write(var + " = " + allVariables.get(var) + "\n");
            }

            writer.close();
            System.out.println("Zrzut pamięci został zapisany do pliku: " + filePath);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania zrzutu pamięci do pliku: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void debugHelper() {
        Scanner scan = new Scanner(System.in);
        switch(scan.next().charAt(0)) {
            case 'c' -> {
                maxCount = debuggerStepCounter - 1;
                break;
            }
            case 's' -> {
                debuggerStepCounter = 0;
                maxCount = scan.nextInt();
                if (maxCount > 0) {
                    break;
                } else {
                    System.out.println("Proszę wybrać liczbę kroków większą od 0.");
                    debugHelper();
                }
            }
            case 'd' -> {
                int depth = scan.nextInt();
                if (depth > listOfBlocks.toArray().length - 1) {
                    System.out.println("Podana liczba jest większa niż poziom" +
                            " zagnieżdżenia bieżącej instrukcji programu. Spróbuj ponownie.");
                    debugHelper();
                } else {
                    System.out.print("Zmienne widoczne w bloku, w którym wystąpił błąd: ");
                    printVariables();
                }
                debugHelper();
            }
            case 'm' -> {
                // 'm' switch in debugger allows to dump memory to a file.
                // The user needs to provide the file path as the next input.
                String filePath = scan.next();
                dumpMemory(filePath);
                debugHelper();
            }
            case 'e' -> {
                System.exit(0);
            }
            default -> {
                System.out.println("Niepoprawne polecenie debuggera.");
                debugHelper();
            }
        }
    }

    public void execute(Block mainBlock) throws Exception{
        mainBlock.execute(this);
    }

    public void debug(Block mainBlock) throws Exception {
        listOfBlocks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        char order = scan.next().charAt(0);
        switch(order) {
            case 'c' -> {
                mainBlock.execute(this);
                break;
            }
            case 's' -> {
                if (debuggerStepCounter > maxCount) {
                    throw new Exception("tutaj blad");
                }
                debuggerStepCounter = 0;
                maxCount = scan.nextInt();
                if (maxCount == 0) {
                    System.out.println(mainBlock.toString());
                    debugHelper();
                } else if (maxCount > 0 ){
                    mainBlock.debug(this);
                } else {
                    System.out.println("Nie da się cofać. Spróbuj ponownie.");
                    debugHelper();
                }
            }
            case 'd' -> {
                System.out.println("Program jeszcze nie rozpoczął działania," +
                        "więc nie ma zmiennych do ukazania. Spróbuj ponownie.");
                debugHelper();
            }
            case 'm' -> {
                String filePath = scan.next();
                dumpMemory(filePath);
                debugHelper();
            }
            case 'e' -> {
                System.exit(0);
            }
            default -> {
                System.out.println("Niepoprawne polecenie debuggera. Spróbuj ponownie.");
                debugHelper();
            }
        }
        System.out.println("Działanie debuggera zostało zakończone.");
    }
}
