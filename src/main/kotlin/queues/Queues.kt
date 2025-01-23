package queues
import java.util.PriorityQueue

interface Queue<T> {
    fun enqueue(elem: T)

    fun peek(): T?

    fun dequeue(): T?

    fun isEmpty(): Boolean

    fun size(): Int
}

class FifoQueue<T> : Queue<T> {
    private val elements = mutableListOf<T>()

    override fun enqueue(elem: T) {
        elements.add(elem)
    }

    override fun peek(): T? = elements.firstOrNull()

    override fun dequeue(): T? {
        if (elements.isNotEmpty()) {
            return elements.removeFirst()
        } else {
            return null
        }
    }

    override fun isEmpty(): Boolean = elements.isEmpty()

    override fun size(): Int = elements.size
}

class LifoQueue<T> : Queue<T> {
    private val elements = mutableListOf<T>()

    override fun enqueue(elem: T) {
        elements.add(elem)
    }

    override fun peek(): T? = elements.lastOrNull()

    override fun dequeue(): T? {
        if (elements.isNotEmpty()) {
            return elements.removeLast()
        } else {
            return null
        }
    }

    override fun isEmpty(): Boolean = elements.isEmpty()

    override fun size(): Int = elements.size
}

class PrQueue<T>(
    comparator: Comparator<T>? = null,
) : Queue<T> {
    private val elements: PriorityQueue<T> =
        if (comparator != null) {
            PriorityQueue(comparator)
        } else {
            PriorityQueue()
        }

    override fun enqueue(elem: T) {
        elements.add(elem)
    }

    override fun peek(): T? = elements.peek()

    override fun dequeue(): T? = elements.poll()

    override fun isEmpty(): Boolean = elements.isEmpty()

    override fun size(): Int = elements.size
}
