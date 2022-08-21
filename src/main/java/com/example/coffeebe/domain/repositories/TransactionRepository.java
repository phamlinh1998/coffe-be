package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Transaction;
import com.example.coffeebe.domain.entities.data.MonthRevenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT tr FROM Transaction tr WHERE tr.user.id = ?1")
    Page<Transaction> findAllByUser(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT tr FROM Transaction tr WHERE tr.user.id = ?1")
    List<Transaction> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT tr FROM Transaction tr WHERE :roleType is NULL or tr.user.role.name = :roleType")
    Page<Transaction> findAllByUserRole(@Param("roleType") String roleType, Pageable pageable);

    @Query("SELECT new com.example.coffeebe.domain.entities.data.MonthRevenue(month(tr.updated_at), sum(tr.amount)) FROM Transaction tr WHERE year(tr.updated_at) = ?1 group by month(tr.updated_at)")
    List<MonthRevenue> getRevenueInYear(int year);
}
