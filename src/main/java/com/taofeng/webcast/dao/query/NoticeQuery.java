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
public class NoticeQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public NoticeQuery(){
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
        public Criteria andNoticeIdIsNull() {
            addCriterion("notice_id is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIsNotNull() {
            addCriterion("notice_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdEqualTo(Long value) {
            addCriterion("notice_id =", value, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotEqualTo(Long value) {
            addCriterion("notice_id <>", value, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThan(Long value) {
            addCriterion("notice_id >", value, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("notice_id >=", value, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThan(Long value) {
            addCriterion("notice_id <", value, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThanOrEqualTo(Long value) {
            addCriterion("notice_id <=", value, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIn(List<Long> values) {
            addCriterion("notice_id in", values, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotIn(List<Long> values) {
            addCriterion("notice_id not in", values, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdBetween(Long value1, Long value2) {
            addCriterion("notice_id between", value1, value2, "notice_id");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotBetween(Long value1, Long value2) {
            addCriterion("notice_id not between", value1, value2, "notice_id");
            return (Criteria) this;
        }

        //password的条件

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
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
