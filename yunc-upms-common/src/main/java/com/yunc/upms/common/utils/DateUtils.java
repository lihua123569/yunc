package com.yunc.upms.common.utils;

import com.liebao.lb7881.common.excepiton.AppException;
import com.yunc.upms.common.enums.DateFormatEnum;
import com.yunc.upms.common.enums.ErrorCodeEnum;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 14031966
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils{
    /**
     * 禁止实例化
     */
    private DateUtils(){};
    
    /**
     * 日期格式化
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param date
     * @param DateFormatEnum:日期格式枚举类，大写
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String format(Date date,DateFormatEnum DateFormatEnum){
        
        return DateFormatUtils.format(date, DateFormatEnum.getFormat());
    }
    /**
     * 重载format，字符串形式的格式转换之后，字符串输出
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param date
     * @param DateFormatEnum
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String format(String date,DateFormatEnum DateFormatEnum){
        
        return format(parseDate(date), DateFormatEnum);
    }
    /**
     * 字符串日期转换
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param date
     * @param DateFormatEnum
     * @return
     * @throws ParseException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date parseDate(String date,DateFormatEnum DateFormatEnum) {
        
        try {
            
            return parseDate(date, new String[]{ DateFormatEnum.getFormat() });
            
        } catch (ParseException e) {
            
            throw new AppException(ErrorCodeEnum.DATE_PARSE_EXCEPTION);
        }
    }
    
    /**
     * 字符串转日期，其中字符串的格式需在枚举中
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param date
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date parseDate(String date){
        
        try {
            
            return parseDate(date, DateFormatEnum.getFormats());
            
        } catch (ParseException e) {
            
            throw new AppException(ErrorCodeEnum.DATE_PARSE_EXCEPTION);
        }
    }
    
    /**
     * 
     * 计算时间差（天数）
     * 〈功能详细描述〉
     *
     * @param date1
     * @param date2
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static long calculateIntervalDays(Date date1,Date date2){
        
        date1 = parseDate(format(date1, DateFormatEnum.yyyy_MM_dd));
        
        date2 = parseDate(format(date2, DateFormatEnum.yyyy_MM_dd));
        
        long intervals = Math.abs(date1.getTime()-date2.getTime());
        
        return intervals/MILLIS_PER_DAY;
    }
    
    
}
