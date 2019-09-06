package com.barista.service.impl;

import com.barista.dao.ServiceUpdateMapper;
import com.barista.entity.ServiceUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 保存业务账号修改资费信息
 *
 * @ClassName serviceUpdateService
 * @Author zhaoth
 * @Date 2019/9/1 15:19
 * @Version 1.0
 */
@Service
public class ServiceUpdateServiceImpl {
    @Autowired
    private ServiceUpdateMapper serviceUpdateMapper;

    /**
     * 保存业务账号修改资费的信息
     *
     * @param serviceUpdate 保存了所有service中的索引字段
     * @return int
     * @author zhaoth
     */
    public int saveUpdateInfo(ServiceUpdate serviceUpdate) {
        return serviceUpdateMapper.updateOrInsertSelective(serviceUpdate);
    }

    /**
     * 批量获取修改记录，
     *
     * @param id        传入最小id，作为limit前的筛选条件
     * @param pageSize  每次返回的条数，如果少于此条数则说明已全部取出
     * @param timePoint 开始处理时的时间点，时间点之后新增的数据归下月处理
     * @return java.util.List<com.barista.entity.ServiceUpdate>
     * @author zhaoth
     */
    public List<ServiceUpdate> getHistoryUpdateInfo(Integer id, Integer pageSize, Date timePoint) {
        return serviceUpdateMapper.selectPaging(id, pageSize, timePoint);
    }

    /**
     * 删除已处理的历史数据
     *
     * @param lastId 最后一条被处理的数据id，删除<=id的行
     * @return int
     * @author zhaoth
     */
    public int deleteUsedUpdateInfo(Integer lastId) {
        return serviceUpdateMapper.deleteUsedUpdateInfo(lastId);
    }

}
