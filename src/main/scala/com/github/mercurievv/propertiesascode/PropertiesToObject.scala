package com.github.mercurievv.propertiesascode

import io.circe.YamlToObject
import io.circe._

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/30/2019
  * Time: 11:38 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object PropertiesToObject {
  def apply(rootClassName: String, yamls: List[String], properties: Map[String, String], types: Map[String, String]): (String, String) = {
    val json: Json = mergeIntoSingleYaml(yamls, properties)
    (
      YamlToObject.jsonToClass(json, rootClassName, types),
      YamlToObject.jsonToClassInstance(json, rootClassName, types),
    )
  }

  private def mergeIntoSingleYaml(yamls: List[String], properties: Map[String, String]) = {
    val propsJson = PropertyToYamlTransformer(properties)
    val json = (yamls.map(yaml.parser.parse).map(_.right.get) :+ propsJson)
      .foldRight(Json.obj())((j1: Json, j2: Json) => j1.deepMerge(j2))
    json
  }

  def mergeIntoSingleYamlJ(yamls: java.util.List[String], properties: java.util.Map[String, String]): String = {
    import scala.collection.JavaConverters._
    Utils.jsonToYamlString(mergeIntoSingleYaml(yamls.asScala.toList, properties.asScala.toMap))
  }

  def applyJ(rootClassName: String, yamls: java.util.List[String], properties: java.util.Map[String, String], types: java.util.Map[String, String]): (String, String) = {
    import scala.collection.JavaConverters._
    apply(rootClassName, yamls.asScala.toList, properties.asScala.toMap, types.asScala.toMap)
  }
}
