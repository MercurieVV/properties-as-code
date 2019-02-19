package com.github.mercurievv.propertiesascode

import io.circe.Json
import io.circe.yaml._
import io.circe.yaml.syntax._

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 2/19/2019
  * Time: 11:06 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object Utils {
 def jsonToYamlString(json: Json): String = json.asYaml.spaces2
}
