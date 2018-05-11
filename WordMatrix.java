import java.util.*;

public class WordMatrix{
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private ArrayList<String> words;
    private Letter[][] matrix;
    private int size;

    public WordMatrix(String[] words){
        for(String i : words){
            i = stripWord(i);
            if(i.length > 0){
                // means word contains all characters
                words.add(i);
            }
        }
        System.out.println(words);
    }

    public String stripWord(String word){
        int lengthy = word.length;
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

    private void FillMatrix(){
        // fills matrix with random values from alphabet
    }

    public void printMe(){
        for(int i=0; i < size; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

}
