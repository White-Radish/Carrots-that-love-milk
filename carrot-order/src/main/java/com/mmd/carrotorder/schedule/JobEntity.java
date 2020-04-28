package com.mmd.carrotorder.schedule;

import lombok.Data;

/**
 * <p>
 * JOB_ENTITY
 * </p>
 *
 * @author hukai
 * @since 2020-04-20
 */
@Data
public class JobEntity {

    /**
     * job名称
     */
    private String name;

    /**
     * job组名
     */
    private String groupName;

    /**
     * 执行的cron
     */
    private String cron;

    /**
     * job描述
     */
    private String description;

    /**
     * 需要执行的job_class
     */
    private String classPath;

    /**
     * 是否开启任务，1开启，0关闭
     */
    private String workStatus;

    

}
