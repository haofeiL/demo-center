import java.util.Arrays;

/**
 * @description:
 * @author: haofeiL
 * @createDate: 2018/9/16 11:14
 * @version: v1.0
 */
public class Test {
    public static void main(String[] args) {
        String s1 = "feafbrgragre";
        String s2 = "febrgragrefa";

//        System.out.println(checkSam(s1, s2));
//        System.out.println(checkSam1(s1, s2));
        /*ListNode l5 = new ListNode(1);
        ListNode l4 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l1 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        System.out.println(isPalindrome(l1));*/

        String ss = "Mr John Smith";
        System.out.println(replaceSpace(ss,ss.length()));
    }

    public boolean checkDifferent(String iniString) {
        // write c
        char[] charArray = iniString.toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            for (int j = 0; j < charArray.length - 1; j++) {
                if (i != j && charArray[i] == charArray[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkSam(String stringA, String stringB) {
        // write code here
        char[] char1 = stringA.toCharArray();
        char[] char2 = stringB.toCharArray();

        int[] a1 = new int[256];
        int[] a2 = new int[256];
        for (int i = 0; i < char1.length; i++) {
            a1[char1[i]]++;
        }
        for (int i = 0; i < char2.length; i++) {
            a2[char2[i]]++;
        }
        for (int i = 0; i < 256; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSam1(String stringA, String stringB) {
        // write code here
        int lenA = stringA.length();
        int lenB = stringB.length();
        if (lenA != lenB) {
            return false;
        } else {
            int[] A = new int[256];
            int[] B = new int[256];
            for (int i = 0; i < stringA.length(); i++) {
                A[stringA.charAt(i)]++;
            }
            for (int i = 0; i < stringA.length(); i++) {
                B[stringB.charAt(i)]++;
            }
            for (int i = 0; i < 256; i++) {
                if (A[i] != B[i]) {
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isPalindrome(ListNode pHead) {
        // write code here
        ListNode previous = null;

        ListNode cur = pHead;
        int count = 0;
        while (cur != null) {
            ListNode node = new ListNode(cur.val);
            node.next = previous;
            previous = node;

            cur = cur.next;
            count++;
        }

        int i = 0;
        int j = count;
        ListNode sHead = previous;
        while (i < j && pHead != null && sHead != null) {
            if (pHead.val != sHead.val) {
                return false;
            }
            pHead = pHead.next;
            sHead = sHead.next;
            i++;
            j--;
        }
        return true;
    }

    public static String replaceSpace(String iniString, int length) {
        // write code here
        int count = 0;
        for(int i=0;i < length; i++){
            if(iniString.charAt(i) == ' '){
                count++;
            }
        }

        char[] array = new char[length + 2 * count];
        for(int i=length-1; i >= 0; i --){
            if(iniString.charAt(i) == ' '){
                count--;
                array[i+2*count] = '%';
                array[i+2*count+1] = '2';
                array[i+2*count+2] = '0';
            }else{
                array[i + 2*count] = iniString.charAt(i);
            }
        }
        return new String(array);
    }

    public boolean isBalance(TreeNode root) {
        // write code here
        if(getLength(root.left) - getLength(root.right) > 1){
            return false;
        }else{
            return isBalance(root.left) && isBalance(root.right);
        }
    }
    public int getLength(TreeNode node){
        if(node == null){
            return 0;
        }
        int leftLen = getLength(node.left) + 1;
        int rightLen = getLength(node.right) + 1;
        return Math.max(leftLen,rightLen);
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
