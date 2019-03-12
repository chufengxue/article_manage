package cn.wu.am.service;

import cn.wu.am.dao.UserMapper;
import cn.wu.am.entity.User;
import cn.wu.am.param.PageBean;
import cn.wu.am.param.QueryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findUserById(int id) {
        return userMapper.findById(id);
    }

    public boolean register(User user) {
        return userMapper.addUser(user) > 0;
    }

    public PageBean<User> getAll(QueryInfo queryInfo) {
        List<User> data = userMapper.getAll(queryInfo.getStartindex(), queryInfo.getPagesize());
        int totalRecord = userMapper.getCount();

        PageBean<User> pageBean = new PageBean<>();
        pageBean.setData(data);
        pageBean.setTotalrecord(totalRecord);
        pageBean.setCurrentpage(queryInfo.getCurrentpage());
        pageBean.setPagesize(queryInfo.getPagesize());
        return pageBean;
    }

    public boolean updateStatus(int id, int status) {
        return userMapper.updateStatus(id, status)>0;
    }

    public User findUser(int id, String password) {
        return userMapper.findByIdAndPassword(id, password);
    }

    public boolean updatePassword(int id, String password) {
        return userMapper.updatePassword(id, password) > 0;
    }

}
