package com.common.download.notify;

import com.common.download.entity.DownloadEntry;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author tianlifei
 * @email 179840045@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Observer
 */
public abstract class DataWatcher implements Observer{

	@Override
	public void update(Observable observable, Object data) {
		if(data instanceof DownloadEntry){
			onDataChanged((DownloadEntry) data);
		}
	}
	
	public abstract void onDataChanged(DownloadEntry downloadEntry);

}
