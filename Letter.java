public class Letter{
    // class used to define aspect of WordMatrix
    private char letter;
    private String word;

    public Letter(char letter, String word){
        this.letter = letter;
        this.word = word;
    }

    public Letter(char letter){
        this.letter = letter;
        this.word = null;
    }

    public char getLetter(){
        return letter;
    }

    public String getWord(){
        return word;
    }

    public boolean isPartOfWord(){
        return (word != null);
    }
}
