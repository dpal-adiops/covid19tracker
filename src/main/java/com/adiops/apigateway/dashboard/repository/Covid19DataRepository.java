package com.adiops.apigateway.dashboard.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adiops.apigateway.dashboard.entity.Covid19DataEntity;

/**
 * Covid19 Data JPA repository interface.
 * @author Deepak Pal
 *
 */
@Repository
public interface Covid19DataRepository extends JpaRepository<Covid19DataEntity, Long>{

	@Query(value = "SELECT distinct(state) FROM Covid19Data",nativeQuery = true)
	List<String> retriveStateList();
	 
	@Query("select a from Covid19Data a where a.state= :state and a.recordDate in :recordDates")
	List<Covid19DataEntity> findAllWithRecordDate(@Param("state") String state,@Param("recordDates") Collection<Date> recordDates);

	Covid19DataEntity findFirstByStateOrderByRecordDateDesc(@Param("state") String state);

}
