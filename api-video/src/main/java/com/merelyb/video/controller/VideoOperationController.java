package com.merelyb.video.controller;

import com.merelyb.bean.ResultBean;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.service.videosearch.YoukuVideoSearchService;
import com.merelyb.utils.json.JsonUtils;
import com.merelyb.video.bean.FilePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @项目: Merelyb
 * @包: com.merelyb.video
 * @作者: LiM
 * @创建时间: 2018-09-03 11:05
 * @Description: ${Description}
 */
@Controller
@RequestMapping("video")
public class VideoOperationController {

    @Autowired
    FilePath filePath;

    private Logger logger = LogManager.getLogger(this.getClass());


    /**
     * 生成文件名
     *
     * @return
     */
    private String getFileName() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    /**
     * 生成随机路径
     *
     * @param sFileName
     * @param sRootPath
     * @return
     */
    private String getRandomFilePath(String sFileName, String sRootPath) {
        int iFileHashCode = sFileName.hashCode();
        int iFDir = iFileHashCode & 0x0f;
        int iSDir = iFileHashCode & 0xff;
        String sDir = sRootPath + "\\" + String.valueOf(iFDir) + "\\" + String.valueOf(iSDir) + "\\";
        File fileRoot = new File(sDir);
        if (!fileRoot.exists()) {
            fileRoot.mkdirs();
        }
        return sDir;
    }

    /**
     * 上传文件
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "uploadJava")
    @ResponseBody
    public ResultBean getPostFile(HttpServletRequest request) {
        ResultBean resultBean = new ResultBean();
        //获取项目路径
        String strRoot = "";
        if (filePath.getCur().equals("0")) {
            strRoot = filePath.getWin();
        } else if (filePath.getCur().equals("1")) {
            strRoot = filePath.getLiu();
        } else {
            strRoot = request.getSession().getServletContext().getRealPath("");
        }

        String sRootPath = strRoot + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //检查form中是否有enctype="multipart/form-data"
        if (!multipartResolver.isMultipart(request)) {
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
            resultBean.setData("");
            resultBean.setCode(ResultConstant.MSG_VIDEO_UPLOADJAVA_FORM);
            return resultBean;
        }
        try {
            //将request变成多部分request
            Map<String, String> mapFile = new HashMap<>();
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            while (iterator.hasNext()) {
                logger.info("--------------文件传输开始-------------------");
                String sFileName = iterator.next();
                MultipartFile multipartFile = multipartHttpServletRequest.getFile(sFileName);
                if (multipartFile != null) {
                    String strFile = multipartFile.getOriginalFilename();
                    logger.info("FileName = " + strFile);
                    logger.info("FileSize = " + multipartFile.getSize());
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    strFile = strFile.substring(strFile.lastIndexOf("\\") + 1);
                    //得到上传文件的扩展名
                    String fileExtName = strFile.substring(strFile.lastIndexOf(".") + 1);
                    logger.info("FileExt = " + fileExtName);
                    //得到文件的保存目录
                    String realSavePath = getRandomFilePath(sFileName, sRootPath) + getFileName() + "." + fileExtName;
                    multipartFile.transferTo(new File(realSavePath));
                    mapFile.put(strFile, realSavePath);
                }
                logger.info("--------------文件传输结束-------------------");
            }
            resultBean.setStatus(true);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
            resultBean.setData(JsonUtils.obj2Json(mapFile));
            resultBean.setCode(ResultConstant.MSG_COMMON_SUCCESS);
            logger.info(resultBean);
        } catch (IOException e) {
            logger.error(e.getMessage());
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
            resultBean.setData("");
            resultBean.setCode(ResultConstant.MSG_VIDEO_UPLOADJAVA_UPLOAD);
            return resultBean;
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
            resultBean.setData("");
            resultBean.setCode(ResultConstant.MSG_COMMON_SYSTEM_ERR);
            return resultBean;
        }

        return resultBean;
    }

    @RequestMapping(method = RequestMethod.POST, value = "searchVideo")
    @ResponseBody
    public ResultBean getVideoInfo(){
        ResultBean resultBean = new ResultBean();
        YoukuVideoSearchService youkuVideoSearchService = new YoukuVideoSearchService();
        youkuVideoSearchService.getVideoInfo("");
        return resultBean;
    }


}
