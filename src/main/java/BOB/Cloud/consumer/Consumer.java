package BOB.Cloud.consumer;


public interface Consumer extends Runnable{

	/**
	 * public void handleItem(String item)
	 * This method is used for handling to send or processing item
	 * @param item derived from queue
	 */
	public void handleItem(String item);
}
