object worksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  println("Welcome to the Scala worksheet");$skip(24); 
  val l = 1 :: 2 :: Nil;System.out.println("""l  : List[Int] = """ + $show(l ));$skip(21); 
  val v = l.toVector;System.out.println("""v  : Vector[Int] = """ + $show(v ));$skip(7); val res$0 = 
  v(1);System.out.println("""res0: Int = """ + $show(res$0));$skip(30); 
  val x = List.fill(3)("foo");System.out.println("""x  : List[String] = """ + $show(x ));$skip(36); 
  val vec = Vector(Vector(1, 2, 3));System.out.println("""vec  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Int]] = """ + $show(vec ));$skip(13); val res$1 = 
  vec.length;System.out.println("""res1: Int = """ + $show(res$1));$skip(47); 
  def len(v: Vector[Vector[Int]]) = {v.length};System.out.println("""len: (v: Vector[Vector[Int]])Int""");$skip(10); val res$2 = 
	len(vec);System.out.println("""res2: Int = """ + $show(res$2))}

}
