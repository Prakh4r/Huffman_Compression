package compression;

import io.BitOutputStream;
import io.HuffHeaderReader;
import io.HuffHeaderWriter;
import model.Header;
import util.FileManager;
import util.FrequencyCounter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanCompressor {
    public void compress(String inputPath, String outputPath) throws IOException{
        FileManager fileManager = new FileManager();
        String text = fileManager.readFile(inputPath);

        FrequencyCounter counter = new FrequencyCounter();
        Map<Character,Integer> frequencyMap = counter.count(text);

        HuffmanTree tree = new HuffmanTree(frequencyMap);
        tree.buildTree();
        Map<Character,String> codes = tree.generateCodes();

        HuffmanEncoder encoder = new HuffmanEncoder();
        String encodedText = encoder.encode(text, codes);

        FileOutputStream fileOut = new FileOutputStream(outputPath);
        DataOutputStream dataOut = new DataOutputStream(fileOut);

        HuffHeaderWriter headerWriter = new HuffHeaderWriter(dataOut);
        int validBits = encodedText.length() % 8;
        if (validBits == 0) {
            validBits = 8;
        }
        headerWriter.writeHeader(frequencyMap, validBits);


        BitOutputStream bitOut = new BitOutputStream(dataOut);
        for(char ch : encodedText.toCharArray()){
            bitOut.writeBit(ch - '0');
        }
        bitOut.close();
        System.out.println("Input Size  : "
                + Files.size(Path.of(inputPath)) + " bytes");

        System.out.println("Output Size : "
                + Files.size(Path.of(outputPath)) + " bytes");

    }
}
