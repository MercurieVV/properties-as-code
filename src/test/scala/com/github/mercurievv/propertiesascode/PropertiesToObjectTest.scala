package com.github.mercurievv.propertiesascode

import org.scalatest.FunSuite

/**
  * Created with IntelliJ IDEA.
  * User: Victor Mercurievv
  * Date: 1/30/2019
  * Time: 11:41 PM
  * Contacts: email: mercurievvss@gmail.com Skype: 'grobokopytoff' or 'mercurievv'
  */
class PropertiesToObjectTest extends FunSuite {

  test("testApply") {
    val tuple = PropertiesToObject(
      "AppPproperties",
      List(
        """
          |aa:
          |  bb:
          |    mm: zzz
        """.stripMargin),
      Map(
        "aa.bb.cc" -> "123",
        "aa.bb.dd" -> "aaa",
        "aa.rr.ff" -> "aaa",
      ),
      Map(
        "cc" -> "Double",
        "rr" -> "rr2",
      )
    )
    assert(tuple._1.replace("\n", "").replace(" ", "") ==
      """
        |case class AppPproperties(
        | aa: aa
        |)
        |case class aa(
        | rr: rr2,
        | bb: bb
        |)
        |case class rr(
        | ff:String
        |)
        |case class bb(
        | dd: String,
        | cc: Double,
        | mm: String
        |)
        |""".stripMargin.replace("\n", "").replace(" ", ""))

    assert(tuple._2.replace("\n", "").replace(" ", "") ==
      """
        |AppPproperties(
        |aa = aa(
        |rr = rr2(
        | ff = "aaa",
        |),
        |bb = bb(
        |dd = "aaa",
        |cc = 123,
        |mm = "zzz",
        |),
        |),
        |)
        |""".stripMargin.replace("\n", "").replace(" ", ""))
  }


  test("mergeIntoSingleYaml"){
    import scala.collection.JavaConverters._
    val yaml = PropertiesToObject.mergeIntoSingleYamlJ(
      List(
        """
          |aa:
          |  bb:
          |    mm: zzz
        """.stripMargin).asJava,
      Map(
        "aa.bb.cc" -> "123",
        "aa.bb.dd" -> "aaa",
        "aa.rr.ff" -> "aaa",
      ).asJava,
    )
    println(yaml)
  }
}
