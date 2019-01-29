package com.test.leetcode;

import java.util.*;

public class ArraySolution {
    public static void main(String[] args) {
//        int[] nums = {1,2,3,4,5,6,7};
//        int[] nums = { 3, 99};
//        int[] nums = {1,2,3,1};
//        int number = removeDuplicates(nums);
//        int[] nums = {2,2,1};
//        int singleNumber = singleNumber(nums);
//        System.out.println(singleNumber);
        //rotate(nums, 5);
        //boolean b = containsDuplicate(nums);
        //System.out.println(b);
        /*for (int num : nums) {
            System.out.print(num + " ");
        }*/

//        int[] num1 = {9,4,9,8,4};
//        int[] num2 = {4,9,5};
//        int[] intersect = intersect(num1, num2);
//        for (int i = 0; i < intersect.length; i++) {
//            System.out.print(intersect[i] + "  ");
//        }

//        int[] nums = {0};
//        int[] ints = plusOne(nums);
//        for (int anInt : ints) {
//            System.out.print(anInt + " ");
//        }
//        int[] nums = {0,1,0,3,12};
//        moveZeroes(nums);
//        for (int num : nums) {
//            System.out.println(num + " ");
//        }

//        int[] nums = {2, 7, 11, 15};
//        int[] ints = twoSum2(nums, 9);
//        for (int anInt : ints) {
//            System.out.print(anInt + " ");
//        }
        int[] prices = {};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        int result = 0;
        int temp = prices[0];
        for (int i = 1; i < prices.length;i++){
            if (temp < prices[i]){
                result += (prices[i] - temp);
            }
            temp = prices[i];
        }
        return result;
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int len = k;
        if (k > nums.length) {
            len = k % nums.length;
        }
        int index = 0;
        while (index < len) {
            int temp = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = temp;
            index++;
        }
    }

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                return false;
            }
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        if (nums.length < 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        int i = 0;
        while (i < nums.length) {
            if (i == nums.length - 1) {
                return nums[i];
            }
            if (nums[i] == nums[i + 1]) {
                i = i + 2;
            } else {
                result = nums[i];
                break;
            }
        }
        return result;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length < 0 || nums2.length < 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[list.size()];
        for (int k = 0; k < result.length; k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        if (digits.length < 0) {
            return new int[0];
        }
        int index = digits.length - 1;
        while (index >= 0) {
            if (digits[index] < 9) {
                digits[index] += 1;
                break;
            } else {
                digits[index] = 0;
                index--;
            }
        }
        if (index < 0) {
            int[] arr = new int[digits.length + 1];
            arr[0] = 1;
            for (int i = 1; i < digits.length; i++) {
                arr[i] = digits[i - 1];
            }
            return arr;
        }
        return digits;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if (nums.length < 0) {
            return;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                int index = i;
                while (index < nums.length - 1) {
                    int temp = nums[index];
                    nums[index] = nums[index + 1];
                    nums[index + 1] = temp;
                    index++;
                }
            }
        }
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();//key为nums数组的值,value为数组下标
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0;i < n / 2;i++){
            for(int j = i; j < n-1-i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }

}
