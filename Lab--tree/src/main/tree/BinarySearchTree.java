package tree;

import java.util.ArrayList;

import estrut.Tree;

public class BinarySearchTree implements Tree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRecursive(root, valor);
    }

    private boolean buscaElementoRecursive(Node current, int valor) {
        if (current == null) {
            return false;
        } 

        if (valor == current.value) {
            return true;
        } 

        return valor < current.value
          ? buscaElementoRecursive(current.left, valor)
          : buscaElementoRecursive(current.right, valor);
    }

    @Override
    public int minimo() {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public int maximo() {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElementoRecursive(root, valor);
    }

    private Node insereElementoRecursive(Node current, int valor) {
        if (current == null) {
            return new Node(valor);
        }

        if (valor < current.value) {
            current.left = insereElementoRecursive(current.left, valor);
        } else if (valor > current.value) {
            current.right = insereElementoRecursive(current.right, valor);
        } else {
            return current;
        }

        return current;
    }

    @Override
    public void remove(int valor) {
        root = removeRecursive(root, valor);
    }

    private Node removeRecursive(Node current, int valor) {
        if (current == null) {
            return null;
        }

        if (valor == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;

        } 
        if (valor < current.value) {
            current.left = removeRecursive(current.left, valor);
            return current;
        }

        current.right = removeRecursive(current.right, valor);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    @Override
    public int[] preOrdem() {
        ArrayList<Integer> res = new ArrayList<>();
        preOrdemHelper(root, res);
        return res.stream().mapToInt(i->i).toArray();
    }

    private void preOrdemHelper(Node node, ArrayList<Integer> res) {
        if (node != null) {
            res.add(node.value);
            preOrdemHelper(node.left, res);
            preOrdemHelper(node.right, res);
        }
    }

    @Override
    public int[] emOrdem() {
        ArrayList<Integer> res = new ArrayList<>();
        emOrdemHelper(root, res);
        return res.stream().mapToInt(i->i).toArray();
    }

    private void emOrdemHelper(Node node, ArrayList<Integer> res) {
        if (node != null) {
            emOrdemHelper(node.left, res);
            res.add(node.value);
            emOrdemHelper(node.right, res);
        }
    }

    @Override
    public int[] posOrdem() {
        ArrayList<Integer> res = new ArrayList<>();
        posOrdemHelper(root, res);
        return res.stream().mapToInt(i->i).toArray();
    }

    private void posOrdemHelper(Node node, ArrayList<Integer> res) {
        if (node != null) {
            posOrdemHelper(node.left, res);
            posOrdemHelper(node.right, res);
            res.add(node.value);
        }
    }
}
