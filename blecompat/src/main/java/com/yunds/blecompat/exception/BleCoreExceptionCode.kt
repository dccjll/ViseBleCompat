package com.yunds.blecompat.exception

/**
 * 定禅天 净琉璃
 * 2018-09-11 14:06:11 星期二
 * 描述：ble异常错误码
 */
enum class BleCoreExceptionCode {
    TIMEOUT,    //超时
    CONNECT_ERR,    //连接异常
    GATT_ERR,   //GATT异常
    INITIATED_ERR,  //初始化异常
    OTHER_ERR,   //其他异常
    PARAMS_ERR,  //参数异常
    DISCONNECTED_ISACTIVE,//主动断开成功
    DISCONNECTED,//被动断开成功
    DATA_ERR,//数据异常

    DATA_ANALYSIS_ERR,//数据解析异常
    BLE_DEVICE_ACK_ERR,//设备应答失败
    BLE_DEVICE_ACK_DATA_VALIDATE_ERR,//设备应答数据校验失败
    DATA_NOT_SUPPORT_CHARSETSET_ERR,//不支持的字符字符集编码异常

    PARSE_PARAM_ERR,//转换参数异常
    PARSE_PARAM_TIMERANGE_ERR,//转换时效参数异常

    PARAM_CHANNELPWD_ERR,//信道密码参数异常
    PARAM_OPENKEY_ERR,//开门秘钥参数异常
    PARAM_USERIDINFO_ERR,//用户ID信息参数异常
    PARAM_OPENSECRETKEY_ERR,//开门加密秘钥参数异常
    PARAM_FINGERLOCKID_ERR,//锁上的指纹id参数异常
    PARAM_LOCKOPENPASSWORD_ERR,//锁上的开锁密码参数异常
    PARAM_MAC_ERR,//mac参数异常
    PARAM_LOCKMANAGEPWD_ERR,//锁具管理密码参数异常
    PARAM_WILDFINGERGROUPID_ERR,//野指纹组ID参数异常
    PARAM_GEMALTOMAC_ERR,//gemalto的mac参数异常
    PARAM_ID2TOKEN_ERR,//id2的token参数异常
    PARAM_ID2CHALLENGE_ERR,//id2的challenge参数异常
    PARAM_DHPUBLICKEY_ERR,//dh的公钥参数异常
    PARAM_WIFISSID_ERR,//wifiSSID参数异常
    PARAM_WIFIPWDSSID_ERR,//wifi密码参数异常
    PARAM_WIFIPUSHDOMAIN_ERR,//wifi推送域名参数异常
    PARAM_WIFIPUSHPORT_ERR,//wifi推送端口参数异常
}