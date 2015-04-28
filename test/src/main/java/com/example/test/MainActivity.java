package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import io.wazza.android.sdk.Wazza;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Wazza.init(this, "8f98fb0c29d532bccba556a5");

        final Button button = (Button) findViewById(R.id.close_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Wazza.destroy();
            }
        });

        final Button button2 = (Button) findViewById(R.id.purchase_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Wazza.purchase("coco");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
