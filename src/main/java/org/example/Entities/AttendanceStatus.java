package org.example.Entities;

public enum AttendanceStatus {
    ABSENT(0, "Absent"),
    PRESENT(1, "Present"),
    LATE(2, "Late"),
    EXCUSED(3, "Excused");

    private final int code;
    private final String description;

    AttendanceStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AttendanceStatus fromCode(int code) {
        for (AttendanceStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid attendance status code: " + code);
    }
}
