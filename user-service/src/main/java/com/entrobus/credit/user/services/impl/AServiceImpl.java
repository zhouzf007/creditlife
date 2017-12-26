package com.entrobus.credit.user.services.impl;

import com.entrobus.credit.user.client.ServiceBClient;
import com.entrobus.credit.user.services.AService;
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
