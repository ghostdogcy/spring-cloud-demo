package com.example.qrcodeservice.controller;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;




@Controller
public class MyQRcodeController {

    //生成二维码
    @GetMapping("/myQRcode")
    public String getQRcode (@RequestParam("qrData") String qrData, Map<String, Object> map) throws Exception
    {
        Qrcode qrcode = new Qrcode();

        qrcode.setQrcodeErrorCorrect('M');//纠错等级
        qrcode.setQrcodeEncodeMode('B');//N代表数字 A代表字母 B代表其他
        qrcode.setQrcodeVersion(7);//版本

        if (StringUtils.isEmpty(qrData))
        {
            qrData = "defalut My QRcode!";
        }
        System.out.println(qrData);

        int width = 67 + 12 * (7 - 1);
        int height = 67 + 12 * (7 - 1);

        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics2D gs = bufferedImage.createGraphics();

        gs.setBackground(Color.white);
        gs.setColor(Color.BLACK);

        gs.clearRect(0,0,width,height);

        int pixoff = 2;

        byte[] d = qrData.getBytes();

        if(0 < d.length && d.length < 120)
        {
            boolean[][] s = qrcode.calQrcode(d);
            for(int i = 0 ; i < s.length; i++)
            {
                for(int j = 0; j < s.length; j++)
                {
                    if(s[j][i])
                    {
                        //j和i顺序不可颠倒，不然扫码可行，但是代码解析会变成一串数字
                        gs.fillRect(j*3+pixoff, i*3+pixoff,3, 3);
                    }
                }
            }
        }
        gs.dispose();
        bufferedImage.flush();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/asserts/img/qrcode.png";
        ImageIO.write(bufferedImage,"png",new File(path));

        map.put("qrcodeName","qrcode.png");

        return "qrcode-java";
    }

    //解析二维码
    @GetMapping("/read")
    public String readQRcode (Map<String,Object> map) throws  Exception
    {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/asserts/img/qrcode.png";
        File file = new File(path);

        BufferedImage bufferedImage = ImageIO.read(file);
        QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();

        String result = new String(qrCodeDecoder.decode(new MyQRCodeImage(bufferedImage)));
        bufferedImage.flush();

        System.out.println(result);

        map.put("res",result);

        return "qrcode-java";
    }

}
