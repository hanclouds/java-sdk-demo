package com.hanclouds.demo;

import com.alibaba.fastjson.JSON;
import com.hanclouds.HanCloudsClient;
import com.hanclouds.enums.DataTypeEnum;
import com.hanclouds.exception.HanCloudsException;
import com.hanclouds.req.DeviceCommandSendRequest;
import com.hanclouds.resp.StringResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 产品级鉴权下发设备命令
 */
public class SendDeviceCommandTest {
    private static Logger logger = LoggerFactory.getLogger(CreateDeviceTest.class);

    static String apiUrl;  //sdk api地址
    static String productKey;  //平台账户下产品key
    static String cmdKey; //平台账户下产品的cmdKey
    static String cmdSecret; //平台账户下产品的cmdSecret

    static {
        apiUrl = "https://api.hanclouds.com/api/v1";
        productKey = "xxxxxxxx";
        cmdKey = "xxxxxxxx";
        cmdSecret = "xxxxxxxxxxxxxxxx";
    }


    @Test
    public void sendDeviceCommand() {
        //设置鉴权参数
        HanCloudsClient hanCloudsClient = new HanCloudsClient(apiUrl);
        hanCloudsClient.putProductAuthParams(productKey, cmdKey, cmdSecret);

        //命令下发请求
        DeviceCommandSendRequest deviceCommandSendRequest = new DeviceCommandSendRequest();
        String deviceKey;  //平台账户产品下设备Key
        DataTypeEnum dataType; //数据类型
        String content;  //命令内容
        {
            deviceKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
            dataType = DataTypeEnum.STRING;
            content = "我是第一个命令";
        }
        //设置设备Key
        deviceCommandSendRequest.setDeviceKey(deviceKey);
        /**
         * 数据类型主要影响如何解析Content数据。
         * 服务器会解析上报的String数据，并按DataType类型下发数据给设备。
         */
        //这里下发string类型数据，就设置数据类型为STRING
        deviceCommandSendRequest.setDataType(dataType);
        //设置下发的内容
        deviceCommandSendRequest.setContent(content);
        try {
            StringResponse response = hanCloudsClient.execute(deviceCommandSendRequest);
            if (response.isSucceed()) {
                //成功后处理代码
                logger.info("响应结果-->{}", JSON.toJSONString(response));
            }
        } catch (HanCloudsException e) {
        }
    }
}
