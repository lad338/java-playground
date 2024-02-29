package interview;

public class ClockHand {

    private static final int MINUTE_ANGLE = 6;
    private static final int HOUR_ANGLE = 30;

    public static void main(String[] args) {
        System.out.println(findAngle(15, 0));
        System.out.println(findAngle(15, 30));
        System.out.println(findAngle(5, 30));
        System.out.println(findAngle(6, 0));
        System.out.println(findAngle(12, 0));
        System.out.println(findAngle(12, 30));
        System.out.println(findAngle(7, 0));
        System.out.println(findAngle(7, 10));

        System.out.println("This is a debug message");
    }

    public static double findAngle(int hour, int minute) {
        if (hour < 0 || hour > 23) {
            return -1;
        }

        if (minute < 0 || minute > 59) {
            return -1;
        }

        double minuteHandAngle = MINUTE_ANGLE * minute;
        double hourHandAngle =
            HOUR_ANGLE * (hour % 12) + HOUR_ANGLE / 60.0f * minute;

        double difference = Math.abs(hourHandAngle - minuteHandAngle);

        // > 180

        if (difference <= 180) {
            return difference;
        } else {
            return 360 - difference;
        }
    }
}
