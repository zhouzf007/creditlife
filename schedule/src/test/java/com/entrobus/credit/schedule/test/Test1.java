package com.entrobus.credit.schedule.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    @Test
    public void testClass(){
        System.out.println(Test1.class.getName());
        System.out.println(Test1.class.getCanonicalName());
        System.out.println(Test1.class.getSimpleName());
        System.out.println(Test1.class.getTypeName());
    }
    @Test
    public void testStream(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        List<Integer> list2 = list.stream().skip(900).limit(10).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }
}
