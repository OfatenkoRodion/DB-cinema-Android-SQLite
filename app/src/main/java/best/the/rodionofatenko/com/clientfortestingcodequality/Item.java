package best.the.rodionofatenko.com.clientfortestingcodequality;

public class Item {
    public String text="";

    public Item(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Item() {
    }

    public void addText(String text){
        this.text+=text;

    }

    public void setText(String text) {
        this.text = text;
    }
}
