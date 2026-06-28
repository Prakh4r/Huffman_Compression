import compression.HuffmanCompressor;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.compress("input.txt","output.huff");

    }
}