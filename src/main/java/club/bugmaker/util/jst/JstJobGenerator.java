package club.bugmaker.util.jst;

import cn.hutool.core.date.CalendarUtil;

import java.util.*;

/**
 * @ClassName: JstJobGenerator
 * @Description: 聚水潭任务生成器
 * @Author: Bruce
 * @Date: 2022/6/7 15:15
 * @Version: V1
 **/
public class JstJobGenerator {

    /**
     * 开始时间日历，默认：当前时间
     */
    private Calendar beginCalendar;

    /**
     * 结束时间日历，默认：当前时间
     */
    private Calendar endCalendar;

    /**
     * 每个单元的时间间隔，默认： 7 天
     */
    private Integer during;

    /**
     * 业务Id列表，默认：null
     */
    private List<String> bizIdList;

    public JstJobGenerator() {
        this.beginCalendar = Calendar.getInstance();
        this.endCalendar = Calendar.getInstance();
        this.during = 7;
    }

    public JstJobGenerator(Calendar beginCalendar, Calendar endCalendar) {
        this.beginCalendar = beginCalendar;
        this.endCalendar = endCalendar;
        this.during = 7;
    }

    public JstJobGenerator(Calendar beginCalendar, Calendar endCalendar, Integer during) {
        this.beginCalendar = beginCalendar;
        this.endCalendar = endCalendar;
        this.during = during;
    }

    public JstJobGenerator(Calendar beginCalendar, Calendar endCalendar, Integer during, List<String> bizIdList) {
        this.beginCalendar = beginCalendar;
        this.endCalendar = endCalendar;
        this.during = during;
        this.bizIdList = bizIdList;
    }

    /**
     * 滚动日历
     */
    private Calendar dynamicCalendar;

    public Queue<JstQueryUnit> genJobQ() {

        // 按照 level 值越大优先级越高来排序
        Queue<JstQueryUnit> jobQ = new PriorityQueue<JstQueryUnit>(Comparator.comparing(JstQueryUnit::getLevel).reversed());

        if (bizIdList == null || bizIdList.size() == 0) {
            dynamicCalendar = beginCalendar;
            genBizJob(null, jobQ);
            return jobQ;
        }

        for (String bizId : bizIdList) {
            dynamicCalendar = beginCalendar;
            genBizJob(bizId, jobQ);
        }
        return jobQ;
    }

    private void genBizJob(String bizId, Queue<JstQueryUnit> jobQ) {

        Date beginDate = CalendarUtil.beginOfDay(dynamicCalendar).getTime();

        dynamicCalendar.add(Calendar.DATE, during);
        Date endDate = CalendarUtil.beginOfDay(dynamicCalendar).getTime();

        jobQ.add(new JstQueryUnit(beginDate, endDate, bizId));

        if (dynamicCalendar.compareTo(endCalendar) <= 0) {
            genBizJob(bizId, jobQ);
        }
    }
}