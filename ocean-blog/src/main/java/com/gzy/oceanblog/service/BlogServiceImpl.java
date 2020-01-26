package com.gzy.oceanblog.service;

import com.gzy.oceanblog.entity.Blog;
import com.gzy.oceanblog.entity.BlogQuery;
import com.gzy.oceanblog.entity.BlogType;
import com.gzy.oceanblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() ==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
            blog.setViews(blogRepository.getOne(blog.getId()).getViews());
            blog.setCreateTime(blogRepository.getOne(blog.getId()).getCreateTime());
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return null;
    }

    @Override
    public Blog getBlog(Long id) {
        return null;
    }

    @Override
    public Blog getAndConvert(Long id) {
        return null;
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blogQuery.getTitle()) && blogQuery.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blogQuery.getTitle()+"%"));
                }
                if (blogQuery.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<BlogType>get("type").get("id"),blogQuery.getTypeId()));
                }
//                if (blogQuery.getRecommend()){
//                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blogQuery.getRecommend()));
//                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return null;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlogByYear(Integer year) {
        return null;
    }
}
