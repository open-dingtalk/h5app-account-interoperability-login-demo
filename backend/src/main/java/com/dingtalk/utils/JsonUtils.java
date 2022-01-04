package com.dingtalk.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ltb
 * @date ：2021/12/30
 */
public class JsonUtils {

    private static final String userFileName = "user.json";

    private static File getFile() throws IOException {
        String path = JsonUtils.class.getClassLoader().getResource("").getPath();
        File dir = new File(path);
        File file = null;
        String[] children = dir.list();
        for (String filename : children) {
            if(userFileName.equals(filename)){
                file =  new File(path + filename);
            }
        }
        if(file == null){
            file =  new File(path + userFileName);
            file.createNewFile();
        }
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
        BufferedWriter writer = null;
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
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(String.valueOf(data));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("msg","写入文件成功");
        map.put("code","1");
        System.out.println("文件写入成功！");
        return map;
    }
}
