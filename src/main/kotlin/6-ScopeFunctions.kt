package org.example

/**
 * Scope fonksiyonlarda objelerin isimleri lambda expression ın receiver veya argument
 * olmasına göre değişir. eğer receiver ise (this) argument ise it olarak
 * adlandırılırlar
 */

private var globalName: String? = "Hello"

var height = 180

fun main() {

    val person: Person? = Person("", "", 1990)
    /**
     * let
     * nesne it olarak adlandırılır. dönüş değeri lambdanın sonucudur.
     */

    /**
     * Chaining call yapılan ve sonucun bir değere atanmadığı durumlarda
     * let kullanılabilir.
     */

    val numbers = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    numbers.map { it / 2 }.filter { it > 3 }.let {
        println(it)
    }

    /**
     * Eğer let den sonra it yi kullanan tek bir fonksiyon kullanılacaksa
     * method referansı (::) kullanarak çağırım yapılabilir.
     */

    numbers.map { it / 2 }.filter { it > 3 }.let(::println)

    doSomethingWithPersonLet(person)

    parseNameLet("Halil")

    parseNameGloballyLet()

    /**
     * with
     * nesneye this olarak erişilir. return değeri lambdanın sonucudur
     * with extension fonksiyon olmadığı için nesne parametre olarak verilir.
     */

    /**
     * ilk kullanımı dönen sonuca ihtiyaç olmayan durumlar içindir.
     * with this object, do the following."
     */

    with(numbers) {
        println("Size is ${this.size}")
    }

    /**
     * bir nesnenin değerleri bir sonuç hesaplamak için gerektiğinde kullanılır
     */

    val firstAndLastSum = with(numbers) {
        first() + last()
    }

    /**
     * run
     * nesneye this kullanılarak erişilir. dönüş değeri lambdanın sonucudur
     */

    /**
     * run with ile aynı işlemi yapar ancak extension fonksiyon olarak implemente
     * edilmiştir.
     */

    val fullName: String? = run {
        person?.name?.plus(person.lastName)?.capitalize()
    }

    /**
     * run aynı zamanda extension fonksiyon olmadan da kullanılır. Bu durumda
     * nesne olmaz. dönüş değeri lambda nın sonucudur.
     */

    val result = fullName ?: run {
        println("Name is null")
        throw Exception("Hello Mother Fucker")
    }

    /**
     * run aynı anda hem nesneyi initialize etmek hem de bu işlemle bir dönüş
     * değeri hesaplamak için kullanılır.
     */

    getPersonInfoRun(person, 180)

    /**
     * apply
     * nesneye this kullanılarak ulaşılır. dönüş değeri nesnenin kendisidir.
     */

    /**
     * Sonuç döndürmeyen ve genellikle nesnenin memberları üzerinde işlem yapılan
     * durumlar için kullanılır. "apply the following assignments to the object."
     */
    val newPerson = Person("", "", 1990).apply {
        height = 183
    }

    /**
     * also
     * nesneye it kullanılarak ulaşılır. dönüş değeri nesnenin kendisidir.
     */

    /**
     * also nesnenin property lerine veya fonksiyonlara erişmekten çok nesnenin
     * kendisi üzerinde yapılan aksiyonlar ve diğer context deki this shadowunu
     * engellemek için kullanılabilir.
     * "and also do the following with the object."
     */

    numbers.also {
        println("before filtering the size of numbers:${it.size}")
    }.filter {
        it > 3
    }
}

// it ve this ile karışıklığı engellemek için kullanılabilir.
fun doSomethingWithPersonLet(person: Person?) {
    person?.let {
        height = it.height
    }
}

fun parseNameLet(name: String?) {
    name?.let {
        // Do something with str
    }
}

fun parseNameGloballyLet() {
    if (globalName != null) {
        // Smart case yapılmaz - globalName.length
    }

    globalName?.let {

    }
}

fun getPersonInfoRun(person: Person?, height: Int) {
    val result = person?.run {
        this.height = height
        "$name $gender $height"
    }
}

