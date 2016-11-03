package com.msky.inputview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends Activity {
EditText e1,e2;
ImageView m1,m2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_login);
        init();
    }

private void init() {
	// TODO Auto-generated method stub
e1=(EditText)findViewById(R.id.phonenumber);
e2=(EditText)findViewById(R.id.password);
m1=(ImageView)findViewById(R.id.del_phonenumber);
m2=(ImageView)findViewById(R.id.del_password);
//������������
EditTextClearTools.addclerListener(e1, m1);
EditTextClearTools.addclerListener(e2, m2);




}
  
}
