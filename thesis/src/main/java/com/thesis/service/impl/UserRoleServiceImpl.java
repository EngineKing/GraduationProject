package com.thesis.service.impl;

import com.thesis.dao.UserRoleDao;
import com.thesis.form.UserRoleForm;
import com.thesis.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author LeiPeng
 * @Date 2020/2/9 - 16:34
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Integer> getByUserId(Integer id) {
        return userRoleDao.getByUserId(id);
    }

    @Override
    public void assign(UserRoleForm userRoleForm) {
        List<Integer> foundRoleIds = userRoleDao.getByUserId(userRoleForm.getUserId());
        List<Integer> newRoleIds = Arrays.asList(userRoleForm.getRoleIds());
        Set<Integer> set = new HashSet<>();
        for(Integer roleId : foundRoleIds){
            if (set.contains(roleId)){
                set.remove(roleId);
            }else {
                set.add(roleId);
            }
        }
        for(Integer roleId : newRoleIds){
            if (set.contains(roleId)){
                set.remove(roleId);
            }else {
                set.add(roleId);
            }
        }
        for (Integer roleId : set) {
            if (foundRoleIds.size() > newRoleIds.size()) {
                userRoleDao.delete(userRoleForm.getUserId(), roleId);
            } else {
                userRoleDao.add(userRoleForm.getUserId(), roleId);
            }
        }
    }
}
