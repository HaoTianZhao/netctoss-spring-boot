package com.barista.service.schedule;

import com.barista.entity.Service;
import com.barista.entity.ServiceUpdate;
import com.barista.service.ServiceService;
import com.barista.service.impl.ServiceUpdateServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务
 *
 * @ClassName Schedule
 * @Author zhaoth
 * @Date 2019/8/30 17:01
 * @Version 1.0
 */
@Component
@EnableScheduling
public class ScheduleService {
    private Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ServiceUpdateServiceImpl serviceUpdateService;

    @Autowired
    private ServiceService serviceService;

    /**
     * 每月第一天0时0分0秒
     * 分批读取资费修改记录，更新业务账号表
     *
     * @author zhaoth
     */
    @Async
    @Scheduled(cron = "0 0 0 1 * ?")
    public void executeEveryMonthBegin() {
        logger.info("---------------------------");
        logger.info("异步定时任务，每月1号0时0分0秒执行。");
        long time = System.currentTimeMillis();
        int totalSize = 0;

        int lastId = 0;
        final int pageSize = 2;
        final Date splitPoint = new Date();
        int infoSize = 0;
        do {
            List<ServiceUpdate> infoList = serviceUpdateService.getHistoryUpdateInfo(lastId, pageSize, splitPoint);
            infoSize = infoList.size();
            logger.info("取出了" + infoSize + "条修改资费数据");
            if (infoSize == 0) {
                break;
            }
            lastId = infoList.get(infoSize - 1).getId();
            totalSize += infoSize;

            List<Service> serviceList = new ArrayList<>(infoSize);
            for (ServiceUpdate info : infoList) {
                Service service = new Service();
                service.setServiceId(info.getServiceId());
                service.setUnixHost(info.getUnixHost());
                service.setOsUsername(info.getOsUsername());
                service.setCostId(info.getCostId());
                serviceList.add(service);
            }
            int updateRoles = serviceService.updateAllById(serviceList);
            logger.info("修改了" + updateRoles + "条业务账号关联的资费数据");
        } while (infoSize == pageSize);
        int deleteRoles = serviceUpdateService.deleteUsedUpdateInfo(lastId);
        logger.info("删除了" + deleteRoles + "条业务账号关联的资费数据");
        logger.info("异步定时任务，执行完毕。总计" + totalSize + "条，耗时：" + (System.currentTimeMillis() - time) + "ms");
        logger.info("---------------------------\r\n");
    }

    /**
     * 每秒执行，打印日志
     */
        @Async
        @Scheduled(cron = "* * * * * *")
        void test() {
            logger.debug("异步任务的我被调度了，现在是:" + new Date());

            //        throw new NullPointerException();

        }

}
