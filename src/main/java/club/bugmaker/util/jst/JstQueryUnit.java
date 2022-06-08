package club.bugmaker.util.jst;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @ClassName: JstQueryUnit
 * @Description: Jst 查询单元
 * @Author: Bruce
 * @Date: 2022/6/7 15:11
 * @Version: V1
 **/
public class JstQueryUnit {

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 业务id
     */
    private String bizId;

    /**
     * 优先级别 默认0
     * @return
     */
    private Integer level = 0;

    public JstQueryUnit(Date startTime, Date endTime, String bizId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bizId = bizId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "JstQueryUnit{" +
                "startTime=" + DateUtil.format(startTime, "yyyyMMdd") +
                ", endTime=" + DateUtil.format(endTime, "yyyyMMdd") +
                ", bizId='" + bizId + '\'' +
                ", level=" + level +
                '}';
    }
}