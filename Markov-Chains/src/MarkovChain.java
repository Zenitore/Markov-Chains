import java.io.FileNotFoundException;

public class MarkovChain {
    private MarkovDictionary dictionary;
    private String inputFile;

    public MarkovChain(String inputFile){
        this.inputFile = inputFile;
        this.dictionary = new MarkovDictionary(inputFile);
    }

    public String generateText(int desiredWords) throws FileNotFoundException {
        return this.dictionary.outputString(desiredWords);
    }

    public String getInputFile(){
        return inputFile;
    }
}
