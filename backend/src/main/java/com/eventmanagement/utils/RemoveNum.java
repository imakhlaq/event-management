package com.eventmanagement.utils;

import java.util.*;

public class RemoveNum {

    public static void removeNum(List<Integer> arr) {

//        inputArrayList= [1,3,2,4,3,1,2]
//        outputArrayList = [4,3,2,1]
/*        var unique = new HashSet<>(arr);
        var list = new ArrayList<>(unique);
        list.sort((a, b) -> b - a);*/

        var ans = new TreeSet<Integer>((a, b) -> b - a);
        ans.addAll(arr);

        System.out.println(ans);
    }

    public static void main(String[] args) {
        removeNum(Arrays.asList(1, 3, 2, 4, 3, 1, 2));
    }
}