package com.iclick.tracking.sync.util

import org.apache.log4j.PropertyConfigurator

object ConfigUtils extends Loggable {

  var conf = System.getProperty("config")
  var prop = Config.getConfigFormPathOrResource(conf)

  val DEBUG = prop.getBoolean("app.debug", false)
  //读取报表的一些配置

}