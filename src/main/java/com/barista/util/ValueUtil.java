package com.barista.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 提供值检测方法的工具类
 *
 * @ClassName ValueUtil
 * @Author zhaoth
 * @Date 2019/8/24 19:18
 * @Version 1.0
 */
public class ValueUtil {

    /**
     * 不进行递归，检查传入的对象本身是否为null，以及是否有值
     *
     * @param objects 任意对象
     * @return boolean
     * @author zhaoth
     */
    public static boolean haveNullOrBlack(Object... objects) {
        for (Object o : objects) {
            if (o == null) {
                return true;
            } else if (o instanceof String) {
                return StringUtils.isBlank((String) o);
            } else if (o.getClass().isArray()) {
                return ((Object[]) o).length == 0;
            } else if (o instanceof Collection) {
                return ((Collection) o).size() == 0;
            } else if (o instanceof Map) {
                return ((Map) o).size() == 0;
            }
        }
        return false;
    }
}
