package com.barista.service;

import com.barista.entity.Service;

import java.util.List;

/**
 * @ClassName ServiceService
 * @Author zhaoth
 * @Date 2019/8/29 17:26
 * @Version 1.0
 */
public interface ServiceService {

    /**
     * 通过任意字段查找完全符合的Service
     *
     * @param service 携带字段的载体
     * @return com.barista.entity.Service
     * @author zhaoth
     */
    List<Service> selectByExistField(Service service);

    /**
     * 业务账号总数
     *
     * @return int
     * @author zhaoth
     */
    int selectCount();

    /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return java.util.List<com.barista.entity.Service>
     * @author zhaoth
     */
    List<Service> selectPaging(Integer currentPage, Integer pageSize);

    /**
     * 通过OS账号，服务器IP，身份证，业务账号状态做筛选后的业务账号数量
     *
     * @param osUsername      OS账号
     * @param unixHost        服务器IP
     * @param accountIdcardNo 身份证
     * @param serviceStatus   业务账号状态
     * @return int
     * @author zhaoth
     */
    int selectCountFilter(String osUsername
            , String unixHost, String accountIdcardNo, String serviceStatus);

    /**
     * 有筛选条件的分页查询
     *
     * @param currentPage     当前页
     * @param pageSize        每页条数
     * @param osUsername      OS账号
     * @param unixHost        服务器IP
     * @param accountIdcardNo 身份证
     * @param serviceStatus   业务账号状态
     * @return java.util.List<com.barista.entity.Service>
     * @author zhaoth
     */
    List<Service> selectPagingFilter(Integer currentPage, Integer pageSize, String osUsername
            , String unixHost, String accountIdcardNo, String serviceStatus);

    /**
     * 新增业务账号
     * @param service 业务账号
     * @return int
     * @author zhaoth
     */
    int insertService(Service service);

    /**
     * 开通业务账号，附属账务账号不是开通状态的话不能开通
     * @param serviceId 业务账号id
     * @return int
     * @author zhaoth
     */
    int startUsing(Integer serviceId);

    /**
     * 暂停业务账号
     * @param serviceId 业务账号id
     * @return int
     * @author zhaoth
     */
    int pauseUsing(Integer serviceId);

    /**
     * 删除业务账号
     * @param serviceId 业务账号id
     * @return int
     * @author zhaoth
     */
    int deleteService(Integer serviceId);

    /**
     * 修改业务账号，目前只修改了使用的资费，批量修改
     * @param serviceList 修改记录
     * @return int
     * @author zhaoth
     */
    int updateAllById(List<Service> serviceList);

    int updateService(Service service);

}
