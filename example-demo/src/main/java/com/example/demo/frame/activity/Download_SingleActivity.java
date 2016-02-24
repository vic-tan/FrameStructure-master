package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.common.download.DownloadManager;
import com.common.download.entity.DownloadEntry;
import com.common.download.notify.DataWatcher;
import com.common.ui.base.activity.BaseActivity;
import com.constants.fixed.GlobalConstants;
import com.example.demo.R;


/**
 * 
 * @author shuwoom
 * @email 294299195@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Test one download task.
 */
public class Download_SingleActivity extends BaseActivity implements OnClickListener{
	
	private final String url = "http://gh-game.oss-cn-hangzhou.aliyuncs.com/1434794302961350.apk";
	private DownloadEntry entry = new DownloadEntry(url);
	
	private Button addBtn;
	private Button cancelBtn;
	private Button pauseBtn;
	private Button resumeBtn;
	
	private TextView showText;
	
	private DataWatcher dataWatcher = new DataWatcher() {

		@Override
		public void onDataChanged(DownloadEntry data) {
			entry = data;
			showText.setText(entry.toString());
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_activity_single);
		
		entry.setName("x三国.apk");
		entry.setSaveUrl(GlobalConstants.DOWNLOAD_PATH + entry.getName());
		showText = (TextView)findViewById(R.id.show_text);
		addBtn = (Button)findViewById(R.id.add_btn);
		cancelBtn = (Button)findViewById(R.id.cancel_btn);
		pauseBtn = (Button)findViewById(R.id.pause_btn);
		resumeBtn = (Button)findViewById(R.id.resume_btn);
		
		addBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		resumeBtn.setOnClickListener(this);
		
	}
	
	@Override
    protected void onResume() {
        super.onResume();
		DownloadManager.getInstance(this).addObserver(dataWatcher);
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        DownloadManager.getInstance(this).removeObserver(dataWatcher);
    }

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.add_btn)
			DownloadManager.getInstance(Download_SingleActivity.this).add(entry);
		else if (v.getId() == R.id.pause_btn)
			DownloadManager.getInstance(Download_SingleActivity.this).pause(entry);
		else if (v.getId() == R.id.resume_btn)
			DownloadManager.getInstance(Download_SingleActivity.this).resume(entry);
		else if (v.getId() == R.id.cancel_btn)
			DownloadManager.getInstance(Download_SingleActivity.this).cancel(entry);
	}

}
