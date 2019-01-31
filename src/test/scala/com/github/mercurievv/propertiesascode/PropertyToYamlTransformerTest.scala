package com.github.mercurievv.propertiesascode

import io.circe.{Json, ParsingFailure}
import org.scalatest.FunSuite

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/23/2019
  * Time: 9:27 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
class PropertyToYamlTransformerTest extends FunSuite {
  test("test") {
    val json = PropertyToYamlTransformer.propToJson("a.b.c", "d")

    assert(Json.obj(("a",
      Json.obj(("b",
        Json.obj(("c",
          Json.fromString("d")))))
    )) == json)
  }

  test("to string"){
    PropertyToYamlTransformer(Map(
      "a.b.c" -> "1",
      "a.b.d" -> "2",
    ))
  }

  test("yaml"){
    import io.circe.yaml._
    val json: Either[ParsingFailure, Json] = parser.parse(
      """
        |key: #comment 1
        |   mm: value line 1 #comment 2
        |   pp: value line 2
        |   #comment 3
        |   dd: value line 3
        |""".stripMargin)
    println(json.right.get)

  }

}
