package org.zen.core.utils

import java.io.{ FileInputStream, IOException, InputStream }
import java.util.Properties
import java.io.File
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger

trait RichProperties {
  def getProperty(name: String): String

  def getInt(name: String, d: Int): Int = {
    val v = getProperty(name)
    if (v == null) return d else v.trim().toInt
  }

  def getDouble(name: String, d: Double): Double = {
    val v = getProperty(name)
    if (v == null) return d else v.trim().toDouble
  }

  def getBoolean(name: String, d: Boolean): Boolean = {
    val v = getProperty(name)
    if (v == null) d else v.trim().toBoolean
  }

  def getString(name: String): String = {
    val v = getProperty(name)
    if (v == null) null else v.trim()
  }

  def getList(name: String): List[String] = {
    val v = getProperty(name)
    if (v == null) List.empty[String] else v.split(",").toList map { x => x.trim() }
  }

  def getIntList(name: String): List[Int] = {
    val v = getProperty(name)
    if (v == null) List.empty[Int] else v.split(",").toList map { x => x.trim().toInt }
  }
}

object Config/* extends Loggable */{
  val logger = Logger(LoggerFactory.getLogger(Config.getClass))
  def getConfig(filename: String): Properties with RichProperties = {
    val prop = new Properties with RichProperties
    prop.putAll(getProperties(filename))
    prop.putAll(getProperties(filename + ".local"))
    prop
  }
  
  def loadProps(filePath: String): Properties with RichProperties = {
    val props = new Properties
    val fis = new FileInputStream(filePath)
    props.load(fis)
    fis.close()
    new Properties(props) with RichProperties
  }

  def getConfigFormPathOrResource(path: String): Properties with RichProperties = {
		val pro = new Properties
    var config: InputStream = null
    //val file = new File(path)
    try{
      config = new FileInputStream(new File(path))
    }catch{
      case e:Exception =>
        logger.warn("load properties file "+path+" error!")
        config = getClass.getResourceAsStream(path)
    }
    pro.load(config)
    config.close()
    logger.info("load config finish! "+pro)
    new Properties(pro) with RichProperties
  }
  private def getProperties(filename: String): Properties = {
    val prop = new Properties
    var is: InputStream = null;
    try {
      is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)
      if (is != null) prop.load(is)
      return prop
    } catch {
      case e: Exception =>
        logger.warn("Error while loading file: " + filename, e)
        return prop
    } finally {
      try {
        if (is != null) is.close()
      } catch {
        case e: IOException => {}
      }
    }
  }
}