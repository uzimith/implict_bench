import shapeless._
import shapeless.ops.record._
import shapeless.ops.hlist.{Mapper, ToTraversable}
import shapeless.tag.Tagged
import Impl._

object Main extends App {

  val user = User("scalaちゃん", 18)
  val post = Post(
    "Scala完全ガイド",
    true,
    user
  )

//  println(reflection(post))
  println(shapeless(post))
//  println(shapelessAny())
//  println(circe())
//  println(jackson())
//  println(noBottleneck(post))
}
