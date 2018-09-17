package com.yunds.blecompat.config

import com.vise.baseble.common.PropertyType

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：蓝牙通讯通道配置接口
 */
interface BleChannelConfig {
    /**
     * 获取写数据的通道
     */
    fun getBleChannelWrite(): PropertyType

    /**
     * 获取接收数据的通道
     */
    fun getBleChannelNotify(): PropertyType

    /**
     * 获取读数据的通道
     */
    fun getBleChannelRead(): PropertyType
}
