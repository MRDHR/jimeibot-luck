package com.dhr.bot.luck

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.content
import java.util.*

object LuckPlugin : KotlinPlugin(
    JvmPluginDescription(
        id = LuckPlugin::class.java.name,
        version = "0.1.0",
        name = "今日人品 power by 一生的等待"
    )
) {
    override fun onEnable() {
        LuckData.reload()
        initLuck()
    }

    private fun initLuck() {
        GlobalEventChannel.subscribeGroupMessages {
            always {
                if (message.content == "jrrp" || message.content == "今日人品" || message.content == "今日rp") {
                    var find = LuckData.users.find { it.userId == sender.id }
                    val calendar = Calendar.getInstance(Locale.CHINA)
                    calendar.set(Calendar.HOUR_OF_DAY, 0)
                    calendar.set(Calendar.MINUTE, 0)
                    calendar.set(Calendar.SECOND, 0)
                    calendar.set(Calendar.MILLISECOND, 0)
                    val user = User(sender.id, (60..200).random(), calendar.timeInMillis)
                    if (null == find) {
                        LuckData.users.add(user)
                    } else {
                        if (calendar.time.after(Date(find.timeStamp))) {
                            //第二天
                            LuckData.users[LuckData.users.lastIndexOf(find)] = user
                        }
                    }
                    find = LuckData.users.find { it.userId == sender.id }
                    if (find != null) {
                        sender.group.sendMessage(senderName + "今日人品：" + find.score.toString())
                    }
                }
            }
        }
    }
}