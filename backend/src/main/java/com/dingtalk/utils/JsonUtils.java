package com.dingtalk.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ltb
 * @date ：2021/12/30
 */
@Slf4j
public class JsonUtils {

    private static final String userFileName = "user.json";

    public static void main(String[] args) throws IOException {
        File file = getFile();
        System.out.println(file);
    }


    private static File getFile() throws IOException {
        InputStream is = JsonUtils.class.getClassLoader().getResourceAsStream(userFileName);
        String filePath = JsonUtils.class.getClassLoader().getResource(userFileName).getFile();
        log.info("filePath:{}", filePath);
        File file = new File(userFileName);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] b = new byte[1024];
        while ((is.read(b)) != -1) {
            // 写入数据
            fos.write(b);
        }
        is.close();
        fos.close();
        return file;
    }


    //读取json文件
    public static String readJsonFile() {
//        String path = JsonUtils.class.getClassLoader().getResource("user.json").getPath();
        String jsonStr = "";
        try {
            File jsonFile = getFile();
            //如果文件不存在，则新建一个
            if (!jsonFile.exists()) {
                try {
                    jsonFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> writeJsonFile(JSONObject data) {
        Map<String, Object> map = new HashMap<>();
        String filePath = JsonUtils.class.getClassLoader().getResource(userFileName).getFile();
        File file = null;
        try {
            file = getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            FileWriter fw = new FileWriter(filePath);
            // 写入数据
            fw.write(data.toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("msg", "写入文件成功");
        map.put("code", "1");
        System.out.println("文件写入成功！");
        return map;
    }
}
