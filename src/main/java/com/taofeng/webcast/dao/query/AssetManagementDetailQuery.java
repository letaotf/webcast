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
public class AssetManagementDetailQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public AssetManagementDetailQuery(){
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
        public Criteria andAssetDetailIdIsNull() {
            addCriterion("asset_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdIsNotNull() {
            addCriterion("asset_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdEqualTo(Long value) {
            addCriterion("asset_detail_id =", value, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdNotEqualTo(Long value) {
            addCriterion("asset_detail_id <>", value, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdGreaterThan(Long value) {
            addCriterion("asset_detail_id >", value, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("asset_detail_id >=", value, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdLessThan(Long value) {
            addCriterion("asset_detail_id <", value, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("asset_detail_id <=", value, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdIn(List<Long> values) {
            addCriterion("asset_detail_id in", values, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdNotIn(List<Long> values) {
            addCriterion("asset_detail_id not in", values, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdBetween(Long value1, Long value2) {
            addCriterion("asset_detail_id between", value1, value2, "asset_detail_id");
            return (Criteria) this;
        }

        public Criteria andAssetDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("asset_detail_id not between", value1, value2, "asset_detail_id");
            return (Criteria) this;
        }

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

        //year_description
        public Criteria andYearDescriptionEqualTo(Long value) {
            addCriterion("year_description =", value, "year_description");
            return (Criteria) this;
        }

        public Criteria andYearDescriptionNotEqualTo(Long value) {
            addCriterion("year_description <>", value, "year_description");
            return (Criteria) this;
        }

        //month_description
        public Criteria andMonthDescriptionEqualTo(Long value) {
            addCriterion("month_description =", value, "month_description");
            return (Criteria) this;
        }

        public Criteria andMonthDescriptionNotEqualTo(Long value) {
            addCriterion("month_description <>", value, "month_description");
            return (Criteria) this;
        }

        //day_description
        public Criteria andDayDescriptionEqualTo(Long value) {
            addCriterion("day_description =", value, "day_description");
            return (Criteria) this;
        }

        public Criteria andDayDescriptionNotEqualTo(Long value) {
            addCriterion("day_description <>", value, "day_description");
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
