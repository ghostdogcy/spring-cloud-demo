package com.example.facerecognitiondemo.factory;

import com.baidu.aip.face.AipFace;

import static com.example.facerecognitiondemo.commons.AIP.*;

public class AipFaceFactory {


    private static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

    /**
     * 获取AipFace对象
     * @return
     */
    public static AipFace getAipFace()
    {
        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
//        String path = "test.jpg";
//        JSONObject res = client.detect(path, new HashMap<String, String>());

        return client;
    }

}
