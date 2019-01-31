package com.test.leetcode;

import java.util.*;

public class StringSolution {
    public static void main(String[] args) {
//        char[] s = {'h','e','l','l','o'};
//        reverseString(s);
//        for (char c : s) {
//            System.out.print(c + " ");
//        }
//        int x = 1534236469;
//        int reverse = reverse(x);
//        System.out.println(reverse);
//        String s = "loveleetcode";
//        int i = firstUniqChar(s);
//        System.out.println(i);
//        String s = "A man, a plan, a canal: Panama";
//        boolean b = isPalindrome(s);
//        System.out.println(b);
        String haystack = "aaaaa", needle = "bba";
        int i = strStr(haystack, needle);
        System.out.println(i);
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * @param s
     */
    public static void reverseString(char[] s) {
        if (s.length < 0) {
            return;
        }
        String str = "";
        for (int i = s.length - 1; i >= 0; i--) {
            str = str + s[i];
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = str.charAt(i);
        }
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0;i < s.length();i ++){
            if (!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), 1);
            }else {
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
        }
        for (int i = 0;i<s.length();i++){
            if (map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        //也可以通过排序后的字符串比较
       if (s.length() != t.length()){
           return false;
       }
       int[] sArray = new int[26];
       int[] tArray = new int[26];
       for (int i = 0; i < s.length();i++){
           sArray[s.charAt(i) - 'a'] ++;
           tArray[t.charAt(i) - 'a'] ++;
       }
       for (int i = 0; i < sArray.length; i ++){
           if (sArray[i] != tArray[i]){
               return false;
           }
       }
       return true;
    }

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s.length() < 0 || s == null){
            return false;
        }
        s = s.toLowerCase();//转换为小写
        String target = "";
        for (int i = 0; i < s.length();i ++){
            if ('a' <= s.charAt(i) && s.charAt(i) <='z'){
                target +=s.charAt(i);
            }else if ('0' <= s.charAt(i) && s.charAt(i) <='9'){
                target +=s.charAt(i);
            }else {
                continue;
            }
        }
        //此时target只有字母和数字字符
        char[] temp = new char[target.length()];
        for (int i = target.length()-1,j = 0; i >= 0&&j < target.length();i --,j ++){
            temp[j] = target.charAt(i);
        }
        for (int i = 0; i < target.length(); i ++){
            if (temp[i] != target.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 实现一个 atoi 函数，使其能将字符串转换成整数。
     * @param str
     * @return
     */
    public static int myAtoi(String str) {

        return 0;
    }

    /**
     * 实现 strStr() 函数。
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int l1 = haystack.length();
        int l2 = needle.length();
        if (l2 > l1){
            return -1;
        }else if (l2 == 0){
            return 0;
        }
        for (int i = 0; i <= l1-l2; i ++){
            if (haystack.substring(i, i+l2).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}
