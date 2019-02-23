package com.example.facerecognitiondemo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.example.facerecognitiondemo.factory.AipFaceFactory;
import com.example.facerecognitiondemo.tool.ChangeEncode;
import org.json.JSONObject;
import com.baidu.aip.face.AipFace;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import com.baiduAI.aip.AIP;
//import com.baiduAI.dao.UserDao;
//import com.baiduAI.daoImpl.UserDaoImpl;
//import com.baiduAI.model.User;
//import com.baiduAI.tool.ChangeEncode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginServlet  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {super();}

    public void destroy() {super.destroy(); }

//        public void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//            this.doPost(request, response);
//        }

    /**
     * 用户注册处理
     */
    @PostMapping(value = "/register")
    public String DTRegister(@RequestParam("snapData") String img, @RequestParam("uid") String uid, Map<String,String> msg)
    {
        String data = img.substring(22, img.length());	//图片去base64头

        AipFace client = AipFaceFactory.getAipFace();

        String imageType = "BASE64";
        String groupId = "group1";      //用户组

        HashMap<String, String> options = new HashMap<String, String>();

//        options.put("quality_control","LOW");     //quality_control   图片质量要求

        // 人脸注册
        JSONObject res = client.addUser(data, imageType, groupId, uid, options);
        System.out.println(res.toString(2));

        if("SUCCESS".equals(res.get("error_msg")))
        {
            msg.put("msg","login success!");
            return "success";
        }
        else
        {
            msg.put("msg","error");
            return "error";
        }

    }

    /**
     * 用户登录校验处理
     */
    @PostMapping(value = "/login")
    public String DTLogin(@RequestParam("snapData") String img, @RequestParam("uid") String uid, Map<String,String> msg) {

        String data = img.substring(22, img.length());	//图片去base64头

        AipFace client = AipFaceFactory.getAipFace();

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL");   //图片质量要求
        options.put("liveness_control", "NORMAL");  //活体检测
        options.put("user_id", uid);                //用户id，有此参数为按uid查询认证人脸
//        options.put("max_user_num", "1");           //返回相似度最高的几个用户，最多返回20个（认证不需要）

        String imageType = "BASE64";
        String groupIdList = "group1";      //查找的用户组群，若多组用逗号分隔

        // 人脸搜索
        JSONObject res = client.search(data, imageType, groupIdList, options);
        System.out.println(res.toString(2));

        if("SUCCESS".equals(res.get("error_msg")))
        {
            msg.put("msg","login success");
            return "success";
        }
        else
        {
            msg.put("msg","error");
            return "error";
        }
    }

    public void init() throws ServletException {}

}
