package com.jeethink.basicInfo.domain;

import com.jeethink.common.core.domain.BaseEntity;

/**
 * 箱体保存中转实体
 * */
public class saveBoxEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 箱体Id */
    private String boxId;
    /** 类型（1主柜，2副柜） */
    private String type;
    /** 排序 */
    private String sort;
    /** 卷宗柜Id */
    private String lockerId;
    /** 箱体数量 */
    private String boxCount;

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setBoxCount(String boxCount) {
        this.boxCount = boxCount;
    }

    public String getBoxCount() {
        return boxCount;
    }
}
