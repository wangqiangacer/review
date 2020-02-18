package com.jacken.algorithm.day01;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] nums =new int[]{2,7,11,15};
        int  target =9;
        int[] objects = twoSum(nums, target);
        for (int object : objects) {
            System.out.println(object);
        }
    }


    public static int[] twoSum(int[] nums, int target) {

        int[] arr=new int[2];

        if(nums != null){
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length-i; j++) {
                    if(nums[j]==target-nums[i]){
                        arr[0]=j;
                        arr[1]=i;
                    }
                }
            }
        }
        return  arr;

    }
}
