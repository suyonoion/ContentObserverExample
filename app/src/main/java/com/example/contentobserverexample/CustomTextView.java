package com.example.contentobserverexample;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView{

	private Context mContext;
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	
	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		new SettingsObserver(new Handler()).observe();;
	}
	
	class SettingsObserver extends ContentObserver{

		public SettingsObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}
		
		void observe(){
			ContentResolver resolver = CustomTextView.this.mContext.getContentResolver();
			resolver.registerContentObserver(Settings.System.getUriFor("example_observer_string"), false, this);
			updateSettings();
		}
		
		
		
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			//super.onChange(selfChange);
			updateSettings();
		}
		
	}


	void updateSettings(){
		//edit
		String ganti_txt = Settings.System.getString(mContext.getContentResolver(),"example_observer_string");
		if(TextUtils.isEmpty(ganti_txt)){
			ganti_txt = "Kosong";
		}
		setText(ganti_txt);
		//edit
	}
	
}
