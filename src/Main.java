import compression.HuffmanCompressor;
import compression.HuffmanDecompressor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("=============================================");
            System.out.println("         HUFFMAN COMPRESSION TOOL");
            System.out.println("=============================================");
            System.out.println("1. Compress a File");
            System.out.println("2. Decompress a File");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1 :
                    compressFile(scanner);
                    break;

                case 2 :
                    decompressFile(scanner);
                    break;

                case 3 :
                    System.out.println("\nThanks for using this tool");
                    return;

                default:
                    System.out.println("\nInvalid choice!\n");
            }
        }
    }
    public static void compressFile(Scanner scanner) throws IOException{
        System.out.print("\nEnter input file path : ");
        String input = scanner.nextLine();

        System.out.print("Enter output file name (.huff) : ");
        String output = scanner.nextLine();

        long start = System.nanoTime();
        new HuffmanCompressor().compress(input, output);
        long end = System.nanoTime();

        long originalSize = Files.size(Path.of(input));
        long compressedSize = Files.size(Path.of(output));

        long difference = Math.abs(originalSize - compressedSize);
        double ratio = (1 - ((double) compressedSize / originalSize)) * 100;

        System.out.println("\n✓ Compression completed successfully!\n");

        System.out.println("--------------- Statistics ----------------");
        System.out.println("Input File        : " + input);
        System.out.println("Output File       : " + output);
        System.out.println("Original Size     : " + originalSize + " bytes");
        System.out.println("Compressed Size   : " + compressedSize + " bytes");

        if (compressedSize < originalSize) {
            System.out.println("Space Saved       : " + difference + " bytes");
            System.out.printf("Compression Ratio : %.2f%%%n", ratio);
        } else {
            System.out.println("File Expanded By  : " + difference + " bytes");
        }

        System.out.printf("Time Taken        : %.2f ms%n",
                (end - start) / 1_000_000.0);

        System.out.println("-------------------------------------------");
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    public static void decompressFile(Scanner scanner) throws IOException{
        System.out.print("\nEnter input file path : ");
        String input = scanner.nextLine();

        System.out.print("Enter output file name (.txt) : ");
        String output = scanner.nextLine();

        long start = System.nanoTime();

        new HuffmanDecompressor().decompress(input, output);

        long end = System.nanoTime();

        long compressedSize = Files.size(Path.of(input));
        long outputSize = Files.size(Path.of(output));

        System.out.println("\n✓ Decompression completed successfully!\n");

        System.out.println("--------------- Statistics ----------------");
        System.out.println("Input File        : " + input);
        System.out.println("Output File       : " + output);
        System.out.println("Compressed Size   : " + compressedSize + " bytes");
        System.out.println("Output Size       : " + outputSize + " bytes");
        System.out.printf("Time Taken        : %.2f ms%n",
                (end - start) / 1_000_000.0);
        System.out.println("-------------------------------------------");
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}