package com.vikash.stationary.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vikash.stationary.entities.ProductStatus;
import com.vikash.stationary.entities.Status;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
	
	@Query(value = "select p from product_status p where p.order_id = ?1")
	List<ProductStatus> findAllByOrderId(Long order_id);
	
	@Query(value = "select p from product_status p where p.status = ?1")
	List<ProductStatus> findAllByStatus(Status status);
	
	@Query(value = "delete from product_status p where p.order_id = ?1")
	void deleteAllByOrderId(Long order_id);
	
}
