import java.io.FileNotFoundException;

public class MarkovChainDriver {
    public static void main(String[] args) throws FileNotFoundException {
        MarkovChain chain = new MarkovChain("MinionsScript");
        System.out.println("Training File: " + chain.getInputFile());
        System.out.println();
        System.out.println(chain.generateText(1000));
    }
}
