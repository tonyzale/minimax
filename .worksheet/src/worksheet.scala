object worksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  println("Welcome to the Scala worksheet");$skip(24); 
  val l = 1 :: 2 :: Nil;System.out.println("""l  : List[Int] = """ + $show(l ));$skip(21); 
  val v = l.toVector;System.out.println("""v  : Vector[Int] = """ + $show(v ));$skip(7); val res$0 = 
  v(1);System.out.println("""res0: Int = """ + $show(res$0));$skip(30); 
  val x = List.fill(3)("foo");System.out.println("""x  : List[String] = """ + $show(x ))}
}
