package org.zen.core.utils

import org.apache.log4j.PropertyConfigurator

object ConfigUtils extends Loggable {

  //-Dconfig=[config dir]
  var conf = System.getProperty("config")
  var prop = Config.getConfigFormPathOrResource(conf)

  val DEBUG = prop.getBoolean("app.debug", false)
  //读取报表的一些配置

}