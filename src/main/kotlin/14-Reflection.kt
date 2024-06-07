package org.example

import java.lang.reflect.Constructor
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * Reflection yazılan kodun yapısının çalışma zamanında incelenmesine olanak
 * sağlayan bir yapıdır. Örneğin bir property nin tipini öğrenme, private
 * ise public yapma gibi işlemler yapılabilir.
 */

class ReflectionClass

/**
 * Fonksiyonlara da reflection ile ulaşılabilir. Fonksiyonlar, propertyler
 * ve constructorlar callable referance olarak geçerler ve ortak süper
 * tipleri KCallable<out R> olarak geçer.
 *
 * Fonksiyon referansları KFunction<out R> nin alt tiplerinden biridir.
 * Bu durum parametre sayısına göre değişir.
 * Örneğin; KFunction3<T1,T2,T3,R>
 */

fun isOdd(x: Int) = x % 2 == 0

class ClassWithPrivateInstructor private constructor()

class ClassWithPrivateProperty {
    private val name: String = "Hello From Private Property"
}

fun createPrivateClassWithReflection(): ClassWithPrivateInstructor {
    return (ClassWithPrivateInstructor::class.java.declaredConstructors[0].apply {
        isAccessible = true
    } as Constructor<ClassWithPrivateInstructor>).newInstance()
}

fun main() {
    /**
     * Sınıf referansını alma
     * Burada dönen tip KClass şeklindedir. Kotlin Class Java class
     * referansı ile aynı ile değil.
     */
    val reflectionKotlin = ReflectionClass::class
    println(reflectionKotlin.constructors.size)
    println(reflectionKotlin.isOpen)

    /**
     * Kotlin Class Java class referansı ile aynı ile değildir. JVM
     * tarafındaki sınıfı almak için .java kullanılmalıdır.
     */
    val reflectionJava = ReflectionClass::class.java
    println(reflectionJava.constructors.size)

    /**
     * Gelecek parametreler fonksiyona uyuyorsa bu şekilde reflection yaparak kullanılabilir.
     */
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))

    val privateClass = createPrivateClassWithReflection()

    val classWithPrivateProperty = ClassWithPrivateProperty()

    val field = ClassWithPrivateProperty::class.memberProperties.find {
        it.name == "name"
    }

    field?.let {
        it.isAccessible = true
        val w = it.get(classWithPrivateProperty)
        println(w)
    }
}