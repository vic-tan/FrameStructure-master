package com.example.localinterface;

/**
 * Created by tanlifei on 16/1/28.
 */
public class JsonReaderBean {
    private final int fileId;//本地文件名字
    private final boolean reader;//是否读取打开读取本地文件

    public JsonReaderBean(int fileId,boolean reader){
        this.fileId=fileId;
        this.reader = reader;
    }

    public int getFileId() {
        return fileId;
    }

    public boolean isReader() {
        return reader;
    }
}
