import java.util.ArrayList;

public class MarkovWord {
    private int index;
    private String word;
    private ArrayList<String> followers;

    public MarkovWord(int index, String word, String follower){
        this.index = index;
        this.word = word;
        followers  = new ArrayList<String>();
        followers.add(follower);
    }

    public int getIndex() {
        return index;
    }

    public String getWord() {
        return word;
    }

    public void addFollower(String follower){
        followers.add(follower);
    }

    public ArrayList<String> getFollowers(){
        return followers;
    }

    public String getRandFollower(){
        return followers.get((int)(Math.random()*followers.size()));
    }
}

