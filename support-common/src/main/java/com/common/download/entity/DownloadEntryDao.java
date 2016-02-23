package com.common.download.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.common.db.DaoSession;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "DOWNLOAD_ENTRY".
*/
public class DownloadEntryDao extends AbstractDao<DownloadEntry, String> {

    public static final String TABLENAME = "DOWNLOAD_ENTRY";

    /**
     * Properties of entity DownloadEntry.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Url = new Property(0, String.class, "url", true, "URL");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property CurrentLength = new Property(2, Integer.class, "currentLength", false, "CURRENT_LENGTH");
        public final static Property TotalLength = new Property(3, Integer.class, "totalLength", false, "TOTAL_LENGTH");
        public final static Property Status = new Property(4, Integer.class, "status", false, "STATUS");
        public final static Property Percent = new Property(5, Integer.class, "percent", false, "PERCENT");
        public final static Property IsSupportRange = new Property(6, Boolean.class, "isSupportRange", false, "IS_SUPPORT_RANGE");
        public final static Property Range_0 = new Property(7, Integer.class, "range_0", false, "RANGE_0");
        public final static Property Range_1 = new Property(8, Integer.class, "range_1", false, "RANGE_1");
        public final static Property Range_2 = new Property(9, Integer.class, "range_2", false, "RANGE_2");
    };


    public DownloadEntryDao(DaoConfig config) {
        super(config);
    }
    
    public DownloadEntryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOWNLOAD_ENTRY\" (" + //
                "\"URL\" TEXT PRIMARY KEY NOT NULL ," + // 0: url
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"CURRENT_LENGTH\" INTEGER," + // 2: currentLength
                "\"TOTAL_LENGTH\" INTEGER," + // 3: totalLength
                "\"STATUS\" INTEGER," + // 4: status
                "\"PERCENT\" INTEGER," + // 5: percent
                "\"IS_SUPPORT_RANGE\" INTEGER," + // 6: isSupportRange
                "\"RANGE_0\" INTEGER," + // 7: range_0
                "\"RANGE_1\" INTEGER," + // 8: range_1
                "\"RANGE_2\" INTEGER);"); // 9: range_2
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOWNLOAD_ENTRY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DownloadEntry entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getUrl());
        stmt.bindString(2, entity.getName());
 
        Integer currentLength = entity.getCurrentLength();
        if (currentLength != null) {
            stmt.bindLong(3, currentLength);
        }
 
        Integer totalLength = entity.getTotalLength();
        if (totalLength != null) {
            stmt.bindLong(4, totalLength);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(5, status);
        }
 
        Integer percent = entity.getPercent();
        if (percent != null) {
            stmt.bindLong(6, percent);
        }
 
        Boolean isSupportRange = entity.getIsSupportRange();
        if (isSupportRange != null) {
            stmt.bindLong(7, isSupportRange ? 1L: 0L);
        }
 
        Integer range_0 = entity.getRange_0();
        if (range_0 != null) {
            stmt.bindLong(8, range_0);
        }
 
        Integer range_1 = entity.getRange_1();
        if (range_1 != null) {
            stmt.bindLong(9, range_1);
        }
 
        Integer range_2 = entity.getRange_2();
        if (range_2 != null) {
            stmt.bindLong(10, range_2);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DownloadEntry readEntity(Cursor cursor, int offset) {
        DownloadEntry entity = new DownloadEntry( //
            cursor.getString(offset + 0), // url
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // currentLength
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // totalLength
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // status
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // percent
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // isSupportRange
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // range_0
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // range_1
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9) // range_2
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DownloadEntry entity, int offset) {
        entity.setUrl(cursor.getString(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setCurrentLength(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setTotalLength(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setStatus(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setPercent(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setIsSupportRange(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setRange_0(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setRange_1(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setRange_2(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(DownloadEntry entity, long rowId) {
        return entity.getUrl();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(DownloadEntry entity) {
        if(entity != null) {
            return entity.getUrl();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
