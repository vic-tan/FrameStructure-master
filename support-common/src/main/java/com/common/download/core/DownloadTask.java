package com.common.download.core;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.common.download.DownloadConfig;
import com.common.download.entity.DownloadEntry;
import com.common.utils.FileUtils;
import com.common.utils.Logger;
import com.constants.level.DownloadStatusLevel;

import java.util.concurrent.ExecutorService;

/**
 * 
 * @author tianlifei
 * @email 179840045@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des A download task which contains an download entry.
 */
@SuppressLint("UseSparseArrays")
public class DownloadTask implements ConnectThread.ConnectListener, DownloadThread.DownloadListener {

	private final DownloadEntry entry;
	private volatile boolean isPaused = false;
	private volatile boolean isCancelled = false;
	private final Handler mHandler;
	private DownloadThread[] downloadThreads;
	private ConnectThread connectThread;
	private final ExecutorService mExecutor;
	private long lastStamp = 0;
	private int[] downloadStatus;

	private Long[] lastModifiedTime;//用于记录下线程的最后活动时间，监测线程是否alive
	private long subThreadRefreshInterval = 10000;


	public DownloadTask(DownloadEntry entry, Handler handler, ExecutorService executor) {
		this.entry = entry;
		mHandler = handler;
		mExecutor = executor;
	}

	public void start() {

		if(entry.getTotalLength() > 0){
			//本地数据库有历史记录
			Logger.d("DownloadTask===>start()#####no need to request content length!");
			startDownload();
		}else{
			//第一次下载
			entry.setStatus(DownloadStatusLevel.CONNECTING.value());
			Logger.d("DownloadTask===>start()#####first start download" + "*****" + entry.toString());
			notifyUpdate(entry, DownloadService.NOTIFY_CONNECTING);
			connectThread = new ConnectThread(entry.getUrl(), this);
			mExecutor.execute(connectThread);
		}
	}



	private void notifyUpdate(DownloadEntry entry, int what) {
		Message msg = mHandler.obtainMessage();
		msg.what = what;
		msg.obj = entry;
		mHandler.sendMessage(msg);

		try {
           Thread.sleep(10);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }

	}

	public void pause(){
		Logger.d("DownloadTask==>pause()");
		isPaused = true;
		if(connectThread != null && connectThread.isRunning()){
			connectThread.cancel();
		}

		if(downloadThreads != null && downloadThreads.length > 0){
			for (DownloadThread thread : downloadThreads) {
				if(thread != null && thread.isRunning()){
					if(entry.getIsSupportRange()){
						thread.pause();
					}else{
						thread.cancel();
					}
				}
			}
		}
	}

	public void cancel(){
		Log.v("gh_download", "DownloadTask==>cancel!!!!!");
		isCancelled = true;
		if(connectThread != null && connectThread.isRunning()){
			connectThread.cancel();
		}

		if(downloadThreads != null && downloadThreads.length > 0){
			for (DownloadThread thread : downloadThreads) {
				if(thread != null && thread.isRunning()){
					thread.cancel();
				}
			}
		}
	}

	private void startMultiThreadDownload(){
		subThreadRefreshInterval  = DownloadConfig.getSubThreadRefrashInterval(entry.getTotalLength());
		entry.setStatus(DownloadStatusLevel.DOWNLOADING.value());
		notifyUpdate(entry, DownloadService.NOTIFY_DOWNLOADING);

		int startPos = 0;
		int endPos = 0;
		int block = entry.getTotalLength() / DownloadConfig.getInstance().getMax_download_threads();
		for(int i = 0; i < DownloadConfig.getInstance().getMax_download_threads(); i++){
			if(i == 0){
				entry.setRange_0(0);
			}else if(i == 1){
				entry.setRange_1(0);
			}else {
				entry.setRange_2(0);
			}
		}
		downloadThreads = new DownloadThread[DownloadConfig.getInstance().getMax_download_threads()];
		downloadStatus = new int[DownloadConfig.getInstance().getMax_download_threads()];
		lastModifiedTime = new Long[DownloadConfig.getInstance().getMax_download_threads()];
		for(int i = 0; i < DownloadConfig.getInstance().getMax_download_threads(); i++){
			lastModifiedTime[i] = System.currentTimeMillis();
		}

		for(int i = 0; i < DownloadConfig.getInstance().getMax_download_threads(); i++){
			startPos = i * block ;
			if(i == 0){
				startPos = startPos + entry.getRange_0();
			}else if(i == 1){
				startPos = startPos + entry.getRange_1();
			}else {
				startPos = startPos + entry.getRange_2();
			}
			if(i == DownloadConfig.getInstance().getMax_download_threads() - 1){
				endPos = entry.getTotalLength() - 1;
			}else{
				endPos = (i + 1) * block - 1;
			}
			if(startPos < endPos){
				downloadThreads[i] = new DownloadThread(entry.getUrl(), i, startPos, endPos, this);
				downloadStatus[i] = DownloadStatusLevel.DOWNLOADING.value();
				mExecutor.execute(downloadThreads[i]);
			}else{
				downloadStatus[i] = DownloadStatusLevel.DONE.value();
			}
		}

		//判断文件是否已经下载完成
		boolean flag = true;
		for(int i = 0; i < downloadStatus.length; i++){
			if(downloadStatus[i] != DownloadStatusLevel.DONE.value())
				flag = false;
		}

		if(flag){
			entry.setStatus(DownloadStatusLevel.DONE.value());
			notifyUpdate(entry, DownloadService.NOTIFY_COMPLETED);
			Logger.d("DownloadTask==>" + "startMultiThreadDownload#####" +entry.getName() + " is already done");
		}
	}

	private void startSingleThreadDownload(){
		entry.setStatus(DownloadStatusLevel.DOWNLOADING.value());
		Logger.d(entry.toString());
		notifyUpdate(entry, DownloadService.NOTIFY_DOWNLOADING);

		downloadThreads = new DownloadThread[1];
		downloadThreads[0] = new DownloadThread(entry.getUrl(), 0, 0, 0, this);
		downloadStatus = new int[1];
		mExecutor.execute(downloadThreads[0]);
	}

	private void startDownload() {
		//判断存储卡剩余控件是否充足
		if(FileUtils.getStorageAvailableSize() <= entry.getTotalLength()){
			entry.setStatus(DownloadStatusLevel.PAUSE.value());
			Logger.d("DownloadTask==>" + "onConnectSuccess##### not enough storage size!!!");
			notifyUpdate(entry, DownloadService.NOTIFY_NOT_ENOUGH_SIZE);
			return;
		}

		if(entry.getIsSupportRange()){
			Logger.d("DownloadTask()==>" + "startDownload#####start multi thread download!!!!");
			startMultiThreadDownload();
		}else{
			Logger.d("DownloadTask()==>" + "startDownload#####start single thread download!!!!");
			startSingleThreadDownload();
		}

	}

	@Override
	public void onConnectSuccess(boolean isSupportRange, int totalLength) {
		entry.setIsSupportRange(isSupportRange);
		entry.setTotalLength(totalLength);
		Logger.d("DownloadTask==>" + "onConnectSuccess#####" + entry.toString());
		startDownload();
	}


	@Override
	public void onConnectFaile(String message) {
		if (isPaused || isCancelled) {
            entry.setStatus(isPaused ? DownloadStatusLevel.PAUSE.value() : DownloadStatusLevel.CANCEL.value());
            notifyUpdate(entry, DownloadService.NOTIFY_PAUSED_OR_CANCELLED);
            Logger.d("DownloadTask==>" + "onConnectFaile#####" + "isPaused or isCancelled is true*****" + entry.toString());
		} else {
			entry.setStatus(DownloadStatusLevel.ERROR.value());
			notifyUpdate(entry, DownloadService.NOTIFY_ERROR);
            Logger.d("DownloadTask==>" + "onConnectFaile#####" + "error*****" + entry.toString());
        }
	}

	@Override
	public synchronized void onProgressChanged(int index, int progress) {

		if(entry.getIsSupportRange()){
			if(index == 0){
				entry.setRange_0(entry.getRange_0() + progress);
			}else if(index == 1){
				entry.setRange_1(entry.getRange_1() + progress);
			}else {
				entry.setRange_2(entry.getRange_2() + progress);
			}
			if(DownloadConfig.getInstance().getMax_download_threads() > 1){
				checkAndRefreshSubThread(index);
			}
		}

		entry.setCurrentLength(entry.getCurrentLength()+progress);

		long stamp = System.currentTimeMillis();
		if(stamp - lastStamp  > 1000){
			lastStamp = stamp;
			int percent = (int)(entry.getCurrentLength() * 100l / entry.getTotalLength());
			entry.setPercent(percent);
			Log.v("gh_download", "index==>" + index + " " + entry.toString());
			notifyUpdate(entry, DownloadService.NOTIFY_UPDATING);
		}


	}

	//检查线程是否alive，如果线程在30内没有更新时间，认为线程dead，重启线程
	private void checkAndRefreshSubThread(int index) {
		long stamp = System.currentTimeMillis();
		int startPos = 0;
		int endPos = 0;
		int block = entry.getTotalLength() / DownloadConfig.getInstance().getMax_download_threads();

		for (int i = 0; i < downloadThreads.length; i++) {
			if(stamp - lastModifiedTime[i] > 2 * subThreadRefreshInterval){
				//重启线程i
				startPos = i * block ;
				if(index == 0){
					startPos = startPos + entry.getRange_0();
				}else if(index == 1){
					startPos = startPos + entry.getRange_1();
				}else if(index == 2){
					startPos = startPos +  entry.getRange_2();
				}
				if(i == DownloadConfig.getInstance().getMax_download_threads() - 1){
					endPos = entry.getTotalLength() - 1;
				}else{
					endPos = (i + 1) * block - 1;
				}
				if(startPos < endPos){
					Logger.d("DownloadTask==>onProgressChanged()###" + " restart sub-thread " + i);
					downloadThreads[i].pause();
					downloadThreads[i] = null;
					downloadThreads[i] = new DownloadThread(entry.getUrl(), i, startPos, endPos, this);
					downloadStatus[i] = DownloadStatusLevel.DOWNLOADING.value();
					mExecutor.execute(downloadThreads[i]);
				}else{
					downloadStatus[i] = DownloadStatusLevel.DONE.value();
				}
				lastModifiedTime[i] = System.currentTimeMillis();
			}
		}

		//刷新线程index时间
		if(stamp - lastModifiedTime[index] > subThreadRefreshInterval){
			lastModifiedTime[index] = stamp;
			Logger.d("DownloadTask==>checkAndRefreshSubThread()##### index: "
			+ index + " refresh sub-thread time***" + stamp);
		}
	}

	@Override
	public synchronized void onDownloadCompleted(int index) {
		Logger.d("DownloadTask==>onDownloadCompleted():index==>" + index);
		downloadStatus[index] = DownloadStatusLevel.DONE.value();

		for(int i = 0; i < downloadStatus.length; i++){
			if(downloadStatus[i] != DownloadStatusLevel.DONE.value()){
				return;
			}
		}

		if(entry.getTotalLength() > 0 && entry.getCurrentLength() != entry.getTotalLength()){
			//下载出现异常，文件不完整,要清除，重新下载
			entry.setStatus( DownloadStatusLevel.ERROR.value());
			entry.reset();
			notifyUpdate(entry, DownloadService.NOTIFY_ERROR);
			Logger.d("DownloadTask==>onDownloadCompleted()#####file is error, reset it!!!!!");
		}else{
			//文件下载完成，没有异常
			entry.setStatus(DownloadStatusLevel.DONE.value());
			entry.setPercent(100);
            notifyUpdate(entry, DownloadService.NOTIFY_COMPLETED);
            Logger.d("DownloadTask==>onDownloadCompleted()#####file is ok!!!!!");
		}
	}

	@Override
	public synchronized void onDownloadError(int index, String message) {
		Logger.e("onDownloadError:" + message);
		downloadStatus[index] = DownloadStatusLevel.ERROR.value();
		for(int i = 0; i < downloadStatus.length; i++){
			if(downloadStatus[i] != DownloadStatusLevel.DONE.value()
					&& downloadStatus[i] != DownloadStatusLevel.ERROR.value()){
				downloadThreads[i].cancelByError();
				return;
			}
		}

		entry.setStatus(DownloadStatusLevel.ERROR.value());
    	notifyUpdate(entry, DownloadService.NOTIFY_ERROR);
	}

	@Override
	public synchronized void onDownloadPaused(int index) {
		downloadStatus[index] = DownloadStatusLevel.PAUSE.value();
		for(int i = 0; i < downloadStatus.length; i++){
			if(downloadStatus[i] != DownloadStatusLevel.DONE.value()
					&& downloadStatus[i] != DownloadStatusLevel.PAUSE.value()){
				return;
			}
		}
		entry.setStatus(DownloadStatusLevel.PAUSE.value());
		Logger.d(entry.toString());
		notifyUpdate(entry, DownloadService.NOTIFY_PAUSED_OR_CANCELLED);
	}

	@Override
	public synchronized void onDownloadCanceled(int index) {
		downloadStatus[index] = DownloadStatusLevel.CANCEL.value();
		for(int i = 0; i < downloadStatus.length; i++){
			if(downloadStatus[i] != DownloadStatusLevel.DONE.value()
					&& downloadStatus[i] != DownloadStatusLevel.CANCEL.value()){
				return;
			}
		}

		entry.setStatus(DownloadStatusLevel.CANCEL.value());
		Logger.d(entry.toString());
		entry.reset();
		notifyUpdate(entry, DownloadService.NOTIFY_PAUSED_OR_CANCELLED);
	}

}
