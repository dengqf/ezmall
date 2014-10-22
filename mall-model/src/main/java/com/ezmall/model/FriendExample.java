package com.ezmall.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public FriendExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameIsNull() {
            addCriterion("MEMBER_USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameIsNotNull() {
            addCriterion("MEMBER_USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameEqualTo(String value) {
            addCriterion("MEMBER_USERNAME =", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameNotEqualTo(String value) {
            addCriterion("MEMBER_USERNAME <>", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameGreaterThan(String value) {
            addCriterion("MEMBER_USERNAME >", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_USERNAME >=", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameLessThan(String value) {
            addCriterion("MEMBER_USERNAME <", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_USERNAME <=", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameLike(String value) {
            addCriterion("MEMBER_USERNAME like", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameNotLike(String value) {
            addCriterion("MEMBER_USERNAME not like", value, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameIn(List<String> values) {
            addCriterion("MEMBER_USERNAME in", values, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameNotIn(List<String> values) {
            addCriterion("MEMBER_USERNAME not in", values, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameBetween(String value1, String value2) {
            addCriterion("MEMBER_USERNAME between", value1, value2, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andMemberUsernameNotBetween(String value1, String value2) {
            addCriterion("MEMBER_USERNAME not between", value1, value2, "memberUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameIsNull() {
            addCriterion("FRIEND_USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameIsNotNull() {
            addCriterion("FRIEND_USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameEqualTo(String value) {
            addCriterion("FRIEND_USERNAME =", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameNotEqualTo(String value) {
            addCriterion("FRIEND_USERNAME <>", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameGreaterThan(String value) {
            addCriterion("FRIEND_USERNAME >", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("FRIEND_USERNAME >=", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameLessThan(String value) {
            addCriterion("FRIEND_USERNAME <", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameLessThanOrEqualTo(String value) {
            addCriterion("FRIEND_USERNAME <=", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameLike(String value) {
            addCriterion("FRIEND_USERNAME like", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameNotLike(String value) {
            addCriterion("FRIEND_USERNAME not like", value, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameIn(List<String> values) {
            addCriterion("FRIEND_USERNAME in", values, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameNotIn(List<String> values) {
            addCriterion("FRIEND_USERNAME not in", values, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameBetween(String value1, String value2) {
            addCriterion("FRIEND_USERNAME between", value1, value2, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andFriendUsernameNotBetween(String value1, String value2) {
            addCriterion("FRIEND_USERNAME not between", value1, value2, "friendUsername");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_friend
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 09 18:11:52 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_friend
     *
     * @mbggenerated Sat Aug 09 18:11:52 CST 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}