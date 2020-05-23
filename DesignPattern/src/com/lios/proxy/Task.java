package com.lios.proxy;

/**
 * @author liaiguang
 * @created 2020/5/23
 */
public class Task implements ITask {
    private String taskName;
    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void doTask() {
        System.out.println("正在执行" + this.taskName + "任务");
    }
}