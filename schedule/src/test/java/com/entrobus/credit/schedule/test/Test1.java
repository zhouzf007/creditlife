package com.entrobus.credit.schedule.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Test1 {
    @Test
    public void testClass(){

        class eee extends  Test1{

        }
        Test1 a = new eee();
        System.out.println(a.getClass().getName());
        System.out.println(a.getClass().getCanonicalName());
        System.out.println(a.getClass().getSimpleName());
        System.out.println(a.getClass().getTypeName());


        int [] b = new int[10];
        System.out.println(b.getClass().getName());
        System.out.println(b.getClass().getCanonicalName());
        System.out.println(b.getClass().getSimpleName());
        System.out.println(b.getClass().getTypeName());
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
    @Test
    public void testEquals(){
        int a = 0;
        Integer b = null;
        Integer c = Integer.valueOf(a);
        Integer d = Integer.valueOf(10);
        System.out.println(Objects.equals(b,a));
        System.out.println(Objects.equals(c,a));
        System.out.println(Objects.equals(c,b));
        System.out.println(Objects.equals(null,b));
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(b == null);
    }
}
