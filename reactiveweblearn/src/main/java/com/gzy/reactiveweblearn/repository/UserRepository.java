package com.gzy.reactiveweblearn.repository;


import com.gzy.reactiveweblearn.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@Repository
public class UserRepository {

    /**
     * 采用内存类型的存储方式 ->Map
     */
    private final ConcurrentMap<Integer,User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();



    /**
     * 保存用户对象
     * @param user
     * @return
     */
    public boolean save(User user){
        // id从1开始
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return  repository.put(id,user) == null;
    }

    /**
     *
     * @return 返回所有用户列表
     */
    public Collection<User> findAll() {
        return repository.values();
    }
}
