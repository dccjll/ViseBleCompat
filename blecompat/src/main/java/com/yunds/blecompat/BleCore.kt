package com.yunds.blecompat

import com.vise.baseble.callback.IBleCallback
import com.vise.baseble.callback.IConnectCallback
import com.vise.baseble.callback.scan.ScanCallback
import com.vise.baseble.common.PropertyType
import com.vise.baseble.core.DeviceMirror
import com.yunds.blecompat.callback.IBleCoreWriteCallback
import com.yunds.blecompat.config.BleConfig
import com.yunds.blecompat.config.BleUUID
import com.yunds.blecompat.filter.ConnectFilter
import com.yunds.blecompat.filter.ScanFilter

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：ble核心操作接口
 */
interface BleCore {

    /**
     * 扫描
     * @param scanFilter    扫描过滤器
     * @param scanCallback 扫描的回调
     */
    fun scan(scanFilter: ScanFilter?, scanCallback: ScanCallback)

    /**
     * 连接
     * @param connectFilter 连接的过滤器
     * @param iConnectCallback 连接的回调
     */
    fun connect(connectFilter: ConnectFilter, iConnectCallback: IConnectCallback)

    /**
     * 向指定mac地址的的设备写入指定的数据
     * @param deviceMirror 设备镜像
     * @param bleConfig 设备ble配置信息
     * @param data 要写入的数据
     * @param iBleCoreWriteCallback 写数据的回调
     */
    fun writeData(connectFilter: ConnectFilter, bleConfig: BleConfig, data: ByteArray, iBleCoreWriteCallback: IBleCoreWriteCallback)


    /**
     * 读数据
     * @param deviceMirror 设备镜像
     * @param propertyTypeRead 读数据的通道属性
     * @param bleUUIDRead 读数据的uuid
     * @param iBleCallback 读数据的回调
     */
    fun readData(deviceMirror: DeviceMirror, propertyTypeRead: PropertyType, bleUUIDRead: BleUUID, iBleCallback: IBleCallback)
}