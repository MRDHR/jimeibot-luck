package com.dhr.bot.luck

import kotlinx.serialization.Serializable
import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object LuckData : AutoSavePluginData("luck") {
    var users: MutableList<User> by value() // List、Set 或 Map 同样支持 var。但请注意这是非引用赋值（详见下文）。
}

@Serializable
class User(var userId: Long, var score: Int, var timeStamp: Long)

