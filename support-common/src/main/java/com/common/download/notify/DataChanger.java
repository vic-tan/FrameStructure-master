package com.common.download.notify;

import android.content.Context;

import com.common.download.db.DBController;
import com.common.download.entity.DownloadEntry;
import com.constants.level.DownloadStatusLevel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

/**
 * 
 * @author tianlifei
 * @email 179840045@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Observable which notifies all the observers.
 */
public class DataChanger extends Observable{
	private static DataChanger mInstance;
	
	private LinkedHashMap<String, DownloadEntry> mOperateEntries;

	private final Context context;
	
	private DataChanger(Context context){
		this.context = context;
		mOperateEntries = new LinkedHashMap<String, DownloadEntry>();
	}

	public synchronized static DataChanger getInstance(Context context){
		if(mInstance == null){
			mInstance = new DataChanger(context);
		}
		return mInstance;
	}
	
	public void updateStatus(DownloadEntry entry) {
		mOperateEntries.put(entry.getUrl(), entry);
        DBController.getInstance(context).newOrUpdate(entry);
		setChanged();
		notifyObservers(entry);
	}
	
	public ArrayList<DownloadEntry> queryAllRecoverableEntries() {
        ArrayList<DownloadEntry> mRecoverableEntries = null;
        for (Map.Entry<String, DownloadEntry> entry : mOperateEntries.entrySet()) {
            if (entry.getValue().getStatus() == DownloadStatusLevel.PAUSE.value()) {
                if (mRecoverableEntries == null) {
                    mRecoverableEntries = new ArrayList();
                }
                mRecoverableEntries.add(entry.getValue());
            }
        }
        return mRecoverableEntries;
    }

	public boolean containsDownloadEntry(String url) {
		return mOperateEntries.containsKey(url);
	}

	public DownloadEntry queryDownloadEntryByUrl(String url) {
		return DBController.getInstance(context).queryByUrl(url);
	}

	public void addToOperatedEntryMap(String url, DownloadEntry entry) {
		mOperateEntries.put(url, entry);
	}

}
