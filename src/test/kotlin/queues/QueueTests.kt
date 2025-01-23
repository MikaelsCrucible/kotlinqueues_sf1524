package queues

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FifoQueueTests {
    @Test
    fun `queue implements FIFO for Int`() {
        val fifoQueue = FifoQueue<Int>()

        fifoQueue.enqueue(1)
        fifoQueue.enqueue(3)
        fifoQueue.enqueue(2)
        assertEquals(1, fifoQueue.dequeue())
        assertEquals(3, fifoQueue.dequeue())
        assertEquals(2, fifoQueue.dequeue())
    }

    @Test
    fun `enqueue adds an element to the queue`() {
        val fifoQueue = FifoQueue<Int>()
        fifoQueue.enqueue(10)
        assertEquals(1, fifoQueue.size())
    }

    @Test
    fun `peek returns the first element without removing it`() {
        val fifoQueue = FifoQueue<String>()
        fifoQueue.enqueue("A")
        fifoQueue.enqueue("B")
        assertEquals("A", fifoQueue.peek())
        assertEquals(2, fifoQueue.size())
    }

    @Test
    fun `dequeue removes and returns the first element`() {
        val fifoQueue = FifoQueue<Double>()
        fifoQueue.enqueue(1.1)
        fifoQueue.enqueue(2.2)
        assertEquals(1.1, fifoQueue.dequeue())
        assertEquals(1, fifoQueue.size())
    }

    @Test
    fun `isEmpty returns true for an empty queue`() {
        val fifoQueue = FifoQueue<Int>()
        assertTrue(fifoQueue.isEmpty())
    }

    @Test
    fun `isEmpty returns false for a non-empty queue`() {
        val fifoQueue = FifoQueue<Int>()
        fifoQueue.enqueue(5)
        assertFalse(fifoQueue.isEmpty())
    }
}

class LifoQueueTests {
    @Test
    fun `queue implements LIFO for Int`() {
        val lifoQueue = LifoQueue<Int>()

        lifoQueue.enqueue(1)
        lifoQueue.enqueue(3)
        lifoQueue.enqueue(2)
        assertEquals(2, lifoQueue.dequeue())
        assertEquals(3, lifoQueue.dequeue())
        assertEquals(1, lifoQueue.dequeue())
    }

    @Test
    fun `enqueue adds an element to the stack`() {
        val lifoQueue = LifoQueue<Int>()
        lifoQueue.enqueue(42)
        assertEquals(1, lifoQueue.size())
    }

    @Test
    fun `peek returns the last element without removing it`() {
        val lifoQueue = LifoQueue<String>()
        lifoQueue.enqueue("X")
        lifoQueue.enqueue("Y")
        assertEquals("Y", lifoQueue.peek())
        assertEquals(2, lifoQueue.size())
    }

    @Test
    fun `dequeue removes and returns the last element`() {
        val lifoQueue = LifoQueue<Double>()
        lifoQueue.enqueue(3.3)
        lifoQueue.enqueue(4.4)
        assertEquals(4.4, lifoQueue.dequeue())
        assertEquals(1, lifoQueue.size())
    }

    @Test
    fun `isEmpty returns true for an empty stack`() {
        val lifoQueue = LifoQueue<Int>()
        assertTrue(lifoQueue.isEmpty())
    }

    @Test
    fun `isEmpty returns false for a non-empty stack`() {
        val lifoQueue = LifoQueue<Int>()
        lifoQueue.enqueue(99)
        assertFalse(lifoQueue.isEmpty())
    }
}

class PrQueueTests {
    @Test
    fun `queue implements Priority for Int`() {
        val prQueue = PrQueue<Int>()

        prQueue.enqueue(1)
        prQueue.enqueue(3)
        prQueue.enqueue(2)
        assertEquals(1, prQueue.dequeue())
        assertEquals(2, prQueue.dequeue())
        assertEquals(3, prQueue.dequeue())
    }

    @Test
    fun `enqueue adds an element to the stack`() {
        val prQueue = PrQueue<Int>()
        prQueue.enqueue(42)
        assertEquals(1, prQueue.size())
    }

    @Test
    fun `peek returns the first element without removing it`() {
        val prQueue = PrQueue<String>()
        prQueue.enqueue("Y")
        prQueue.enqueue("X")
        assertEquals("X", prQueue.peek())
        assertEquals(2, prQueue.size())
    }

    @Test
    fun `dequeue removes and returns the first element`() {
        val prQueue = PrQueue<Double>()
        prQueue.enqueue(3.3)
        prQueue.enqueue(4.4)
        assertEquals(3.3, prQueue.dequeue())
        assertEquals(1, prQueue.size())
    }

    @Test
    fun `isEmpty returns true for an empty stack`() {
        val prQueue = PrQueue<Int>()
        assertTrue(prQueue.isEmpty())
    }

    @Test
    fun `isEmpty returns false for a non-empty stack`() {
        val prQueue = PrQueue<Int>()
        prQueue.enqueue(99)
        assertFalse(prQueue.isEmpty())
    }

    class PointComparator : Comparator<Point> {
        override fun compare(
            p1: Point,
            p2: Point,
        ): Int =
            when {
                p1.coordX != p2.coordX -> p1.coordX - p2.coordX
                else -> p1.coordY - p2.coordY
            }
    }

    @Test
    fun `queue implements Priority for Point`() {
        val prQueue = PrQueue<Point>(PointComparator())

        prQueue.enqueue(Point(2, 2))
        prQueue.enqueue(Point(1, 1))
        prQueue.enqueue(Point(0, 1))
        prQueue.enqueue(Point(1, 2))
        prQueue.enqueue(Point(0, 2))
        prQueue.enqueue(Point(1, 0))
        prQueue.enqueue(Point(2, 0))
        prQueue.enqueue(Point(0, 0))
        prQueue.enqueue(Point(2, 1))

        assertEquals(Point(0, 0), prQueue.dequeue())
        assertEquals(Point(0, 1), prQueue.dequeue())
        assertEquals(Point(0, 2), prQueue.dequeue())
        assertEquals(Point(1, 0), prQueue.dequeue())
        assertEquals(Point(1, 1), prQueue.dequeue())
        assertEquals(Point(1, 2), prQueue.dequeue())
        assertEquals(Point(2, 0), prQueue.dequeue())
        assertEquals(Point(2, 1), prQueue.dequeue())
        assertEquals(Point(2, 2), prQueue.dequeue())
    }
}

data class Point(
    val coordX: Int,
    val coordY: Int,
)
