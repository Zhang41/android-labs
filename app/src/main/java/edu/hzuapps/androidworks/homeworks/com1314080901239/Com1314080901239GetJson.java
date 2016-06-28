package com.example.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Com1314080901239GetJson extends Activity {

	private final int SUCCESS = 1;
	private final int FAILURE = 0;
	private final int ERRORCODE = 2;
	private TextView tv;
	
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case SUCCESS:
				String result=getJson(msg.obj.toString());
				//���̸߳���UI
				tv.setText(result);
				Toast.makeText(Com1314080901239GetJson.this, "��ȡ���ݳɹ�", Toast.LENGTH_SHORT)
				.show();
				break;
			case FAILURE:
				Toast.makeText(Com1314080901239GetJson.this, "��ȡ����ʧ��", Toast.LENGTH_SHORT)
				.show();
			case ERRORCODE:
				Toast.makeText(Com1314080901239GetJson.this, "��ȡ��CODE�벻Ϊ200��",
						Toast.LENGTH_SHORT).show();
			default:
				break;
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_com1314080901239_get_json);
		tv=(TextView) findViewById(R.id.tv);
	}
	
	public String getJson(String string){
		JSONObject jObject= null;
		try {
			jObject =new JSONObject(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����JsonObjectʧ��");
		}
		//����JSON����
		String city=jObject.optString("city");
		String city_en=jObject.optString("city_en");
		String date_y=jObject.optString("date_y");
		String week=jObject.optString("week");
		int cityid=jObject.optInt("cityid");
		String temp1=jObject.optString("temp1");
		System.out.println(temp1+""+date_y+""+city_en+""+cityid+""+week+""+city);
		String weatherResult="���У�"+city+"\n����Ӣ������"+city_en+"\n���ڣ�"+date_y+"\n���ڣ�"+week+"\n���б�ţ�"+cityid+"\n�¶ȣ�"+temp1;
		return weatherResult;
	}
	
	public void click(View v){
		//�����̻߳�ȡ��������
		new Thread(){
			int code=0;
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					String path="https://raw.githubusercontent.com/hzuapps/android-labs/master/app/src/main/java/edu/hzuapps/androidworks/homeworks/com1314080901239/city.json";
					URL url=new URL(path);
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					//����GET����
					conn.setRequestMethod("GET");
					//��������ʱʱ��
					conn.setConnectTimeout(5000);
					code=conn.getResponseCode();
					System.out.println(code);
					//200��ʾ��ȡ����ɹ�
					if(code==200){
						System.out.println("��ȡ���ݳɹ�");
						InputStream is=conn.getInputStream();
						String result = readMyInputStream(is);
						
						//handle Message
						Message msg=new Message();
						msg.obj= result;
						msg.what=SUCCESS;
						handler.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.what = ERRORCODE;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("��ȡ����ʧ��");
					e.printStackTrace();
					Message msg = new Message();
					msg.what = FAILURE;
					handler.sendMessage(msg);
				}
				
					
				super.run();
			}
		}.start();
	}
	
	//��������ʽ��ȡ����
	public String readMyInputStream(InputStream is){
		byte[] result;
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			byte[] buffer=new byte[1024];
			int len;
			while((len = is.read(buffer))!= -1){
				baos.write(buffer,0,len);
			}
			is.close();
			baos.close();
			result =baos.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorString="��ȡ����ʧ��";
			return errorString;
		}
		return new String(result);
	}
}
