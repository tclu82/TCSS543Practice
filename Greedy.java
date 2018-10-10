import java.util.Arrays;

public class Greedy {

    public Greedy() {
        Interval[] intervals = {
                new Interval(0, 6),
                new Interval(1, 4),
                new Interval(3, 5),
                new Interval(3, 8),
                new Interval(4, 7),
                new Interval(5, 9),
                new Interval(6, 10),
                new Interval(8, 11),
        };
        System.out.println(findMaxJobsWithGreedy(intervals));
    }

    private int findMaxJobsWithGreedy(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1.end < o2.end) {
                return -1;
            } else if (o1.end == o2.end) {
                return 0;
            } else {
                return 1;
            }
        });
        int count = 1, endTime = intervals[0].end;
        for (Interval cur: intervals) {
            if (cur.start >= endTime) {
                count++;
                endTime = cur.end;
            }
        }
        return count;
    }
}

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
