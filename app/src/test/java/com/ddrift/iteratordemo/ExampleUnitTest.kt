package com.ddrift.iteratordemo

import org.junit.Test

import org.junit.Assert.*


class ExampleUnitTest {

    /**
     * throw IndexOutOfBoundsException
     */
    @Test
    fun normalTest() {
        val helloList = arrayListOf(1,2,3)
        for(i in helloList.indices) {
            print(helloList[i])
            if(i == 1)
                helloList.removeAt(1)
        }
    }

    @Test
    fun normalFixedTest() {
        val helloList = arrayListOf(1,2,3)
        var index = 0
        while(index < helloList.size) {
            print(helloList[index])

            if(index == 1) {
                helloList.removeAt(1)
            } else
                index++
        }
    }

    /**
     * throw java.util.ConcurrentModificationException
     * @see Iterable<out T>
     * @see IteratorImpl
     */
    @Test
    fun iteratorTest() {
        val helloList = arrayListOf(1,2,3)
        helloList.forEach { _ ->
            helloList.removeAt(1)
        }
    }

    @Test
    fun iteratorFixedTest() {
        val helloList = arrayListOf(1,2,3)
        val iterable = helloList.iterator()
        var count = 0
        while(iterable.hasNext()) {
            iterable.next()
            if(count == 1)
                iterable.remove()
            count++
        }
        print(helloList.size)
    }

    /**
     *
     *   public inline fun <T> Iterable<T>.forEachIndexed(action: (index: Int, T) -> Unit): Unit {
     *      var index = 0
     *      for (item in this) action(checkIndexOverflow(index++), item)
     *      }
     *
     */
    @Test
    fun forEachIndexTest() {
        val helloList = arrayListOf(1,2,3)
        helloList.forEachIndexed { index, item ->
            print("hello$item\n")
            if(index == 1) {
                print("remove item")
                helloList.removeAt(index)
            }
        }
    }

    class A {
        private var i = 0

        operator fun hasNext() = i < 10

        operator fun next() = i++
    }

    operator fun A.iterator() = this

    @Test
    fun fooTest(){
        for(i in A()) print(i)
    }
}
