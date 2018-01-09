package com.entrobus.credit.vo.schedule;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class QuartzJobVo implements Serializable {
    @NotBlank(message = "jobName 必填")
    private String jobName;
    private String groupName;
    @NotBlank(message = "cron 必填")
//    @Pattern(regexp = "^[\\d/\\]$")
    private String cron;

    private String param;
    @NotBlank(message = "description 必填")
    private String description;
    private String stateName;
    //任务状态
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
