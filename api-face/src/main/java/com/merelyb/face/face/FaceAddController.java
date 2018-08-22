package com.merelyb.face.face;

import com.merelyb.bean.ResultBean;
import com.merelyb.bean.face.*;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.utils.CommonUtils;
import com.merelyb.utils.httpclient.HttpClientResult;
import com.merelyb.utils.httpclient.HttpClientUtils;
import com.merelyb.utils.json.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @项目: Merelyb
 * @包: com.merelyb.face.face
 * @作者: LiM
 * @创建时间: 2018-08-21 13:54
 * @Description: ${Description}
 */
@Controller
@RequestMapping("face")
public class FaceAddController {

    //Face++应用Key
    private String apiKey = "nRPkBCRetYyVtz5tyL282Ew5-6z-esGZ";
    //Face++应用Secret
    private String apiSecret = "hI4fH210IDiHPROU7PMHo-OJ4rh0kIdv";

    private Logger logger = LogManager.getLogger(this.getClass());

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取车牌信息
     *
     * @param strData
     * @param resultBean
     */
    private void getPlateLicenseInfo(String strData, ResultBean resultBean) {
        PlateLicenseResult plateLicenseResult = JsonUtils.json2Obj(strData, PlateLicenseResult.class);
        if (plateLicenseResult.getError_message() != null) {
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_PLATELICENSEINFO);
            resultBean.setData("");
            resultBean.setMsg(plateLicenseResult.getError_message());
        } else {
            if (plateLicenseResult.getResults().size() > 0) {
                List<PlateLicenseReturn> plateLicenseReturnList = new ArrayList<>();
                for (int i = 0; i < plateLicenseResult.getResults().size(); i++) {
                    PlateLicense plateLicense = plateLicenseResult.getResults().get(i);
                    PlateLicenseReturn plateLicenseReturn = new PlateLicenseReturn();
                    plateLicenseReturn.setPlateLicenseColor(plateLicense.getColor());
                    plateLicenseReturn.setPlateLicenseNum(plateLicense.getLicense_plate_number());
                    plateLicenseReturn.setPlateLicenseLoc("(" + String.valueOf(plateLicense.getBound().getLeft_top().getX()) + ", "
                            + String.valueOf(plateLicense.getBound().getLeft_top().getY()) + ")"
                            + "(" + String.valueOf(plateLicense.getBound().getRight_top().getX()) + ", "
                            + String.valueOf(plateLicense.getBound().getRight_top().getY()) + ")"
                            + "(" + String.valueOf(plateLicense.getBound().getRight_bottom().getX()) + ", "
                            + String.valueOf(plateLicense.getBound().getRight_bottom().getY()) + ")"
                            + "(" + String.valueOf(plateLicense.getBound().getLeft_bottom().getX()) + ", "
                            + String.valueOf(plateLicense.getBound().getLeft_bottom().getY()) + ")");
                    plateLicenseReturnList.add(plateLicenseReturn);
                }
                resultBean.setStatus(true);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_PLATELICENSEINFO);
                resultBean.setData(JsonUtils.objs2Json(plateLicenseReturnList));
                resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
            } else {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_PLATELICENSEINFO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_QUERY_NO_DATA);
            }
        }
    }

    /**
     * 获取身份证信息
     * @param strData
     * @param resultBean
     * @throws ParseException
     */
    private void getIDCardInfo(String strData, ResultBean resultBean) throws ParseException {
        String sFormatData = strData.replaceAll("Temporary ID Photo", "Temporary_ID_Photo")
                .replaceAll("ID Photo", "ID_Photo");
        IDCardResult idCardResult = JsonUtils.json2Obj(sFormatData, IDCardResult.class);
        if (idCardResult.getError_message() != null) {
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_IDCARDINFO);
            resultBean.setData("");
            resultBean.setMsg(idCardResult.getError_message());
        } else {
            if (idCardResult.getCards().size() > 0) {
                List<IDCardReturn> idCardReturnList = new ArrayList<>();
                for (int i = 0; i < idCardResult.getCards().size(); i++) {
                    IDCardInfo idCardInfo = idCardResult.getCards().get(i);
                    IDCardReturn idCardReturn = new IDCardReturn();
                    idCardReturn.setName(idCardInfo.getName());
                    idCardReturn.setSex(idCardInfo.getGender().equals("男") ? 0 : 1);
                    idCardReturn.setNation(idCardInfo.getRace());
                    idCardReturn.setBirth(simpleDateFormat.parse(idCardInfo.getBirthday()));
                    idCardReturn.setAddress(idCardInfo.getAddress());
                    idCardReturn.setCardNum(idCardInfo.getId_card_number());
                    idCardReturn.setOffice(idCardInfo.getIssued_by());
                    idCardReturn.setEffective(idCardInfo.getValid_date());
                    idCardReturnList.add(idCardReturn);
                }
                resultBean.setStatus(true);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_IDCARDINFO);
                resultBean.setData(JsonUtils.objs2Json(idCardReturnList));
                resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
            } else {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_IDCARDINFO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_QUERY_NO_DATA);
            }
        }
    }

    /**
     * 驾照信息
     * @param strData
     * @param resultBean
     * @throws Exception
     */
    private void getDriverLicenseInfo(String strData, ResultBean resultBean) throws Exception {
        String sFormatData = strData.replaceAll("class", "carType");
        DriverLicenseResult driverLicenseResult = JsonUtils.json2Obj(sFormatData, DriverLicenseResult.class);
        if(driverLicenseResult.getError_message() != null){
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_DRIVERLICENSEINFO);
            resultBean.setData("");
            resultBean.setMsg(driverLicenseResult.getError_message());
        }else {
            if(driverLicenseResult.getCards().size() > 0){
                List<DriverLicenseReturn> driverLicenseReturnList = new ArrayList<>();
                for (int i = 0; i < driverLicenseResult.getCards().size(); i++) {
                    DriverLicenseInfo driverLicenseInfo = driverLicenseResult.getCards().get(i);
                    DriverLicenseReturn driverLicenseReturn = new DriverLicenseReturn();
                    driverLicenseReturn.setAddress(driverLicenseInfo.getAddress());
                    driverLicenseReturn.setBirthday(simpleDateFormat.parse(driverLicenseInfo.getBirthday()));
                    driverLicenseReturn.setCarType(driverLicenseInfo.getCarType());
                    driverLicenseReturn.setCountry(driverLicenseInfo.getNationality());
                    driverLicenseReturn.setDlNum(driverLicenseInfo.getLicense_number());
                    driverLicenseReturn.setEffectiveNum(driverLicenseInfo.getValid_for());
                    driverLicenseReturn.setEffectiveStart(simpleDateFormat.parse(driverLicenseInfo.getValid_from()));
                    driverLicenseReturn.setEffectiveTime(driverLicenseInfo.getValid_date());
                    driverLicenseReturn.setFirstCheck(simpleDateFormat.parse(driverLicenseInfo.getIssue_date()));
                    driverLicenseReturn.setName(driverLicenseInfo.getName());
                    driverLicenseReturn.setOffice(driverLicenseInfo.getIssued_by());
                    driverLicenseReturn.setSex(driverLicenseInfo.getGender().equals("男")?0:1);
                    driverLicenseReturn.setVersion(driverLicenseInfo.getVersion());
                    driverLicenseReturnList.add(driverLicenseReturn);
                }
                resultBean.setStatus(true);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_DRIVERLICENSEINFO);
                resultBean.setData(JsonUtils.objs2Json(driverLicenseReturnList));
                resultBean.setMsg(ResultConstant.MSG_COMMON_SUCCESS);
            }else{
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_DRIVERLICENSEINFO);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_QUERY_NO_DATA);
            }
        }
    }

    /**
     * 识别图片信息
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "getImageInfo")
    @ResponseBody
    public ResultBean getInfoByImage(HttpServletRequest request) {
        ResultBean resultBean = new ResultBean();
        String sKey = request.getParameter("APIKey") != null ? request.getParameter("APIKey") : "";
        String sFile = request.getParameter("FileData") != null ? request.getParameter("FileData") : "";
        String sType = request.getParameter("sType") != null ? request.getParameter("sType") : "";

        logger.info("sKey: " + sKey + "  sFile: " + sFile + "  sType: " + sType);
        CommonUtils commonUtils = new CommonUtils();
        if (sKey.equals("") || sFile.equals("") || sType.equals("") || commonUtils.isNumeric(sType) == false) {
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_INFOBYIMAGE);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATA_NULL);
            logger.info(JsonUtils.obj2Json(resultBean));
            return resultBean;
        }

        String sUrl = "";
        switch (Integer.parseInt(sType)) {
            case 0: {
                sUrl = "https://api-cn.faceplusplus.com/imagepp/v1/licenseplate";
                break;
            }
            case 1: {
                sUrl = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";
                break;
            }
            case 2:{
                sUrl = "https://api-cn.faceplusplus.com/cardpp/v1/ocrdriverlicense";
                break;
            }

        }
        BasePara basePara = new BasePara();
        basePara.setApi_key(apiKey);
        basePara.setApi_secret(apiSecret);
        basePara.setImage_url("");
        basePara.setImage_file(null);
        basePara.setImage_base64(sFile);

        try {
            Map<String, Object> mapPara = null;
            mapPara = commonUtils.objectToMap(basePara);
            if(Integer.parseInt(sType) == 1){
                mapPara.put("legality", 1);
            }
            if (mapPara == null) {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_INFOBYIMAGE);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
                logger.info(JsonUtils.obj2Json(resultBean));
                return resultBean;
            }
            HttpClientUtils httpClientUtils = new HttpClientUtils();
            HttpClientResult httpClientResult = httpClientUtils.httpPostRequest(sUrl, mapPara);
            if (httpClientResult.isSuccessed()) {
                switch (Integer.parseInt(sType)) {
                    case 0: {
                        getPlateLicenseInfo(httpClientResult.getData(), resultBean);
                        break;
                    }
                    case 1: {
                        getIDCardInfo(httpClientResult.getData(), resultBean);
                        break;
                    }
                    case 2: {
                        getDriverLicenseInfo(httpClientResult.getData(), resultBean);
                        break;
                    }
                }

            } else {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_INFOBYIMAGE);
                resultBean.setData("");
                resultBean.setMsg(ResultConstant.MSG_COMMON_QUERY_NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_FACE + CodeConstant.CODE_FACE_INFOBYIMAGE);
            resultBean.setData("");
            resultBean.setMsg(ResultConstant.MSG_COMMON_DATABASE_ERE);
            logger.info(JsonUtils.obj2Json(resultBean));
            return resultBean;
        }
        logger.info(JsonUtils.obj2Json(resultBean));
        return resultBean;
    }


}
