package com.hanclouds.demo;

import com.alibaba.fastjson.JSON;
import com.hanclouds.HanCloudsClient;
import com.hanclouds.exception.HanCloudsException;
import com.hanclouds.req.DeviceCreateRequest;
import com.hanclouds.resp.DeviceCreateResponse;
import org.junit.Test;

/**
 * 产品级鉴权创建设备
 */
public class CreateDeviceTest {

    static String apiUrl;  // sdk api地址
    static String productKey;  //平台账户下产品key
    static String accessKey;  //平台账户下产品的accessKey
    static String accessSecret;  //平台账户下产品的accessSecret

    static {
        apiUrl = "https://api.hanclouds.com/api/v1";
        productKey = "xxxxxxxx";
        accessKey = "xxxxxxxx";
        accessSecret = "xxxxxxxxxxxxxxxx";
    }


    @Test
    public void createDevice() {
        //设置鉴权参数
        HanCloudsClient hanCloudsClient = new HanCloudsClient(apiUrl);
        hanCloudsClient.putProductAuthParams(productKey, accessKey, accessSecret);
        //设置调用参数。
        /**
         * 为什么我们授权那里设置了ProductKey，这里还需要设置呢？
         * 这里只是个巧合，我们不光可以使用产品授权，也可以使用用户授权创建设备。
         * 所以授权参数与调用参数要独立分开，互不影响。
         */
        DeviceCreateRequest deviceCreateRequest = new DeviceCreateRequest();
        String sn;  //设备序列号
        String deviceName;  //设备名称
        String deviceType;  //设备类型
        {
            sn = "1111111111111111";
            deviceName = "测试设备";
            deviceType = "real";
        }

        deviceCreateRequest.setProductKey(productKey);
        deviceCreateRequest.setSn(sn);
        deviceCreateRequest.setDeviceName(deviceName);
        deviceCreateRequest.setDeviceType(deviceType);
        try {
            //发送创建设备请求
            DeviceCreateResponse response = hanCloudsClient.execute(deviceCreateRequest);
            if (response.isSucceed()) {
                //成功后处理代码
                System.out.println("响应结果-->" + JSON.toJSONString(response));
            }

            //response可以获取其它信息，具体请查看方法列表。
        } catch (HanCloudsException e) {
        }
    }
}
