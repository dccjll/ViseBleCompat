package com.yunds.blecompat.config

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：蓝牙通讯UUID接口
 */
interface BleUUIDConfig {

    /**
     * 获取写数据的BleUUID
     */
    fun getBleUUIDWrite(): BleUUID

    /**
     * 获取接收通知的BleUUID
     */
    fun getBleUUIDNotify(): BleUUID

    /**
     * 获取读数据的BleUUID
     */
    fun getBleUUIDRead(): BleUUID
}
