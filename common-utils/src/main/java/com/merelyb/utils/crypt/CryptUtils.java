package com.merelyb.utils.crypt;

import com.merelyb.utils.calendar.ChineseEra;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.crypt
 * @作者: LiM
 * @创建时间: 2018-08-07 16:45
 * @Description: ${Description}
 */
public class CryptUtils {

    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 加密
     * @param strSource
     * @param dtDate
     * @return
     * @throws Exception
     */
    public String Encrypt(String strSource, Date dtDate) throws Exception {
        String sTime = dateFormat.format(dtDate);
        ChineseEra chineseEra = new ChineseEra();
        String sDay = chineseEra.eightCharactersOfDate(dtDate);
        String sChina = new String(Base64.encodeBase64(sDay.getBytes("UTF-8")));
        String sPwd = new String(Base64.encodeBase64(strSource.getBytes("UTF-8")));
        char[] cTime = sTime.toCharArray();
        char[] cPwd = sPwd.toCharArray();
        char[] cChina = sChina.toCharArray();
        if(cPwd.length > 10) {
            for (int i = 0; i < cTime.length; i++) {
                int iIndex = Integer.parseInt(String.valueOf(cTime[i]));
                if (iIndex > 0) {
                    cPwd[iIndex] = (char) (cPwd[iIndex] + cChina[i]);
                }
            }
            int iLength = cPwd.length - 1;
            for (int i = 0; i < cTime.length; i++) {
                int iIndex = Integer.parseInt(String.valueOf(cTime[i]));
                if (iIndex > 0) {
                    cPwd[iLength-iIndex] = (char) (cPwd[iLength-iIndex] + cChina[i]);
                }
            }
        }
        sPwd = new String(cPwd);
        return sPwd;
    }

    /**
     * 解密
     * @param strSource
     * @param dtDate
     * @return
     * @throws Exception
     */
    public String Decrypt(String strSource, Date dtDate) throws Exception {
        String sTime = dateFormat.format(dtDate);
        ChineseEra chineseEra = new ChineseEra();
        String sDay = chineseEra.eightCharactersOfDate(dtDate);
        String sChina = new String(Base64.encodeBase64(sDay.getBytes("UTF-8")));
        char[] cTime = sTime.toCharArray();
        char[] cChina = sChina.toCharArray();
        char[] cPwd = strSource.toCharArray();
        if(cPwd.length > 10) {
            for (int i = cTime.length - 1; i > -1; i--) {
                int iIndex = Integer.parseInt(String.valueOf(cTime[i]));
                if (iIndex > 0) {
                    cPwd[iIndex] = (char) (cPwd[iIndex] - cChina[i]);
                }
            }
            int iLength = cPwd.length - 1;
            for (int i = cTime.length - 1; i > -1; i--) {
                int iIndex = Integer.parseInt(String.valueOf(cTime[i]));
                if (iIndex > 0) {
                    cPwd[iLength-iIndex] = (char) (cPwd[iLength-iIndex] - cChina[i]);
                }
            }
        }
        String sPwd = new String(cPwd);
        sPwd = new String(Base64.decodeBase64(sPwd.getBytes("UTF-8")));
        return sPwd;
    }

    /***
     * BASE64 加密
     * @param sSource
     * @return
     * @throws Exception
     */
    public String EncryptBase64(String sSource) throws Exception {
        byte[] bData = null;
        String sReturn = null;
        bData = sSource.getBytes("utf-8");
        if (bData != null) {
            sReturn = new BASE64Encoder().encode(bData);
        }
        return sReturn;
    }

    /**
     * BASE64 解密
     * @param sSource
     * @return
     * @throws Exception
     */
    public  String DecryptBase64(String sSource) throws Exception {
        byte[] bData = null;
        String sReturn = null;
        if (sSource != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            bData = decoder.decodeBuffer(sSource);
            sReturn = new String(bData, "utf-8");
        }
        return sReturn;
    }
}
