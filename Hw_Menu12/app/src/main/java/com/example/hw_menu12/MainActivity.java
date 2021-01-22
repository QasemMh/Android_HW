package com.example.hw_menu12;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgroup;
    RadioButton rbtn;
    Button btn;
    TextView txt3, seekTxt;
    ImageView img;
    CheckBox box, box2, box3;
    SeekBar seekbar;

    double total;
    double tax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        rgroup = findViewById(R.id.radiogroup1);
        img = findViewById(R.id.imageView);
        txt3 = findViewById(R.id.textView3);
        seekbar = findViewById(R.id.seekBar2);
        seekTxt = findViewById(R.id.textView6);

        box = findViewById(R.id.checkBox);
        box2 = findViewById(R.id.checkBox2);
        box3 = findViewById(R.id.checkBox3);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekTxt.setText("Tax: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioResult = selectedRadio();
                String taxText = seekTxt.getText().toString();

                if (radioResult != null) {
                    tax = Integer.parseInt(taxText.substring(taxText.indexOf(" ") + 1, taxText.indexOf("%"))) / 100.0;
                    total = 0;
                    total += getRadioPrice();
                    total += getBoxPrice();
                    total += (total * tax);

                    txt3.setText("");
                    txt3.setText("bill :\n" + radioResult.getText() + "\n" + getCheckedBoxText() + taxText + "\n" + "Total:" + total);

                } else {
                    Toast.makeText(MainActivity.this, "please Select main Meal ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //get selected radioButton
    public RadioButton selectedRadio() {
        int selectId = rgroup.getCheckedRadioButtonId();
        rbtn = findViewById(selectId);
        return rbtn;
    }
    //change imageView on radioButton clicked
    public void ClickRadio(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton2:
                if (checked) {
                    img.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
                }
                break;
            case R.id.radioButton:
                if (checked) {
                    img.setImageDrawable(getDrawable(R.drawable.ic_launcher_background));
                }
                break;
            case R.id.radioButton3:
                if (checked) {
                    img.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
                }
                break;
        }
    }
    //get price of selected option
     public int getRadioPrice() {
        int price = 0;
        switch (rbtn.getId()) {
            case R.id.radioButton:
                price = 15;
                break;
            case R.id.radioButton2:
                price = 25;
                break;
            case R.id.radioButton3:
                price = 12;
                break;
        }
        return price;
    }
    //get price of checked option
    public int getBoxPrice() {
        int price = 0;
        if (box.isChecked()) price += 3;
        if (box2.isChecked()) price += 2;
        if (box3.isChecked()) price += 3;

        return price;
    }
    //get text value of checked option
    public String getCheckedBoxText() {
        String check = "";
        if (box.isChecked()) check += box.getText().toString() + "\n";
        if (box2.isChecked()) check += box2.getText().toString() + "\n";
        if (box3.isChecked()) check += box3.getText().toString() + "\n";
        return check;
    }


}