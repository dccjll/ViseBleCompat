package com.yunds.blecompat.callback

import com.vise.baseble.core.BluetoothGattChannel
import com.vise.baseble.model.BluetoothLeDevice

/**
 * 定禅天 净琉璃
 * 2018-09-11 15:25:55 星期二
 * 描述：ble响应回调
 */
interface IBleCoreResponseCallback: IBleCoreWriteCallback {

    /**
     * 接收到数据
     * @param data 接收到的数据
     * @param bluetoothGattChannel 接收数据的通道
     * @param bluetoothLeDevice 设备信息
     */
    fun onReceivedData(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?)
}