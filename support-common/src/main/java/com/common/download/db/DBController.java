package com.common.download.db;

import android.content.Context;

import com.common.download.entity.DownloadEntry;
import com.common.download.entity.DownloadEntryDao;
import com.common.ui.base.main.BaseApplication;

import java.util.List;

/**
 * @author tianlifei
 * @email 179840045@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des DBController
 */
public class DBController {
    private static DBController mInstance;
    private DownloadEntryDao dao;

    private DBController(Context context) {
        dao = BaseApplication.daoMaster.newSession().getDownloadEntryDao();
    }

    public static DBController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBController(context);
        }
        return mInstance;
    }

    public synchronized void newOrUpdate(DownloadEntry entry) {
        dao.insertOrReplace(entry);
    }

    public synchronized List<DownloadEntry> queryAll() {
        return dao.loadAll();
    }

    public synchronized DownloadEntry queryByUrl(String url) {
        List<DownloadEntry> list = dao.queryBuilder().where(DownloadEntryDao.Properties.Url.eq(url)).list();
        if (list != null)
            return list.get(0);
        else
            return null;
    }

    public synchronized void deleteByUrl(String url) {
        dao.deleteByKey(url);
    }

}
