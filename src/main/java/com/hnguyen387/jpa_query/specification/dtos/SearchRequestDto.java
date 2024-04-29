package com.hnguyen387.jpa_query.specification.dtos;

public class SearchRequestDto {
	String column;
	String value;
	QueryOperators operators;
	String joinTbl;
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public QueryOperators getOperators() {
		return operators;
	}
	public void setOperators(QueryOperators operators) {
		this.operators = operators;
	}
	public String getJoinTbl() {
		return joinTbl;
	}
	public void setJoinTbl(String joinTbl) {
		this.joinTbl = joinTbl;
	}
	
}
