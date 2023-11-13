package christmas.constants.dishes;

import java.util.Arrays;

public enum Badge {
    NONE(0, 4_999, "없음"),
    STAR(5_000, 9_999, "별"),
    TREE(10_000, 19_999, "트리"),
    SANTA(20_000, Integer.MAX_VALUE, "산타");

    private final int startOfRangeInclusive;
    private final int endOfRangeInclusive;
    private final String name;

    Badge(int startOfRangeInclusive, int endOfRangeInclusive, String name) {
        this.startOfRangeInclusive = startOfRangeInclusive;
        this.endOfRangeInclusive = endOfRangeInclusive;
        this.name = name;
    }

    public static Badge getBy(int benefit) {
        return Arrays.stream(Badge.values())
                .filter(badge -> benefit >= badge.startOfRangeInclusive && benefit <= badge.endOfRangeInclusive)
                .findFirst().orElse(NONE);
    }

    public String getName() {
        return name;
    }

}
