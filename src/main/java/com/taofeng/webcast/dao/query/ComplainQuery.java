package com.taofeng.webcast.dao.query;

import com.taofeng.webcast.common.query.BaseCriteria;
import com.taofeng.webcast.common.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午5:15
 * @since V1.0
 */
public class ComplainQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public ComplainQuery(){
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

        //complain_id 条件
        public Criteria andComplainIdIsNull() {
            addCriterion("complain_id is null");
            return (Criteria) this;
        }

        public Criteria andComplainIdIsNotNull() {
            addCriterion("complain_id is not null");
            return (Criteria) this;
        }

        public Criteria andComplainIdEqualTo(Long value) {
            addCriterion("complain_id =", value, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdNotEqualTo(Long value) {
            addCriterion("complain_id <>", value, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdGreaterThan(Long value) {
            addCriterion("complain_id >", value, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("complain_id >=", value, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdLessThan(Long value) {
            addCriterion("complain_id <", value, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdLessThanOrEqualTo(Long value) {
            addCriterion("complain_id <=", value, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdIn(List<Long> values) {
            addCriterion("complain_id in", values, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdNotIn(List<Long> values) {
            addCriterion("complain_id not in", values, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdBetween(Long value1, Long value2) {
            addCriterion("complain_id between", value1, value2, "complain_id");
            return (Criteria) this;
        }

        public Criteria andComplainIdNotBetween(Long value1, Long value2) {
            addCriterion("complain_id not between", value1, value2, "complain_id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }


        public Criteria andPersonByReportedIdEqualTo(Long value) {
            addCriterion("person_by_reported_id =", value, "person_by_reported_id");
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
