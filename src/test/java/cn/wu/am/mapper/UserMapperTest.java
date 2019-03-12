package cn.wu.am.mapper;

import cn.wu.am.dao.UserMapper;
import cn.wu.am.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        String username = "admin";
        String password = "admin123";
        User user = userMapper.findByUsernameAndPassword(username, password);
        System.out.println(userMapper);
        System.out.println(user);
    }
}
