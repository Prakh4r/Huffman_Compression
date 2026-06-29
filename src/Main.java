import compression.HuffmanCompressor;
import compression.HuffmanDecompressor;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.compress("input.txt","output.huff");
        System.out.println();
        HuffmanDecompressor decompressor = new HuffmanDecompressor();
        decompressor.decompress("output.huff", "decoded.txt");
    }
}