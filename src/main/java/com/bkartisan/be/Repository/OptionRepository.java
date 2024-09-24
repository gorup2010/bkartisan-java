package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bkartisan.be.Entity.Option;
import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    List<Option> findByParentOptionOptionId(Integer parentOptionId);

    @Query(value = """
            SELECT optionName
            FROM options
            WHERE parentOptionId = (
                SELECT optionId
                FROM options
                WHERE optionName = ?1
            );
            """, nativeQuery = true)
    List<String> findNamesOfChildOptionsByOptionName(String optionName);
}