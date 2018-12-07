package impl

import shapeless.labelled.FieldType
import shapeless.{LabelledGeneric, Poly1}
import shapeless.ops.record.Keys

case class User(name: String, age: Int)

object Main extends App {
  def refrection(): Unit = {
    def toJSONString(elem: Any): String = elem match {
      case elem: Int    => s"${elem}"
      case elem: String => s""""${elem}""""
    }

    val user = User("scalaちゃん", 18)
    val fields = user.getClass.getDeclaredFields.map(_.getName)
    val values = user.productIterator.toList.map(toJSONString)

    val json = fields
      .zip(values)
      .map {
        case (field, value) => s"${field}: ${value}"
      }
      .mkString("{", ", ", "}")

    println(json)
  }

  def shapeless(): Unit = {
    object toJSONString extends Poly1 {
      implicit val atInt = at[FieldType[Symbol, Int]](elem => s"${elem}")
      implicit val atString =
        at[FieldType[Symbol, String]](elem => """"${elem}"""")
    }

    val genUser = LabelledGeneric[User]
    val fields =
      Keys[genUser.Repr].apply.toList[Symbol].map(_.name)
    val values =
      genUser.to(User("scalaちゃん", 18)).map(toJSONString)
    println(fields)
    println(values)
  }

  refrection()
  shapeless()
}
