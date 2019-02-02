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
      List(
        "aa.bb.cc=123",
        "aa.bb.dd=aaa",
      ),
      Map("cc" -> "Double")
    )
    assert(tuple._1.replace("\n", "") ==
      """
        |case class AppPproperties(
        | aa: aa
        |)
        |case class aa(
        | bb: bb
        |)
        |case class bb(
        | dd: String,
        |cc: Double,
        |mm: String
        |)
        |""".stripMargin.replace("\n", ""))

    assert(tuple._2.replace("\n", "").replace(" ", "") ==
      """
        |AppPproperties(
        |aa = aa(
        |bb = bb(
        |dd = "aaa"
        |cc = 123
        |mm = "zzz"
        |)
        |)
        |)
      |""".stripMargin.replace("\n", "").replace(" ", ""))
  }

}
