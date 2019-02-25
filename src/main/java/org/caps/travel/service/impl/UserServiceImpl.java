package org.caps.travel.service.impl;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.User;
import org.caps.travel.mapper.UserMapper;
import org.caps.travel.service.UserService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * <p>Title: userService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/22 13:36
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Value("${pageSize}")
    private Integer pageSize;


    @Override
    public User confirmUser(User user) {
        user.setState(1);
        return userMapper.selectOne(user);
    }

    @Override
    public int userRegister(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User showUserDetail(User user) {
        user.setState(1);
        return userMapper.selectOne(user);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int active(String activeCode) {
        User user=new User();
        user.setState(1);
        Example example=new Example(User.class);
        example.createCriteria().andEqualTo("code",activeCode);
        return userMapper.updateByExampleSelective(user,example);
        /*相当于：update user set state='1' where code=#{}*/
    }

    @Override
    public int checkUsername(String username) {
        Example example=new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        return userMapper.selectCountByExample(example);
    }

    @Override
    public Page<User> selectPageByQueryVo(QueryVo vo) {
        Page<User> page=new Page<>();
        //每页数
        page.setSize(this.pageSize);
        vo.setSize(this.pageSize);
        if (null != vo) {
            if(null != vo.getName() && !"".equals(vo.getName().trim())){
                vo.setName(vo.getName().trim());
            }
            // 判断当前页
            if (null != vo.getPage()) {
                page.setPage(vo.getPage());
                vo.setStartRow((vo.getPage() -1)*vo.getSize());
            }

            //总条数
            Integer totalCount  = userMapper.customerCountByQueryVo(vo);
            page.setTotal(totalCount );
            //总页数
            Integer totalPage=(totalCount + page.getSize() - 1) / page.getSize();
            page.setTotalPage(totalPage);
            //总条数
            page.setRows(userMapper.selectCustomerListByQueryVo(vo));
        }
        return page;
    }

    @Override
    public void updateStateById(String id) {
        User user=new User();
        user.setState(2);
        Example example=new Example(User.class);
        example.createCriteria().andEqualTo("userid",id);
        userMapper.updateByExampleSelective(user,example);
        /*相当于：update user set state='2' where userid=#{}*/
    }
}
