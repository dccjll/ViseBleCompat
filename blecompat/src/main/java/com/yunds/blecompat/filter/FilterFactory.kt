package com.yunds.blecompat.filter

import com.vise.baseble.model.BluetoothLeDevice
import java.util.*

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：过滤器工厂
 */
class FilterFactory {

    companion object {
        val instance = FilterFactory()
    }

    /**
     * 获取扫描过滤器
     */
    fun getScanFilter(): ScanFilter {
        return ScanFilter()
    }

    /**
     * 获取扫描过滤器<br/>
     * 名称
     */
    fun getNameScanFilter(name: String): ScanFilter {
        return ScanFilter().setName(name)
    }

    /**
     * 获取扫描过滤器<br/>
     * mac
     */
    fun getMacScanFilter(mac: String): ScanFilter {
        return ScanFilter().setMac(mac)
    }

    /**
     * 获取扫描过滤器<br/>
     * uuid
     */
    fun getUUIDScanFilter(uuid: UUID): ScanFilter {
        return ScanFilter().setUuid(uuid)
    }

    /**
     * 获取扫描过滤器<br/>
     * 名称列表
     */
    fun getNameListScanFilter(nameList: List<String>): ScanFilter {
        return ScanFilter().setNameList(nameList)
    }

    /**
     * 获取扫描过滤器<br/>
     * mac列表
     */
    fun getMacListScanFilter(macList: List<String>): ScanFilter {
        return ScanFilter().setMacList(macList)
    }

    /**
     * 获取扫描过滤器<br/>
     * 信号强度
     */
    fun getRssiScanFilter(rssi: Int): ScanFilter {
        return ScanFilter().setRssi(rssi)
    }

    /**
     * 获取扫描过滤器<br/>
     * 名称正则
     */
    fun getRegularNameScanFilter(regularName: String): ScanFilter {
        return ScanFilter().setRegularName(regularName)
    }

    /**
     * 获取扫描过滤器<br/>
     * 信号强度与名称正则
     */
    fun getRssiRegularNameScanFilter(rssi: Int, regularName: String): ScanFilter {
        return getRssiScanFilter(rssi).setRegularName(regularName)
    }

    /**
     * 获取连接过滤器
     */
    fun getConnectFilter(): ConnectFilter {
        return ConnectFilter()
    }

    /**
     * 获取连接过滤器<br/>
     * 设备
     */
    fun getBluetoothLeDeviceConnectFilter(bluetoothLeDevice: BluetoothLeDevice): ConnectFilter {
        return ConnectFilter().setbluetoothLeDevice(bluetoothLeDevice)
    }

    /**
     * 获取连接过滤器<br/>
     * mac
     */
    fun getMacConnectFilter(mac: String): ConnectFilter {
        return ConnectFilter().setMac(mac)
    }

    /**
     * 获取连接过滤器<br/>
     * 名称
     */
    fun getNameConnectFilter(name: String): ConnectFilter {
        return ConnectFilter().setName(name)
    }
}