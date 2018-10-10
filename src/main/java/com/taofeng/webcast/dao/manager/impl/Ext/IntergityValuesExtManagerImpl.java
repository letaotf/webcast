package com.taofeng.webcast.dao.manager.impl.Ext;

import com.taofeng.webcast.common.DTO.IntergitValuesDTO;
import com.taofeng.webcast.dao.domain.IntergityValuesDO;
import com.taofeng.webcast.dao.manager.Ext.IIntergityValuesExtManager;
import com.taofeng.webcast.dao.mapper.ext.IntergityValuesExtMapper;
import com.taofeng.webcast.dao.query.IntergityValuesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>诚信值sql语句</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/7 上午9:54
 * @since V1.0
 */
@Component
public class IntergityValuesExtManagerImpl implements IIntergityValuesExtManager {

    @Autowired
    private IntergityValuesExtMapper intergityValuesExtMapper;

    /**
     * 根据条件进行查询
     * @param query
     * @return
     */
    @Override
    public List<IntergityValuesDO> selectByQuery(IntergityValuesQuery query) {
        return intergityValuesExtMapper.selectByQuery(query);
    }

    /**
     * 查询出来的数量
     * @param query
     * @return
     */
    @Override
    public Integer countByQuery(IntergityValuesQuery query) {
        return intergityValuesExtMapper.countByQuery(query);
    }

    /**
     * 更新语句，条件可以为空
     * @param record
     * @param query
     * @return
     */
    @Override
    public Integer updateByQuerySelective(IntergityValuesDO record, IntergityValuesQuery query) {
        return intergityValuesExtMapper.updateByQuerySelective(record,query);
    }


    /**
     * 根据条件查询 (查询出详情)
     * @param query
     * @return
     */
    @Override
    public List<IntergitValuesDTO> selectIntergityDetailsByQuery(IntergityValuesQuery query) {
        return intergityValuesExtMapper.selectIntergityDetailsByQuery(query);
    }
}
