package com.example.project_basketballstats;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Winning extends Activity {
	
	Button b,e;
	EditText a,d,c;
	String k;
	String[] Team;
	String[] Teams;
	String Team1;
	String Team2;
	
	public void onCreate(Bundle savedInstanceState) {
		int o[] = new int[5];
		int g[] = new int[5];
		int score1=0,score2=0;
		//Toast.makeText(this, "winning", Toast.LENGTH_LONG).show();
		SharedPreferences p=PreferenceManager.getDefaultSharedPreferences(this);
		String get = p.getString("pref_list", "1");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winningstats);
		k = getIntent().getExtras().getString("Team");
		Team = k.split("vs");
		Team1 = Team[0];
		Teams = Team[1].split("\n");
		Team2 = Teams[0];
		//Toast.makeText(Winning.this, Team1 + " " + Team2, Toast.LENGTH_SHORT).show();
		Team1 = Team1.replace("\n", "");
		Team1 = Team1.replace(" ", "");
		Team2 = Team2.replace("\n", "");
		Team2 = Team2.replace(" ", "");
		b = (Button) findViewById(R.id.button1);
		e = (Button) findViewById(R.id.button2);
		a = (EditText) findViewById(R.id.editText1);
		d = (EditText) findViewById(R.id.editText2);
		c = (EditText) findViewById(R.id.editText3);
		MainDatabase db = new MainDatabase(this);
		db.open();
		int i;
		o = db.getfouls(db.getTeamId(Team1));
		g = db.getfouls(db.getTeamId(Team2));
		score1 = db.getScore(db.getTeamId(Team1));
		score2 = db.getScore(db.getTeamId(Team2));
		for(i=0;i<5;i++) {
			if(o[i]>=Integer.valueOf(get)) {
				score2++;
			}
		}
		for(i=0;i<5;i++) {
			if(g[i]>=Integer.valueOf(get)) {
				score1++;
			}
		}
		int s = score1;
		int y = score2;
		a.setText(String.valueOf(s));
		d.setText(String.valueOf(y));
		if(s>y) {
			c.setText(Team1 + " Won !!");
		} else if (s<y) {
			c.setText(Team2 + " Won !!");
		} else {
			c.setText("Draw !!");
		}
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(Winning.this, Stats.class);
				a.putExtra("Team", Team1);
				startActivity(a);
			}
		});
		
		e.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent a = new Intent(Winning.this, Stats.class);
				a.putExtra("Team", Team2);
				startActivity(a);
			}
		});
		db.close();
		
	}

}