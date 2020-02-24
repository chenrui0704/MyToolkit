package pers.cr.toolkit.util;

import java.util.List;
import java.util.Map;

/**
 * 验证工具类
 *
 * @date 2020-02-24
 * @author Cr
 * */
public class VerifyUtil {

    public static boolean verifyStringIsEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean verifyListIsEmpty(List list){
        return null == list || 0 == list.size();
    }

    public static boolean verifyMapIsEmpty(Map map){
        return null == map || 0 == map.size();
    }

    /**
     *  目前支持 map, list , string 空值验证
     *  其余类型支持null值验证
     *
     * @date 2020-02-24
     * @author cr
     * */
    public static boolean verifyIsEmpty(Object o){
        if(null == o){
            return true;
        }
        if(o instanceof Map){
            return verifyMapIsEmpty((Map) o);
        }else if(o instanceof List){
            return verifyListIsEmpty((List) o);
        }else if(o instanceof String){
            return verifyStringIsEmpty((String) o);
        }
        return false;
    }

}
