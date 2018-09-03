package com.merelyb.video.controller;

import com.merelyb.bean.ResultBean;
import com.merelyb.constant.CodeConstant;
import com.merelyb.constant.ResultConstant;
import com.merelyb.utils.json.JsonUtils;
import com.merelyb.video.bean.FilePath;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        return new SimpleDateFormat("yyyyMMddHHmmsszzz").format(new Date());
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
        String sDir = sRootPath + "\\" + String.valueOf(iFDir) + "\\" + String.valueOf(iSDir);
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
        if(filePath.getCur().equals("0")){
            strRoot = filePath.getWin();
        }else if(filePath.getCur().equals("1")){
            strRoot = filePath.getLiu();
        }else {
            strRoot = request.getSession().getServletContext().getRealPath("");
        }
        String sTmpPath = strRoot + "/temp";
        File fileTmp = new File(sTmpPath);
        if (!fileTmp.exists()) {
            fileTmp.mkdir();
        }

        String sRootPath = strRoot + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());

        try {
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(fileTmp);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    logger.info("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
                resultBean.setData("");
                resultBean.setCode(ResultConstant.MSG_VIDEO_UPLOADJAVA_FORM);
                return resultBean;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024*10字节，也就是6MB
            upload.setFileSizeMax(1024 * 1024 * 6);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024 * 1024 * 12);

            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，
            // 每一个FileItem对应一个Form表单的输入项

            List<FileItem> fileItemList = upload.parseRequest(request);
            if (fileItemList.size() == 0) {
                resultBean.setStatus(false);
                resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
                resultBean.setData("");
                resultBean.setCode(ResultConstant.MSG_VIDEO_UPLOADJAVA_NOFILE);
                return resultBean;
            }
            Map<String, String> mapFile = new HashMap<>();
            for (FileItem fileItem : fileItemList) {
                logger.info("--------------解析文件开始-------------------");
                //如果fileitem中封装的是普通输入项的数据
                if (fileItem.isFormField()) {
                    String name = fileItem.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = fileItem.getString("UTF-8");
                    logger.info(name + "=" + value);
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String strFileName = fileItem.getName();
                    logger.info("FileName = " + strFileName);
                    if (strFileName == null || strFileName.trim().equals("")) {
                        logger.info("--------------解析文件结束-------------------");
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    strFileName = strFileName.substring(strFileName.lastIndexOf("\\") + 1);
                    //得到上传文件的扩展名
                    String fileExtName = strFileName.substring(strFileName.lastIndexOf(".") + 1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    logger.info("上传的文件的扩展名是：" + fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream in = fileItem.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = getFileName();
                    //得到文件的保存目录
                    String realSavePath = getRandomFilePath(saveFilename, sRootPath);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);

                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    mapFile.put(strFileName, realSavePath + saveFilename);
                    //删除处理文件上传时生成的临时文件
                    File[] fileTmps = fileTmp.listFiles();
                    if (fileTmps.length > 0) {
                        for (int j = fileTmps.length - 1; j > 0; j--) {
                            fileTmps[j].deleteOnExit();
                        }
                    }
                }
                logger.info("--------------解析文件结束-------------------");
            }
            resultBean.setStatus(true);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
            resultBean.setData(JsonUtils.obj2Json(mapFile));
            resultBean.setCode(ResultConstant.MSG_COMMON_SUCCESS);
            logger.info(JsonUtils.obj2Json(resultBean));
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            logger.error(e.getMessage());
            resultBean.setStatus(false);
            resultBean.setCode(CodeConstant.CODE_MOUDLE_VIDEO + CodeConstant.CODE_VIDE_UPLOADJAVA);
            resultBean.setData("");
            resultBean.setCode(ResultConstant.MSG_VIDEO_UPLOADJAVA_SIZE);
            return resultBean;
        } catch (FileUploadException e) {
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


}
