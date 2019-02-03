package com.github.mercurievv.propertiesascode

import io.circe.YamlToObject
import io.circe._
import io.circe.yaml._
import java.io

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/30/2019
  * Time: 11:38 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object PropertiesToObject {
  def apply(rootClassName: String, yamls: List[String], properties: List[String], types: Map[String, String]): (String, String) = {
    val propsJson = PropertyToYamlTransformer(properties.map(_.split('=')).map{case Array(a: String,b : String) => (a,b)}.toMap)
    val json = (yamls.map(yaml.parser.parse).map(_.right.get) :+ propsJson)
      .foldRight(Json.obj())((j1: Json, j2: Json) => j1.deepMerge(j2))
    (
      YamlToObject.jsonToClass(json, rootClassName, types),
      YamlToObject.jsonToClassInstance(json, rootClassName, types),
    )
  }

  def applyJ(rootClassName: String, yamls: java.util.List[String], properties: java.util.List[String], types: java.util.Map[String, String]): (String, String) ={
    import scala.collection.JavaConverters._
    apply(rootClassName, yamls.asScala.toList, properties.asScala.toList, types.asScala.toMap)
  }
}
