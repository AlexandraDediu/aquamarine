val x = 5 //also type inference
println(x)

var name = "John" //daca vrem ca valoarea sa se poata modifica
name = "Mary"
println(name)

var name1: String? = null //var can be nullable
val length = name1?.length ?: -1
println(length)

//val length2 = name1!!.length
//println(length2)

fun sumOfTwo(x: Int, y: Int): Int {
    return x + y

}

//inline fuction
fun sumOfTwo1(x: Int, y: Int) = x + y
println(sumOfTwo1(5, 6))

fun printName1(name: String) {
    println(name)
}
printName1("John")

fun printTwoWords(x: String, y: String = "a") {
    println("First string is : $x, second string is : $y")
}
printTwoWords("b")

fun getGreatestValue(x: Int, y: Int): Int {
    return if (x > y) {
        x
    } else {
        y
    }
}

fun getGreatestValue(x: Int, y: Int) = if (x > y) x else y

fun printName(name: String) {
    when (name) {
        "John" -> println("John Bon Jovi")
        "Mark" -> println("Mark twain")
        else -> println("No name")
    }
}
printName("John")

fun printNumber() {
    for (i in 1..4) {
        println(i)
    }
}
printNumber()


//-----------------lab2-------------------

val str = "abc"
str[0]
str.length
str.substring(0, 2)
val num = 6
"My favorite number is ${num * num}"

for (l in str) print("$l ")
str.forEach { print("$it ") }

fun isEven(num: Int) = num % 2 == 0
isEven(5)

fun consonants(str: String): String {
    var result :String ="";
    for(chr in str){
        if(!(chr in "AEIOUaeiou")){
            result += chr
        }
    }
    return result
}

fun consonants1(str: String) =str.filterNot { it in "AEIOUaeiou" }
consonants1("Hello, world!")

//extension functions
fun String.consonants() = filterNot { it in "AEIOUaeiou" }
"Hello, wordl".consonants()

fun Int.isEven() = rem(2)==0
4.isEven()
