package compression;

import io.BitInputStream;
import io.HuffHeaderReader;
import model.Header;
import model.Node;
import util.FileManager;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanDecompressor {
    public void decompress(String inputPath, String outputPath) throws IOException{
        try(
            FileInputStream fileIn = new FileInputStream(inputPath);
            DataInputStream dataIn = new DataInputStream(fileIn);
        ) {

            HuffHeaderReader reader = new HuffHeaderReader(dataIn);
            Header header = reader.readHeader();

            HuffmanTree huffmanTree = new HuffmanTree(header.getFrequencyMap());
            Node root = huffmanTree.buildTree();

            BitInputStream bitIn = new BitInputStream(dataIn);


            int headerSize = 4 + 4 + header.getFrequencyMap().size() * 6 + 1;
            long fileSize = Files.size(Path.of(inputPath));

            int compressedBytes = (int) (fileSize - headerSize);
            int totalValidBits = (compressedBytes - 1) * 8 + header.getValidBitsInLastByte();

            HuffmanDecoder decoder = new HuffmanDecoder();
            String decodedText = decoder.decode(bitIn, root, totalValidBits);

            FileManager fileManager = new FileManager();
            fileManager.writeFile(outputPath, decodedText);

            System.out.println("Input Size  : "
                    + Files.size(Path.of(inputPath)) + " bytes");

            System.out.println("Output Size : "
                    + Files.size(Path.of(outputPath)) + " bytes");
        }
    }
}
