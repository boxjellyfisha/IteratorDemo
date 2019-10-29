## ForEach_ for (element in this)
> #### for loop iterates through anything that provides an iterator.

### **in** Operator
> #### The in operator is used to check whether an object belongs to a collection.

### implement the Iterable<out T>

``` kotlin
    private open inner class IteratorImpl : Iterator<E> {
        /** the index of the item that will be returned on the next call to [next]`()` */
        protected var index = 0

        override fun hasNext(): Boolean = index < size

        override fun next(): E {
            if (!hasNext()) throw NoSuchElementException()
            return get(index++)
        }
    }
```
### Diagram

``` puml
@startuml

interface Iterator<out T> {
    + Boolean hasNext()
    + E next()
}

class IteratorImpl {
    ~ var index = 0
    + override Boolean hasNext()
    + override E next()
}

interface MutableList<E> {
   + Boolean addAll(index: Int, elements: Collection<E>)
}

interface Collection<out E> {
  + Boolean isEmpty()
  /* a in b	b.contains(a) */
  + operator Boolean contains(element: @UnsafeVariance E)
  + Boolean containsAll(elements: Collection<@UnsafeVariance E>)
}

interface List<E> {
  + Int indexOf(element: @UnsafeVariance E)
  + ListIterator<E> listIterator()
}

interface MutableIterable<E>
interface MutableCollection<E>

interface Iterable<out T> {
  + Iterator<T> iterator()
}

MutableList <|.. ArrayList
MutableCollection <|.. MutableList
Collection <|.. MutableCollection
MutableIterable <|.. MutableCollection
List <|.. MutableList
Collection <|.. List
Iterable <|.. Collection

Iterator <|.. IteratorImpl

note right of MutableList: "Supports adding and removing elements."
note right of List: "Support only read-only access to the list."
note right of Collection: "Support only read-only access to the collection."


@enduml

```

#### Operator overloading. 
Kotlin allows us to provide implementations for a predefined set of operators on our types. 
To implement an operator, we provide a member function 
or an extension function with a fixed name, for the corresponding type, 
i.e. left-hand side type for binary operations and argument type for unary ones ...
[more](https://www.kotlincn.net/docs/reference/operator-overloading.html)


expect marks a declaration as platform-specific, expecting an implementation in platform modules.
