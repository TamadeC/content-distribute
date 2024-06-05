package com.processing.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.processing.service.BaiduAiService;
import com.processing.utils.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @createDate 2024/6/5 12:52
 */
@Service
@Log4j2
public class BaiduAiServiceImpl implements BaiduAiService {

    @Value("${baidu.appid}")
    private String appid;

    @Value("${baidu.api-key}")
    private String apiKey;

    @Value("${baidu.secret-key}")
    private String secretKey;

    @Value("${baidu.url}")
    private String url;

    @Value("${baidu.access-token-url}")
    private String accessTokenUrl;

    @Value("${ai.prompt}")
    private String prompt;

    /**
     * 百度ai
     * @param paragraph
     * @return
     */
    @Override
    public Result optimizeParagraph(String paragraph) {
        if (StrUtil.isBlank(paragraph)) {
            return Result.fail("文本为空");
        }
        String accessToken = getAccessToken();
        if (StrUtil.isBlank(accessToken)) {
            log.info("获取accessToken失败");
            return Result.fail("获取accessToken失败");
        }
        String requestUrl = url + accessToken;
        ArrayList<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, ArrayList<Map<String, String>>> headMap = new HashMap<>();
        map.put("role","user");
        map.put("content", prompt + paragraph);
        list.add(map);
        headMap.put("messages", list);
        String body = JSONUtil.toJsonStr(headMap);
        String result = HttpRequest.post(requestUrl).header(Header.CONTENT_TYPE, "application/json").body(body).execute().body();
        return Result.ok(result);
    }

    /**
     * 百度AccessToken获取
     * @return
     */
    private String getAccessToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", apiKey);
        params.put("client_secret", secretKey);

        String body = HttpRequest.post(accessTokenUrl).header(Header.CONTENT_TYPE, "application/json").
                form(params).execute().body();
        if (StrUtil.isBlank(body)) {
            return null;
        }
        JSON parse = JSONUtil.parse(body);
        String accessToken = String.valueOf(parse.getByPath("access_token"));
        return accessToken;
    }
}
