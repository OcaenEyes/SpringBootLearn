package com.gzy.oceanblog.repository;

import com.gzy.oceanblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    List<String> findGroupYear();

    List<Blog> findByYear(Integer year);

    List<Blog> findByYearAndMonth(Integer year,Integer month);

    List<Blog> findTop(Pageable pageable);
}
