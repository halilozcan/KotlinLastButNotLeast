package org.example

interface Device

class Computer : Device
class Telephone : Device

fun addComputer(list: MutableList<Any>) {
    list.add(Computer())
}

fun main() {
    /**
     * Class = Telephone
     * Types = Telephone, Telephone?
     * Subtypes = Telephone inherit eden herhangi bir şey ya da Telephone un kendisi
     */

    /**
     * A -> A?
     * Telephone -> Telephone?
     * Telephone? -> Telephone // burası error verir.
     *
     * Nullable değer kabul eden bir değişken içerisinde null olmayan bir değer
     * tutulabilir ama tam tersi olamaz.
     */
    val telephones = mutableListOf(Telephone(), Telephone(), Telephone())

    /**
     * Bu eklemenin yapılamamasının sebebi MutableList in invariant olmasıdır.
     * Invariance sadece aynı tipi kabul eder. Burada MutableList invariant
     * olmasaydı bir Exception fırlatılması söz konusu olurdur.
     */
    // addComputer(telephones)

    println(telephones)

    val computers = mutableListOf(Computer(), Computer(), Computer())

    val devices = mutableListOf(Telephone(), Telephone(), Computer())

    copyData(computers, devices) // List<Device> is subtype of List<Computer>
    copyData(telephones, devices) // List<Device> is subtype of List<Telephone>

    /**
     * Compile hatası verir. Çünkü Computer ve Telephone arasında bir typing yoktur
     */
    // copyData(computers, telephones)
}

fun <T> copyData(source: MutableList<T>, destination: MutableList<in T>) {
    for (element in source) {
        destination.add(element)
    }
}