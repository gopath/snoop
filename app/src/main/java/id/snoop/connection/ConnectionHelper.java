package id.snoop.connection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*
*   Created by : Fathia MFA
*/
public class ConnectionHelper {
			
	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting() 
				&& netInfo.isAvailable() && netInfo.isConnected()) {
			return true;
		}
		return false;
	}
	
}
