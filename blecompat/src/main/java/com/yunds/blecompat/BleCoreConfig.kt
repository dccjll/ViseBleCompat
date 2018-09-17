package com.yunds.blecompat

import com.yunds.blecompat.config.BleConfig

/**
 * 定禅天 净琉璃
 * 2018-09-12 13:16:06 星期三
 * 描述：ble核心配置抽象类
 */
abstract class BleCoreConfig {
    /**
     * 获取ble的配置
     */
    abstract fun getBleConfig(): BleConfig

    /**
     * 获取ble的配置管理器
     */
    fun getBleCoreManager(): BleCore = BleCoreManager()
}