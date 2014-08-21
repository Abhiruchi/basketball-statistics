package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Teamonetwo  extends Activity{
	
	String k;
	
	/** Called when the activity is first created. */
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		//k = getIntent().getExtras().getString("Team");
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.teamonetwo);
	    k = getIntent().getExtras().getString("Team");
        if(k!=null) {
        	Log.d("Teamonetwo",k);
        }
	    Gallery g = (Gallery) findViewById(R.id.gallery);
	    g.setAdapter(new ImageAdapter(this));
	    final String team[] = k.split("vs");
	    final String teams[] = team[1].split("\n");
	    final TextView v1 = (TextView) findViewById(R.id.textView1);
	    g.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if(position==0) {
	            	v1.setText(team[0]);
		        } else { 
	            	v1.setText(teams[0]);
		        }
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });

	    g.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Intent a = new Intent(Teamonetwo.this, SampleActivity.class);
	            a.putExtra("Position",""+position);
	            a.putExtra("Team", k);
	            //Toast.makeText(Teamonetwo.this,"" + position, Toast.LENGTH_LONG).show();
	            startActivity(a);
	        }
	    });
	}
	
	public class ImageAdapter extends BaseAdapter {
		
	    int mGalleryItemBackground;
	    private Context mContext;

	    private Integer[] mImageIds = {
	            R.drawable.teamone,
	            R.drawable.teamtwo,
	    };

	    public ImageAdapter(Context c) {
	        mContext = c;
	        TypedArray a = obtainStyledAttributes(R.styleable.GalleryActivity);
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.GalleryActivity_android_galleryItemBackground, 0);
	        a.recycle();
	    }

	    public int getCount() {
	        return mImageIds.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView i = new ImageView(mContext);
	        i.setImageResource(mImageIds[position]);
	        i.setLayoutParams(new Gallery.LayoutParams(800, 800));
	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	        i.setBackgroundResource(mGalleryItemBackground);

	        return i;
	    }
	}

}
