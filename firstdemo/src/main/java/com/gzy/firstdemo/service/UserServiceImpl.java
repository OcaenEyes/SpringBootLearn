package com.gzy.firstdemo.service;

import com.gzy.firstdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Repository注解⽤用于标注数据访问组
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;


    @Override
    public int save(User user) {
        String sql ="INSERT INTO user (user_id, user_name, user_room_id ) VALUES(?,?,?)";
        return jdbcTemplate1.update(sql,user.getUserId(),user.getUserName(),user.getUserRoomId());
    }

    @Override
    public int updateUserName(User user) {
        String sql ="UPDATE user SET  user_name = ?  WHERE user_id = ?";
        return jdbcTemplate1.update(sql,user.getUserName(),user.getUserId());
    }

    @Override
    public int updateUserRoomId(User user) {
        String sql ="UPDATE user SET  user_room_id = ?  WHERE user_id = ?";
        return jdbcTemplate1.update(sql,user.getUserRoomId(),user.getUserId());
    }

    @Override
    public int delete(User user) {
        String sql ="DELETE FROM user  WHERE user_id = ? ";
        return jdbcTemplate1.update(sql,user.getUserId());
    }

    @Override
    public List<User> findAll() {
        String sql ="SELECT * FROM user";
        return jdbcTemplate1.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findById(Long id) {
        String sql ="SELECT * FROM user WHERE user_id = ?";
        return jdbcTemplate1.queryForObject(sql,new Object[] {id},new BeanPropertyRowMapper<>(User.class));
    }
}
