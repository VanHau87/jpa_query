package com.hnguyen387.jpa_query.specification.dtos;

import java.util.List;

public class RequestDto {
	private List<SearchRequestDto> dtos;
	private ConditionalOperators operator;
	private PageRequestDto pageDto;

	public List<SearchRequestDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<SearchRequestDto> dtos) {
		this.dtos = dtos;
	}

	public ConditionalOperators getOperator() {
		return operator;
	}

	public void setOperator(ConditionalOperators operator) {
		this.operator = operator;
	}

	public PageRequestDto getPageDto() {
		return pageDto;
	}

	public void setPageDto(PageRequestDto pageDto) {
		this.pageDto = pageDto;
	}

}
