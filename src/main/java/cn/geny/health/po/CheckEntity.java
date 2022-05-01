package cn.geny.health.po;

import cn.geny.health.bo.Summary;
import cn.geny.health.constant.Constants;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/13 0:46
 */

/**
 * 检查实体
 *
 * @author hydraone
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "check_entity")
public class CheckEntity {
    /**
     * 检查实体ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 名称
     */
    @TableField(value = "`NAME`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "DESCRIPTION")
    private String description;

    /**
     * 类型
     */
    @TableField(value = "`TYPE`")
    private String type;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标志
     */
    @TableField(value = "DEL_FLAG")
    private String delFlag;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM1")
    @JsonIgnore
    private String param1;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM2")
    @JsonProperty("price")
    private String price;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM3")
    @JsonProperty("priceSale")
    private String priceSale;

    /**
     * 预留字段
     */
    @TableField(value = "PARAM4")
    private String param4;

    @TableField(exist = false)
    private Summary summary;

    @JsonGetter("images")
    public List<String> getImages() {
        String[] images = StringUtils.split(param1, ',');
        return Arrays.stream(images).map(item -> Constants.MINIO_URI + "/demo/" + item).collect(Collectors.toList());
    }

    @JsonGetter("description")
    public String getProductDescription() {
        Document document = Jsoup.parse(description);
        Elements imgs = document.getElementsByTag("img");
        imgs.forEach(img -> {
            img.attr("src", Constants.MINIO_URI + "/demo/" + img.attr("src"));
        });
        return document.outerHtml();
    }


    @TableField(exist = false)
    private List<String> groups;

    @TableField(exist = false)
    private List<String> tags;

    @TableField(exist = false)
    private List<String> items;

    @TableField(exist = false)
    private List<String> children;
}