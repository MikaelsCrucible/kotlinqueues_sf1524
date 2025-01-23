package queues

interface Acceptor<T> {
    fun accept(item: T)
}

interface Forwarder<T> {
    fun forward()
}

class QueueNode<T>(
    private val queue: Queue<T>, // 节点的队列
    private val successor: Acceptor<T>, // 后继节点
) : Acceptor<T>,
    Forwarder<T> {
    override fun accept(item: T) {
        queue.enqueue(item)
    }

    override fun forward() {
        val item = queue.dequeue()
        if (item != null) {
            successor.accept(item)
        }
    }
}

class Sink<T> : Acceptor<T> {
    private val acceptedItems = mutableListOf<T>()

    override fun accept(item: T) {
        acceptedItems.add(item)
    }

    fun getAccepted(): List<T> = acceptedItems
}
