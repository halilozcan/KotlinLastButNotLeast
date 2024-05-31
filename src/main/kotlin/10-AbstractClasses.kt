package org.example

/**
 * Abstract classlar abstract fonksiyonlar aracılığıyla kendi üzerindeki
 * sorumluluğu kendini kalıtım alan sınıfa aktarır. Böylece kendini kalıtım
 * alan sınıf çalışan implementasyonu sağlar.
 */
abstract class Employee(val name: String, val experience: Int, open val dateOfBirth: Int) {

    abstract var salary: Double

    abstract fun dateOfBirth(): String

    fun printDetails() {
        println("Name:$name")
        println("Experience:$experience")
        println("Date of birth:${dateOfBirth()}")
    }
}

class SoftwareEngineer(name: String, experience: Int, override val dateOfBirth: Int) :
    Employee(name, experience, dateOfBirth) {
    override var salary: Double = 50000.00


    override fun dateOfBirth(): String {
        return "Birth date is:$dateOfBirth"
    }

}

/**
 * Abstract classlar interfacelerin bütün implementasyonlarının override edilmesini
 * işlemini kendilerine alarak kod fazlalığını engeller
 */

interface AnimationListener {
    fun onStart()
    fun onResume()
    fun onDestroy()
}

abstract class AnimationContract : AnimationListener {
    override fun onStart() {}
    override fun onResume() {}
    override fun onDestroy() {}
}

fun main() {
    val softwareEngineer = SoftwareEngineer("Halil", 6, 1994)
    softwareEngineer.printDetails()

    val animationContract = object : AnimationContract() {
        override fun onStart() {
            super.onStart()
        }
    }
}

abstract class Fragment {
    abstract fun onCreateView(): View
    abstract fun onViewCreated()
    abstract fun onCreate()
}

abstract class BaseFragment : Fragment() {

    abstract fun initArguments()
    abstract fun layoutResId(): Int
    abstract fun observeUi()

    override fun onCreate() {
        initArguments()
    }

    override fun onCreateView(): View {
        return View(layoutResId())
    }

    override fun onViewCreated() {
        observeUi()
    }
}

class View(layoutResId: Int)

class HomeFragment : BaseFragment() {
    override fun initArguments() {}
    override fun layoutResId() = 15
    override fun observeUi() {}
}