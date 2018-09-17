package com.yunds.blecompat.callback

import com.vise.baseble.core.BluetoothGattChannel
import com.vise.baseble.model.BluetoothLeDevice
import com.yunds.blecompat.exception.BleCoreException

/**
 * 定禅天 净琉璃
 * 2018-09-11 15:25:55 星期二
 * 描述：ble写数据回调
 */
interface IBleCoreWriteCallback {
    /**
     * 写数据成功
     * @param data 写入或接收到的数据
     * @param bluetoothGattChannel 通讯通道
     * @param bluetoothLeDevice 通讯设备
     */
    fun onWriteSuccess(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?)

    /**
     * 操作失败
     * @param exception 异常信息
     */
    fun onFailure(exception: BleCoreException?)
}