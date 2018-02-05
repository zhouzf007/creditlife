package com.entrobus.credit.manager.sys.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统角色Controller
 */
@RestController
@RequestMapping("/data/center")
public class DataCenterController extends ManagerBaseController {



    @RequestMapping("/h5DayActive")
    public WebResult h5DayActive(String startTime, String endTime, Integer listType) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime+" 23:59:59");
        //根据统计列表类型计算X轴数据列表
        List<String> xAxisData = new ArrayList<>();
        //日活列表数据
        List<Long> seriesData = new ArrayList<>();
        //累积列表
        List<Long> areaData = new ArrayList<>();
        //表格列表
        List<Map<String,Object>> tabList = new ArrayList<>();
        if(listType == Constants.DATA_CENTER_LIST_TYPE.HOUR){

        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.DAY){
            int dateCount = (int) ((endDate.getTime()-startDate.getTime())/(24*60*60*1000));
            Long count = 0L;
            Calendar calendar=Calendar.getInstance();
            for (int i = 0; i <= dateCount; i++) {
                Long dayActivity = i<3 ? (i+1)*100L : (i>6 ? i*100 : i*100-200);
                xAxisData.add(new SimpleDateFormat("MM-dd").format(startDate));
                seriesData.add(dayActivity);
                count += dayActivity;
                areaData.add(count);
                Map<String,Object> map = new HashMap<>();
                map.put("xAxisData",new SimpleDateFormat("MM-dd").format(startDate));
                map.put("seriesData",dayActivity);
                tabList.add(map);
                calendar.setTime(startDate);
                calendar.add(Calendar.DATE, 1); //加一天
                startDate = calendar.getTime();
            }

        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.WEEK){

        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.MONTH){

        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.ROLLUP){

        }
        return WebResult.ok().put("xAxisData",xAxisData).put("seriesData",seriesData).put("areaData",areaData).put("tabList",tabList);
    }

}
