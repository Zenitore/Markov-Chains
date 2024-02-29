import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MarkovDictionary {

    private File f;
    private int numWords;
    private ArrayList<String> vals;
    private ArrayList<String> keys;
    private ArrayList<MarkovWord> totalDictionary;


    public MarkovDictionary(String inputFile){
        f = new File(inputFile);
        numWords = 0;
        vals = new ArrayList<String>();
        keys = new ArrayList<String>();
        totalDictionary = new ArrayList<MarkovWord>();
    }

    public void train() throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        try {
            s = new Scanner (f);
        }catch(FileNotFoundException fileDoesNotExist){
            System.out.println("File was unable to be located");
            System.exit(0);
        }
        String curWord = "";
        s.useDelimiter(" ");
        String prevWord = s.next();
        while (s.hasNext()){
            curWord = s.next();
            this.vals.add(curWord);
            this.keys.add(prevWord);
            prevWord = curWord;
        }
    }

    public void dictionaryCreation(){
        ArrayList<String> knownKeys = new ArrayList<String>();
        for(int i = 0; i < this.keys.size(); i++){
            if(!knownKeys.contains(this.keys.get(i))){
                this.totalDictionary.add(new MarkovWord(this.totalDictionary.size()+1, this.keys.get(i), this.vals.get(i)));
                knownKeys.add(this.keys.get(i));
                this.numWords++;
            }
            else{
                int index = knownKeys.indexOf(this.keys.get(i));
                this.totalDictionary.get(index).addFollower(this.vals.get(i));
            }
        }
    }

    private MarkovWord getRandWord(){
        return this.totalDictionary.get((int)(Math.random() * numWords));
    }

    private MarkovWord findWordInList(String word){
        for(int i = 0; i<numWords; i++){
            if(this.totalDictionary.get(i).getWord().equals(word)){
                return totalDictionary.get(i);
            }
        }
        return null;
    }

    public String outputString(int desiredWords) throws FileNotFoundException{
        this.train();
        this.dictionaryCreation();
        MarkovWord current = this.getRandWord();
        String follower;
        StringBuilder finalOutput = new StringBuilder();
        for (int i = 0; i < desiredWords; i++){
            finalOutput.append(current.getWord());
            finalOutput.append(" ");
            follower = current.getRandFollower();
            current = this.findWordInList(follower);
        }
        return finalOutput.toString();
    }
    public int getNumWords(){
        return numWords;
    }
}
