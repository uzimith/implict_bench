import shapeless._
import shapeless.labelled._
import shapeless.ops.hlist.{Mapper, ToTraversable}
import shapeless.ops.record.Keys
import shapeless.tag.Tagged

case class User(
    name: String,
    age: Int
)
case class Post(
    title: String,
    isPublished: Boolean,
    writer: User,
)

object Impl {
  object Func {
    def toJSONString(elem: Any): String = elem match {
      case elem: Int     => s"${elem}"
      case elem: Boolean => if (elem) "true" else "false"
      case elem: String  => s""""${elem}""""
      case elem: Product =>
        val fields = elem.getClass.getDeclaredFields.map(_.getName)
        val values = elem.productIterator.toList.map(Func.toJSONString)
        fields
          .zip(values)
          .map {
            case (field, value) => s""""${field}":${value}"""
          }
          .mkString("{", ",", "}")
    }
  }

  def reflection(elem: Post): String = {
    Func.toJSONString(elem)
  }

  object JSONString extends Poly1 {
    implicit def atInt[K] =
      at[FieldType[K, Int]](elem => s"${elem}")
    implicit def atBoolean[K] =
      at[FieldType[K, Boolean]](elem => s"${elem}")
    implicit def atString[K] =
      at[FieldType[K, String]](elem => s""""${elem}"""")
    implicit def atUser[K] =
      at[FieldType[K, User]](elem => s"${shapeless(elem)}")
  }

  object symbolName extends Poly1 {
    implicit def atTaggedSymbol[T] = at[Symbol with Tagged[T]](_.name)
  }

  def shapeless[T,
                Repr <: HList,
                KeysRepr <: HList,
                KeyMapperRepr <: HList,
                ValueMapperRepr <: HList](elem: T)(
      implicit gen: LabelledGeneric.Aux[T, Repr],
      keys: Keys.Aux[Repr, KeysRepr],
      keyMapper: Mapper.Aux[symbolName.type, KeysRepr, KeyMapperRepr],
      valueMapper: Mapper.Aux[JSONString.type, Repr, ValueMapperRepr],
      keyTraversable: ToTraversable.Aux[KeyMapperRepr, List, String],
      valueTraversable: ToTraversable.Aux[ValueMapperRepr, List, String])
    : String = {
    val fields = keys().map(symbolName).toList
    val values = gen
      .to(elem)
      .map(JSONString)
      .toList

    fields
      .zip(values)
      .map {
        case (field, value) => s""""${field}":${value}"""
      }
      .mkString("{", ",", "}")
  }

//  def shapelessAny[A](elem: A): String = {
//    val userGen = LabelledGeneric[User]
//    val fields: List[String] =
//      Keys[userGen.Repr].apply.toList[Symbol].map(_.name)
//    val values = user.productIterator.toList.map(Func.toJSONString)
//
//    fields
//      .zip(values)
//      .map {
//        case (field, value) => s""""${field}":${value}"""
//      }
//      .mkString("{", ",", "}")
//  }
//
//  def circe[A](elem: A): String = {
//    elem.asJson.noSpaces
//  }
//
//  val mapper = new ObjectMapper
//  mapper.registerModule(DefaultScalaModule)
//
//  def jackson[A](elem: A): String = {
//    mapper.writeValueAsString(elem)
//  }
//
  def noBottleneck(elem: Post): String = {
    s"""{"title":"${elem.title}","isPublished":${elem.isPublished},"writer":{"name":"${elem.writer.name}","age":${elem.writer.age}}}"""

  }
}
