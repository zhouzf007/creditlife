package com.encredit.user.services.impl;

import com.encredit.user.client.ServiceBClient;
import com.encredit.user.services.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouzf on 2017/11/28.
 */
@Service
public class AServiceImpl implements AService{

    @Autowired
    private ServiceBClient serviceBClient;

    @Override
    public String printServiceA(String name) {
        return "===>name:" + name + "<br/>" + serviceBClient.printServiceB();
    }
}
