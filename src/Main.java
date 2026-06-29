import compression.HuffmanCompressor;
import io.BitInputStream;
import io.HuffHeaderReader;
import model.Header;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.compress("input.txt","output.huff");

        DataInputStream in = new DataInputStream(new FileInputStream("output.huff"));

        HuffHeaderReader reader = new HuffHeaderReader(in);
        Header header = reader.readHeader();

        System.out.println(header.getFrequencyMap());
        System.out.println(header.getValidBitsInLastByte());

        in.close();
    }
}