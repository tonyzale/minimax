object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val l = 1 :: 2 :: Nil                           //> l  : List[Int] = List(1, 2)
  val v = l.toVector                              //> v  : Vector[Int] = Vector(1, 2)
  v(1)                                            //> res0: Int = 2
  val x = List.fill(3)("foo")                     //> x  : List[String] = List(foo, foo, foo)
}