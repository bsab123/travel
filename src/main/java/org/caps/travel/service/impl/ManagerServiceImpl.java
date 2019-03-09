package org.caps.travel.service.impl;

import org.caps.travel.entity.Manager;
import org.caps.travel.mapper.ManagerMapper;
import org.caps.travel.service.FoodService;
import org.caps.travel.service.ManagerService;
import org.caps.travel.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public int insertManager(Manager manager) {
        return 0;
    }

    @Override
    public int deleteManager(String id) {
        return 0;
    }

    @Override
    public List<Manager> getAllManager(String name) {
        return null;
    }

    @Override
    public Manager confirmManager(String name, String password) {
        /*String md5password = MD5Utils.md5(password);*/
        Example example=new Example(Manager.class);
        example.createCriteria().andEqualTo("password",password)
                                .andEqualTo("name",name);
        Manager manager = managerMapper.selectOneByExample(example);
        if(manager !=null){
            return manager;
        }
        return null;
    }
}
