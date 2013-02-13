package BOB.Cloud.consumer;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.cloudera.flume.handlers.thrift.Priority;
import com.cloudera.flume.handlers.thrift.ThriftFlumeEvent;
import com.cloudera.flume.handlers.thrift.ThriftFlumeEventServer.Client;

public class Network_Manager {
	
	/**
	 * Thrift Flume Event is an event for thrift which acknowledges
	 * it's event so, Flume's node can detect event's source
	 * @see #tfe
	 */
	ThriftFlumeEvent tfe;
	
	/**
	 * Map type variable filed stores a string , ByteBuffer 
	 * for transport to the flume's node
	 */
	Map<String, java.nio.ByteBuffer> fields;
	
	/**
	 * public Network_manager()
	 * A constructor for network_manager
	 * It initializes Its variable
	 */
	public Network_Manager(){
		this.tfe = new ThriftFlumeEvent();
		this.fields = new HashMap<String, ByteBuffer>();
	}
	
	/**
	 * public void sendItem(String _item)
	 * this method used to send an chunk of string to flume node.
	 * the parameter, _item is rebuilt suitable for flume.
	 * @param _item a string that will be rebuilt
	 */
	public void sendItem(String _item){
		tfe.fields = fields;
        tfe.priority = Priority.INFO;
        tfe.timestamp = new Date().getTime();
        tfe.host = "localhost";

        tfe.body = ByteBuffer.wrap(_item.getBytes());
        
	}
	
	/**
	 * public static Client getClient()
	 * This method is for getting Client using Thrift's class
	 * @return an client for communicating with Flume
	 */
	public static Client getClient() {
        TTransport transport = new TSocket("localhost", 12345);
        if (!transport.isOpen()) {
            try {
                transport.open();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        TProtocol protocol = new TBinaryProtocol(transport);
        return new Client(protocol);
    }
	
}
