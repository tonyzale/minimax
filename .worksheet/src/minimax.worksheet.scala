package minimax;

object worksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(80); 
  println("Welcome to the Scala worksheet");$skip(24); 
  val l = 1 :: 2 :: Nil;System.out.println("""l  : List[Int] = """ + $show(l ));$skip(21); 
  val v = l.toVector;System.out.println("""v  : Vector[Int] = """ + $show(v ));$skip(7); val res$0 = 
  v(1);System.out.println("""res0: Int = """ + $show(res$0));$skip(30); 
  val x = List.fill(3)("foo");System.out.println("""x  : List[String] = """ + $show(x ));$skip(36); 
  val vec = Vector(Vector(1, 2, 3));System.out.println("""vec  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Int]] = """ + $show(vec ));$skip(13); val res$1 = 
  vec.length;System.out.println("""res1: Int = """ + $show(res$1));$skip(47); 
  def len(v: Vector[Vector[Int]]) = {v.length};System.out.println("""len: (v: Vector[Vector[Int]])Int""");$skip(10); val res$2 = 
	len(vec);System.out.println("""res2: Int = """ + $show(res$2));$skip(30); 
	for (i <- 0 to 2) println(i);$skip(31); 
	val s = Vector("a", "b", "c");System.out.println("""s  : scala.collection.immutable.Vector[String] = """ + $show(s ));$skip(16); val res$3 = 
	"abc".hashCode;System.out.println("""res3: Int = """ + $show(res$3));$skip(17); val res$4 = 
	"abcd".hashCode;System.out.println("""res4: Int = """ + $show(res$4));$skip(15); val res$5 = 
	"ab".hashCode;System.out.println("""res5: Int = """ + $show(res$5));$skip(14); val res$6 = 
	"a".hashCode;System.out.println("""res6: Int = """ + $show(res$6));$skip(19); val res$7 = 
	"abcdef".hashCode;System.out.println("""res7: Int = """ + $show(res$7))}
}
