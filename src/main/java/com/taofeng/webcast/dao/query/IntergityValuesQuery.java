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
public class IntergityValuesQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public IntergityValuesQuery(){
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
        public Criteria andIntergityIdIsNull() {
            addCriterion("intergity_id is null");
            return (Criteria) this;
        }

        public Criteria andIntergityIdIsNotNull() {
            addCriterion("intergity_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntergityIdEqualTo(Long value) {
            addCriterion("intergity_id =", value, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdNotEqualTo(Long value) {
            addCriterion("intergity_id <>", value, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdGreaterThan(Long value) {
            addCriterion("intergity_id >", value, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("intergity_id >=", value, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdLessThan(Long value) {
            addCriterion("intergity_id <", value, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdLessThanOrEqualTo(Long value) {
            addCriterion("intergity_id <=", value, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdIn(List<Long> values) {
            addCriterion("intergity_id in", values, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdNotIn(List<Long> values) {
            addCriterion("intergity_id not in", values, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdBetween(Long value1, Long value2) {
            addCriterion("intergity_id between", value1, value2, "intergity_id");
            return (Criteria) this;
        }

        public Criteria andIntergityIdNotBetween(Long value1, Long value2) {
            addCriterion("intergity_id not between", value1, value2, "intergity_id");
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

        //value的条件
        public Criteria andValueNumGreaterThanOrEqualTo(Double value) {
            addCriterion("value_num >=", value, "value_num");
            return (Criteria) this;
        }

        public Criteria andValueNumBetween(Double value1, Double value2) {
            addCriterion("value_num between", value1, value2, "value_num");
            return (Criteria) this;
        }

        public Criteria andValueNumNotBetween(Double value1, Double value2) {
            addCriterion("value_num not between", value1, value2, "value_num");
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
