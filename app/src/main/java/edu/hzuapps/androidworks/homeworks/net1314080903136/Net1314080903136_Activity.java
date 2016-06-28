package edu.hzuapps.androidworks.homeworks;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Net1314080903136_Activity extends Activity {
	private RelativeLayout root;
	private String key;
	private String encryptFrom, encryptTo; // �����ļ����ƣ�����֮���ļ�����·��
	private String decryptFrom, decryptTo; // �����ļ����ƣ�����֮���ļ�����·��
	private EditText keyEditText, editText1, editText2, editText3, editText4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		setContentView(root);
	}

	private void init() {
		// TODO Auto-generated method stub
		root = new RelativeLayout(this);
		root.setBackgroundColor(Color.WHITE);
		root.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));

		keyEditText = new EditText(this);
		keyEditText.setId(10);
		keyEditText.setHint("������Կ");
		RelativeLayout.LayoutParams keyParams = new RelativeLayout.LayoutParams(
				-2, -2);
		keyParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		editText1 = new EditText(this);
		editText1.setId(11);
		editText1.setHint("�����ļ����ƣ�Ĭ��sdcard��Ŀ¼");
		RelativeLayout.LayoutParams edit1Params = new RelativeLayout.LayoutParams(
				-2, -2);
		edit1Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		edit1Params.addRule(RelativeLayout.BELOW, 10);

		editText2 = new EditText(this);
		editText2.setId(12);
		editText2.setHint("���ܱ���·����Ĭ��sdcard��Ŀ¼");
		RelativeLayout.LayoutParams edit2Params = new RelativeLayout.LayoutParams(
				-2, -2);
		edit2Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		edit2Params.addRule(RelativeLayout.BELOW, 11);

		Button button1 = new Button(this);
		button1.setId(13);
		button1.setText("����");
		button1.setOnClickListener(new Button1OnClickListener());
		RelativeLayout.LayoutParams b1Params = new RelativeLayout.LayoutParams(
				-2, -2);
		b1Params.addRule(RelativeLayout.BELOW, 12);
		b1Params.addRule(RelativeLayout.CENTER_HORIZONTAL);

		editText3 = new EditText(this);
		editText3.setId(14);
		editText3.setHint("�����ļ����ƣ�Ĭ��sdcard��Ŀ¼");
		RelativeLayout.LayoutParams edit3Params = new RelativeLayout.LayoutParams(
				-2, -2);
		edit3Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		edit3Params.addRule(RelativeLayout.BELOW, 13);

		editText4 = new EditText(this);
		editText4.setId(15);
		editText4.setHint("���ܱ���·����Ĭ��sdcard��Ŀ¼");
		RelativeLayout.LayoutParams edit4Params = new RelativeLayout.LayoutParams(
				-2, -2);
		edit4Params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		edit4Params.addRule(RelativeLayout.BELOW, 14);

		Button button2 = new Button(this);
		button2.setId(16);
		button2.setText("����");
		button2.setOnClickListener(new Button2OnClickListener());
		RelativeLayout.LayoutParams b2Params = new RelativeLayout.LayoutParams(
				-2, -2);
		b2Params.addRule(RelativeLayout.BELOW, 15);
		b2Params.addRule(RelativeLayout.CENTER_HORIZONTAL);

//		root.addView(keyEditText, keyParams);
		root.addView(editText1, edit1Params);
		root.addView(editText2, edit2Params);
		root.addView(button1, b1Params);
		root.addView(editText3, edit3Params);
		root.addView(editText4, edit4Params);
		root.addView(button2, b2Params);
	}

	/**
	 * ����
	 * 
	 * @author ���Ȼ
	 * 
	 */
	private final class Button1OnClickListener implements View.OnClickListener {

		public void onClick(View v) {
			key = keyEditText.getText().toString();
			encryptFrom = editText1.getText().toString();
			encryptTo = editText2.getText().toString();
			Net1314080903136_FileDES net1314080903136_FileDES;
			try {
				net1314080903136_FileDES = new Net1314080903136_FileDES("spring.sky");
				net1314080903136_FileDES.doEncryptFile("sdcard/" + encryptFrom, "sdcard/"
						+ encryptTo); // ����
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����
	 * 
	 * @author ���Ȼ
	 * 
	 */
	private final class Button2OnClickListener implements View.OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			key = keyEditText.getText().toString();
			decryptFrom = editText3.getText().toString();
			decryptTo = editText4.getText().toString();
			Net1314080903136_FileDES net1314080903136_FileDES;
			try {
				net1314080903136_FileDES = new Net1314080903136_FileDES("spring.sky");
				net1314080903136_FileDES.doDecryptFile("sdcard/" + decryptFrom, "sdcard/"
						+ decryptTo); // ����
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
