package org.caps.travel.service;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.User;
import org.caps.travel.utils.Page;

/**
 * <p>Title: userService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/22 13:37
 */
public interface UserService {
    public User confirmUser(User user);

    public int userRegister(User user);

    public User showUserDetail(User user);

    public int updateUserInfo(User user);

    public int active(String activeCode);

    public int checkUsername(String username);

    public Page<User> selectPageByQueryVo(QueryVo vo);

    public void updateStateById(String id);
}
