package com.taofeng.webcast.dao.mapper.ext;

import com.taofeng.webcast.common.DTO.IntergitValuesDTO;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.query.IntergityValuesQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntergityValuesExtMapper {
    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<IntergityValuesDO> selectByQuery(IntergityValuesQuery query);

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    Integer countByQuery(IntergityValuesQuery query);

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    int updateByQuerySelective(@Param("record") IntergityValuesDO record, @Param("query") IntergityValuesQuery query);

    /**
     * 根据条件查询 (查询出详情)
     * @param query
     * @return
     */
    List<IntergitValuesDTO> selectIntergityDetailsByQuery(IntergityValuesQuery query);
}