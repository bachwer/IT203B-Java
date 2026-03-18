package Session09.util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static int randomInt(int min, int max){
        return random.nextInt(max -min + 1) + min;
    }

    public static boolean chance(int percent){
        return random.nextInt(100) < percent;
    }
}
