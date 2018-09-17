package com.yunds.blecompat.filter

import java.util.*

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：扫描过滤器
 */
class ScanFilter (
    /**
     * 需要匹配的设备名称
     */
    var name: String? = null,

    /**
     * 需要匹配的设备mac地址
     */
    var mac: String? = null,

    /**
     * 需要匹配的设备UUID
     */
    var uuid: UUID? = null,

    /**
     * 需要扫描的设备的名称的列表
     */
    var nameList: List<String>? = null,

    /**
     * 需要扫描的设备的mac列表
     */
    var macList: List<String>? = null,

    /**
     * 需要扫描的设备的信号
     */
    var rssi: Int? = null,

    /**
     * 需要扫描的设备的名称的正则表达式
     */
    var regularName: String? = null) {

    fun setName(name: String?): ScanFilter {
        this.name = name
        return this
    }

    fun setMac(mac: String?): ScanFilter {
        this.mac = mac
        return this
    }

    fun setUuid(uuid: UUID?): ScanFilter {
        this.uuid = uuid
        return this
    }

    fun setNameList(nameList: List<String>?): ScanFilter {
        this.nameList = nameList
        return this
    }

    fun setMacList(macList: List<String>?): ScanFilter {
        this.macList = macList
        return this
    }

    fun setRssi(rssi: Int?): ScanFilter {
        this.rssi = rssi
        return this
    }
    fun setRegularName(regularName: String?): ScanFilter {
        this.regularName = regularName
        return this
    }
}