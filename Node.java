class Node {
    Player player;
    Node next;

    public Node(Player player) {
        this.player = player;
        this.next = null;
    }
}

class PlayerLinkedList {
    private Node head;

    public PlayerLinkedList() {
        this.head = null;
    }

    public PlayerLinkedList(Player[] players){
        this.head=null;
        for(Player p:players){
            this.append(p);
        }
    }

    // Add a new node to the end of the linked list
    public void append(Player player) {
        Node newNode = new Node(player);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Traverse the linked list using the next() method
    public void traverse() {
        Node current = head;
        while (current != null) {
            current = current.next;
        }
        System.out.println();
    }
}

