package impl

import shapeless._
import shapeless.labelled._
import shapeless.record._
import shapeless.ops.record._
import shapeless.syntax.singleton._
import io.circe._
import io.circe.syntax._
import io.circe.generic.auto._

case class User(name: String, age: Int)

object Impl {
  val user = User("scalaちゃん", 18)

  def refrection(): Unit = {
    def toJSONString(elem: Any): String = elem match {
      case elem: Int    => s"${elem}"
      case elem: String => s""""${elem}""""
    }

    val fields = user.getClass.getDeclaredFields.map(_.getName)
    val values = user.productIterator.toList.map(toJSONString)

    val json = fields
      .zip(values)
      .map {
        case (field, value) => s""""${field}":${value}"""
      }
      .mkString("{", ",", "}")

  }

  def shapeless(): Unit = {
    object toJSONString extends Poly1 {
      implicit def atInt[K] =
        at[FieldType[K, Int]](elem => field[K](s"${elem}"))
      implicit def atString[K] =
        at[FieldType[K, String]](elem => field[K](s""""${elem}""""))
    }

    val userGen = LabelledGeneric[User]
    val fields: List[String] =
      Keys[userGen.Repr].apply.toList[Symbol].map(_.name)
    val values = userGen
      .to(user)
      .map(toJSONString)
      .toList

    val json = fields
      .zip(values)
      .map {
        case (field, value) => s""""${field}":${value}"""
      }
      .mkString("{", ",", "}")
  }

  def circe(): Unit = {
    val json = user.asJson.noSpaces
  }

  def noBottleneck(): Unit = {
    val json = s"""{"name":"${user.name}","age":${user.age}"""
  }
}
