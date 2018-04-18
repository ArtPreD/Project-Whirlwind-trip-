package ua.com.meraya;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.ads.AdView;


public class AndroidLauncher extends AndroidApplication {

	private RelativeLayout mainView;

	private AdView bannerView;
	private ViewGroup bannerContainer;
	private RelativeLayout.LayoutParams bannerParams;

	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		mainView = new RelativeLayout(this);
		setContentView(mainView);

//		View gameView = initializeForView(new MainEngine(gameCallBack));
//		mainView.addView(gameView);

		bannerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bannerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerContainer = new LinearLayout(this);

		mainView.addView(bannerContainer, bannerParams);
		bannerContainer.setVisibility(View.GONE);
	}

	private GameCallback gameCallBack = new GameCallback() {
		@Override
		public void sendMessage(int message) {

			if (message == MainEngine.SHOW_BANNER) {
				AndroidLauncher.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {

					}
				});
			} else if (message == MainEngine.HIDE_BANNER){
				AndroidLauncher.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {

					}
				});
			}else if (message == MainEngine.LOAD_INTERSTITIAL){

			}else if (message == MainEngine.OPEN_MARKET){
				Uri uri = Uri.parse(getString(R.string.share_url));
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}else if (message == MainEngine.SHOW_INTERSTITIAL){
				AndroidLauncher.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {

					}
				});
			}else if (message == MainEngine.SHARE){
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				String shareTitle = getString(R.string.share_title);
				String shareBody = getString(R.string.share_body);
				String url = getString(R.string.share_url);

				String body = shareBody + url;

				sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareTitle);
				sharingIntent.putExtra(Intent.EXTRA_SUBJECT, body);

				startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
			}
		}
	};
}
