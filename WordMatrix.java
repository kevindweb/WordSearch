import java.util.*;

public class WordMatrix{
    private char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public Random rand = new Random();
    private List<String> myWords;
    private Letter[][] matrix;
    private int size;

    public WordMatrix(String[] words){
        myWords = new ArrayList<String>();
        for(String i : words){
            i = stripWord(i);
            if(i.length() > 0 && !myWords.contains(i)){
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
            fillMatrix();
            // loop through myWords and placeWord
            for(String s : myWords){
                placeWord(s);
            }
            // placeWord(myWords.get(0));
            // placeWord(myWords.get(1));
        } else{
            System.out.println("Matrix is empty!");
        }
    }

    private String stripWord(String word){
        word = word.toUpperCase();
        int lengthy = word.length();
        String duplicate = "";
        if(lengthy > 0){
            for(int i=0; i < lengthy; i++){
                // loops through characters
                char letter = word.charAt(i);
                int ascii = (int) letter;
                if(ascii > 64 && ascii < 91){
                    duplicate += letter;
                }
            }
            return duplicate;
        }
        return "";
    }

    private void placeWord(String word){
        // places specific word in our matrix
        // go random first, and if it doesn't fit, try positions in order
        int orientation = rand.nextInt(2);
        int lengthy = word.length();
        int row;
        int col;
        if(orientation == 0){
            // vertical placement
            row = rand.nextInt(size - lengthy + 1);
            col = rand.nextInt(size);
            // starting position of word in matrix
        } else{
            // horizontal placement
            row = rand.nextInt(size);
            // starting position of word in matrix
            col = rand.nextInt(size - lengthy + 1);
        }
        int testRow = row;
        int testCol = col;
        // first for loop is to test whether word can fit
        for(int i=0; i < lengthy; i++){
            char letter = word.charAt(i);
            Letter currLetter = matrix[testRow][testCol];
            if(currLetter.getLetter() != letter && currLetter.isPartOfWord()){
                // letter inside another word is already here, cannot place letter
                placeWord(word, orientation, 0, 0);
                // start at first position and try again in order
                return;
            }
            if(orientation == 0){
                testRow++;
            } else{
                testCol++;
            }
        }
        // second is to actually input values
        for(int i=0; i < lengthy; i++){
            char letter = word.charAt(i);
            Letter currLetter = matrix[row][col];
            if(!currLetter.isPartOfWord()){
                Letter ourLetter = new Letter(letter, word);
                matrix[row][col] = ourLetter;
            } else if(currLetter.getLetter() == letter && currLetter.isPartOfWord()){
                // means same letter is already there, don't do anything, we're fine
            } else if(currLetter.getLetter() == letter && !currLetter.isPartOfWord()){
                Letter ourLetter = new Letter(letter, word);
                matrix[row][col] = ourLetter;
                // place letter with word over letter without word
            } else{
                // letter inside another word is already here, cannot place letter
                placeWord(word, orientation, 0, 0);
                // start at first position and try again in order
                return;
            }
            if(orientation == 0){
                row++;
            } else{
                col++;
            }
        }
    }

    private void placeWord(String word, int orientation, int prevRow, int prevCol){
        // means we have already tried to place this word
        // test positions in order instead of random placement
        // if we get to the last prevRow or prevColumn and cannot place, expand matrix and add word
        if(prevRow > size - 1 || prevCol > size - 1){
            System.out.println("Outside bounds!!");
            // expand matrix and test again
            expandMatrix(word);
            return;
        }
        int row = prevRow;
        int col = prevCol;
        int lengthy = word.length();
        int testRow = row;
        int testCol = col;
        // first for loop is to test whether word can fit
        for(int i=0; i < lengthy; i++){
            char letter = word.charAt(i);
            Letter currLetter = matrix[testRow][testCol];
            if(currLetter.getLetter() != letter && currLetter.isPartOfWord()){
                // letter inside another word is already here, cannot place letter
                if(orientation == 0){
                    placeWord(word, orientation, prevRow, ++prevCol);
                } else{
                    placeWord(word, orientation, ++prevRow, prevCol);
                }
                return;
            }
            if(orientation == 0){
                testRow++;
            } else{
                testCol++;
            }
        }
        for(int i=0; i < lengthy; i++){
            char letter = word.charAt(i);
            Letter currLetter = matrix[row][col];
            if(!currLetter.isPartOfWord()){
                Letter ourLetter = new Letter(letter, word);
                matrix[row][col] = ourLetter;
            } else if(currLetter.getLetter() == letter && currLetter.isPartOfWord()){
                // means same letter is already there, don't do anything, we're fine
            } else if(currLetter.getLetter() == letter && !currLetter.isPartOfWord()){
                Letter ourLetter = new Letter(letter, word);
                matrix[row][col] = ourLetter;
                // place letter with word over letter without word
            } else{
                // letter inside another word is already here, cannot place letter
                if(orientation == 0){
                    placeWord(word, orientation, prevRow, ++prevCol);
                } else{
                    placeWord(word, orientation, ++prevRow, prevCol);
                }
                return;
            }
            if(orientation == 0){
                row++;
            } else{
                col++;
            }
        }
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
        int prevSize = prev.length;
        Letter[][] duplicate = new Letter[size][size];
        for(int i=0; i < size; i++){
            for(int x=0; x < size; x++){
                if(i < prevSize && x < prevSize){
                    duplicate[i][x] = prev[i][x];
                } else{
                    char curr = alphabet[rand.nextInt(lengthy)];
                    Letter currLetter = new Letter(curr);
                    duplicate[i][x] = currLetter;
                }
            }
        }
        matrix = duplicate;
    }

    private void expandMatrix(String wordToAdd){
        System.out.println("Expanding! " + wordToAdd);
        size++;
        fillMatrix(matrix);
        // now add wordToAdd to expanded matrix
        int lengthy = wordToAdd.length();
        int orientation = rand.nextInt(2);
        int row;
        int col;
        // see placeWord method for info about variables
        if(orientation == 0){
            row = rand.nextInt(size - lengthy + 1);
            col = size - 1;
            // place randomly in last column
        } else{
            col = rand.nextInt(size - lengthy + 1);
            row = size - 1;
            // place randomly in last row
        }
        for(int i=0; i < lengthy; i++){
            char letter = wordToAdd.charAt(i);
            Letter ourLetter = new Letter(letter, wordToAdd);
            matrix[row][col] = ourLetter;
            if(orientation == 0){
                row++;
            } else{
                col++;
            }
        }
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
