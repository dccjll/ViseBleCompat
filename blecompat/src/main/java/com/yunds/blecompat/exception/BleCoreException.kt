package com.yunds.blecompat.exception

import com.vise.baseble.common.BleExceptionCode
import com.vise.baseble.exception.BleException
import com.vise.log.ViseLog

/**
 * 定禅天 净琉璃
 * 2018-09-11 14:10:08 星期二
 * 描述：ble异常
 */
open class BleCoreException(var code: BleCoreExceptionCode? = BleCoreExceptionCode.OTHER_ERR): Exception()

fun BleException.parse(): BleCoreException {
    val bleCoreException = BleCoreException()
    when (this.code) {
        BleExceptionCode.TIMEOUT -> bleCoreException.code = BleCoreExceptionCode.TIMEOUT
        BleExceptionCode.CONNECT_ERR -> bleCoreException.code = BleCoreExceptionCode.CONNECT_ERR
        BleExceptionCode.GATT_ERR -> bleCoreException.code = BleCoreExceptionCode.GATT_ERR
        BleExceptionCode.INITIATED_ERR -> bleCoreException.code = BleCoreExceptionCode.INITIATED_ERR
        BleExceptionCode.OTHER_ERR -> bleCoreException.code = BleCoreExceptionCode.OTHER_ERR
        else -> {
            ViseLog.e("BleException.parse()", "can nont recognize exception")
        }
    }
    return bleCoreException
}