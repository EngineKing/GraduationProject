package com.thesis.dao;

import com.thesis.entity.Department;
import com.thesis.entity.ExpertGroup;
import com.thesis.form.DepartmentForm;
import com.thesis.form.ExpertGroupForm;
import com.thesis.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 17:24
 */
public interface ExpertGroupDao {
    void add(ExpertGroup expertGroup);

    ExpertGroup get(@Param("departmentId") Integer id);

    void delete(@Param("departmentId") Integer id);

    void update(ExpertGroup expertGroup);

    List<ExpertGroup> pageQuery(@Param("expertGroupForm") ExpertGroupForm expertGroupForm, @Param("query") Query query);

    int pageQueryCount(@Param("expertGroupForm") ExpertGroupForm expertGroupForm, @Param("query") Query query);

    ExpertGroup getByName(@Param("expertGroupName") String name);

    List<Integer> getLeaderIds();
}
