package org.example

interface Puppy {
    fun eat() {
        println("puppy eat")
    }

    fun sleep() {
        println("puppy sleep")
    }
}

interface Human {
    fun eat() {
        println("human eat")
    }

    fun sleep()
}

class Creature : Puppy, Human {
    override fun eat() {
        super<Puppy>.eat()
        super<Human>.eat()
    }

    override fun sleep() {
        super<Puppy>.sleep()
        /**
         * Abstract üyeler direkt olarak erişilemez
         */
        //super<Human>.sleep()
    }
}

/**
 * Uygulamalarda test yazarken genellikle fake, mock ve stub kavramları
 * karşımıza çıkar. Interface programming fake oluştururken senaryoların
 * takip edileceği şekilde test yazılmak üzerine kurgulanır.
 */
interface Source {
    fun makeRequest(): String
}

class NetworkSource : Source {
    override fun makeRequest(): String {
        return "Hello"
    }
}

class TestFakeNetworkSource : Source {
    override fun makeRequest(): String {
        return "Actual"
    }
}