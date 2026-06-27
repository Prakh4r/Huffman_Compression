import compression.HuffmanEncoder;
import compression.HuffmanTree;
import model.Node;
import util.FrequencyCounter;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        String text = "ABCA";
        FrequencyCounter counter = new FrequencyCounter();
        Map<Character,Integer> frequencyMap = counter.count(text);
        System.out.println("Frequency of each character in text:- ");
        System.out.println(frequencyMap);
        System.out.println("------------------------------------");

        HuffmanTree tree = new HuffmanTree(frequencyMap);
        tree.buildTree();


        Map<Character, String> codes = tree.generateCodes();
        System.out.println("Codes for each character in text:- ");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("'" + entry.getKey() + "' -> " + entry.getValue());
        }
        System.out.println("------------------------------------");

        HuffmanEncoder encoder = new HuffmanEncoder();
        String encodedText = encoder.encode(text, codes);
        System.out.println("Encoded String : " + encodedText);
        System.out.println("------------------------------------");
    }
}