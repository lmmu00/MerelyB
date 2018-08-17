package com.merelyb.utils.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.database
 * @作者: LiM
 * @创建时间: 2018-08-06 14:30
 * @Description: ${Description}
 */
public class ConvertToSQLStrUtils {

    public String ConvertToSQL(Byte bValue){
        if(bValue == null){
            return "NULL";
        }
        return String.valueOf(bValue);
    }

    public String ConvertToSQL(Short sValue){
        if(sValue == null){
            return "NULL";
        }
        return String.valueOf(sValue);
    }

    public String ConvertToSQL(Integer iValue){
        if(iValue == null){
            return "NULL";
        }
        return String.valueOf(iValue);
    }

    public String ConvertToSQL(Long lValue){
        if(lValue == null){
            return "NULL";
        }
        return String.valueOf(lValue);
    }

    public String ConvertToSQL(Float fValue){
        if(fValue == null){
            return "NULL";
        }
        return String.valueOf(fValue);
    }

    public String ConvertToSQL(Double dValue){
        if(dValue == null){
            return "NULL";
        }
        return String.valueOf(dValue);
    }

    public String ConvertToSQL(String strValue){
        if(strValue == null){
            return "NULL";
        }
        return "'" + strValue + "'";
    }

    public String ConvertToSQL(Date dtValue){
        if(dtValue == null){
            return "NULL";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  "'" + dateFormat.format(dtValue) + "'";
    }

    public String ConvertToSQL(Date dtValue, String sFormat){
        if(dtValue == null){
            return "NULL";
        }
        DateFormat dateFormat = new SimpleDateFormat(sFormat);
        return  "'" + dateFormat.format(dtValue) + "'";
    }

}
