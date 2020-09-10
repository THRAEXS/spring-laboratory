package org.agriculture.project.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.agriculture.base.entity.Entity;
import org.agriculture.platform.entity.Dict;

import java.math.BigDecimal;

/**
 * @author MLeo
 * @date 2020/09/09 18:44
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Project extends Entity<Project> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编号
     */
    private String itemNo;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 所属行业. {@link Dict#getId()}
     */
    private String industry;

    /**
     * 进展阶段. {@link Dict#getId()}
     */
    private String stage;

    /**
     * 总投资(万)
     */
    private BigDecimal investment;

    /**
     * 资金分类. {@link Dict#getId()}
     */
    private String fund;

    /**
     * 年度
     */
    private Long year;

    /**
     * 工期建设单位
     */
    private String company;

    /**
     * 审批文号
     */
    private String approvalNo;

    /**
     * 审批机关
     */
    private String approvalOffice;

    /**
     * 建设地点
     */
    private String place;

    /**
     * 所属地区
     */
    private String region;

    /**
     * 主管单位
     */
    private String unit;

    /**
     * 项目负责人
     */
    private String leader;

    /**
     * 电话
     */
    private String phone;

    @TableLogic
    private Integer deleted;

}
