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
public class AssetManagementQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public AssetManagementQuery(){
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
        public Criteria andAssetIdIsNull() {
            addCriterion("asset_id is null");
            return (Criteria) this;
        }

        public Criteria andAssetIdIsNotNull() {
            addCriterion("asset_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssetIdEqualTo(Long value) {
            addCriterion("asset_id =", value, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdNotEqualTo(Long value) {
            addCriterion("asset_id <>", value, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdGreaterThan(Long value) {
            addCriterion("asset_id >", value, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("asset_id >=", value, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdLessThan(Long value) {
            addCriterion("asset_id <", value, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdLessThanOrEqualTo(Long value) {
            addCriterion("asset_id <=", value, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdIn(List<Long> values) {
            addCriterion("asset_id in", values, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdNotIn(List<Long> values) {
            addCriterion("asset_id not in", values, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdBetween(Long value1, Long value2) {
            addCriterion("asset_id between", value1, value2, "asset_id");
            return (Criteria) this;
        }

        public Criteria andAssetIdNotBetween(Long value1, Long value2) {
            addCriterion("asset_id not between", value1, value2, "asset_id");
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

        //assetNO
        public Criteria andAssetNOLike(String value) {
            addCriterion("asset_no like", value, "asset_no");
            return (Criteria) this;
        }

        public Criteria andAssetNONotLike(String value) {
            addCriterion("asset_no not like", value, "asset_no");
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
