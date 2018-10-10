package com.taofeng.webcast.dao.query;

import com.taofeng.webcast.common.query.BaseCriteria;
import com.taofeng.webcast.common.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

/**
 * <p>管理员查询条件</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午5:15
 * @since V1.0
 */
public class TimeQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public TimeQuery(){
        super();
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        super.oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    private static class GeneratorCriteria extends BaseCriteria{
        public GeneratorCriteria(){
            super();
        }

        //userId 条件
        public Criteria andTimeIdIsNull() {
            addCriterion("time_id is null");
            return (Criteria) this;
        }

        public Criteria andTimeIdIsNotNull() {
            addCriterion("time_id is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIdEqualTo(Long value) {
            addCriterion("time_id =", value, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotEqualTo(Long value) {
            addCriterion("time_id <>", value, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThan(Long value) {
            addCriterion("time_id >", value, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("time_id >=", value, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThan(Long value) {
            addCriterion("time_id <", value, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdLessThanOrEqualTo(Long value) {
            addCriterion("time_id <=", value, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdIn(List<Long> values) {
            addCriterion("time_id in", values, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotIn(List<Long> values) {
            addCriterion("time_id not in", values, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdBetween(Long value1, Long value2) {
            addCriterion("time_id between", value1, value2, "time_id");
            return (Criteria) this;
        }

        public Criteria andTimeIdNotBetween(Long value1, Long value2) {
            addCriterion("time_id not between", value1, value2, "time_id");
            return (Criteria) this;
        }

        //status的条件
        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratorCriteria{
        protected Criteria(){
            super();
        }
    }

}
