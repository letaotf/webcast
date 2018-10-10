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
public class AdministorQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public AdministorQuery(){
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
        public Criteria andAdministorIdIsNull() {
            addCriterion("administor_id is null");
            return (Criteria) this;
        }

        public Criteria andAdministorIdIsNotNull() {
            addCriterion("administor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdministorIdEqualTo(Long value) {
            addCriterion("administor_id =", value, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdNotEqualTo(Long value) {
            addCriterion("administor_id <>", value, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdGreaterThan(Long value) {
            addCriterion("administor_id >", value, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("administor_id >=", value, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdLessThan(Long value) {
            addCriterion("administor_id <", value, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdLessThanOrEqualTo(Long value) {
            addCriterion("administor_id <=", value, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdIn(List<Long> values) {
            addCriterion("administor_id in", values, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdNotIn(List<Long> values) {
            addCriterion("administor_id not in", values, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdBetween(Long value1, Long value2) {
            addCriterion("administor_id between", value1, value2, "administor_id");
            return (Criteria) this;
        }

        public Criteria andAdministorIdNotBetween(Long value1, Long value2) {
            addCriterion("administor_id not between", value1, value2, "administor_id");
            return (Criteria) this;
        }

        //名字
        public Criteria andAdministorNameEqualTo(String value) {
            addCriterion("administor_name =", value, "administor_name");
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
