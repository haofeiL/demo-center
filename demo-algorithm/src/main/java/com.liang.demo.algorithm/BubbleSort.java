package com.liang.demo.algorithm;

/**
 * 冒泡排序
 * int[] arr = {5, 2, 8, 9, 2, 3, 4, 9};
 * 一、
 *
 * @description:
 * @author: haofeiL
 * @createDate: 2018/9/12 15:19
 * @version: v1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 9, 2, 3, 4, 9};
        sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
