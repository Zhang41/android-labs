package edu.hzuapps.androidworks.homeworks.net1314080903133;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-8/����3:33:00
 * Description:
 */
public class Net1314080903133EmptyClassroom extends Activity {

	private static Button findButton = null; // ��ѯ����
	private static TextView contentstextView2 = null; // �ս�����Ϣshow
	private static String allemptyclassinfo = null; // �ս�����Ϣ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net1314080903133_empty_classroom);

		findButton = (Button) findViewById(R.id.button1Find);
		contentstextView2 = (TextView) findViewById(R.id.textView2);

		findButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				// �����ڰ�׿���߳̽���������������Ҫ�ڴ��������߳���ִ��
				new Thread(new Runnable() {

					@Override
					public void run() {

						// System.out.println("starting.....");
						InputStream inputStream = null;
						DataInputStream dataInputStream = null;
						try {
							// �ս�����Ϣ��ԴURL
							URL url = new URL(
									"http://1.zooo.vipsinaapp.com/miapp/index.php?aid=ks&uid=olOSijlybNLFRSBN7ub12yQQSkxY&svr=1.wechatlab.vipsinaapp.com");
							// URL url = new URL("https://www.baidu.com");
							// System.out.println(url);

							inputStream = url.openStream();
							dataInputStream = new DataInputStream(inputStream);

							String line = ""; // ��ȡ��������һ������
							String info = ""; // ����������һ�����ݣ���ս�����Ϣ
							while ((line = dataInputStream.readLine()) != null) {
								// System.out.println(line);
								info += DealWithString(line);
							}

							// UI�߳����õ�����allemptyclassinfo������Ϊȫ�ֱ���
							allemptyclassinfo = info;

						} catch (MalformedURLException e) {
							e.printStackTrace();
							// �쳣����
							allemptyclassinfo = "\n\n\n   1��URLЭ�顢��ʽ����·������\n   2��jar���� ";
							allemptyclassinfo += "\n\n\n  �úü����������еĴ���   "; 
						} catch (IOException e) {
							e.printStackTrace();
							// �쳣����
							allemptyclassinfo = "\n\n\n   ������̳�����˷������쳣������\n\n   ������������Կ���";
						} finally {
							// �޸�UIֻ����UI�߳���
							runOnUiThread(new Runnable() {
								public void run() {
									contentstextView2
											.setText(allemptyclassinfo);
								}
							});
							// �ر�����������
							try {
								if (dataInputStream != null) {
									dataInputStream.close();
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();// Thread()

			}

		});// setOnClickListener

	}

	/**
	 * ����������Ľ�����������������
	 * 
	 * @param readline
	 *            ��������ַ���
	 * @return �ս�����Ϣ
	 */
	public static String DealWithString(String readline) {

		// html���ַ�����"kb"��ͷ�Ķ������ս�����Ϣ
		if (readline.startsWith("kb")) {
			System.out.println(readline);
			return readline;
		}
		// ���html���ַ�û����"kb"��ͷ,�򷵻ؿ��ַ���ƴ��
		return "test! ";

	}
}
