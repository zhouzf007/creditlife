package com.entrobus.credit.common.util;

import java.util.List;
import java.util.function.ToIntFunction;

/**
 * lambda工具类，使用java8 新特性
 * Created by laotou on 2017/7/21.
 */
public class LambdaUtil {
    /**
     * 对集合进行分批次批量操作,用于避免批量处理时由于list过大导致的异常
     * @param list 要处理的集合
     * @param batchtSize 触发分批次的list最大条数，即每批次最大处理的条数，
     *                   当list.size()大于batchtSize时分多次执行doFunction.applyAsInt()
     *                   当batchtSize<= 0 时 batchtSize取默认值3000
     * @param doFunction 要执行的操作，常见的如 sql批量插入数据，使用java8定义的函数式接口ToIntFunction
     *                   其中doFunction.applyAsInt(list)的返回值应该取该批次执行成功的行数
     * @param <T>
     * @return 执行成功的总行数，即doFunction.applyAsInt(list)返回值的总和
     */
    public static<T> int batch(List<T> list,int batchtSize,ToIntFunction<List<T>> doFunction){
        if (batchtSize<=0) batchtSize = 3000;
        int size = list.size();//总行数
        if (size <= batchtSize){
            return doFunction.applyAsInt(list);
        }
        //分批次，每次最多执行batchtSize条，解决单次执行语句过长报错
        int row = 0;
        for (int i = 0; i < size; i += batchtSize) {
            int e = i + batchtSize;
            List<T> list2 = list.subList(i, size > e ? e : size);
            row += doFunction.applyAsInt(list2);
        }
        return row;
    }
}
