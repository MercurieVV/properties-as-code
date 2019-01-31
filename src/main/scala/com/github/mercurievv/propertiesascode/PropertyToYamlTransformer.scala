package com.github.mercurievv.propertiesascode

import java.util

import io.circe.Json
import io.circe.generic.auto._
//import io.circe.java8.time._

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/23/2019
  * Time: 7:30 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object PropertyToYamlTransformer {
  def applyJ(keyValue: util.Map[String, String]): String = {
    import scala.collection.JavaConverters._

    val scalaMap = keyValue.asScala
    apply(scalaMap.toMap)
      .spaces2
  }

  def apply(scalaMap: Map[String, String]): Json = {
    scalaMap.toStream
      .map(t => propToJson(t._1, t._2))
      .fold(Json.Null)((json: Json, json0: Json) => json.deepMerge(json0))
    //      .asYaml

  }

  def propToJson(propertyName: String, value: String): Json = {
    propertyName.split('.')
      .foldRight({
        if (value.forall(_.isDigit))
          Json.fromBigDecimal(BigDecimal(value))
        else
          Json.fromString(value)
      })({
        (op, name) => Json.obj((op, name))
      })
  }
}
