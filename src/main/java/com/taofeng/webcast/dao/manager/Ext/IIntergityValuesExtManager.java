package com.taofeng.webcast.dao.manager.Ext;

import com.taofeng.webcast.common.DTO.IntergitValuesDTO;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.query.IntergityValuesQuery;

import java.util.List;

/**
 * <p>诚信值查询语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
public interface IIntergityValuesExtManager {

    /**
     * 根据条件进行查询
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
    Integer updateByQuerySelective(IntergityValuesDO record,IntergityValuesQuery query);

    /**
     * 根据条件查询 (查询出详情)
     * @param query
     * @return
     */
    List<IntergitValuesDTO> selectIntergityDetailsByQuery(IntergityValuesQuery query);
}
