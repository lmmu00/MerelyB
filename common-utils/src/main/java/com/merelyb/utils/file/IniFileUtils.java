package com.merelyb.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IniFileUtils {

    /**
     * 节点列表
     */
    private Map<String, Map<String, String>> mapFile;

    /**
     * 文件地址
     */
    private String strFile;

    /**
     * 解析文件
     * @param strFile
     * @return
     */
    private int parseFile(String strFile) throws IOException {
        if (this.mapFile != null){
            mapFile.clear();
        }else {
            mapFile = new HashMap<>();
        }

        File iniFile = new File(strFile);
        //文件不存在
        if (!iniFile.exists()) return 1;
        ArrayList<String> slFile = new ArrayList<>();
        ArrayList<Integer> slSectionNo = new ArrayList<>();
        //读取文件到List中
        BufferedReader bufferedReader = new BufferedReader(new FileReader(iniFile));
        try {
            String sLine = "";
            while ((sLine = bufferedReader.readLine()) != null){
                sLine = sLine.trim();
                if (sLine.equals("")) continue;
                slFile.add(sLine);
                if((sLine.indexOf("[") == 0) &&(sLine.indexOf("]") == sLine.length() -1))
                {
                    slSectionNo.add(slFile.size() - 1);
                }
            }
        }finally {
            bufferedReader.close();
        }
        //解析节点
        for (int iIndex = slSectionNo.size() -1; iIndex > -1; iIndex--) {
            Integer iNo = slSectionNo.get(iIndex);
            String sSection = slFile.get(iNo);
            int iBegin, iEnd;
            if(iIndex == slSectionNo.size() -1){
                iBegin = slSectionNo.get(iIndex);
                iEnd = slFile.size() - 1;
            }else {
                iBegin = slSectionNo.get(iIndex);
                iEnd = slSectionNo.get(iIndex + 1) - 1;
            }

            Map<String, String> mapSection = new HashMap<>();
            for (int iLine = iEnd; iLine > iBegin ; iLine--) {
                String[] sNodes = slFile.get(iLine).split("=");
                if(sNodes.length == 0) continue;
                mapSection.put(sNodes[0], slFile.get(iLine).replace(sNodes[0] + "=", "").trim());
            }

            this.mapFile.put(sSection, mapSection);
        }

        return 0;
    }

    /**
     * 保存文件
     * @param strFile
     */
    private void saveFile(String strFile){

    }

    /**
     * 初始化
     * @param strFile
     */
    public IniFileUtils(String strFile) throws IOException {
        this.strFile = strFile;
        parseFile(strFile);
    }

    /**
     * 获取节点数据
     * @param sSection
     * @param sNode
     * @return
     */
    public String getNodeValue(String sSection, String sNode){
        if (!this.mapFile.containsKey(sSection)) return "";
        if(!this.mapFile.get(sSection).containsKey(sNode)) return "";
        return this.mapFile.get(sSection).get(sNode);
    }

    /**
     * 获取所有节点
     * @return
     */
    public List<String> getAllSection(){
        List<String> listSection = new ArrayList<>();
        listSection.addAll(this.mapFile.keySet());
        return listSection;
    }

    /**
     * 获取节点的所有子节点
     * @param sSection
     * @return
     */
    public List<String> getAllNode(String sSection){
        List<String> listNode = new ArrayList<>();
        if (this.mapFile.containsKey(sSection)){
            listNode.addAll(this.mapFile.get(sSection).keySet());
        }
        return listNode;
    }

    /**
     * 新增数据
     * @param sSection
     * @param sNode
     * @param sValue
     */
    public int addNewValue(String sSection, String sNode, String sValue){
        if(this.mapFile.containsKey(sSection)){
            //子节点已经存在
            if(this.mapFile.get(sSection).containsKey(sNode)) return 1;
            this.mapFile.get(sSection).put(sNode, sValue);
        }else {
            Map<String, String> mapNode = new HashMap<>();
            mapNode.put(sNode, sValue);
            this.mapFile.put(sSection, mapNode);
        }
        return 0;
    }

    /**
     * 更新数据
     * @param sSection
     * @param sNode
     * @param sValue
     * @return
     */
    public int updateValue(String sSection, String sNode, String sValue){
        if(this.mapFile.containsKey(sSection)){
            if(this.mapFile.get(sSection).containsKey(sNode)){
                this.mapFile.get(sSection).put(sNode, sValue);
            }else  return 2;
        }else  return  1;
        return 0;
    }
}
