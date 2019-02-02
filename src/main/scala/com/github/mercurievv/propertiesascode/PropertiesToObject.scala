package com.github.mercurievv.propertiesascode

import io.circe.YamlToObject

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/30/2019
  * Time: 11:38 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object PropertiesToObject {
  def apply(rootClassName: String, properties: List[String], types: Map[String, String]): (String, String) = {
    val json = PropertyToYamlTransformer(properties.map(_.split('=')).map{case Array(a: String,b : String) => (a,b)}.toMap)
//    GetTypesFromYaml.yamlCommentsToType()
    (
      YamlToObject.jsonToClass(json, rootClassName, types = types),
      YamlToObject.jsonToClassInstance(json, rootClassName),
    )
  }

  def applyJ(rootClassName: String, properties: java.util.List[String], types: java.util.Map[String, String]): Unit ={
    import scala.collection.JavaConverters._
    apply(rootClassName, properties.asScala.toList, types.asScala.toMap)
  }
}
