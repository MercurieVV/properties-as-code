package com.github.mercurievv.propertiesascode

import io.circe.YamlToObject
import io.circe._
import io.circe.yaml._

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/25/2019
  * Time: 3:18 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object YamlToCode {
  def apply(yamlStr: String): String = {
    val types = GetTypesFromYaml.yamlCommentsToType(yamlStr)
    val json = yaml.parser.parse(yamlStr)
    YamlToObject.jsonToClass(json.right.get, types = types)
  }
}
