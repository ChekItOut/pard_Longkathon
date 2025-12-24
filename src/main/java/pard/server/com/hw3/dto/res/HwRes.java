package pard.server.com.hw3.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HwRes {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class statusDueDate {
        private String status;
        private String dueDate;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class titleLevel{
        private String title;
        private int level;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class allInfo{
        private Long id;
        private String title;
        private int level;
        private String dueDate;
        private String status;
    }
}
