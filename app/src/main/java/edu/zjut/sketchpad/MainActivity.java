package edu.zjut.sketchpad;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MainActivity extends AppCompatActivity {

    Button colorPicker, resetPaint, adjustWidth;

    SketchView sketchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar()!=null) getSupportActionBar().hide();

        colorPicker = findViewById(R.id.colorPicker);

        resetPaint = findViewById(R.id.clearScreen);

        adjustWidth = findViewById(R.id.adjustWidth);

        sketchView = findViewById(R.id.sketchView);

        ButtonClickedListeners buttonClickedListeners = new ButtonClickedListeners();

        colorPicker.setOnClickListener(buttonClickedListeners.new colorPickerListener());

        resetPaint.setOnClickListener(buttonClickedListeners.new clrListener());

        adjustWidth.setOnClickListener(buttonClickedListeners.new adjustListener());
    }

    class ButtonClickedListeners{
        class colorPickerListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                ColorPickerDialogBuilder
                        .with(MainActivity.this)
                        .setTitle("选择颜色")
                        .initialColor(sketchView.getColor())
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                //mview.setCurCursorColor(selectedColor);
                            }
                        })
                        .setPositiveButton("好", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors) {
                                sketchView.changeColor(lastSelectedColor);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .build()
                        .show();
            }
        }

        class clrListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                sketchView.clear();
            }
        }

        class adjustListener implements View.OnClickListener{
            @Override
            public void onClick(View view) {
                if (sketchView.getStrokeWidth()==10)
                    sketchView.setStrokeWidth(15);
                else sketchView.setStrokeWidth(10);
            }
        }
    }
}