import java.util.*;

/**
 * * Created by Andrew Cantrell on February 20
 * For Dr. Han
 * EGR 227
 * Due February 21
 * HW#4: Anagram, Part 2 Angrams
 * This class is utilized by Anagram Main and runs a lot of different
 * functionality. It constructs the Anagrams object, then can print any
 * possible solution of an anagram from a passed in string and a maximum
 * number of words given by the user. There are only two private methods
 * both are simple helper methods of the constructor and the print method.
 **/

public class Anagrams {
    private Map<String, LetterInventory> wordMap;
    private List<String> dictionary;
    private ArrayList<String> pruned;

    /**
     * This method is a simple constructor that creates a word map linked
     * to the LetterInventory of each word. And it constructs a List of
     * all of the initial words in dictionary.
     *
     * @param dictionary
     */
    public Anagrams(List<String> dictionary) {
        wordMap = new HashMap<>();
        for (String word : dictionary) {
            LetterInventory temp = new LetterInventory(word);
            wordMap.put(word, temp);
        }
        this.dictionary = new ArrayList(dictionary);
    }

    /**
     * This method trims down the inventory to the set of words that can fit
     * inside the provided dictionary of words. It then calls a recursvie helper
     * method to do the heavy lifting and processing of the anagrams problem.
     *
     * @param text to find anagrams of
     * @param max  number of words possible in the anagram list
     */
    public void print(String text, int max) {
        if (max < 0) throw new IllegalArgumentException();
        LetterInventory textInv = new LetterInventory(text);
        pruned = prune(textInv);
        Stack<String> solution = new Stack<>();
        print(textInv, max, solution);
    }

    /**
     * This is a recursive helper method for the print function that returns
     * all possible anagram solutions that fit inside the overall nunmber of
     * words possible. It allows for infinite number of words in the list if
     * the user passes in zero.
     *
     * @param textInv  Inventory of letters for passed in text
     * @param max      number of words in solution
     * @param solution list of solution possibilities
     */
    private void print(LetterInventory textInv, int max, Stack<String> solution) {
        //Found a solution
        if (textInv.size() == 0) {
            System.out.println(solution); //print solution list
        } else if (solution.size() == max && solution.size() != 0) {
            return;
        }
        //For each word in the pruned list of words
        for (String word : pruned) {
            //Comparing number of letters in the LetterInventory
            LetterInventory newInv = textInv.subtract(wordMap.get(word));
            if (newInv != null) {
                solution.push(word);
                print(newInv, max, solution);
                solution.pop();
            }
        }
    }

    /**
     * Returns a pruned list of words from the dictionary that can actually
     * work with the provided text given by the user.
     *
     * @param textInv inventory of passed in String by user
     * @return the pruned list
     */
    private ArrayList<String> prune(LetterInventory textInv) {
        //List to return
        ArrayList<String> tempList = new ArrayList<>(dictionary);
        for (String word : dictionary) {
            if (textInv.subtract(wordMap.get(word)) == null) {
                //Remove only if it doesnt work with the dictionary
                tempList.remove(word);
            }
        }
        return tempList;
    }
}
