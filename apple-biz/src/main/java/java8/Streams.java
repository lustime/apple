package java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Streams {

    private enum Status{
        OPEN, CLOSED
    }

    private static final class Task{
        private final Status staus;
        private final Integer points;

        public Task(Status staus, Integer points) {
            this.staus = staus;
            this.points = points;
        }

        public Status getStaus() {
            return staus;
        }

        public Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", staus, points);
        }
    }

    public static void main(String[] args) {

        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 10),
                new Task(Status.CLOSED, 8)
        );

        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter(task -> task.getStaus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println(totalPointsOfOpenTasks);

        final double totalPoint = tasks
                .stream()
                .parallel()
                .map(task -> task.getPoints())
                .reduce(0, Integer::sum);
        System.out.println("total points(all task):" + totalPoint);

        final Map<Status, List<Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Task::getStaus));
        System.out.println(map);

        final Collection<String> result = tasks
                .stream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(point -> point/totalPoint)
                .boxed()
                .mapToLong(weight -> (long)(weight*100))
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
