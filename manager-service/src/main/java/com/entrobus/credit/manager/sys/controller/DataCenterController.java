package com.entrobus.credit.manager.sys.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import org.joda.time.DateTime;
import org.joda.time.Months;
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
        //合计
        Long dataCount = 0L;

        if(listType == Constants.DATA_CENTER_LIST_TYPE.ROLLUP){
            dataCount = 10000000L;
        }else{
            int timeCount = getTimeCount(startDate,endDate,listType);
            Long count = 0L;
            Calendar calendar=Calendar.getInstance();
            for (int i = 0; i <= timeCount; i++) {
                Long dayActive = i+10L;
                xAxisData.add(getXAxisData(startDate,listType));
                seriesData.add(dayActive);
                count += dayActive;
                areaData.add(count);
                Map<String,Object> map = new HashMap<>();
                map.put("xAxisData",getXAxisData(startDate,listType));
                map.put("seriesData",dayActive);
                tabList.add(map);
                calendar.setTime(startDate);
                calendar.add(getField(listType), 1);
                startDate = calendar.getTime();
            }
        }
        return WebResult.ok()
                .put("xAxisData",xAxisData)
                .put("seriesData",seriesData)
                .put("areaData",areaData)
                .put("tabList",tabList)
                .put("dataCount",dataCount);
    }


    /**
     * 时间段数
     * @param startDate
     * @param endDate
     * @param listType
     * @return
     */
    private int getTimeCount(Date startDate,Date endDate,Integer listType){
        if(listType == Constants.DATA_CENTER_LIST_TYPE.HOUR){
            return  (int) ((endDate.getTime()-startDate.getTime())/(60*60*1000));
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.DAY){
            return (int) ((endDate.getTime()-startDate.getTime())/(24*60*60*1000));
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.WEEK){
            return (int) ((endDate.getTime()-startDate.getTime())/(7*24*60*60*1000));
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.MONTH){
            return Months.monthsBetween(new DateTime(startDate.getTime()),new DateTime(endDate.getTime())).getMonths();
        }
        return 0;
    }

    /**
     * 格式化x轴数据
     * @param startDate
     * @param listType
     * @return
     */
    private String getXAxisData(Date startDate,Integer listType){
        if(listType == Constants.DATA_CENTER_LIST_TYPE.HOUR){
            return  new SimpleDateFormat("MM-dd HH:mm").format(startDate);
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.DAY){
            return  new SimpleDateFormat("MM-dd").format(startDate);
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.WEEK){
            return new SimpleDateFormat("MM-dd").format(startDate)+"当周";
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.MONTH){
            return new SimpleDateFormat("MM").format(startDate)+"月";
        }
        return "";
    }


    /**
     * 时间递增类型
     * @param listType
     * @return
     */
    private int getField(Integer listType){
        if(listType == Constants.DATA_CENTER_LIST_TYPE.HOUR){
            return Calendar.HOUR;
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.DAY){
            return Calendar.DATE;
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.WEEK){
            return Calendar.WEEK_OF_MONTH;
        }
        if(listType == Constants.DATA_CENTER_LIST_TYPE.MONTH){
            return Calendar.MONTH;
        }
        return 0;
    }


}
