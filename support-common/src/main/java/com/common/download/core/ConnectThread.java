package com.common.download.core;

import com.common.utils.Logger;
import com.constants.fixed.GlobalConstants;


import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author tianlifei
 * @email 179840045@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Connecting network to get content length and whether is's supporting ranges operation.
 */
public class ConnectThread implements Runnable{
	private final ConnectListener listener;
	private final String url;
	private volatile boolean isRunning;
	
	public ConnectThread(String url, ConnectListener listener){
		this.url = url;
		this.listener = listener;
	}
	
	@Override
	public void run() {
		isRunning = true;
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection)new URL(url).openConnection();
			connection.setRequestMethod("GET");
//			connection.setRequestProperty("Range","bytes=0-");
			connection.setConnectTimeout(GlobalConstants.CONNECT_TIME);
			connection.setReadTimeout(GlobalConstants.READ_TIME);
			int responseCode = connection.getResponseCode();
			int contentLength = connection.getContentLength();
			boolean isSupportRange = false;
			if(responseCode == HttpURLConnection.HTTP_OK){
				String ranges = connection.getHeaderField("Accept-Ranges");
                if ("bytes".equals(ranges)){
                    isSupportRange = true;
                }
                listener.onConnectSuccess(isSupportRange, contentLength);
                Logger.d("ConnectThread==>run()#####" + url + "*****connect success " + responseCode);
			}else{
				listener.onConnectFaile("server error:" + responseCode);
				Logger.d("ConnectThread==>run()#####" + url + "*****server error: " + responseCode);
			}
			isRunning = false;
		}  catch (Exception e) {
			Logger.d("ConnectThread==>run()#####" + url + "*****connect exception***" + e.getMessage());
			isRunning = false;
			listener.onConnectFaile(e.getMessage());
		} finally{
			if (connection != null){
				Logger.d("ConnectThread==>run()#####" + url + "*****close connection");
                connection.disconnect();
            }
		}
	}
	
	public boolean isRunning() {
        return isRunning;
    }

    public void cancel() {
        Thread.currentThread().interrupt();
    }
	
	interface ConnectListener{
		public void onConnectSuccess(boolean isSupportRange, int totalLength);
		public void onConnectFaile(String message);
	}

}
