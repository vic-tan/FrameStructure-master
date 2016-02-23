package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;

import com.common.ui.base.activity.BaseActivity;
import com.common.utils.StartActUtils;
import com.example.demo.R;


/**
 * 
 * @author shuwoom
 * @email 294299195@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Test one download task.
 */
public class Download_MainActivity extends BaseActivity {
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_activity_main);

	}

	/**单个下载**/
	public void A(View v){
		StartActUtils.start(this,Download_SingleActivity.class);
	}

	/**列表并点击进入详情下载**/
	public void B(View v){
		StartActUtils.start(this,Download_ListActivity.class);
	}

	/**升级对话框下载**/
	public void C(View v){
		StartActUtils.start(this,Download_SingleActivity.class);
	}

	/** 通知栏下载 **/
	public void D(View v){
		StartActUtils.start(this,Download_SingleActivity.class);
	}
	

}
