package club.bugmaker.util;

import club.bugmaker.util.jst.JstJobGenerator;
import club.bugmaker.util.jst.JstQueryUnit;

import java.util.Calendar;
import java.util.Queue;

public class JstJobGeneratorTest {

    public static void main(String[] args) {

        Calendar begin = Calendar.getInstance();
        begin.set(2022, 0, 1);

        Calendar end = Calendar.getInstance();

        JstJobGenerator generator = new JstJobGenerator(begin, end);

        Queue<JstQueryUnit> queryUnitQueue = generator.genJobQ();

        int num = 0;

        while (!queryUnitQueue.isEmpty()) {

            JstQueryUnit queryUnit = queryUnitQueue.poll();

            System.out.println("开始处理： " + queryUnit);

            if (num % 5 == 0) {
                System.out.println("模拟出现异常");

                queryUnit.setLevel(queryUnit.getLevel() + 1);
                queryUnitQueue.add(queryUnit);
            }

            num += 1;
        }

        System.out.println("处理完成，发送通知消息");
    }

}
