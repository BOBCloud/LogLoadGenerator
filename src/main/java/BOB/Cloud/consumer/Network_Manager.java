package BOB.Cloud.consumer;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.cloudera.flume.handlers.thrift.Priority;
import com.cloudera.flume.handlers.thrift.ThriftFlumeEvent;
import com.cloudera.flume.handlers.thrift.ThriftFlumeEventServer.Client;

public class Network_Manager {
	
	ThriftFlumeEvent tfe;
	Map<String, java.nio.ByteBuffer> fields;
	
	public Network_Manager(){
		this.tfe = new ThriftFlumeEvent();
		this.fields = new HashMap<String, ByteBuffer>();
	}
	
	public void sendItem(String _item){
		tfe.fields = fields;
        tfe.priority = Priority.INFO;
        tfe.timestamp = new Date().getTime();
        tfe.host = "localhost";

        tfe.body = ByteBuffer.wrap(_item.getBytes());
        
        Client client = getClient();
        try {
        	try {
				client.append(tfe);
			} catch (TException e) {
				e.printStackTrace();
			}
        } finally {
            try {
                client.close();
            } catch (TException ex) {
                Logger.getLogger(Network_Manager.class.getName()).log(Level.ALL, null, ex);
            }
        }
	}
	
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
