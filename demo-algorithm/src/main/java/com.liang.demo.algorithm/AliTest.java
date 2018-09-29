package com.liang.demo.algorithm;

/**
 * 1 2 3 4 5 6 -> 2 1 4 3 6 5 
 * @description:
 * @author: haofeiL
 * @createDate: 2018/8/6 15:50
 * @version: v1.0
 */
public class AliTest {

    public static void main(String[] args) throws Exception {
        Node node6 = new Node('6', null);
        Node node5 = new Node('5', node6);
        Node node4 = new Node('4', node5);
        Node node3 = new Node('3', node4);
        Node node2 = new Node('2', node3);
        Node node1 = new Node('1', node2);

        Node head = reverse(node1);
        Node current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }

    }

    /**
     * 反转函数
     *
     * @param current 发转前链表的头
     * @return 反转后链表的头
     * @throws Exception
     */
    public static Node reverse(Node current) throws Exception {
        if (current == null || current.next == null) {
            return current;
        }
        Node startHead = current.next;
        Node previous = null;
        while (current != null) {
            Node next = current.next;//下一个节点
            Node next2 = next.next;//下下个节点

            /**
             * ④当上个节点不为空时，修改它的next指针，指向当前节点的下一个节点
             */
            if (previous != null) {
                previous.next = next;
            }
            previous = current;

            /**
             * ①将下一个节点next指针指向当前节点
             */
            next.next = current;
            /**
             * ②将当前节点next指针指向下下个节点
             */
            current.next = next2;

            /**
             * ③直接跳到下下个节点，while继续执行
             */
            current = next2;
        }

        return startHead;
    }
}

/**
 * 节点对象：单向链表
 */
class Node {
    char value;
    Node next;

    public Node(char value, Node next) {
        this.value = value;
        this.next = next;
    }
}
