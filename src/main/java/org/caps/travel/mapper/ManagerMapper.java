package org.caps.travel.mapper;

import org.caps.travel.entity.Manager;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
public interface ManagerMapper extends MyMapper<Manager> {
}