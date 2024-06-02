package org.example

/**
 * Genericler inheritance kavramı normalden farklıdır. Örneğin X sınıfı Y sınıfının alt
 * sınıfı olarak düşünülürse Array<X>, Array<Y> nin alt tipi olarak kabul edilmez.
 * Bu durum variance yaparak çözülür.
 */

/**
 * Type nesnelerin paylaştıkları propertyleri tanımlar. Yani derleyiciye yazılımcının
 * veriyi nasıl kullanmayı planladığını söyler.
 *
 * Class ise bu tipin bir implementasyonudur.
 */

data class Kitty(val kittyName: String) : Mammal(kittyName) {
    override fun eat() {}
    override fun sleep() {}
}

data class Lion(val lionName: String) : Mammal(lionName) {
    override fun eat() {}
    override fun sleep() {}
}

open class Mammal(val name: String) {
    open fun eat() {}
    open fun sleep() {}
}

/**
 * Burada Mammal belirtilip Kitty gönderilebilmesinin sebebi List in out ile işaretlenip
 * covariance sağlamasıdır. Covariance gönderilen tipin alt tipinin de gönderilmesini
 * sağlar. Fonksiyon parametresi ile gönderilen parametre eşleşmediklerinde kabul
 * kriterleri, bunların en azından tanımlanan tipin alt türü olmasıdır.
 */
fun feed(elements: List<Mammal>) {
    elements.forEach {
        it.eat()
    }
}

fun main() {
    val kitties = listOf(Kitty("A"), Kitty("B"), Kitty("C"))
    val lions = listOf(Lion("D"), Lion("E"))

    // Covariance
    feed(kitties)
    feed(lions)

    val allElements = listOf(Kitty("D"), Kitty("A"), Lion("C"))

    // Contravariance
    val compareNames = Comparator { o1: Mammal, o2: Mammal ->
        o1.name.first().code - o2.name.first().code
    }

    println(allElements.sortedWith(compareNames))
}