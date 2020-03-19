import com.sun.security.ntlm.Server
import java.lang.Exception
import kotlin.script.experimental.dependencies.DependenciesResolver

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
    var result: String = "";
    for (chr in str) {
        if (!(chr in "AEIOUaeiou")) {
            result += chr
        }
    }
    return result
}

fun consonants1(str: String) = str.filterNot { it in "AEIOUaeiou" }
consonants1("Hello, world!")

//extension functions
fun String.consonants() = filterNot { it in "AEIOUaeiou" }
"Hello, wordl".consonants()

fun Int.isEven() = rem(2) == 0
4.isEven()


//Higher order functions
enum class Type {
    FOOD,
    OTHER
}

data class Product(
    val name: String,
    val type: Type,
    val unitPrice: Double,
    val quantity: Double = 1.0
)

val milk = Product("milk", Type.FOOD, 3.5)
Product("car", Type.OTHER, 1000.0, 2.0)

//fun Product.calculatePrice() =quantity *unitPrice *((if(type ==Type.FOOD)119.0 else 119.0)/100)
fun Product.calculatePrice(applyTax: Product.() -> Double) = quantity * unitPrice * applyTax(this)
/*milk.calculatePrice{
    (if(type == Type.FOOD) 105.0 else 119.0)/100
}*/

val romanianTax: Product.() -> Double = {
    (if (type == Type.FOOD) 105.0 else 119.0) / 100
}

val usTax: Product.() -> Double = {
    115.0 / 100
}
milk.calculatePrice(romanianTax)
milk.calculatePrice(usTax)

fun Product.calculatePrice(applyTax: Product.() -> Double, discount: Product.() -> Double) =
    quantity * unitPrice * applyTax(this) - discount(this)

//val discount: Product.()


//scope functions with, also, run, apply, let, run

class RectangularShape(
    var x: Int, var y: Int, var with: Int, var height: Int, var
    color: Int
) {
    fun measure() {}
    fun render() {}
}

//T is receiver
inline fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

fun initShape(shape: RectangularShape?) {
    shape?.apply {
        x = 10
        y = 20
        with = 100
        height = 200
        color = 0xFF0066
    } ?: throw java.lang.IllegalArgumentException()

    /*if (shape != null) {
        shape.x = 10
        shape.y = 20
        shape.with = 100
        shape.height = 200
        shape.color = 0xFF0066
    } else {
        throw IllegalArgumentException()
    }*/
}


fun <T> T.let(block: T.() -> Unit): T {
    block()
    return this
}

fun initShapeWithLet(shape: RectangularShape?) {
    val shape = shape?.let {
        x = 10
        y = 20
        with = 100
        height = 200
        color = 0xFF0066
    } ?: throw java.lang.IllegalArgumentException()
}

//-------Collections----------
//Lists
val data = listOf<Int>(1, 2, 3) //not listOf(1,2, 'a') ->object
val data2 = listOf<Int>(1, 3, 5)
data[0]

data + data2 //concatenare
fun Int.isEven1(): Boolean = rem(2) == 0
data.forEach {
    print(it)
}
data.map { it.isEven1() }.forEach { print(it) }
data.filterNot { it.isEven() }


val data3 = listOf(4, 6, 34, 9, 2, 4, 7)
data3.mapIndexed { index, it -> if (!it.isEven1()) index else -1 }.filter { it > 0 }
data3.mapIndexedNotNull { index, it -> if (!it.isEven1()) index else null }

fun Int.isPrime(): Boolean {
    for (i in 2..this / 2) {
        if (this % i == 0)
            return false
    }
    return true
}
2.isPrime()

fun Int.isPrime1(): Boolean {
    return (2..this / 2).none { this % it == 0 }
}
6.isPrime1()

//Objects

object DataBaseConnection {
    init {
        //connect to db
    }

    fun getData(): String = ""
}
DataBaseConnection.getData() //singleton

//Enum vs Sealed Classes

enum class Direction {
    N,
    S,
    W,
    E
}

fun printDirection(direction: Direction) =
    when(direction){
        Direction.N->print("north")
        Direction.E->TODO()
        Direction.S->TODO()
        Direction.W->TODO()
    }

sealed class ServerResponse{
    class Success(val data: String) :ServerResponse()
    class Failed(val ex:Exception): ServerResponse()
    object NoInternetConnection :ServerResponse()
}

ServerResponse.Success("succes")

fun handleResponse(response: ServerResponse):Unit = when(response){
    is ServerResponse.Success ->print(response.data)
    is ServerResponse.Failed -> throw response.ex
    ServerResponse.NoInternetConnection -> print("no internet")

}

handleResponse(ServerResponse.Success("ok"))
handleResponse(ServerResponse.NoInternetConnection)