package com.abc.bookstore.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102400753292";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDDEfihycgbef+vGmhclrMOxiHtOl8CWVaJi+in76U3PDpHDYK0QXiF6Stsj9eZXE11scjiSimKVoE8IXNXe34yMocfri2ZE9zJtUfR5NRa7NXyOKbysWn7HbMgLp4JQMSQ9j/oBuzPAwg9hMMV48yTQFYXL+CGteAr/d5af7bBcLnRfpfgcdP/pqr2dpvsmCqXNXPSVOAFn0j7ZSwB6hvxrJfTbg9XN17wgV6QxGl0RwaNamXkRvsbZloTSoQCejca/ZNmEZia8kbsz+xK35rwmr0RnKOKGltlgIMbuWbya3Wo7kLeE/utP5mqCOd4sRh8X2dg3F7gfbP+fcc5gWcdAgMBAAECggEAf4613d3b25PITVFgYwMsbYHEV6Mu05Nl0iXJ6l7704WvuhysloCldjOF8MSSwDcqc8bgzJCgX1S3x4E8Mj2PxPVjhKTaLlpdqwy+vRL8hcSPFohTKyo2Wj4OjwHM19Ihd4V/PpOgsLGT5t50z1CZysRsR1dIY+50e2/Twav37r9KjiksltZRtukce+c+6XuSaSSmF9D+E4ST41A0G5K0ErJyf1uOPgu5hq0RrCPBVY4zZIiIcI2+4HxtrUw8Jow5ePKAF2/bpgsPoqYl/K52k3mvjDpLEJtBm9JC01lL6XzlngjP75RG8MaElJVa1UNjbeGwrpTrKMBuORJdpxEv5QKBgQDhasjPdIhL8E+Yc+hzWe/Xhzmxg63hkvT5/C0nxviWsRvLjVC+s6F3pE06Fs3y9mWS0y/KehieuZcBY6G9U4YlG6kk+QMWyVZzpjtp+JPTpK1NYPE2MGS9pPtAywCDRxmbKyt8AFy7fyH/Wv0gWBqX0dmvp+wsJA5f4s4U5aKP8wKBgQDdiSxwSt9kFFE0LuoEIR6pqfQU3CxR8DWNMGe4LOBtFJvIRElIh3lN54O6I3fNFWkdEc8NUEEiJ9ugkxHr2kclPk9agRq/HuvR84euDKonhS1dr6SjrNxIQcddiJS3qaqLGiLSMJMmIPoU2GRjYcJHepnkqpkUjaPQIrtPnFsArwKBgD0VMas9ub1SNk9qgT/YzCxMQW7/7rtkliuzkQzpVu2GurBy78FtbcC87gxip5ZB/I5H2fh1E3zXb7r5o5dpLb2afEA/lf2zu1CFaC40JOLCChAgx9xWSsY1EbyiBdaNCrxIe3UTqA1KywymPFdqaHJhdCqsR0xCzP5guFVjUKTPAoGAWTW+/m04Lh38YU4vEIChz2LQFoJfGpPnjLacXc88hNCStv/AnwNm9tcu9YsI2D4x+PHN9ieGQxHX4H647cgVdEr2O5TXx3TVM9OkpJMXBhkcHqj3XK3kevh6JxE0Cg25zpA0y32iE+2dpz1Il7irOouFY07LnDo5aDxEP5fQEaMCgYB02iWB73fG8ACLmXPXbgW8wK1MdA/vjx/Ebxl3C8SU9E+lRmowAU62mVHDd2LlRSNRlEgtjr1AQCazLdSW1JusNTbgfDQWAJZ/dS8GlX1XHiIy0vbxDJsp1js+UdulRFeoGpdM3YQf4EQcuE/S3cjL6f9o4M/QTlbSlJM2w2nz8g==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAij2zHowCd2AGmugeDK2ZcEKRHI4tFBDBXVsQgUWymFH/GxEX6lJH7/uFgrT1bXuZuAp9EYoz5NjKSyyp2W3bReRjzU04SOnS01mm5plADA2w1QSbkidbcZFG2KQAiiLZ/D/Ch4Q2tLYel+sbMqbsP9KCX42YLTiP/NVsBx2UZ7nIl+UNDay+wueDtidqGvTq/P7FjNUFMvI/Ot67OuSrwGl1Xyys8mlid5tmjHKOc46+To0scSrZ1qnIY+Jnx6M9S+/2srFUd0z8eR4llCDgUOir/JHkSAvSd8EGneprzJEE+zkjcoI0ix5s7zFnxKNmKrPm3jXYSDbBSsJvaYT57QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://39.97.230.224:80/bookstore/client/order/paysuccess";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
