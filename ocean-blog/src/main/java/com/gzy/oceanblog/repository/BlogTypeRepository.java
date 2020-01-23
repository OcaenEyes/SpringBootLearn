package com.gzy.oceanblog.repository;

import com.gzy.oceanblog.entity.BlogType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTypeRepository extends JpaRepository<BlogType,Long> {

    BlogType getTypeByName(String name);

    @Query("SELECT t FROM BlogType t")
    List<BlogType> findTop(Pageable pageable);
}
