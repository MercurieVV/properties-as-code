[![Release](https://jitpack.io/v/MercurieVV/properties-as-code.svg)]
(https://jitpack.io/#MercurieVV/properties-as-code)
# properties-as-code
Generate classes and its instances from property files (yaml currently)

For example you input
```scala
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
```

and on output tou will get tuple of 2 strings,
case classes
```scala
case class AppPproperties(
 aa: aa
)
case class aa(
 rr: rr2,
 bb: bb
)
case class rr(
 ff:String
)
case class bb(
 dd: String,
 cc: Double,
 mm: String
)
```
and ist inits:
```scala
AppPproperties(
    aa = aa(
        rr = rr2(
         ff = "aaa",
        ),
        bb = bb(
            dd = "aaa",
            cc = 123,
            mm = "zzz",
        ),
    ),
)
```