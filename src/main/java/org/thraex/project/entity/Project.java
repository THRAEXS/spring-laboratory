package org.thraex.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.thraex.base.entity.Entity;

/**
 * @author 鬼王
 * @date 2020/09/09 18:44
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Project extends Entity<Project> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 进展阶段
     */
    private String stage;

    /**
     * 总投资
     */
    private String investment;

    /**
     * 资金分类
     */
    private String fund;

    /**
     * 年度
     */
    private String year;

    /**
     * 年度
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

}
