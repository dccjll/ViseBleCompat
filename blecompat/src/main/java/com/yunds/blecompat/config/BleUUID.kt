package com.yunds.blecompat.config

import java.util.*

/**
 * 定禅天 净琉璃
 * 2018-09-11 09:41:18 星期二
 * 描述：蓝牙通讯UUID描述
 */
class BleUUID {
    /**
     * 服务uuid
     */
    private var serviceUUID: UUID? = null
    /**
     * 特征uuid
     */
    private var characteristicUUID: UUID? = null

    /**
     * 特征描述符uuid
     */
    private var descriptorUUID: UUID? = null

    fun getServiceUUID(): UUID? {
        return serviceUUID
    }

    fun setServiceUUID(serviceUUIDWrite: UUID): BleUUID {
        this.serviceUUID = serviceUUIDWrite
        return this
    }

    fun getCharacteristicUUID(): UUID? {
        return characteristicUUID
    }

    fun setCharacteristicUUID(characteristicUUIDWrite: UUID): BleUUID {
        this.characteristicUUID = characteristicUUIDWrite
        return this
    }

    fun getDescriptorUUID(): UUID? {
        return descriptorUUID
    }

    fun setDescriptorUUID(descriptorUUID: UUID): BleUUID {
        this.descriptorUUID = descriptorUUID
        return this
    }
}
