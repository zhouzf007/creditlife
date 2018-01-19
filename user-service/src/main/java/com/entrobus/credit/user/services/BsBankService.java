package com.entrobus.credit.user.services;

import com.entrobus.credit.common.bean.WebResult;

import java.util.Map;

/**
 * Created by mozl on 2018/1/18.
 */
public interface BsBankService {

    WebResult verify(Map<String, String> map);

}
