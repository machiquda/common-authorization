package com.quhuwai.common.author.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 基础DO类，提供toString快方法
 * Created by liwei on 2015/6/16.
 */
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -1394589131426860408L;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

