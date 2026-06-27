import compression.HuffmanTree;
import model.Node;
import util.FrequencyCounter;
import java.util.Map;

public class Main{
    public static void main() {
        String text = "Hello\nWorld";
        FrequencyCounter counter = new FrequencyCounter();
        Map<Character,Integer> frequencyMap = counter.count(text);

        System.out.println(frequencyMap);

        HuffmanTree tree = new HuffmanTree(frequencyMap);
        tree.buildTree();


        Map<Character, String> codes = tree.generateCodes();
        for(Map.Entry<Character,String> entry : codes.entrySet()){
            System.out.println("'" + entry.getKey() + "' -> " + entry.getValue());
        }
    }
}