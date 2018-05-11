package java8;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DateTimeTest {

    public static void main(String[] args) {
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        final LocalDate date = LocalDate.now();
        final LocalDate dataFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dataFromClock);

        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);

        final LocalDateTime dateTime = LocalDateTime.now();
        final LocalDateTime dateTimeFromClock = LocalDateTime.now(clock);
        System.out.println(dateTime);
        System.out.println(dateTimeFromClock);
    }
}
