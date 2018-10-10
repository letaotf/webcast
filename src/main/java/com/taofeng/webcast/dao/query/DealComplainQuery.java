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
public class DealComplainQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public DealComplainQuery(){
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

        //deal_complain_id 条件
        public Criteria andDealComplainIdIsNull() {
            addCriterion("deal_complain_id is null");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdIsNotNull() {
            addCriterion("deal_complain_id is not null");
            return (Criteria) this;
        }

        public Criteria andDealComplainIddEqualTo(Long value) {
            addCriterion("deal_complain_id =", value, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdNotEqualTo(Long value) {
            addCriterion("deal_complain_id <>", value, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdGreaterThan(Long value) {
            addCriterion("deal_complain_id >", value, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("deal_complain_id >=", value, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdLessThan(Long value) {
            addCriterion("deal_complain_id <", value, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIddLessThanOrEqualTo(Long value) {
            addCriterion("deal_complain_id <=", value, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdIn(List<Long> values) {
            addCriterion("deal_complain_id in", values, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdNotIn(List<Long> values) {
            addCriterion("deal_complain_id not in", values, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdBetween(Long value1, Long value2) {
            addCriterion("deal_complain_id between", value1, value2, "deal_complain_id");
            return (Criteria) this;
        }

        public Criteria andDealComplainIdNotBetween(Long value1, Long value2) {
            addCriterion("deal_complain_id not between", value1, value2, "deal_complain_id");
            return (Criteria) this;
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
