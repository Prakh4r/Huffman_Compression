package compression;

import model.Node;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private final Map<Character,Integer> frequencyMap;
    private Node root;

    public HuffmanTree(Map<Character,Integer> frequencyMap){
        this.frequencyMap = frequencyMap;
    }
    public Node buildTree(){
        if (frequencyMap.isEmpty()) {
            return null;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        while(pq.size() > 1){
            Node leftChild = pq.poll();
            Node rightChild = pq.poll();
            pq.add(new Node(leftChild.getFrequency() + rightChild.getFrequency(), leftChild, rightChild));
        }
        root = pq.poll();
        return root;
    }
    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node node) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            System.out.println(node.getCharacter() + " : " + node.getFrequency());
        } else {
            System.out.println("* : " + node.getFrequency());
        }
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }
}
