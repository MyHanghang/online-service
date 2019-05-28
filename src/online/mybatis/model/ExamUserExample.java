package online.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExamUserExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ExamUserExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
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

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

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

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNull() {
			addCriterion("userName is null");
			return (Criteria) this;
		}

		public Criteria andUsernameIsNotNull() {
			addCriterion("userName is not null");
			return (Criteria) this;
		}

		public Criteria andUsernameEqualTo(String value) {
			addCriterion("userName =", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotEqualTo(String value) {
			addCriterion("userName <>", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThan(String value) {
			addCriterion("userName >", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameGreaterThanOrEqualTo(String value) {
			addCriterion("userName >=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThan(String value) {
			addCriterion("userName <", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLessThanOrEqualTo(String value) {
			addCriterion("userName <=", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameLike(String value) {
			addCriterion("userName like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotLike(String value) {
			addCriterion("userName not like", value, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameIn(List<String> values) {
			addCriterion("userName in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotIn(List<String> values) {
			addCriterion("userName not in", values, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameBetween(String value1, String value2) {
			addCriterion("userName between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUsernameNotBetween(String value1, String value2) {
			addCriterion("userName not between", value1, value2, "username");
			return (Criteria) this;
		}

		public Criteria andUserpassIsNull() {
			addCriterion("userPass is null");
			return (Criteria) this;
		}

		public Criteria andUserpassIsNotNull() {
			addCriterion("userPass is not null");
			return (Criteria) this;
		}

		public Criteria andUserpassEqualTo(String value) {
			addCriterion("userPass =", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassNotEqualTo(String value) {
			addCriterion("userPass <>", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassGreaterThan(String value) {
			addCriterion("userPass >", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassGreaterThanOrEqualTo(String value) {
			addCriterion("userPass >=", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassLessThan(String value) {
			addCriterion("userPass <", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassLessThanOrEqualTo(String value) {
			addCriterion("userPass <=", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassLike(String value) {
			addCriterion("userPass like", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassNotLike(String value) {
			addCriterion("userPass not like", value, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassIn(List<String> values) {
			addCriterion("userPass in", values, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassNotIn(List<String> values) {
			addCriterion("userPass not in", values, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassBetween(String value1, String value2) {
			addCriterion("userPass between", value1, value2, "userpass");
			return (Criteria) this;
		}

		public Criteria andUserpassNotBetween(String value1, String value2) {
			addCriterion("userPass not between", value1, value2, "userpass");
			return (Criteria) this;
		}

		public Criteria andUsertypeIsNull() {
			addCriterion("userType is null");
			return (Criteria) this;
		}

		public Criteria andUsertypeIsNotNull() {
			addCriterion("userType is not null");
			return (Criteria) this;
		}

		public Criteria andUsertypeEqualTo(String value) {
			addCriterion("userType =", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeNotEqualTo(String value) {
			addCriterion("userType <>", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeGreaterThan(String value) {
			addCriterion("userType >", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeGreaterThanOrEqualTo(String value) {
			addCriterion("userType >=", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeLessThan(String value) {
			addCriterion("userType <", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeLessThanOrEqualTo(String value) {
			addCriterion("userType <=", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeLike(String value) {
			addCriterion("userType like", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeNotLike(String value) {
			addCriterion("userType not like", value, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeIn(List<String> values) {
			addCriterion("userType in", values, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeNotIn(List<String> values) {
			addCriterion("userType not in", values, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeBetween(String value1, String value2) {
			addCriterion("userType between", value1, value2, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsertypeNotBetween(String value1, String value2) {
			addCriterion("userType not between", value1, value2, "usertype");
			return (Criteria) this;
		}

		public Criteria andUsergroupIsNull() {
			addCriterion("userGroup is null");
			return (Criteria) this;
		}

		public Criteria andUsergroupIsNotNull() {
			addCriterion("userGroup is not null");
			return (Criteria) this;
		}

		public Criteria andUsergroupEqualTo(String value) {
			addCriterion("userGroup =", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupNotEqualTo(String value) {
			addCriterion("userGroup <>", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupGreaterThan(String value) {
			addCriterion("userGroup >", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupGreaterThanOrEqualTo(String value) {
			addCriterion("userGroup >=", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupLessThan(String value) {
			addCriterion("userGroup <", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupLessThanOrEqualTo(String value) {
			addCriterion("userGroup <=", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupLike(String value) {
			addCriterion("userGroup like", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupNotLike(String value) {
			addCriterion("userGroup not like", value, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupIn(List<String> values) {
			addCriterion("userGroup in", values, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupNotIn(List<String> values) {
			addCriterion("userGroup not in", values, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupBetween(String value1, String value2) {
			addCriterion("userGroup between", value1, value2, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUsergroupNotBetween(String value1, String value2) {
			addCriterion("userGroup not between", value1, value2, "usergroup");
			return (Criteria) this;
		}

		public Criteria andUserphoneIsNull() {
			addCriterion("userPhone is null");
			return (Criteria) this;
		}

		public Criteria andUserphoneIsNotNull() {
			addCriterion("userPhone is not null");
			return (Criteria) this;
		}

		public Criteria andUserphoneEqualTo(String value) {
			addCriterion("userPhone =", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneNotEqualTo(String value) {
			addCriterion("userPhone <>", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneGreaterThan(String value) {
			addCriterion("userPhone >", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneGreaterThanOrEqualTo(String value) {
			addCriterion("userPhone >=", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneLessThan(String value) {
			addCriterion("userPhone <", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneLessThanOrEqualTo(String value) {
			addCriterion("userPhone <=", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneLike(String value) {
			addCriterion("userPhone like", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneNotLike(String value) {
			addCriterion("userPhone not like", value, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneIn(List<String> values) {
			addCriterion("userPhone in", values, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneNotIn(List<String> values) {
			addCriterion("userPhone not in", values, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneBetween(String value1, String value2) {
			addCriterion("userPhone between", value1, value2, "userphone");
			return (Criteria) this;
		}

		public Criteria andUserphoneNotBetween(String value1, String value2) {
			addCriterion("userPhone not between", value1, value2, "userphone");
			return (Criteria) this;
		}

		public Criteria andUseraddrIsNull() {
			addCriterion("userAddr is null");
			return (Criteria) this;
		}

		public Criteria andUseraddrIsNotNull() {
			addCriterion("userAddr is not null");
			return (Criteria) this;
		}

		public Criteria andUseraddrEqualTo(String value) {
			addCriterion("userAddr =", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrNotEqualTo(String value) {
			addCriterion("userAddr <>", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrGreaterThan(String value) {
			addCriterion("userAddr >", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrGreaterThanOrEqualTo(String value) {
			addCriterion("userAddr >=", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrLessThan(String value) {
			addCriterion("userAddr <", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrLessThanOrEqualTo(String value) {
			addCriterion("userAddr <=", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrLike(String value) {
			addCriterion("userAddr like", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrNotLike(String value) {
			addCriterion("userAddr not like", value, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrIn(List<String> values) {
			addCriterion("userAddr in", values, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrNotIn(List<String> values) {
			addCriterion("userAddr not in", values, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrBetween(String value1, String value2) {
			addCriterion("userAddr between", value1, value2, "useraddr");
			return (Criteria) this;
		}

		public Criteria andUseraddrNotBetween(String value1, String value2) {
			addCriterion("userAddr not between", value1, value2, "useraddr");
			return (Criteria) this;
		}

		public Criteria andLastloginIsNull() {
			addCriterion("lastLogin is null");
			return (Criteria) this;
		}

		public Criteria andLastloginIsNotNull() {
			addCriterion("lastLogin is not null");
			return (Criteria) this;
		}

		public Criteria andLastloginEqualTo(Date value) {
			addCriterionForJDBCDate("lastLogin =", value, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginNotEqualTo(Date value) {
			addCriterionForJDBCDate("lastLogin <>", value, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginGreaterThan(Date value) {
			addCriterionForJDBCDate("lastLogin >", value, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("lastLogin >=", value, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginLessThan(Date value) {
			addCriterionForJDBCDate("lastLogin <", value, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("lastLogin <=", value, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginIn(List<Date> values) {
			addCriterionForJDBCDate("lastLogin in", values, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginNotIn(List<Date> values) {
			addCriterionForJDBCDate("lastLogin not in", values, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("lastLogin between", value1, value2, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andLastloginNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("lastLogin not between", value1, value2, "lastlogin");
			return (Criteria) this;
		}

		public Criteria andSpare1IsNull() {
			addCriterion("spare1 is null");
			return (Criteria) this;
		}

		public Criteria andSpare1IsNotNull() {
			addCriterion("spare1 is not null");
			return (Criteria) this;
		}

		public Criteria andSpare1EqualTo(String value) {
			addCriterion("spare1 =", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1NotEqualTo(String value) {
			addCriterion("spare1 <>", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1GreaterThan(String value) {
			addCriterion("spare1 >", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1GreaterThanOrEqualTo(String value) {
			addCriterion("spare1 >=", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1LessThan(String value) {
			addCriterion("spare1 <", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1LessThanOrEqualTo(String value) {
			addCriterion("spare1 <=", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1Like(String value) {
			addCriterion("spare1 like", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1NotLike(String value) {
			addCriterion("spare1 not like", value, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1In(List<String> values) {
			addCriterion("spare1 in", values, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1NotIn(List<String> values) {
			addCriterion("spare1 not in", values, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1Between(String value1, String value2) {
			addCriterion("spare1 between", value1, value2, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare1NotBetween(String value1, String value2) {
			addCriterion("spare1 not between", value1, value2, "spare1");
			return (Criteria) this;
		}

		public Criteria andSpare2IsNull() {
			addCriterion("spare2 is null");
			return (Criteria) this;
		}

		public Criteria andSpare2IsNotNull() {
			addCriterion("spare2 is not null");
			return (Criteria) this;
		}

		public Criteria andSpare2EqualTo(String value) {
			addCriterion("spare2 =", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2NotEqualTo(String value) {
			addCriterion("spare2 <>", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2GreaterThan(String value) {
			addCriterion("spare2 >", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2GreaterThanOrEqualTo(String value) {
			addCriterion("spare2 >=", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2LessThan(String value) {
			addCriterion("spare2 <", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2LessThanOrEqualTo(String value) {
			addCriterion("spare2 <=", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2Like(String value) {
			addCriterion("spare2 like", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2NotLike(String value) {
			addCriterion("spare2 not like", value, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2In(List<String> values) {
			addCriterion("spare2 in", values, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2NotIn(List<String> values) {
			addCriterion("spare2 not in", values, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2Between(String value1, String value2) {
			addCriterion("spare2 between", value1, value2, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare2NotBetween(String value1, String value2) {
			addCriterion("spare2 not between", value1, value2, "spare2");
			return (Criteria) this;
		}

		public Criteria andSpare3IsNull() {
			addCriterion("spare3 is null");
			return (Criteria) this;
		}

		public Criteria andSpare3IsNotNull() {
			addCriterion("spare3 is not null");
			return (Criteria) this;
		}

		public Criteria andSpare3EqualTo(String value) {
			addCriterion("spare3 =", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3NotEqualTo(String value) {
			addCriterion("spare3 <>", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3GreaterThan(String value) {
			addCriterion("spare3 >", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3GreaterThanOrEqualTo(String value) {
			addCriterion("spare3 >=", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3LessThan(String value) {
			addCriterion("spare3 <", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3LessThanOrEqualTo(String value) {
			addCriterion("spare3 <=", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3Like(String value) {
			addCriterion("spare3 like", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3NotLike(String value) {
			addCriterion("spare3 not like", value, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3In(List<String> values) {
			addCriterion("spare3 in", values, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3NotIn(List<String> values) {
			addCriterion("spare3 not in", values, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3Between(String value1, String value2) {
			addCriterion("spare3 between", value1, value2, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare3NotBetween(String value1, String value2) {
			addCriterion("spare3 not between", value1, value2, "spare3");
			return (Criteria) this;
		}

		public Criteria andSpare4IsNull() {
			addCriterion("spare4 is null");
			return (Criteria) this;
		}

		public Criteria andSpare4IsNotNull() {
			addCriterion("spare4 is not null");
			return (Criteria) this;
		}

		public Criteria andSpare4EqualTo(String value) {
			addCriterion("spare4 =", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4NotEqualTo(String value) {
			addCriterion("spare4 <>", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4GreaterThan(String value) {
			addCriterion("spare4 >", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4GreaterThanOrEqualTo(String value) {
			addCriterion("spare4 >=", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4LessThan(String value) {
			addCriterion("spare4 <", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4LessThanOrEqualTo(String value) {
			addCriterion("spare4 <=", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4Like(String value) {
			addCriterion("spare4 like", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4NotLike(String value) {
			addCriterion("spare4 not like", value, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4In(List<String> values) {
			addCriterion("spare4 in", values, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4NotIn(List<String> values) {
			addCriterion("spare4 not in", values, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4Between(String value1, String value2) {
			addCriterion("spare4 between", value1, value2, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare4NotBetween(String value1, String value2) {
			addCriterion("spare4 not between", value1, value2, "spare4");
			return (Criteria) this;
		}

		public Criteria andSpare5IsNull() {
			addCriterion("spare5 is null");
			return (Criteria) this;
		}

		public Criteria andSpare5IsNotNull() {
			addCriterion("spare5 is not null");
			return (Criteria) this;
		}

		public Criteria andSpare5EqualTo(String value) {
			addCriterion("spare5 =", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5NotEqualTo(String value) {
			addCriterion("spare5 <>", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5GreaterThan(String value) {
			addCriterion("spare5 >", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5GreaterThanOrEqualTo(String value) {
			addCriterion("spare5 >=", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5LessThan(String value) {
			addCriterion("spare5 <", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5LessThanOrEqualTo(String value) {
			addCriterion("spare5 <=", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5Like(String value) {
			addCriterion("spare5 like", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5NotLike(String value) {
			addCriterion("spare5 not like", value, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5In(List<String> values) {
			addCriterion("spare5 in", values, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5NotIn(List<String> values) {
			addCriterion("spare5 not in", values, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5Between(String value1, String value2) {
			addCriterion("spare5 between", value1, value2, "spare5");
			return (Criteria) this;
		}

		public Criteria andSpare5NotBetween(String value1, String value2) {
			addCriterion("spare5 not between", value1, value2, "spare5");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

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