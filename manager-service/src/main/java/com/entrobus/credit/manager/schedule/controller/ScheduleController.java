package com.entrobus.credit.manager.schedule.controller;

import com.entrobus.credit.common.annotation.RecordLog;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.client.ScheduleClient;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.vo.schedule.QuartzJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController extends ManagerBaseController{
    @Autowired
    private ScheduleClient scheduleClient;


    /**
     * 添加任务
     * @param vo
     * @return
     */
    @PostMapping("/job")
    @RecordLog(desc = "添加任务")
    public WebResult addJob( QuartzJobVo vo) {
        return scheduleClient.addJob(vo);
    }

    /**
     * 编辑任务
     * 暂时仅支持编辑任务 cron
     * @param vo
     * @return
     */
    @PutMapping("/job/{jobName}")
    @RecordLog(desc = "编辑任务",relId = "jobName")
    public WebResult editJob(@PathVariable String jobName, QuartzJobVo vo) {
        return scheduleClient.editJob(jobName,vo);
    }

    /**
     * 任务列表
     * @return
     */
    @GetMapping("/job")
    public WebResult jobList( String jobName, String groupName, Integer offset, Integer limit){
        return scheduleClient.jobList(jobName,groupName,offset,limit);
    }
    /**
     * 任务组名称
     * @return
     */
    @GetMapping("/groupName")
    public  List<Map<String,String>> groupNames()  {
        List<String> strings = scheduleClient.groupNames();
        if (strings != null)
            return strings.stream().map(this::toGroupMap).collect(Collectors.toList());
        return new ArrayList<>();
    }

    private Map<String, String> toGroupMap(String s) {
        Map<String,String> map = new HashMap<>();
        map.put("groupName",s);
        return map;
    }

    /**
     * 删除任务
     * @param jobName
     * @return
     */
    @DeleteMapping("/job/{jobName}")
    @RecordLog(desc = "删除任务",relId = "jobName")
    public WebResult removeJob(@PathVariable String jobName){
        return scheduleClient.removeJob(jobName);
    }

    /**
     * 暂停任务
     * @param jobName
     * @param groupName
     * @return
     */
    @PostMapping("/pauseJob")
    @RecordLog(desc = "暂停任务",relId = "jobName")
    public WebResult pauseJob(@RequestParam String jobName,@RequestParam(required = false) String groupName) {
        return scheduleClient.pauseJob(jobName,groupName);
    }

    /**
     * 恢复任务
     * @param jobName
     * @param groupName
     * @return
     */
    @PostMapping("/resumeJob")
    @RecordLog(desc = "恢复任务",relId = "jobName")
    public WebResult resumeJob(@RequestParam String jobName,@RequestParam(required = false) String groupName){
        return scheduleClient.resumeJob(jobName,groupName);
    }
    /**
     * 触发（执行）任务
     * @param jobName
     * @param groupName
     * @return
     */
    @PostMapping("/triggerJob")
    @RecordLog(desc = "触发（执行）任务",relId = "jobName")
    public WebResult triggerJob(@RequestParam String jobName,@RequestParam(required = false) String groupName){
        return scheduleClient.triggerJob(jobName,groupName);
    }



}
