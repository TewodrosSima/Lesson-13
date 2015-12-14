package com.example.tewodros_2.d;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    String ParsingDta= "{\"Employee\":[{\"id\":\"101\",\"name\":\"sonoo jaiswal\",\"salary\":\"50000\"},{\"id\":\"102\",\"name\":\"vimal jaiswal\",\"salary\":\"600000\"}]}";
    TextView textView11;
    ArrayList arrayList;
    String str="";
    ListView listView;
    NewDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new NewDataBase(this);
        // this.deleteDatabase("EmployeeDatabase.db");
        database.getWritableDatabase();
        textView11=(TextView)findViewById(R.id.textView11);
        listView=(ListView)findViewById(R.id.listView);
 
        try
        {
            JSONObject jsonObject=new JSONObject(ParsingDta);
            JSONArray jsonArray= jsonObject.getJSONArray("Employee");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String id=jsonObject1.getString("id").toString();
                String name=jsonObject1.getString("name").toString();
                String salary=jsonObject1.getString("salary").toString();
                database.insertData(id,name,salary);
                str+="\n Employee"+i+ "\n name:"+name+"\n id:"+id+"\n salary:" +salary+"\n";
                //textView1.setText(str);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        arrayList=database.fetchData();
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.activity_list_item,android.R.id.text1,arrayList);
        listView.setAdapter(adapter);
    }
}