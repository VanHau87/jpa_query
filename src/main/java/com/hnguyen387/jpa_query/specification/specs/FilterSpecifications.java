package com.hnguyen387.jpa_query.specification.specs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hnguyen387.jpa_query.specification.dtos.ConditionalOperators;
import com.hnguyen387.jpa_query.specification.dtos.SearchRequestDto;

import jakarta.persistence.criteria.Predicate;

@Service
public class FilterSpecifications<T> {
	
	public Specification<T> searchSpecification(SearchRequestDto dto) {
		/*
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return null;
			}
		};
		*/
		return (root, query, builder) -> builder.equal(root.get(dto.getColumn()), dto.getValue());
	}
	
	public Specification<T> searchSpecification(List<SearchRequestDto> dtos, ConditionalOperators operator) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			dtos.forEach(dto -> {
				switch (dto.getOperators()) {
				case EQUAL:
					Predicate equal = builder.equal(root.get(dto.getColumn()), dto.getValue());
					predicates.add(equal);
					break;
				case LIKE:
					Predicate like = builder.like(root.get(dto.getColumn()), String.format("%%%s%%", dto.getValue()));
					predicates.add(like);
					break;
				case IN:
					String[] items = Arrays.stream(dto.getValue().split(","))
								.map(String::trim)
								.toArray(String[]::new);
					Predicate in = root.get(dto.getColumn()).in(Arrays.asList(items));
					predicates.add(in);
					break;
				case LESS_THAN:
					Predicate lessThan = builder.lessThan(root.get(dto.getColumn()), dto.getValue());
					predicates.add(lessThan);
					break;
				case GREATER_THAN:
					Predicate greaterThan = builder.greaterThan(root.get(dto.getColumn()), dto.getValue());
					predicates.add(greaterThan);
					break;
				case BETWEEN:
					int[] ranges = Arrays.stream(dto.getValue().split(","))
								.map(String::trim)
								.mapToInt(Integer::parseInt)
								.sorted()
								.toArray();
					int min = ranges[0];
					int max = ranges[1];
					Predicate between = builder.between(root.get(dto.getColumn()), min, max);
					predicates.add(between);
					break;
				case JOIN:
					Predicate join = builder.equal(root.join(dto.getJoinTbl()).get(dto.getColumn()), dto.getValue());
					predicates.add(join);
					break;
				}
				
			});
			Predicate conditionalPredicate = null;
			switch (operator) {
			case AND:
				conditionalPredicate = builder.and(predicates.toArray(new Predicate[0]));
				break;
			case OR:
				conditionalPredicate = builder.or(predicates.toArray(new Predicate[0]));
				break;
			}
			return conditionalPredicate;
		};
	}
}
