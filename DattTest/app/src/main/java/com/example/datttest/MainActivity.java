package com.example.datttest;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class MainActivity extends AppCompatActivity {
    //编辑框对象
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        edit = (EditText)findViewById(R.id.edit);

        String inputText = load();
        if (!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(MainActivity.this,"显示信息成功",Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * 活动销毁时触发的函数
     */
    protected void onDestroy(){
        super.onDestroy();
        //拿到edit编辑框中的数据并且存储
        String inputText = edit.getText().toString();
        save(inputText);
    }


    /**
     * AＰＰ加载时保留原先有的数据
     */
    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        //可以存储一个变长字符串
        StringBuffer content = new StringBuffer();
        try{
            in = openFileInput("hello");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            //如果读取出的数据不是空的数据
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }



    /**
     * 数据持久化存储
     */
    public void save(String text){
        //String data = "念念不忘，必有回响！";
        //文件流
        FileOutputStream out = null;
        //写入对象
        BufferedWriter writer = null;
        try {
            //使用私有方式写入data
            out = openFileOutput(text, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
