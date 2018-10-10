package com.taofeng.webcast.dao.query;

import com.taofeng.webcast.common.query.BaseCriteria;
import com.taofeng.webcast.common.query.BaseQuery;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>主播管理查询条件</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午5:15
 * @since V1.0
 */
public class NetworkAnchorQuery extends BaseQuery implements Serializable{
    private static final long serialVersionUID = -2352063873742916262L;

    public NetworkAnchorQuery(){
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

        //
        public Criteria andNetworkAnchorIdIsNull() {
            addCriterion("network_anchor_id is null");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdIsNotNull() {
            addCriterion("network_anchor_id is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdEqualTo(Long value) {
            addCriterion("network_anchor_id =", value, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdNotEqualTo(Long value) {
            addCriterion("network_anchor_id <>", value, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdGreaterThan(Long value) {
            addCriterion("network_anchor_id >", value, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("network_anchor_id >=", value, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdLessThan(Long value) {
            addCriterion("network_anchor_id <", value, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdLessThanOrEqualTo(Long value) {
            addCriterion("network_anchor_id <=", value, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdIn(List<Long> values) {
            addCriterion("network_anchor_id in", values, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdNotIn(List<Long> values) {
            addCriterion("network_anchor_id not in", values, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdBetween(Long value1, Long value2) {
            addCriterion("network_anchor_id between", value1, value2, "network_anchor_id");
            return (Criteria) this;
        }

        public Criteria andNetworkAnchorIdNotBetween(Long value1, Long value2) {
            addCriterion("network_anchor_id not between", value1, value2, "network_anchor_id");
            return (Criteria) this;
        }

        //userId 条件
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

        //audit_status的条件设置
        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("audit_status =", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("audit_status <>", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(Integer value) {
            addCriterion("audit_status like", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(Integer value) {
            addCriterion("audit_status not like", value, "audit_status");
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

        //申请时间 gmt_create
        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(LocalDateTime value) {
            addCriterion("gmt_create =", value, "gmt_create");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(LocalDateTime value) {
            addCriterion("gmt_create <>", value, "gmt_create");
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratorCriteria{
        protected Criteria(){
            super();
        }
    }

}
