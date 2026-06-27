import compression.HuffmanEncoder;
import compression.HuffmanTree;
import model.Node;
import util.FileManager;
import util.FrequencyCounter;

import java.io.IOException;
import java.util.Map;

public class Main{
    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        //actually means D:\HuffmanCompression\HuffmanCompression\input.txt
        String text = fileManager.readFile("input.txt");
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
            char ch = entry.getKey();

            String display;

            switch (ch) {
                case '\n' -> display = "\\n";
                case '\r' -> display = "\\r";
                case '\t' -> display = "\\t";
                case ' '  -> display = "space";
                default   -> display = Character.toString(ch);
            }

            System.out.println("'" + display + "' -> " + entry.getValue());
        }
        System.out.println("------------------------------------");

        HuffmanEncoder encoder = new HuffmanEncoder();
        String encodedText = encoder.encode(text, codes);
        System.out.println("Encoded String : " + encodedText);
        System.out.println("------------------------------------");
    }
}