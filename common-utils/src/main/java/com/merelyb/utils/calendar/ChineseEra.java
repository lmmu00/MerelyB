package com.merelyb.utils.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.calendar
 * @作者: LiM
 * @创建时间: 2018-08-07 20:50
 * @Description: ${Description}
 */
public class ChineseEra {

    private int[] lunarInfo = new int[]{0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260,
            0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6,
            0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0,
            0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54,
            0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50,
            0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0,
            0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0,
            0x0a5b0, 0x14573, 0x052b0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6,
            0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950,
            0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4,
            0x0d250, 0x0d558, 0x0b540, 0x0b6a0, 0x195a6, 0x095b0, 0x049b0,
            0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60,
            0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58,
            0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0,
            0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0,
            0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52,
            0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0,
            0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250,
            0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577,
            0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0, 0x14b63};
    //天干常量
    private String[] HeavenlyStem = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    //地支常量
    private String[] GroundBranch = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    //立春时间
    private long[] lSpringInfo = new long[]{0, 21208, 42467, 63836, 85337, 107014,
            128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989,
            308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224,
            483532, 504758};


    /**
     * 获取天干地支
     *
     * @param iIndex
     * @return
     */
    private String getStemBranch(int iIndex) {
        return (HeavenlyStem[iIndex % 10] + GroundBranch[iIndex % 12]);
    }

    /**
     * 获取立春时间
     * @param iYear
     * @param n
     * @return
     */
    private int getSpringDay(int iYear, int n) {
        long times = 31556925974l * (iYear - 1900) + lSpringInfo[n] * 60000l + (long) 0.7 * (iYear - 1900);
        Date offDate = new Date(times - 2208549300000l);
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        cal.setTime(offDate);
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(cal.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(cal.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(cal.MILLISECOND, -(zoneOffset + dstOffset));
        // 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
        return (cal.get(Calendar.DATE));
    }

    /**
     * 返回 小时对应的 支的索引
     *
     * @param iHour
     * @return
     */
    private int getHourBranch(int iHour) {
        if (iHour >= 23 || iHour < 1)
            return 0;
        else if (iHour >= 1 && iHour < 3)
            return 1;
        else if (iHour >= 3 && iHour < 5)
            return 2;
        else if (iHour >= 5 && iHour < 7)
            return 3;
        else if (iHour >= 7 && iHour < 9)
            return 4;
        else if (iHour >= 9 && iHour < 11)
            return 5;
        else if (iHour >= 11 && iHour < 13)
            return 6;
        else if (iHour >= 13 && iHour < 15)
            return 7;
        else if (iHour >= 15 && iHour < 17)
            return 8;
        else if (iHour >= 17 && iHour < 19)
            return 9;
        else if (iHour >= 19 && iHour < 21)
            return 10;
        else if (iHour >= 21 && iHour < 23)
            return 11;
        return 0;
    }

    /**
     * 根据 日干 推算 时柱 根据提供的推算图来计算
     *
     * @param sStem
     * @param iHour
     * @return
     */
    private int getHourStem(String sStem, int iHour) {
        int iIndex = 1;
        for (String sHStem : HeavenlyStem) {
            if (sHStem.equals(sStem)) {
                break;
            }
            iIndex++;
        }
        iIndex = iIndex % 5; // 五个为一周期
        int iHourIndex = getHourBranch(iHour);
        if (iHourIndex > 10)
            return iHourIndex - 10 + (iIndex - 1) * 2;
        else {
            iHourIndex = iHourIndex + (iIndex - 1) * 2;
            return iHourIndex >= 10 ? iHourIndex % 10 : iHourIndex;
        }
    }

    /**
     *
     * @param iYear
     * @param iMonth
     * @return 获取UTC时间
     */
    private Long getUTCTime(int iYear, int iMonth){
        Calendar calUTC = Calendar.getInstance();
        calUTC.set(iYear, iMonth, 1, 0, 0, 0);
        // 2、取得时间偏移量：
        int zoneOffset = calUTC.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = calUTC.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        calUTC.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        return calUTC.getTimeInMillis();

    }

    /**
     * 根据日期获取八字
     * @param dtDate
     * @return
     */
    public String eightCharactersOfDate(Date dtDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dtDate);
        int iYear = calendar.get(Calendar.YEAR), iMonth = calendar.get(Calendar.MONTH),
                iDay = calendar.get(Calendar.DATE) - 1, iHour = calendar.get(Calendar.HOUR_OF_DAY);;
        String sYear = "", sMonth = "", sDay = "", sHour = "";

        if (iMonth < 2) {
            sYear = getStemBranch(iYear - 1900 + 36 - 1);
        } else {
            sYear = getStemBranch(iYear - 1900 + 36);
        }
        int iSpringDate = getSpringDay(iYear, 2); // 立春日期
        // 月柱 1900年1月小寒以前为 丙子月(60进制12)
        int firstNode = getSpringDay(iYear, iMonth * 2); // 返回当月「节」为几日开始
        sMonth = getStemBranch((iYear - 1900) * 12 + iMonth + 12);
        //当月一日与 1900/1/1 相差天数
        //1900/1/1与 1970/1/1 相差25567日, 1900/1/1 日柱为甲戌日(60进制10)
        int dayCyclical = Integer.parseInt(String.valueOf(getUTCTime(iYear, iMonth)/ 86400000 + 25567 + 10));
        // 依节气调整二月分的年柱, 以立春为界
        if (iMonth == 1 && (iDay + 1) >= iSpringDate)
            sYear= getStemBranch(iYear - 1900 + 36);
        // 依节气月柱, 以「节」为界
        if ((iDay + 1) >= firstNode)
            sMonth = getStemBranch((iYear - 1900) * 12 + iMonth + 13);
        // 日柱
        sDay = getStemBranch(dayCyclical + iDay);

        // 时柱
        sHour = HeavenlyStem[getHourStem(sDay.substring(0, 1), iHour)] + GroundBranch[getHourBranch(iHour)];

        return  sYear + sMonth + sDay + sHour;
    }

}
