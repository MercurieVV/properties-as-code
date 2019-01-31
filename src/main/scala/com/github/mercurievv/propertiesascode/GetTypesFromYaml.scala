package com.github.mercurievv.propertiesascode

import scala.util.matching.Regex

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/25/2019
  * Time: 6:25 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object GetTypesFromYaml {
  val pattern: Regex = "(\\w*)\\s*:\\s*.*\\#type-(\\w*)\\s*".r
  def yamlCommentsToType(yaml: String): Map[String, String] = {
    yaml.lines.flatMap(line => {
      pattern.findAllMatchIn(line)
        .map(m => (m.group(1), m.group(2)))
    }).toMap
  }

}
