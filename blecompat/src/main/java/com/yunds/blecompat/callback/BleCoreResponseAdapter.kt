package com.yunds.blecompat.callback

/**
 * 定禅天 净琉璃
 * 2018-09-11 15:25:55 星期二
 * 描述：ble响应适配器
 */
abstract class BleCoreResponseAdapter: IBleCoreResponseCallback {

    var receivedFinish: Boolean = false//是否接收完成

    var dataTotalLength:Int = 0//数据总长度

    var dataTotal: ByteArray? = null//接收到的总数据

    /**
     * 数据是否接收完成
     */
    fun onReceivedFinish(): Boolean {
        return receivedFinish
    }

    /**
     * 获取接收到的总数据
     */
    fun getReceivedTotalData(): ByteArray? {
        return dataTotal
    }

    /**
     * 回调接收收据
     */
    abstract fun onReceivedFinish(totalData: ByteArray)
}