package org.example;

public class RedBlackTree<T extends Comparable<T>> {

    private enum Color { RED, BLACK }

    private class Node {
        T key;
        Color color;
        Node left, right, parent;

        Node(T key) {
            this.key = key;
            this.color = Color.RED;
        }
    }

    private Node root;

    private boolean isRed(Node n) {
        return n != null && n.color == Color.RED;
    }

    private boolean isBlack(Node n) {
        return n == null || n.color == Color.BLACK;
    }

    private void setColor(Node n, Color c) {
        if (n != null) n.color = c;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;

        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;

        y.right = x;
        x.parent = y;
    }

    public void insert(T key) {
        if (contains(key)) return;

        Node node = new Node(key);
        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            current = key.compareTo(current.key) < 0 ? current.left : current.right;
        }

        node.parent = parent;
        if (parent == null) root = node;
        else if (key.compareTo(parent.key) < 0) parent.left = node;
        else parent.right = node;

        fixInsert(node);
    }

    private void fixInsert(Node x) {
        while (x != root && isRed(x.parent)) {
            Node grandparent = x.parent.parent;

            if (x.parent == grandparent.left) {
                Node uncle = grandparent.right;

                if (isRed(uncle)) {
                    setColor(x.parent, Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(grandparent, Color.RED);
                    x = grandparent;
                } else {
                    if (x == x.parent.right) {
                        x = x.parent;
                        rotateLeft(x);
                    }
                    setColor(x.parent, Color.BLACK);
                    setColor(grandparent, Color.RED);
                    rotateRight(grandparent);
                }
            } else {
                Node uncle = grandparent.left;

                if (isRed(uncle)) {
                    setColor(x.parent, Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(grandparent, Color.RED);
                    x = grandparent;
                } else {
                    if (x == x.parent.left) {
                        x = x.parent;
                        rotateRight(x);
                    }
                    setColor(x.parent, Color.BLACK);
                    setColor(grandparent, Color.RED);
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    public boolean contains(T key) {
        Node n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) n = n.left;
            else if (cmp > 0) n = n.right;
            else return true;
        }
        return false;
    }

    private Node searchNode(T key) {
        Node n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) n = n.left;
            else if (cmp > 0) n = n.right;
            else return n;
        }
        return null;
    }

    public boolean delete(T key) {
        Node node = searchNode(key);
        if (node == null) return false;
        deleteNode(node);
        return true;
    }

    private void deleteNode(Node z) {
        Node y = z;
        Node x;
        Node xParent;
        Color yOriginalColor = y.color;

        if (z.left == null) {
            x = z.right;
            xParent = z.parent;
            transplant(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            xParent = z.parent;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;

            if (y.parent == z) {
                xParent = y;
                if (x != null) x.parent = y;
            } else {
                xParent = y.parent;
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == Color.BLACK)
            fixDelete(x, xParent);
    }

    private void transplant(Node a, Node b) {
        if (a.parent == null) root = b;
        else if (a == a.parent.left) a.parent.left = b;
        else a.parent.right = b;

        if (b != null) b.parent = a.parent;
    }

    private Node minimum(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    private void fixDelete(Node x, Node parent) {
        while (x != root && isBlack(x)) {
            if (parent == null) break;

            if (x == parent.left) {
                Node sib = parent.right;

                if (isRed(sib)) {
                    setColor(sib, Color.BLACK);
                    setColor(parent, Color.RED);
                    rotateLeft(parent);
                    sib = parent.right;
                }

                if (isBlack(sib.left) && isBlack(sib.right)) {
                    setColor(sib, Color.RED);
                    x = parent;
                    parent = x.parent;
                } else {
                    if (isBlack(sib.right)) {
                        setColor(sib.left, Color.BLACK);
                        setColor(sib, Color.RED);
                        rotateRight(sib);
                        sib = parent.right;
                    }
                    sib.color = parent.color;
                    parent.color = Color.BLACK;
                    setColor(sib.right, Color.BLACK);
                    rotateLeft(parent);
                    x = root;
                }
            } else {
                Node sib = parent.left;

                if (isRed(sib)) {
                    setColor(sib, Color.BLACK);
                    setColor(parent, Color.RED);
                    rotateRight(parent);
                    sib = parent.left;
                }

                if (isBlack(sib.left) && isBlack(sib.right)) {
                    setColor(sib, Color.RED);
                    x = parent;
                    parent = x.parent;
                } else {
                    if (isBlack(sib.left)) {
                        setColor(sib.right, Color.BLACK);
                        setColor(sib, Color.RED);
                        rotateLeft(sib);
                        sib = parent.left;
                    }
                    sib.color = parent.color;
                    parent.color = Color.BLACK;
                    setColor(sib.left, Color.BLACK);
                    rotateRight(parent);
                    x = root;
                }
            }
        }
        setColor(x, Color.BLACK);
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Дерево порожнє.");
            return;
        }
        printTree(root, "", true, "");
    }

    private void printTree(Node node, String prefix, boolean isTail, String branch) {
        if (node == null) return;

        String color = node.color == Color.RED ? "R" : "B";
        String connector = isTail ? "└──" : "├──";

        if (branch.isEmpty()) {
            System.out.println(connector + " " + node.key + "(" + color + ")");
        } else {
            System.out.println(prefix + connector + branch + "─ " + node.key + "(" + color + ")");
        }

        String newPrefix = prefix + (isTail ? "    " : "│   ");
        boolean hasLeft = node.left != null;
        boolean hasRight = node.right != null;

        if (hasLeft && hasRight) {
            printTree(node.left, newPrefix, false, "L");
            printTree(node.right, newPrefix, true, "R");
        } else if (hasLeft) {
            printTree(node.left, newPrefix, true, "L");
        } else if (hasRight) {
            printTree(node.right, newPrefix, true, "R");
        }
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node n) {
        if (n != null) {
            inorder(n.left);
            System.out.print(n.key + " ");
            inorder(n.right);
        }
    }

    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node n) {
        if (n != null) {
            System.out.print(n.key + " ");
            preorder(n.left);
            preorder(n.right);
        }
    }

    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(Node n) {
        if (n != null) {
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.key + " ");
        }
    }
}
