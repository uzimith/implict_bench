package impl

object Main extends App {
  def mapMeth(x: Int): Seq[Int] = {
    1.to(x).map(x => x.*(2))
  }

  def forMeth(x: Int): Seq[Int] = {
    for {
      a <- 1 to x
    } yield {
      a.*(2)
    }
  }

  def nestForMeth(x: Int): Seq[Int] = {
    for {
      a <- 1 to x
      b <- 1 to a
    } yield {
      a + b
    }
  }

  def flatMapMeth(x: Int): Seq[Int] = {
    1.to(x).flatMap { a =>
      (1 to a).map(b => a + b)
    }
  }
}
