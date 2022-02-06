/**
 * * Created by Andrew Cantrell on February 13
 * For Dr. Han
 * EGR 227
 * Due February 14
 * HW#4: Anagram, Part 1 LetterInventory
 * This class is utilized by Anagram Main and runs a lot of different
 * functionality. It constructs the Letter Inventory, then can return
 * the number of times any specific letter appears in the text. It can
 * return the size of the given inventory of letters. It can return whether
 * or not the inventory is empty. It can add different inventories of letters
 * together or subtract them. It can also get the percentage of a specific
 * letter in the inventory.
 **/
public class LetterInventory {
    private static final int numLetters = 26;
    public int[] inventory;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int totalCount = 0;

    /**
     * Basic empty constructor of LetterInventory
     */
    public LetterInventory() {
        inventory = new int[numLetters];
        for(int i = 0; i < numLetters; i++){
            inventory[i] = 0;
        }
    }

    /**
     * Basic consructor given a string of letters to construct
     * @param data to be added in
     */
    public LetterInventory(String data) {
        inventory = new int[numLetters];
        //Initialize array
        for(int i = 0; i < numLetters; i++){
            inventory[i] = 0;
        }

        for (int i = 0; i < data.length(); i++) {
            for (int j = 0; j < numLetters; j++) {
                if (Character.toString(data.charAt(i))
                        .equalsIgnoreCase(Character.toString(alphabet.charAt(j)))){
                    totalCount++;
                    inventory[j]++;
                }
            }
        }
    }

    /**
     * Returns the count of any given character as long as it is alhpabetical
     * @param letter to be found
     * @return occurrences of letter
     */
    public int get(char letter){
        if(!Character.isAlphabetic(letter)){throw new IllegalArgumentException();}
        char tempChar = ((Character.toString(letter)).toLowerCase()).charAt(0);
        if(tempChar >= 'a') {
            return inventory[tempChar - 'a'];
        }
            return 0;
    }
    /**
     * Sets any given character value in the inventory to a set ammount
     * @param letter to be added in
     * @param value to be set to
     */
    public void set(char letter, int value){
        if(value < 0 || !Character.isAlphabetic(letter)){throw new IllegalArgumentException();}
        char tempChar = ((Character.toString(letter)).toLowerCase()).charAt(0);
        if(tempChar >= 'a'){
            int displacement = tempChar - 'a';
            System.out.println(displacement);
            totalCount -= inventory[displacement];
            inventory[displacement] = value;
            totalCount += inventory[displacement];
        }
    }

    /**
     * @return the size of the inventory
     */
    public int size(){
        return totalCount;
    }

    /**
     * Returns whether or not the inventory is empty.
     * @return
     */
    public boolean isEmpty(){
        return (totalCount <= 0);
    }

    /**
     * Returns string version of the inventory
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < numLetters; i++){
            if(inventory[i] != 0) {
                for (int j = 0; j < inventory[i]; j++) {
                    sb.append(alphabet.charAt(i));
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Adds the values of any given two letter inventories together and returns
     * that letter inventory.
     * @param other
     * @return
     */
    public LetterInventory add(LetterInventory other){
        LetterInventory temp = new LetterInventory();
        for(int i = 0; i < numLetters; i++){
            temp.inventory[i] += inventory[i];
            temp.inventory[i] += other.inventory[i];
            temp.totalCount += temp.inventory[i];
        }

        return temp;
    }

    /**
     * Subtracts the values of any given two letter inventories together and returns
     * that letter inventory.
     * @param other
     * @return
     */
    public LetterInventory subtract(LetterInventory other){
        LetterInventory temp = new LetterInventory();
        for(int i = 0; i < numLetters; i++){
            temp.inventory[i] += inventory[i];
            temp.inventory[i] -= other.inventory[i];
            if(temp.inventory[i] < 0){
                return null;
            }
            temp.totalCount += temp.inventory[i];
        }
        return temp;
    }

    /**
     * Returns the percentage of any given letter to the total ammount of letters
     * in the inventory
     * @param letter to find percentage of
     * @return letter percentage
     */
    public double getLetterPercentage(char letter){
        if(!Character.isAlphabetic(letter)){throw new IllegalArgumentException();}
        for(int i = 0; i < numLetters; i++){
            if(Character.toString(letter).equalsIgnoreCase(Character.toString(alphabet.charAt(i)))){
                double count = (double) inventory[i];
                double size = (double) totalCount;

                return count / size;
            }
        }
        throw new IllegalArgumentException("That letter is not in the alphabet");
    }
}
