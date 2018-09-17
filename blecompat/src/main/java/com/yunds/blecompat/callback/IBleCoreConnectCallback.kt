package com.yunds.blecompat.callback

import com.vise.baseble.core.DeviceMirror
import com.yunds.blecompat.exception.BleCoreException

/**
 * 定禅天 净琉璃
 * 2018-09-11 15:30:05 星期二
 * 描述：连接回调
 */
interface IBleCoreConnectCallback {
    /**
     * 连接成功
     * @param deviceMirror 设备
     */
    fun onConnectSuccess(deviceMirror: DeviceMirror?)

    /**
     * 连接失败
     * @param exception 异常信息
     */
    fun onConnectFailure(exception: BleCoreException?)

    /**
     * 连接断开
     * @param isActive 是否是主动断开的连接
      */
    fun onDisconnect(isActive: Boolean)
}