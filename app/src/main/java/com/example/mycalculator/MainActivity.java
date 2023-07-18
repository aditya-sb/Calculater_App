package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(view -> {
            if (getString(R.string.display).equals(display.getText().toString())){
                display.setText("");
            }
        });
    }

    private void updateText (String strToAdd) {
        String oldstr = display.getText().toString();
        int cursorpos = display.getSelectionStart();
        String leftstr = oldstr.substring(0, cursorpos);
        String rightstr = oldstr.substring(cursorpos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s",leftstr,strToAdd,rightstr));
        }
        display.setSelection(cursorpos+1);
    }
    public void btn0(View view){
        updateText( "0");
    }
    public void btn1(View view){
        updateText( "1");
    }
    public void btn2(View view){
        updateText( "2");
    }
    public void btn3(View view){
        updateText( "3");
    }
    public void btn4 (View view){
        updateText( "4");
    }
    public void btn5 (View view){
        updateText( "5");
    }
    public void btn6(View view){
        updateText( "6");
    }
    public void btn7 (View view){
        updateText( "7");
    }
    public void btn8 (View view){
        updateText( "8");
    }
    public void btn9 (View view){
        updateText( "9");
    }
    public void addbtn(View view){
        updateText( "+");
    }
    public void subtractbtn(View view){
        updateText( "-");
    }
    public void parenthesisbtn(View view){
        int cursorPos = display.getSelectionStart();
        int open =0;
        int close =0;
        int textLen = display.getText().length();

        for(int i = 0; i< cursorPos ; i++){
            if (display.getText().toString().charAt(i) == '('){
                open += 1;
            }
            if (display.getText().toString().charAt(i) == ')'){
                close += 1;
            }
        }
        if (open==close || display.getText().toString().charAt(textLen - 1) == '('){
            updateText( "(");
        }
        else if (open > close && display.getText().toString().charAt(textLen - 1) != '('){
            updateText( ")");
        }
        display.setSelection(cursorPos+1);
    }
    public void exponentbtn (View view){
        updateText( "^");
    }
    public void decimalbtn (View view){
        updateText( ".");
    }
    public void dividebtn (View view){
        updateText( "÷");
    }
    public void multiplybtn (View view){
        updateText( "×");
    }
    public void plusminusbtn (View view){
        updateText( "-");
    }
    public void clearbtn(View view){
        display.setText("");
    }
    public void backspacebtn(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos -1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
    public void equalsbtn (View view){
        String userExp = display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

}