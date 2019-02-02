[![Release](https://jitpack.io/v/MercurieVV/properties-as-code.svg)]
(https://jitpack.io/#MercurieVV/properties-as-code)
# properties-as-code
Generate classes and its instances from property files (yaml currently)

For example you input
```scala
    val tuple = PropertiesToObject(
      List(
        "aa.bb.cc=123",
        "aa.bb.dd=aaa",
      ),
      Map("cc" -> "Double")
    )
```

and on output tou will get tuple of 2 strings,
case classes
```scala
case class AppPproperties(
 aa: aa
)
case class aa(
 bb: bb
)
case class bb(
 dd: String,
cc: Double
)
```
and ist inits:
```scala
AppPproperties(
  aa = aa(
  bb = bb(
    dd = "aaa")
    cc = 123)
  )
)
```