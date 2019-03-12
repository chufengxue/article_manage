package cn.wu.am.dao;

import cn.wu.am.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int addUser(User user);

    List<User> findAll();

    List<User> getAll(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    User findById(Integer id);

    User findByUsername(String username);

    User findByIdAndPassword(@Param("id")Integer id, @Param("password")String password);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);

    int updateStatus(@Param("id") Integer id, @Param("status") int status);

    /**
     * 统计有多少用户
     * @return
     */
    int getCount();
}
