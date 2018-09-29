package com.liang.demo.algorithm;

/**
 * 6 5 4 3 2 1 -> 1 2 3 4 5 6 
 * @description:
 * @author: haofeiL
 * @createDate: 2018/8/6 17:21
 * @version: v1.0
 */
public class AliTest2 {
    public static void main(String[] args) {
        Node node6 = new Node('6', null);
        Node node5 = new Node('5', node6);
        Node node4 = new Node('4', node5);
        Node node3 = new Node('3', node4);
        Node node2 = new Node('2', node3);
        Node node1 = new Node('1', node2);

        Node head = reverse2(node1);
        Node current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    public static Node reverse2(Node current) {
        if (current == null || current.next == null) {
            return current;
        }

        Node head = null;
        Node previous = null;
        while (current != null) {
            Node next = current.next;
            if (null == next) {
                head = current;
            }
            current.next = previous;
            previous = current;

            current = next;

        }
        return head;
    }

}


