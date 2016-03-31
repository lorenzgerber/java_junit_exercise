import java.util.NoSuchElementException;

/**
 * Queue for ints implemented with a circular buffer.
 */
public class CircularBuffer {

    private int[] buffer;
    private int nextInsert = 0;
    private int nextRemove = 0;
    private int maxSize;

    /**
     * Creates a new circular buffer with a given size;
     * @param size The size of the buffer.
     * @throws IllegalArgumentException If the size is smaller than 1.
     */
    public CircularBuffer(int size) {

        this.maxSize = size;


        if (size==0){
            throw new IllegalArgumentException("wrong size argument, should be at least 1");
        }
        buffer = new int[size];


    }

    /**
     * Adds an element to the buffer.
     * @param i The element to add.
     * @throws IllegalStateException If the buffer is full.
     */
    public void put(int i) {
        int test = this.nextInsert - this.maxSize();
        System.out.format("insert is %d, Limit is %d\n", this.nextInsert, this.nextRemove+this.maxSize);

        if(this.nextInsert >= (this.nextRemove + this.maxSize)){
            throw new IllegalStateException("Buffer is full");
        }
        this.buffer[nextInsert % this.maxSize()] = i;
        nextInsert++;
    }

    /**
     * Returns and removes the first element in the buffer.
     * @return The first element in the buffer.
     * @throws NoSuchElementException If the buffer is empty.
     */
    public int take() {

        if (this.size()==0){
            throw new NoSuchElementException("The buffer is empty.");
        }
        nextRemove++;
        return buffer[(nextRemove-1) % this.maxSize()];

    }

    /**
     * @return The current number of elements in the queue.
     */
    public int size() {
        return this.nextInsert - this.nextRemove;
    }

    /**
     * @return The maximum number of elements in the queue.
     */
    public int maxSize() {

        return this.maxSize;
    }
}