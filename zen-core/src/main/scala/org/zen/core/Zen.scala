package org.zen.core

import org.zen.core.utils.ConfigUtils


object Zen extends App{
  
  //1,读取配置
  val test1 = ConfigUtils.prop.getString("test.1")
  println(test1)
  //2,读取插件配置
  
  //3,加载扩展点,在扩展点的每步中加载插件
}