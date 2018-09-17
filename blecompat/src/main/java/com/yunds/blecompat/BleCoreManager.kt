package com.yunds.blecompat

import android.text.TextUtils
import com.vise.baseble.ViseBle
import com.vise.baseble.callback.IBleCallback
import com.vise.baseble.callback.IConnectCallback
import com.vise.baseble.callback.scan.*
import com.vise.baseble.common.PropertyType
import com.vise.baseble.core.BluetoothGattChannel
import com.vise.baseble.core.DeviceMirror
import com.vise.baseble.exception.BleException
import com.vise.baseble.model.BluetoothLeDevice
import com.vise.log.ViseLog
import com.yunds.blecompat.callback.BleCoreResponseAdapter
import com.yunds.blecompat.callback.IBleCoreWriteCallback
import com.yunds.blecompat.config.BleConfig
import com.yunds.blecompat.config.BleUUID
import com.yunds.blecompat.exception.BleCoreException
import com.yunds.blecompat.exception.BleCoreExceptionCode
import com.yunds.blecompat.exception.parse
import com.yunds.blecompat.filter.ConnectFilter
import com.yunds.blecompat.filter.ScanFilter
import com.yunds.blecompat.utils.ByteUtils
import com.yunds.blecompat.utils.MacAddressUtils

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：ble核心操作
 */
class BleCoreManager: BleCore {

    override fun scan(scanFilter: ScanFilter?, scanCallback: ScanCallback) {
        when (scanCallback) {
            is SingleFilterScanCallback -> {
                if (!TextUtils.isEmpty(scanFilter?.name)) {
                    scanCallback.setDeviceName(scanFilter?.name)
                }
                if (!TextUtils.isEmpty(scanFilter?.mac)) {
                    scanCallback.setDeviceMac(scanFilter?.mac)
                }
            }
            is UuidFilterScanCallback -> {
                if (scanFilter?.uuid != null) {
                    scanCallback.setUuid(scanFilter.uuid)
                }
            }
            is ListFilterScanCallback -> {
                if (scanFilter?.nameList != null && scanFilter.nameList?.size?:0 > 0) {
                    scanCallback.setDeviceNameList(scanFilter.nameList)
                }
                if (scanFilter?.macList != null && scanFilter.macList?.size?:0 > 0) {
                    scanCallback.setDeviceMacList(scanFilter.macList)
                }
            }
            is RegularFilterScanCallback -> {
                if (scanFilter?.rssi != null) {
                    scanCallback.setDeviceRssi(scanFilter.rssi?:0)
                }
                if (!TextUtils.isEmpty(scanFilter?.regularName)) {
                    scanCallback.setRegularDeviceName(scanFilter?.regularName)
                }
            }
        }
        ViseBle.getInstance().startScan(scanCallback)
    }

    override fun connect(connectFilter: ConnectFilter, iConnectCallback: IConnectCallback) {
        val existBluetotohDevice = connectFilter.bluetoothLeDevice != null
        val isMac = MacAddressUtils.checkAddress(connectFilter.mac?:"")
        val existName = TextUtils.isEmpty(connectFilter.name)
        if (!existBluetotohDevice && !isMac && !existName) {
            throw BleCoreException(BleCoreExceptionCode.PARAMS_ERR)
        }
        when {
            existBluetotohDevice -> ViseBle.getInstance().connect(connectFilter.bluetoothLeDevice, iConnectCallback)
            isMac -> ViseBle.getInstance().connectByMac(connectFilter.mac, iConnectCallback)
            existName -> ViseBle.getInstance().connectByName(connectFilter.name, iConnectCallback)
        }
    }

    override fun writeData(connectFilter: ConnectFilter, bleConfig: BleConfig, data: ByteArray, iBleCoreWriteCallback: IBleCoreWriteCallback) {
        connect(connectFilter, object : IConnectCallback{
            override fun onConnectFailure(exception: BleException?) {
                iBleCoreWriteCallback.onFailure(exception?.parse())
            }

            override fun onConnectSuccess(deviceMirror: DeviceMirror?) {
                if (deviceMirror == null) {
                    iBleCoreWriteCallback.onFailure(BleCoreException(BleCoreExceptionCode.CONNECT_ERR))
                    return
                }
                if (iBleCoreWriteCallback is BleCoreResponseAdapter) {
                    /*receiveData(deviceMirror, bleConfig, object : IBleCoreResponseCallback{
                        override fun onReceivedData(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?) {
                            if (iBleCoreWriteCallback.onReceivedFinish()) {
                                ViseLog.i( "onReceivedDataFinish, data lost")
                            }
                            iBleCoreWriteCallback.onReceivedData(data, bluetoothGattChannel, bluetoothLeDevice)
                            if (iBleCoreWriteCallback.onReceivedFinish()) {
                                deviceMirror.unregisterNotify(bleConfig.getBleChannelNotify() == PropertyType.PROPERTY_INDICATE)
                                iBleCoreWriteCallback.onReceivedFinish(iBleCoreWriteCallback.getReceivedTotalData()?:ByteArray(0))
                            }
                        }

                        override fun onWriteSuccess(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?) {
                            iBleCoreWriteCallback.onWriteSuccess(data, bluetoothGattChannel, bluetoothLeDevice)
                        }

                        override fun onFailure(exception: BleCoreException?) {
                            iBleCoreWriteCallback.onFailure(exception)
                        }
                    })*/
                    val isIndicate = bleConfig.getBleChannelNotify() == PropertyType.PROPERTY_INDICATE
                    val bluetoothGattChannel = BluetoothGattChannel.Builder()
                            .setBluetoothGatt(deviceMirror.bluetoothGatt)
                            .setPropertyType(bleConfig.getBleChannelNotify())
                            .setServiceUUID(bleConfig.getBleUUIDNotify().getServiceUUID())
                            .setCharacteristicUUID(bleConfig.getBleUUIDNotify().getCharacteristicUUID())
                            /*.setDescriptorUUID(bleConfig.getBleUUIDNotify().getDescriptorUUID())*/
                            .builder()
                    deviceMirror.bindChannel(object : IBleCallback {
                        override fun onSuccess(data: ByteArray, bluetoothGattChannel: BluetoothGattChannel, bluetoothLeDevice: BluetoothLeDevice) {
                            deviceMirror.setNotifyListener(bluetoothGattChannel.gattInfoKey, object : IBleCallback{
                                override fun onSuccess(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?) {
                                    if (iBleCoreWriteCallback.onReceivedFinish()) {
                                        ViseLog.i( "onReceivedDataFinish, data lost")
                                    }
                                    iBleCoreWriteCallback.onReceivedData(data, bluetoothGattChannel, bluetoothLeDevice)
                                    if (iBleCoreWriteCallback.onReceivedFinish()) {
                                        deviceMirror.unregisterNotify(bleConfig.getBleChannelNotify() == PropertyType.PROPERTY_INDICATE)
                                        iBleCoreWriteCallback.onReceivedFinish(iBleCoreWriteCallback.getReceivedTotalData()?:ByteArray(0))
                                    }
                                }

                                override fun onFailure(exception: BleException?) {
                                    iBleCoreWriteCallback.onFailure(exception?.parse())
                                }
                            })
                            val bluetoothGattChannel111 = BluetoothGattChannel.Builder()
                                    .setBluetoothGatt(deviceMirror.bluetoothGatt)
                                    .setPropertyType(bleConfig.getBleChannelWrite())
                                    .setServiceUUID(bleConfig.getBleUUIDWrite().getServiceUUID())
                                    .setCharacteristicUUID(bleConfig.getBleUUIDWrite().getCharacteristicUUID())
                                    /*.setDescriptorUUID(bleConfig.getBleUUIDWrite().getDescriptorUUID())*/
                                    .builder()
                            deviceMirror.bindChannel(object : IBleCallback{
                                override fun onSuccess(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?) {
                                    ViseLog.i("写入成功的数据:${ByteUtils.bytesToHexString(data)}")
                                    iBleCoreWriteCallback.onWriteSuccess(data, bluetoothGattChannel, bluetoothLeDevice)
                                }

                                override fun onFailure(exception: BleException?) {
                                    iBleCoreWriteCallback.onFailure(exception?.parse())
                                }
                            }, bluetoothGattChannel111)
                            deviceMirror.writeData(data)
                            ViseLog.i("申请写入的数据:${ByteUtils.bytesToHexString(data)}")
                        }

                        override fun onFailure(exception: BleException) {
                            iBleCoreWriteCallback.onFailure(exception.parse())
                        }
                    }, bluetoothGattChannel)
                    deviceMirror.registerNotify(isIndicate)
                    return
                }
                val bluetoothGattChannel = BluetoothGattChannel.Builder()
                        .setBluetoothGatt(deviceMirror.bluetoothGatt)
                        .setPropertyType(bleConfig.getBleChannelWrite())
                        .setServiceUUID(bleConfig.getBleUUIDWrite().getServiceUUID())
                        .setCharacteristicUUID(bleConfig.getBleUUIDWrite().getCharacteristicUUID())
                        /*.setDescriptorUUID(bleConfig.getBleUUIDWrite().getDescriptorUUID())*/
                        .builder()
                deviceMirror.bindChannel(object : IBleCallback{
                    override fun onSuccess(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?) {
                        ViseLog.i("写入成功的数据:${ByteUtils.bytesToHexString(data)}")
                        iBleCoreWriteCallback.onWriteSuccess(data, bluetoothGattChannel, bluetoothLeDevice)
                    }

                    override fun onFailure(exception: BleException?) {
                        iBleCoreWriteCallback.onFailure(exception?.parse())
                    }
                }, bluetoothGattChannel)
                deviceMirror.writeData(data)
                ViseLog.i("申请写入的数据:${ByteUtils.bytesToHexString(data)}")
            }

            override fun onDisconnect(isActive: Boolean) {
                iBleCoreWriteCallback.onFailure(if (isActive) BleCoreException(BleCoreExceptionCode.DISCONNECTED_ISACTIVE) else BleCoreException(BleCoreExceptionCode.DISCONNECTED))
            }
        })
    }

    override fun readData(deviceMirror: DeviceMirror, propertyTypeRead: PropertyType, bleUUIDRead: BleUUID, iBleCallback: IBleCallback) {
        val bluetoothGattChannel = BluetoothGattChannel.Builder()
                .setBluetoothGatt(deviceMirror.bluetoothGatt)
                .setPropertyType(propertyTypeRead)
                .setServiceUUID(bleUUIDRead.getServiceUUID())
                .setCharacteristicUUID(bleUUIDRead.getCharacteristicUUID())
                .setDescriptorUUID(bleUUIDRead.getDescriptorUUID())
                .builder()
        deviceMirror.bindChannel(iBleCallback, bluetoothGattChannel)
        deviceMirror.readData()
    }

    /*private fun receiveData(deviceMirror: DeviceMirror, bleConfig: BleConfig, iBleCoreResponseCallback: IBleCoreResponseCallback) {
        val isIndicate = bleConfig.getBleChannelNotify() == PropertyType.PROPERTY_INDICATE
        val bluetoothGattChannel = BluetoothGattChannel.Builder()
                .setBluetoothGatt(deviceMirror.bluetoothGatt)
                .setPropertyType(bleConfig.getBleChannelNotify())
                .setServiceUUID(bleConfig.getBleUUIDNotify().getServiceUUID())
                .setCharacteristicUUID(bleConfig.getBleUUIDNotify().getCharacteristicUUID())
                .setDescriptorUUID(bleConfig.getBleUUIDNotify().getDescriptorUUID())
                .builder()
        deviceMirror.bindChannel(object : IBleCallback {
            override fun onSuccess(data: ByteArray, bluetoothGattChannel: BluetoothGattChannel, bluetoothLeDevice: BluetoothLeDevice) {
                deviceMirror.setNotifyListener(bluetoothGattChannel.gattInfoKey, object : IBleCallback{
                    override fun onSuccess(data: ByteArray?, bluetoothGattChannel: BluetoothGattChannel?, bluetoothLeDevice: BluetoothLeDevice?) {
                        iBleCoreResponseCallback.onReceivedData(data, bluetoothGattChannel, bluetoothLeDevice)
                    }

                    override fun onFailure(exception: BleException?) {
                        iBleCoreResponseCallback.onFailure(exception?.parse())
                    }
                })

            }

            override fun onFailure(exception: BleException) {
                iBleCoreResponseCallback.onFailure(exception.parse())
            }
        }, bluetoothGattChannel)
        deviceMirror.registerNotify(isIndicate)
    }*/

    companion object {
        private const val tag = "BleCoreManager"
    }
}