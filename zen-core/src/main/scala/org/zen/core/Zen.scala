package org.zen.core

import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger

object Zen extends App {
  val logger = Logger(LoggerFactory.getLogger(Zen.getClass))
  logger.info("This is Scala-logger info...")
  logger.debug("This is Scala-logger debug...")
  logger.error("This is Scala-logger error...")
  //1,读取配置

  //2,读取插件配置

  //3,加载扩展点,在扩展点的每步中加载插件
}