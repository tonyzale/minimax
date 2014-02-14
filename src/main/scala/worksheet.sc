package minimax;

object worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val l = 1 :: 2 :: Nil                           //> l  : List[Int] = List(1, 2)
  val v = l.toVector                              //> v  : Vector[Int] = Vector(1, 2)
  v(1)                                            //> res0: Int = 2
  val x = List.fill(3)("foo")                     //> x  : List[String] = List(foo, foo, foo)
  val vec = Vector(Vector(1, 2, 3))               //> vec  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[I
                                                  //| nt]] = Vector(Vector(1, 2, 3))
  vec.length                                      //> res1: Int = 1
  def len(v: Vector[Vector[Int]]) = {v.length}    //> len: (v: Vector[Vector[Int]])Int
	len(vec)                                  //> res2: Int = 1
	for (i <- 0 to 2) println(i)              //> 0
                                                  //| 1
                                                  //| 2
	val s = Vector("a", "b", "c")             //> s  : scala.collection.immutable.Vector[String] = Vector(a, b, c)
	"abc".hashCode                            //> res3: Int = 96354
	"abcd".hashCode                           //> res4: Int = 2987074
	"ab".hashCode                             //> res5: Int = 3105
	"a".hashCode                              //> res6: Int = 97
	"abcdef".hashCode                         //> res7: Int = -1424385949
}