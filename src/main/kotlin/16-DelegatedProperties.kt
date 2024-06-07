package org.example

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Propertylerin her zaman manuel olarak implementasyonunun yapılmasının
 * istenmediği durumlarda delegate edilirler ve böylece sadece bir kere
 * tanımlanmış olurlar. Daha sonra delegation ile beraber tekrar
 * kullanılabilirler.
 */

/**
 * Delegation by anahtar kelimesi ile yapılır. Property den değer
 * alınması ve değer atanması getValue() ve setValue() fonksiyonlarına
 * verilir. Property delegation işleminde interface implemente etmeye
 * gerek yoktur ancak getValue() veya setValue() fonksiyonları tanımlanmak
 * zorundadır.
 */

/**
 * Eğer delegation da val kullanılırsa sadece getValue fonksiyonunu
 * yazmak yeterlidir ancak var ile kullanılacaksa o zaman setValue
 * fonksiyonu da yazılmalıdır.
 */

class UserDelegate

fun userDelegation(userDelegate: UserDelegate = UserDelegate()): ReadWriteProperty<Any?, UserDelegate> =
    object : ReadWriteProperty<Any?, UserDelegate> {
        var currentValue = userDelegate
        override fun getValue(thisRef: Any?, property: KProperty<*>): UserDelegate {
            return currentValue
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: UserDelegate) {
            currentValue = value
        }
    }

val userDelegate: UserDelegate by userDelegation()


/**
 * Json parsing vb. işlemleri kolaylaştırmak için map delegation
 * kullanılabilir.
 * Sadece val değil var propertylerde de kullanılabilir.
 */

class UserMapDelegate(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

/**
 * Bir property başka bir proterty e delegate edilebilir.
 * Aşağıdaki örnekte deprecated property i silmeden eski sürümleri de
 * destekleyerek geliştirme ve notify etme amacıyla kullanım vardır.
 */

class DelegateAnotherProperty {
    var privacyPermission: String = ""

    @Deprecated("Use new name instead", ReplaceWith("privacyPermission"))
    var permission: String by this::privacyPermission
}

/**
 * Delegation sınıf oluşturarak fonksiyonlarla tanımlanabildiği gibi
 * anonymous object ile de tanımlama yapılabilir. Bu fonksiyonlar
 * extension ve operator fonksiyon olarak tanımlanabilir
 */

/**
 * Primitive bir tipin tanımlaması yapılırken ilk değer atanması
 * yapılmak istenmediği zaman lateinit kullanılamaz. Bundan dolayı
 * bu tip genellikle nullable hale getirilir. Daha sonra her yerde
 * null safety ile kontrol yapılmaya çalışılır. Bunu yapmak yerine
 * notNull() delegation ı kullanılabilir
 */

var notNull: Int by Delegates.notNull()

class BaseView

interface ViewBinding

abstract class Lifecycle {
    abstract fun addObserver(lifecycleObserver: LifecycleObserver)
}

interface LifecycleOwner {

    val lifecycle: Lifecycle

    fun isViewCreated(): Boolean = true
}

interface LifecycleObserver {
    fun onDestroy(owner: LifecycleOwner) {}
}

class LifecycleRegister : Lifecycle() {
    override fun addObserver(lifecycleObserver: LifecycleObserver) {
        // add lifecycle observer
    }
}

open class BaseSampleFragment : LifecycleOwner {

    private var lifecycleOwner: LifecycleOwner? = null
    fun onViewCreated() {
        lifecycleOwner = this
    }

    fun getLifecycleOwner(): LifecycleOwner = lifecycleOwner!!

    fun requireView(): BaseView = BaseView()
    override val lifecycle: Lifecycle
        get() = LifecycleRegister()
}

class SampleFragment : BaseSampleFragment() {
    val binding: HomeScreenBinding by viewBinding(HomeScreenBinding::bind)
}

class HomeScreenBinding : ViewBinding {

    companion object {
        fun bind(view: BaseView): HomeScreenBinding {
            return HomeScreenBinding()
        }
    }
}

fun <T : ViewBinding> BaseSampleFragment.viewBinding(factory: (BaseView) -> T): ReadOnlyProperty<BaseSampleFragment, T> =
    object : ReadOnlyProperty<BaseSampleFragment, T>, LifecycleObserver {

        private var binding: T? = null

        override operator fun getValue(thisRef: BaseSampleFragment, property: KProperty<*>): T {
            binding ?: factory(requireView()).also {
                if (getLifecycleOwner().isViewCreated()) {
                    getLifecycleOwner().lifecycle.addObserver(this)
                    binding = it
                }
            }
            return binding!!
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }
    }

fun main() {
    val userMapDelegate = UserMapDelegate(mapOf("name" to "Halil", "age" to 25))

    println(userMapDelegate.name)
    println(userMapDelegate.age)

    val delegateAnotherProperty = DelegateAnotherProperty()

    delegateAnotherProperty.permission = "Camera"
    println(delegateAnotherProperty.privacyPermission)

    // notNull initialize edilmediği için hata fırlatılır
    // println(notNull)
    notNull = 14
    println(notNull)
}
