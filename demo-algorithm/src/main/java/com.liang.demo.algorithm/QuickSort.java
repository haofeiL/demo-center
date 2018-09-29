package com.liang.demo.algorithm;

/**
 * 快速排序
 * int[] arr = {5, 2, 8, 9, 2, 3, 4, 9};
 *
 * @description:
 * @author: haofeiL
 * @createDate: 2018/9/12 14:38
 * @version: v1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 9, 2, 3, 4, 9};
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


    public static void sort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }

        int key = arr[start];

        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && key < arr[j]) {
                j--;
            }

            while (i < j && key >= arr[i]) {
                i++;
            }

            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }

        int tmp = arr[i];
        arr[i] = key;
        arr[start] = tmp;

        sort(arr, start, i - 1);
        sort(arr, i + 1, end);
    }
}
