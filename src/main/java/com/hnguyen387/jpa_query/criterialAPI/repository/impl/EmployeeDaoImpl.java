package com.hnguyen387.jpa_query.criterialAPI.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hnguyen387.jpa_query.criterialAPI.globalexceptions.CannotDeleteException;
import com.hnguyen387.jpa_query.criterialAPI.globalexceptions.CannotUpdateException;
import com.hnguyen387.jpa_query.criterialAPI.models.Employee;
import com.hnguyen387.jpa_query.criterialAPI.repository.EmployeeDao;
import com.hnguyen387.jpa_query.criterialAPI.utils.RequestUpdateDto;
import com.hnguyen387.jpa_query.criterialAPI.utils.SearchDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeDaoImpl implements EmployeeDao{
	@Autowired
	private EntityManager entityManager;
	
	private CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
	@Override
	public List<Employee> findBy(String property, Object value) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> employee = cq.from(Employee.class);
		Predicate predicate = null;
		if (value instanceof Number) {
			predicate = cb.equal(employee.get(property), value);
		} else {
			predicate = cb.like(employee.get(property), "%"+value+"%");
		}
		cq.where(predicate);
		TypedQuery<Employee> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public int updateEmployee(RequestUpdateDto dto) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaUpdate<Employee> criteriaUpdate = cb.createCriteriaUpdate(Employee.class);
		Root<Employee> root = criteriaUpdate.from(Employee.class);
		SearchDto updateValue = dto.updateValue;
		SearchDto updateField = dto.updateField;
		criteriaUpdate.set(root.get(updateValue.fieldName), updateValue.value);
		criteriaUpdate.where(cb.equal(root.get(updateField.fieldName), updateField.value));
		try {
			return entityManager.createQuery(criteriaUpdate).executeUpdate();
		} catch (Exception e) {
			Throwable rootCause = e.getCause();
			if (rootCause != null) {
				throw new CannotUpdateException("Field to update Employee with reason: " + rootCause.getMessage());
			} else {
				throw new CannotUpdateException("Field to update Employee with reason: " + e.getMessage());
			}
			
		}
	}
	@Override
	public int deleteBy(SearchDto dto) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaDelete<Employee> criteriaDelete = cb.createCriteriaDelete(Employee.class);
		Root<Employee> root = criteriaDelete.from(Employee.class);
		//criteriaDelete.where(cb.equal(root.get(dto.fieldName), dto.value));
		try {
			Predicate predicate = cb.like(root.get(dto.fieldName), "%"+dto.value+"%");
			criteriaDelete.where(predicate);
			return entityManager.createQuery(criteriaDelete).executeUpdate();
		} catch (Exception e) {
			Throwable rootCause = e.getCause();
			if (rootCause != null) {
				throw new CannotDeleteException("Field to delete Employee with reason: " + rootCause.getMessage());
			} else {
				throw new CannotDeleteException("Field to update Employee with reason: " + e.getMessage());
			}
		}
	}

}
