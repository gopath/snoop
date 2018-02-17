package id.snoop.connection;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
*   Created by : Fathia MFA
*/
public class AsynConnection extends AsyncHttpResponseHandler {
	
	private ConnectionInterface callback;
	private String urlToConnect;
	private int type;
	private final int timeout = 60000;	
	
	public enum RequestType{
		GET, POST, PUT, DELETE
	};
	
	private HashMap<String, String> hParams;
	private HashMap<String, String> hHeader;
	private RequestParams rParams;
	private RequestType rType;	
	
	public AsynConnection(ConnectionInterface connectionInterface, String url, int type) {
		// TODO Auto-generated constructor stub
		this.callback = connectionInterface;
		this.urlToConnect = url;
		this.type = type;
	}
	
	public void asyncConnectRequest(HashMap<String, String> headers, HashMap<String, String> params, RequestType request){
		
		this.rType = request;
		this.hParams = params;
		this.hHeader = headers;
		this.rParams = new RequestParams(hParams);
		
		AsyncHttpClient client = new AsyncHttpClient();
		if(hHeader != null){
			Iterator<String> keySetIterator = hHeader.keySet().iterator();		
			String headerName = "";
			String headerValue = "";
			
			while(keySetIterator.hasNext()){
				headerName = keySetIterator.next();
				headerValue = hHeader.get(headerName);
				client.addHeader(headerName, headerValue);			
				Log.i("Header Name", headerName);
				Log.i("Header Value", headerValue);			
			}
		}
		
		client.setTimeout(timeout);
		if(request == RequestType.GET){			
			client.get(urlToConnect, this);	
		} else if(request == RequestType.POST){
			if(hParams != null){
				Log.i("Parameter Name", hParams.toString());				
				client.post(urlToConnect, rParams, this);
			} else {
				client.post(urlToConnect, this);
			}			
		} else if(request == RequestType.PUT){
			if(hParams != null){
				Log.i("Parameter Name", hParams.toString());
				client.put(urlToConnect, rParams, this);
			} else {
				client.put(urlToConnect, this);
			}
		}
	}
		
	@Override
	public void onSuccess(int statusCode, String content) {
		// TODO Auto-generated method stub
		super.onSuccess(statusCode, content);		
		callback.connectionOnSuccess(content, statusCode, type);		
	}
	
	@Override
	public void onFailure(int statusCode, Throwable error, String content) {
		// TODO Auto-generated method stub
		super.onFailure(statusCode, error, content);
		if(statusCode != 0){
			callback.connectionOnFailed(content, statusCode, type);
		} else {
			content = "{\"responseCode\":0,\"message\":\"Connection time out\"}";
			callback.connectionOnFailed(content, 0, type);
		}
	}

}