package com.sp.mytest;

/**
 * @author zgq
 * @version 1.0
 * @describe --步骤式进度条bean
 * @date 2019/4/10
 */
public class MyStepProgressBarBean {

    private String content;
    private String tiem;

    public MyStepProgressBarBean(String content, String tiem) {
        this.content = content;
        this.tiem = tiem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTiem() {
        return tiem;
    }

    public void setTiem(String tiem) {
        this.tiem = tiem;
    }
}
