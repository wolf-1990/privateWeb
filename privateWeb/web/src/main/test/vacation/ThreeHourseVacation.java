package vacation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description TOO
 * @Author 赵赫智
 * @Date 2019/8/15 14:26
 **/
public class ThreeHourseVacation {
    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.of(2019, 10, 24, 9, 33, 00);
        String leaveTime = getLeaveTime(startTime,6,30);
        String offWorkTime = getLeaveTime(startTime, 9, 30);
        //请三小时假开始时间以及请假结束时间
        System.out.print("如果今天要请三个小时假，那么请假开始于"+leaveTime+"结束于："+offWorkTime);
    }
    //输入上班时间以及间隔时长
    private static String getLeaveTime(LocalDateTime startTime,Integer hour,Integer minute){
        LocalDateTime localDateTime = startTime.plusHours(hour).plusMinutes(minute);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format = localDateTime.format(dateTimeFormatter);
        return format;
    }

}
