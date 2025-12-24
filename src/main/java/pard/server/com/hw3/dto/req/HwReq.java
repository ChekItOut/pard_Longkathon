package pard.server.com.hw3.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class HwReq {

    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class HwInfo {
        private String title;

        private String status;

        private String dueDate;

        private int level;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class HwTitle{
        private String title;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class HwStatus{
        private String status;
    }
}
