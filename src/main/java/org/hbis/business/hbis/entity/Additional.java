package org.hbis.business.hbis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2020/09/07 21:59
 */
@Data
@Accessors(chain = true)
public class Additional implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * Professional field
     */
    private String professional;

    /**
     * Professional: Information technology
     */
    private String pit;

    /**
     * Professional: Automation technology
     */
    private String pat;

    /**
     * Professional: Communication technology
     */
    private String pct;

    /**
     * Professional: Network technology
     */
    private String pnt;

    /**
     * Professional: Entrance guard monitoring
     */
    private String peg;

    /**
     * Application case
     */
    private String cases;

    /**
     * Case: Information system
     */
    private String cis;

    /**
     * Case: Automatic system
     */
    private String cas;

    /**
     * Case: Communication system
     */
    private String ccs;

    /**
     * Case: Monitoring system
     */
    private String cms;

    /**
     * Case: Entrance guard system
     */
    private String ceg;

    /**
     * Enterprise culture
     */
    private String culture;

    /**
     * Enterprise culture: Enterprise vision
     */
    private String cev;

    /**
     * Enterprise culture: Recreational activity
     */
    private String cra;

    /**
     * Enterprise culture: Team building
     */
    private String ctb;

}
