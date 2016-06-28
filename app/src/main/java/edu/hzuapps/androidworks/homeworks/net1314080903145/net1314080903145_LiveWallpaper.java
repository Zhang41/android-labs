package edu.hzuapps.androidworks.homeworks.net1314080903145;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import edu.hzuapps.androidworks.homeworks.net1314080903145.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.WindowManager;

public class Net1314080903145_LiveWallpaper extends WallpaperService {
	public static final String SHARED_PREFS_NAME = "com.njue.livewallpaper";
	 DisplayMetrics dm;
	 static int w1;
	 static int h1;
	 static int i=0;
	 static int row=0;
	 static int col=0;
	 static int textCount;
	 int leaf1Count=1;
	 int flower1Count=2;
	 int flower2Count=2;
	 ArrayList<Net1314080903145_leafInfo> leaf1list=new ArrayList<Net1314080903145_leafInfo>();
	 ArrayList<Net1314080903145_leafInfo> flower1list=new ArrayList<Net1314080903145_leafInfo>();
	 ArrayList<Net1314080903145_leafInfo> flower2list=new ArrayList<Net1314080903145_leafInfo>();
	 ArrayList<Net1314080903145_info> list=new ArrayList<Net1314080903145_info>();
	 Paint paint= new Paint();
	 static String loveText="�������� ������ħ����Ѫ�� ��Ϊ������������ ��׹����ֻΪ��� �������� ";
	 static int wordCount=9;
	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		 return new WallpaperEngine(getResources());
	}
	public class WallpaperEngine extends Engine implements SharedPreferences.OnSharedPreferenceChangeListener{
		 private final Handler handler=new Handler();        
	     private Bitmap image; //Image
	     Bitmap image2;
	     private Bitmap flower2; //Image01 for fire01.PNG
	        private Bitmap flower1; //Image02 for fire02.PNG
	        private Bitmap leaf;
	        private boolean visible; //��ʾ״̬
	        private int     width;   //��
	        private int     height;  //��
	        //������
	     Canvas c;
	     SharedPreferences prefs;
	     private final Runnable drawThread=new Runnable() {
	            public void run() {
	            	if(i==Net1314080903145_LiveWallpaper.loveText.length()-1){
	            		i=0;
	            		row=0;
	            		col=0;
	            		list.clear();
	            	}
	            	else{
	            		if(textCount==20){
	            	i++;
	            	col++;
	            	textCount=0;
	            		}
	            	textCount++;
	            	}
	            	leafInfo();
	            	flower1Info();
	            	flower2Info();
	                drawFrame();
	            }
	        };
	public WallpaperEngine(Resources r) {
		 prefs = Net1314080903145_LiveWallpaper.this.getSharedPreferences(SHARED_PREFS_NAME, 0);
         prefs.registerOnSharedPreferenceChangeListener(this);
         onSharedPreferenceChanged(prefs, null);
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
	           image=BitmapFactory.decodeResource(r,R.drawable.background);
	            dm=new DisplayMetrics();
	           wm.getDefaultDisplay().getMetrics(dm);
	 	       w1=dm.widthPixels;
	 	       h1=dm.heightPixels;
	 	      flower2=BitmapFactory.decodeResource(r,R.drawable.floewr2);
	            flower1=BitmapFactory.decodeResource(r,R.drawable.flower4);
	            leaf=BitmapFactory.decodeResource(r,R.drawable.leaf1);
	        }
    //��������ͨ��SurfaceHolder��������ֽ��Canvas��
    @Override
    public void onCreate(SurfaceHolder surfaceHolder) {
        super.onCreate(surfaceHolder);
    }
    //��������
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(drawThread);
    }
    //���Surface
    @Override
    public void onSurfaceChanged(SurfaceHolder holder,
        int format,int width,int height) {
        super.onSurfaceChanged(holder,format,width,height);
        this.width =width;
        this.height=height;
       // drawFrame();
    }
    //����Surface
    @Override
    public void onSurfaceCreated(SurfaceHolder holder) {
        super.onSurfaceCreated(holder);
    }
    //����Surface
    @Override
    public void onSurfaceDestroyed(SurfaceHolder holder) {
        super.onSurfaceDestroyed(holder);
        visible=false;
        handler.removeCallbacks(drawThread);
    }
    //����ɼ�/���ɼ�״̬
    @Override
    public void onVisibilityChanged(boolean visible) {
        this.visible=visible;
        if (visible) {
            drawFrame();
        } else {
            handler.removeCallbacks(drawThread);
        }
    }
    //�����ֽλ��
    @Override
    public void onOffsetsChanged(float xOffset,float yOffset,
        float xStep,float yStep,int xPixels,int yPixels) {
       // drawFrame();
    }
    //Frame���
    private void drawFrame() {
    	//��������
        SurfaceHolder holder=getSurfaceHolder();
        Canvas c=holder.lockCanvas();
        //���
       
	//	paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		paint.setTextSize(16);
		paint.setAntiAlias(true);
		paint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));


	/*	 Context context = getBaseContext();
		  // ����
		  Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/ss.ttf");
		  paint.setTypeface(typeface); // ����Paint������
		  paint.setAntiAlias(true);*/

			image2=Bitmap.createScaledBitmap(image,w1,h1, false);
        c.drawBitmap(image2, 0,0, null);
        Net1314080903145_drawText1 text=new Net1314080903145_drawText1(i,w1,h1);
        Paint p1=new Paint();
        p1.setTextSize(16);
        p1.setColor(Color.BLACK);
        p1.setStrokeWidth(4);
		p1.setAntiAlias(true);
		p1.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        drawPast(c,p1);
        drawImage(c);
        paint.setColor(Color.argb(51/4*textCount, 0, 0, 0));
        c.drawText(text.getText(),text.getX(),text.getY(), paint);
    if(textCount==19)
        list.add(new Net1314080903145_info(text.getText(),text.getX(),text.getY()));
        //��������
        holder.unlockCanvasAndPost(c);
        //����
        handler.removeCallbacks(drawThread);
        if (visible) handler.postDelayed(drawThread,10);
    }
    public void drawPast(Canvas c,Paint p){
    	if(list.size()>0){
    	for(int j=0;j<list.size();j++){
    		Net1314080903145_info in=list.get(j);
    		c.drawText(in.getText(), in.getRealwidth(), in.getRealheight(), p);
    	}
    	}
    }
    public void drawImage(Canvas c){
    	if(leaf1list.size()>0){
    	for(int a=0;a<leaf1Count;a++){
    		Net1314080903145_leafInfo leaf1=leaf1list.get(a);
    		c.drawBitmap(leaf, leaf1.getWidth(),3*leaf1.getHeight(),null);
    	}
    	}
    	if(flower1list.size()>0){
        	for(int a=0;a<flower1Count;a++){
        		Net1314080903145_leafInfo leaf1=flower1list.get(a);
        		c.drawBitmap(flower1, leaf1.getWidth(),3*leaf1.getHeight(),null);
        	}
        	}
    	if(flower2list.size()>0){
        	for(int a=0;a<flower2Count;a++){
        		Net1314080903145_leafInfo leaf1=flower2list.get(a);
        		c.drawBitmap(flower2, leaf1.getWidth(),3*leaf1.getHeight(),null);
        	}
        	}
    }
    
    public void leafInfo(){
    	if(leaf1list.size()>0){
    		for(int leaf1=0;leaf1<leaf1Count;leaf1++){
           Net1314080903145_leafInfo l1= leaf1list.get(leaf1);
         int leaf1temp=l1.getHeight();
         leaf1temp++;
         if(3*leaf1temp>h1){
        	 l1.setHeight(0);
        	 l1.setWidth(new Random().nextFloat()*w1);
         }
         else{
         l1.setHeight(leaf1temp);
         }
         leaf1list.set(leaf1, l1);
            		}
    	}
    	else{
    		for(int leaf1=0;leaf1<leaf1Count;leaf1++){
    	Net1314080903145_leafInfo l1=new Net1314080903145_leafInfo(new Random().nextFloat()*w1,new Random().nextInt(200));
    	leaf1list.add(l1);
    		}
    	}
    }
    
    public void flower1Info(){
    	if(flower1list.size()>0){
    		for(int flower1=0;flower1<flower1Count;flower1++){
           Net1314080903145_leafInfo f1= flower1list.get(flower1);
         int leaf1temp=f1.getHeight();
         leaf1temp++;
         if(3*leaf1temp>h1){
        	 f1.setHeight(0);
        	 f1.setWidth(new Random().nextFloat()*w1);
         }
         else{
         f1.setHeight(leaf1temp);
         }
         flower1list.set(flower1, f1);
            		}
    	}
    	else{
    		for(int leaf1=0;leaf1<flower1Count;leaf1++){
    	Net1314080903145_leafInfo l1=new Net1314080903145_leafInfo(new Random().nextFloat()*w1,new Random().nextInt(200));
    	flower1list.add(l1);
    		}
    	}
    }
    
    public void flower2Info(){
    	if(flower2list.size()>0){
    		for(int flower1=0;flower1<flower2Count;flower1++){
           Net1314080903145_leafInfo f1= flower2list.get(flower1);
         int leaf1temp=f1.getHeight();
         leaf1temp++;
         if(3*leaf1temp>h1){
        	 f1.setHeight(0);
        	 f1.setWidth(new Random().nextFloat()*w1);
         }
         else{
         f1.setHeight(leaf1temp);
         }
         flower2list.set(flower1, f1);
            		}
    	}
    	else{
    		for(int leaf1=0;leaf1<flower2Count;leaf1++){
    	Net1314080903145_leafInfo l1=new Net1314080903145_leafInfo(new Random().nextFloat()*w1,new Random().nextInt(200));
    	flower2list.add(l1);
    		}
    	}
    }
	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String key) {
		// TODO Auto-generated method stub
		if(key!=null){
		if(key.equals("leaf1Count")){
		leaf1Count = Integer.parseInt(prefs.getString(getResources().getString(R.string.leaf1Count), "1"));
		leaf1list.clear();
		}
		if(key.equals("flower1Count")){
			flower1Count = Integer.parseInt(prefs.getString(getResources().getString(R.string.flower1Count), "2"));
			flower1list.clear();
			}
		if(key.equals("flower2Count")){
			flower2Count = Integer.parseInt(prefs.getString(getResources().getString(R.string.flower2Count), "2"));
			flower2list.clear();
			}
		if(key.equals("inputText")){
			loveText = prefs.getString(getResources().getString(R.string.inputText),loveText);
			loveText=loveText+"   ";      //��ĩβ�Ӽ����ո��Ŀ�ľ�������ȫ����ʾ������ͣ�ټ��룬����ͷ��ʼд����
			i=0;
    		row=0;
    		col=0;
    		list.clear();
			}
		if(key.equals("wordCount")){
			wordCount = Integer.parseInt(prefs.getString(getResources().getString(R.string.wordCount), "9"));
			i=0;                                       //����ַ������ı䣬��ʼ��������������¿�ʼ�����ַ�
    		row=0;
    		col=0;
    		list.clear();
			}
		if(key.equals("reset")){
				loveText="�������� ������ħ����Ѫ�� ��Ϊ������������ ��׹����ֻΪ��� �������� ";
				 wordCount=9;
				 i=0;                                       //����ָ�Ĭ�����ã���ʼ��������������¿�ʼ�����ַ�
		    	row=0;
		    	col=0;
		    	list.clear();
		    	
			}
		}
	}
}
}
