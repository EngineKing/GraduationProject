package com.thesis.dao;

import com.thesis.entity.Annex;
import org.apache.ibatis.annotations.Param;

/**
 * @Author LeiPeng
 * @Date 2020/1/27 - 10:47
 */
public interface AnnexDao {
    Annex get(@Param("annexId") Integer id);

    void add(Annex annex);
}
