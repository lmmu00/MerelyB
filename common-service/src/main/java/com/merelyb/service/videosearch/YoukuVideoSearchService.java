package com.merelyb.service.videosearch;

import com.merelyb.utils.httpclient.HttpClientResult;
import com.merelyb.utils.httpclient.HttpClientUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @项目: Merelyb
 * @包: com.merelyb.service.videosearch
 * @作者: LiM
 * @创建时间: 2018-09-17 15:18
 * @Description: 优酷查询视频
 */
public class YoukuVideoSearchService {
    private String sSearchUrl = "https://so.youku.com/search_video/q_";

    public void getVideoInfo(String sVideo) {
        String sUrl = sSearchUrl + "%E9%93%81%E9%BD%BF%E9%93%9C%E7%89%99%E7%BA%AA%E6%99%93%E5%B2%9A";
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        try {
            HttpClientResult httpClientResult = httpClientUtils.httpGetRequest(sUrl);
            if (httpClientResult.isSuccessed()) {
                String sDoc = httpClientResult.getData();
                Document document = Jsoup.parse(sDoc);
                Elements eleScripts = document.getElementsByTag("script");
                if (eleScripts != null && eleScripts.size() > 0) {
                    for (int i = 0; i < eleScripts.size() - 1; i++) {
                        Element eleScript = eleScripts.get(i);
                        String sDocMain = eleScript.data();
                        if (!(sDocMain.contains("bpmodule-main") && sDocMain.contains("domid"))) continue;
                        sDocMain = sDocMain.replaceAll("bigview.view", "");
                        sDocMain = sDocMain.substring(1, sDocMain.length() - 1);
                        JSONObject jsonObject = new JSONObject(sDocMain);
                        Document docMain = Jsoup.parse(jsonObject.getString("html"));
                        Elements eleVideoContainers = docMain.getElementsByClass("sk-result-list");
                        if (eleVideoContainers != null) {
                            Element eleVideoContainer = eleVideoContainers.first();
                            if (eleVideoContainer != null) {
                                Elements eleVideos = eleVideoContainer.getElementsByClass("sk-mod");
                                if (eleVideos != null) {
                                    for (int j = 0; j < eleVideos.size() - 1; j++) {
                                        Element eleVideo = eleVideos.get(j);
                                        Elements elePlays = eleVideo.getElementsByClass("mod-play");
                                        if (elePlays == null || elePlays.get(0).children().size() == 0) continue;
                                        Elements eleLefts = eleVideo.getElementsByClass("mod-left");
                                        if (eleLefts != null && eleLefts.size() > 0) {
                                            Elements eleAs = eleLefts.get(0).getElementsByTag("a");
                                            if (eleAs != null && eleAs.size() > 0) {
                                                String sVideoUrl = eleAs.get(0).attr("href");
                                                Elements eleImgs = eleAs.get(0).getElementsByTag("img");
                                                if (eleImgs != null && eleImgs.size() > 0) {
                                                    String sImgUrl = eleImgs.get(0).attr("src");
                                                }
                                            }
                                        }
                                        Elements eleMains = eleVideo.getElementsByClass("mod-main");
                                        if(eleMains != null && eleMains.size() > 0){

                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
