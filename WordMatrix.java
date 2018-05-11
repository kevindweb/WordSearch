import java.util.*;

public class WordMatrix{
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public Random rand = new Random();
    private ArrayList<String> myWords;
    private Letter[][] matrix;
    private int size;

    public WordMatrix(String[] words){
        myWords = new ArrayList<String>();
        for(String i : words){
            i = stripWord(i);
            if(i.length() > 0){
                // means word contains all characters
                myWords.add(i);
            }
        }
        int longest = 0;
        int lengthy = myWords.size();
        for(int x = 0; x < lengthy; x++){
            String curr = myWords.get(x);
            int currLength = curr.length();
            if(currLength > longest){
                longest = currLength;
            }
        }
        size = longest;
        if(size > 0){
            matrix = new Letter[size][size];
            // System.out.println("Size: " + size);
            fillMatrix();
        } else{
            System.out.println("Matrix is empty!");
        }
    }

    public String stripWord(String word){
        word = word.toLowerCase();
        int lengthy = word.length();
        String duplicate = "";
        if(lengthy > 0){
            for(int i=0; i < lengthy; i++){
                // loops through characters
                char letter = word.charAt(i);
                int ascii = (int) letter;
                if(ascii > 96 && ascii < 123){
                    duplicate += letter;
                }
            }
            return duplicate;
        }
        return "";
    }

    private void fillMatrix(){
        // fills matrix with random values from alphabet
        int lengthy = alphabet.length;
        for(int i=0; i < size; i++){
            for(int x=0; x < size; x++){
                // allows for matrix expansion without overriding previous values
                char curr = alphabet[rand.nextInt(lengthy)];
                Letter currLetter = new Letter(curr);
                matrix[i][x] = currLetter;
            }
        }
    }

    private void fillMatrix(Letter[][] prev){
        // prev is if we are expanding instead of filling empty matrix
        int lengthy = alphabet.length;
        Letter[][] duplicate = new Letter[size][size];
        for(int i=0; i < size; i++){
            for(int x=0; x < size; x++){
                // allows for matrix expansion without overriding previous values
                char curr = alphabet[rand.nextInt(lengthy)];
                Letter currLetter = new Letter(curr);
                matrix[i][x] = currLetter;
            }
        }
        matrix = duplicate;
    }

    public void expandMatrix(){
        size++;
        fillMatrix(matrix);
    }

    public void printMe(){
        System.out.println("Here is the word search!!\n");
        for(int i=0; i < size; i++){
            for(int x=0; x < size; x++){
                System.out.print(matrix[i][x].getLetter() + " ");
            }
            System.out.println();
        }
    }

}
