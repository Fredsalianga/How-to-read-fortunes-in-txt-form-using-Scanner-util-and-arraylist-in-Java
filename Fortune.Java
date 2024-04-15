import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A fortune teller - will enable the client user to obtain a "fortune cookie"
 * style fortune.
 * 
 * @author david levine and Frederico Salianga
 * @version 26 November 2022 (although you will need to update this)
 *
 */
public class Fortune {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        Scanner mystic = new Scanner(new File("fortunes.txt"));
        ArrayList<String> fortunes = new ArrayList<>();
        while (mystic.hasNextLine()) {
            fortunes.add(mystic.nextLine());
        }
        mystic.close();

        // Filter fortunes containing "in"
        List<String> filteredFortunes = keepKey(fortunes, "in");
        
        // Print filtered fortunes
        System.out.println("Filtered fortunes containing 'in':");
        for (String fortune : filteredFortunes) {
            System.out.println(fortune);
        }

        // Allow user to specify a keyword
        System.out.print("Enter a keyword to search for in fortunes: ");
        String keyword = console.nextLine();

        // Filter fortunes containing user-specified keyword
        List<String> userFilteredFortunes = keepKey(fortunes, keyword);

        // Choose a random fortune from filtered list
        String userFortune = getFortune(userFilteredFortunes);

        // Print random fortune
        System.out.println("Random fortune containing '" + keyword + "':");
        System.out.println(userFortune);
        
        // Handle empty filtered list
        if (userFilteredFortunes.isEmpty()) {
            System.out.println("Nothing good comes from thinking of " + keyword);
        }

        console.close();
    }

    /**
     * Return a random fortune from a non-empty list of possible fortunes.
     * @param fortunes the list of possible fortunes
     * @return the fortune for this invocation of the method
     */
    public static String getFortune(List<String> fortunes) {
        if (fortunes.isEmpty()) {
            return "No fortunes available";
        }
        
        Random random = new Random();
        int randomIndex = random.nextInt(fortunes.size());
        return fortunes.get(randomIndex);
    }

    /**
     * Filter a list of strings to keep only those containing a specified key.
     * @param strings the list of strings to filter
     * @param key the key to search for in the strings
     * @return the filtered list of strings
     */
    public static List<String> keepKey(List<String> strings, String key) {
        List<String> filteredList = new ArrayList<>();
        for (String str : strings) {
            if (str.contains(key)) {
                filteredList.add(str);
            }
        }
        return filteredList;
    }

}
