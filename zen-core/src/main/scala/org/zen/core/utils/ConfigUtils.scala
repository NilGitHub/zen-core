package org.zen.core.utils


object ConfigUtils extends Loggable {
  //-Dconfig=[config dir]
  var conf = System.getProperty("config")
//  val log4j = System.getProperty("log4j")
//  System.setProperty("log4j.configuration",log4j)
  
  var prop = Config.getConfigFormPathOrResource(conf)

  //读取报表的一些配置

}