import java.util.*;

public class Main{
    public static void main(String[] args){
        String[] words = {"word", "ANother", "test", "thingsnnn", "more", "even", "onto", "bread", "stain", "drink"};
        int lengthy = args.length;
        if(lengthy > 0){
            // you can use command line arguments to input words into word search
            words = new String[lengthy];
            for(int i=0; i < lengthy; i++){
                words[i] = args[i];
            }
        }
        WordMatrix search = new WordMatrix(words);

        search.printMe();
    }
}
