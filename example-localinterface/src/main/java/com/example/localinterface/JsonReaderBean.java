package com.example.localinterface;

/**
 * Created by tanlifei on 16/1/28.
 */
public class JsonReaderBean {
    private final String fileName;//本地文件名字
    private final boolean reader;//是否读取打开读取本地文件

    public JsonReaderBean(String fileName,boolean reader){
        this.fileName=fileName;
        this.reader = reader;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isReader() {
        return reader;
    }
}
