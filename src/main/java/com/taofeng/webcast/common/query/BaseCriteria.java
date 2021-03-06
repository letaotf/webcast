package com.taofeng.webcast.common.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>基础条件查询的类</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午4:46
 * @since V1.0
 */
public class BaseCriteria implements Serializable {

    private static final long serialVersionUID = -169662693597215496L;

    protected List<Criterion> criteria;

    protected BaseCriteria() {
        super();
        criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
        return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
        return criteria;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    protected void addCriterion(String condition) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
        if (value == null) {
            throw new RuntimeException("Value for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, value1, value2));
    }
}
