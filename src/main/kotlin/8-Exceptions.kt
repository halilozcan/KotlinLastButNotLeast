package org.example

fun main() {
    /**
     * Kotlin de Java da bulunan checked exceptions yoktur
     * Appendable append(CharSequence csq) throws IOException;
     * yani bir method tanımlaması yaparken bunun aynı zamanda exception
     * fırlatacağını belirtemezsiniz.
     */

    /**
     * Nothing değer döndürülmeyen ve değerin asla olmadığını belirtmek için
     * kullanılan bir sınıftır. Bir exception fırlatılması ifadesinin sonucu
     * Nothing dir. Bu tip hiç bir zaman erişilmeyecek code konumlarını işaretlemek
     * için kullanılır. Nothing final bir classtır. Constructor ı private dır.
     * Genellikle sadece exception fırlatan methodlar için kullanılır.
     * Üst sınıf Any dir.
     */

    val name: String? = null

    val length = name?.length ?: error("name can not be null")
    println(length)

    /**
     * Any bütün sınıfların atasıdır. equals, hashcode ve toString methodlarına
     * sahiptir. Bu methodlar opendır ve kalıtım alan sınıflar tarafından override
     * edilebilir. Nesnesi oluşturulabilir.
     * Javadaki Object sınıfı gibi düşünülebilir.
     */

    /**
     * Unit object classtır. Kalıtım alınamaz ya da nesnesi oluşturulamaz.
     * Javadaki void gibi düşünülebilir. Üst sınıfı Any dir ve override edilmiş
     * toString methoduna sahiptir.
     */
}

fun error(message: String): Nothing {
    throw Exception(message)
}

class MathOperation(var number1: Int, var number2: Int, var number3: Int) {
    private fun minusTwoInteger(number1: Int, number2: Int): Int {
        return number1 - number2
    }

    /**
     * Java tarafından kotlin kodu çağırılırken hangi exception ın handle edilmesi
     * gerektiğini belirtmek için böyle bir annotation kullanılması gereklidir.
     * Yoksa compiler hata verir.
     */
    @Throws(ArithmeticException::class)
    fun doOperation() {
        number1 / minusTwoInteger(number2, number3)
    }

    fun closeOperation() {
        number1 = 0
        number2 = 0
        number3 = 0
    }
}