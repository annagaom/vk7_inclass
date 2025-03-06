package org.example.Entities;

public enum AikidoRank {
    WHITE_BELT(1, "White Belt"),
    YELLOW_BELT(2, "Yellow Belt"),
    GREEN_BELT(3, "Green Belt"),
    BROWN_BELT(4, "Brown Belt"),
    BLACK_BELT(5, "Black Belt");

    private final int level;
    private final String description;

    AikidoRank(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public static AikidoRank fromLevel(int level) {
        for (AikidoRank rank : values()) {
            if (rank.level == level) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Invalid Aikido rank level: " + level);
    }
}
