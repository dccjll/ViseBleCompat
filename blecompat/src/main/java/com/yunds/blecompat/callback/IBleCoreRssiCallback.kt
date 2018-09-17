package com.yunds.blecompat.callback

import com.yunds.blecompat.exception.BleCoreException

/**
 * 定禅天 净琉璃
 * 2018-09-11 15:28:59 星期二
 * 描述：rssi回调
 */
interface IBleCoreRssiCallback {

    /**
     * 接收信息成功
     * @param rssi 信号
     */
    fun onSuccess(rssi: Int?)

    /**
     * 失败
     * @param exception 异常信息
     */
    fun onFailure(exception: BleCoreException?)
}