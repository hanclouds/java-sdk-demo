package com.hanclouds.demo;

import com.alibaba.fastjson.JSON;
import com.hanclouds.HanCloudsClient;
import com.hanclouds.enums.DataTypeEnum;
import com.hanclouds.exception.HanCloudsException;
import com.hanclouds.req.DeviceDataStreamUploadDataRequest;
import com.hanclouds.resp.BooleanResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 产品级鉴权上传设备数据
 */
public class UploadDeviceDataTest {
    private static Logger logger = LoggerFactory.getLogger(CreateDeviceTest.class);

    static String apiUrl;    //sdk api地址
    static String productKey;    //平台账户下产品key
    static String uploadKey;    //平台账户下产品的uploadKey
    static String uploadSecret;    //平台账户下产品的uploadSecret

    static {
        apiUrl = "https://api.hanclouds.com/api/v1";
        productKey = "xxxxxxxx";
        uploadKey = "xxxxxxxx";
        uploadSecret = "xxxxxxxxxxxxxxxx";
    }

    @Test
    public void uploadDeviceData() {
        HanCloudsClient hanCloudsClient = new HanCloudsClient(apiUrl);
        hanCloudsClient.putProductAuthParams(productKey, uploadKey, uploadSecret);

        //上传数据请求
        DeviceDataStreamUploadDataRequest deviceDataStreamUploadDataRequest = new DeviceDataStreamUploadDataRequest();

        String deviceKey;        //平台账户产品下设备Key
        String dataStreamName;        //平台账户产品下数据流名
        DataTypeEnum dataType;        //数据类型
        {
            deviceKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
            dataStreamName = "stream_test";
            dataType = DataTypeEnum.STRING;
        }
        //设置调用参数。
        deviceDataStreamUploadDataRequest.setDeviceKey(deviceKey);
        deviceDataStreamUploadDataRequest.setDataName(dataStreamName);
        deviceDataStreamUploadDataRequest.setDataType(dataType);
        /**
         * 数据类型主要影响如何解析Content数据。
         * 服务器会解析上报的String数据，并按DataType类型上传数据点
         */
        deviceDataStreamUploadDataRequest.setContent("第一次上传数据");
        try {
            BooleanResponse response = hanCloudsClient.execute(deviceDataStreamUploadDataRequest);
            if (response.isSucceed()) {
                //成功后处理代码
                logger.info("响应结果-->{}", JSON.toJSONString(response));
            }
        } catch (HanCloudsException e) {
        }
    }
}
