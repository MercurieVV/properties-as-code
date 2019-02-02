package io.circe

import io.circe.Json.{JBoolean, JNumber, JObject, JString}

import scala.util.matching.Regex

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/24/2019
  * Time: 4:12 AM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
object YamlToObject {

  private def getTypeName(propName: String, jsonValue: Json, types: Map[String, String]): String = (types.get(propName), propName, jsonValue) match {
    case (Some(t), _, _) => t
    case (None, name, value: JBoolean) => "Boolean"
    case (None, name, value: JNumber) => "BigDecimal"
    case (None, name, value: JString) => "String"
    case (None, name, value: JObject) => name
  }

  def jsonToClass(json: Json, name: String = "AppPproperties", types: Map[String, String] = Map.empty): String = {
    val o = json.asInstanceOf[JObject]
    val objectProps = o.value.toMap
    val objPropsStr = objectProps.map { case (propName, jsonValue) => s"$propName: ${getTypeName(propName, jsonValue, types)}" }.mkString(",\n")
    val init = s"case class $name(\n $objPropsStr\n)\n"
    val res = objectProps
      .filter(_._2.isInstanceOf[JObject])
      .foldLeft("")({
        case (classCreate, (propName, propJson)) =>
          jsonToClass(propJson.asInstanceOf[JObject], propName, types)
      })
    init + res + "\n"
  }

  def jsonToClassInstance(json: Json, className: String = "AppPproperties"): String = {
    json match {
      case o: JObject =>
        o.value.toMap
          .foldLeft(s"$className(")({
            case (classInstantiate, (propName, propJson)) =>
              val propInstantiate = jsonToClassInstance(propJson, propName)
              s"$classInstantiate \n$propName = $propInstantiate\n"
          }) + ")"
      case s: JString => s""""${s.value}""""
      case n: JNumber => s"""${n.value}"""
    }
  }
}

