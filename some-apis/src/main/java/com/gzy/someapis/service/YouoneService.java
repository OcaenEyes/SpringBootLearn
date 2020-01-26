package com.gzy.someapis.service;

import com.gzy.someapis.entity.YouoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface YouoneService {

    Page<YouoneEntity> listYouone(Pageable pageable);

    Page<YouoneEntity> getPage(Integer pageNum ,Integer pageLimit);

    Page<YouoneEntity> getPageSort(Integer pageNum ,Integer pageLimit);
}
