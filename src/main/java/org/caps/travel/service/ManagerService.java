package org.caps.travel.service;

import org.caps.travel.entity.Manager;

import java.util.List;

public interface ManagerService {
     int insertManager(Manager manager);

     int deleteManager(String id);

     List<Manager> getAllManager(String name);

     Manager confirmManager(String name,String password);
}
