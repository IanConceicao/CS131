import java.util.concurrent.atomic.AtomicLongArray;
    
class AcmeSafeState implements State {
    private AtomicLongArray atomicArray;
    private int arrLength;
    private long[] longArray;
    private boolean hasChanged = true;
    
    AcmeSafeState(int length) {
	atomicArray = new AtomicLongArray(length);
	longArray = new long[length];
	arrLength = length;
    }

    public int size() { return arrLength; }

    public long[] current() {
	//Translate atomic long array into normal long array
	if(hasChanged){ //update array
	    for(int i = 0; i < atomicArray.length(); i++)
		longArray[i] = atomicArray.get(i);
	    hasChanged = false;
	}
	return longArray;
     }

    public void swap(int i, int j) {
        atomicArray.decrementAndGet(i);
        atomicArray.incrementAndGet(j);
	hasChanged = true;
    }
}
