package id.snoop.connection;

/*
*   Created by : Fathia MFA
*/
public interface ConnectionInterface {
	public void connectionOnSuccess(Object value, int responseCode, int type);
	public void connectionOnFailed(Object value, int responseCode, int type);	
}
