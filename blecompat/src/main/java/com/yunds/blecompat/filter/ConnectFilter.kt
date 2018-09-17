package com.yunds.blecompat.filter

import com.vise.baseble.model.BluetoothLeDevice

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：连接过滤器
 */
class ConnectFilter (

    /**
     * 需要匹配的设备
     */
    var bluetoothLeDevice: BluetoothLeDevice? = null,

    /**
     * 需要匹配的设备名称
     */
    var name: String? = null,

    /**
     * 需要匹配的设备mac地址
     */
    var mac: String? = null) {

    fun setbluetoothLeDevice(bluetoothLeDevice: BluetoothLeDevice?): ConnectFilter {
        this.bluetoothLeDevice = bluetoothLeDevice
        return this
    }

    fun setName(name: String?): ConnectFilter {
        this.name = name
        return this
    }

    fun setMac(mac: String?): ConnectFilter {
        this.mac = mac
        return this
    }
}