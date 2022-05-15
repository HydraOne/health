package cn.geny.health.bo;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/5/12 16:24
 */
@Data
public class HealthResultsBO {
    private String orderId;

    private List<HealthResult> items;

    @Data
    public static class HealthResult{
        private String id;

        private String name;

        private String content;

        private String info;
    }

}
