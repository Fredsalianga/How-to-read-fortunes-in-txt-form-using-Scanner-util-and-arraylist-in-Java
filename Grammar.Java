import java.io.FileNotFoundException;
import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Grammar encapsulates a context free grammar which can be used to create
 * random sentences from within the grammar.
 * 
 * @author david levine and Frederico Salianga
 * @version 26 November 2022
 *
 */
public class Grammar {
    private Map<String, List<String>> grammar;

    /**
     * Construct a grammar from a file.
     * 
     * @param fileName name of the file containing grammatical rules
     * @throws FileNotFoundException if <code>fileName</code> is not found
     */
    public Grammar(String fileName) throws FileNotFoundException {
        Scanner gramFile = new Scanner(new java.io.File(fileName));
        grammar = new TreeMap<String, List<String>>();

        while (gramFile.hasNextLine()) {
            String symbol = gramFile.nextLine().strip();
            if (symbol.length() > 0) {
                boolean doneWithSymbol = false;
                java.util.ArrayList<String> productions = new java.util.ArrayList<>();
                while (!doneWithSymbol) {
                    String expansion = gramFile.nextLine().strip();
                    if (expansion.endsWith(";")) {
                        doneWithSymbol = true;
                    }
                    expansion = expansion.substring(0, expansion.length() - 1).strip();
                    productions.add(expansion);
                }
                grammar.put(symbol, productions);
            }
        }
        gramFile.close();
    }

    /**
     * Pick a random expansion of a given non-terminal from the grammar TO BE
     * COMPLETED BY STUDENT - currently returns its parameter!!!!
     * 
     * @param startSymbol the symbol to expand
     * @return a random expansion of <code>startSymbol</code>
     */
    public String expand(String startSymbol) {
        if (isNonTerminal(startSymbol)) {
            List<String> productions = grammar.get(startSymbol);
            if (productions != null && !productions.isEmpty()) {
                Random random = new Random();
                String chosenProduction = productions.get(random.nextInt(productions.size()));
                return chosenProduction;
            }
        }
        return startSymbol;
    }

    /**
     * Check if a symbol is a non terminal.
     * 
     * @param symbol the symbol to check
     * @return true if the symbol is a non-terminal, otherwise false
     */
    private boolean isNonTerminal(String symbol) {
        return symbol.startsWith("<") && symbol.endsWith(">");
    }
}
