package com.example.daliys.mios;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;

public class MainActivity extends Activity  {
    //Экземпляры классов наших кнопок
    Button buttonOff;
    Button buttonFront,buttonBack;


    //---------Cпина-------
    Button button34,button35,button36,button37,button42,
            button43,button44,button45,button50,button51,
            button52,button53,button54,button55,button56,button57;

    ImageView main_body1,main_body2;
    ImageView image221,image222,image223,image224,image225,image226,image227,image228,image229,
            image230,image231,image232,image233,image234,image235,image236;
    ImageView image101,image102,image103,image104,image105,image106,image107,image108,image109,image110,
            image111,image112,image113,image114,image115,image116,image117,image118,image119,image120;

    //----Управление квадратом------------
    Button buttonUp , buttonDown , buttonRight, buttonLeft;


    //----Управление-Режимом------
    Button buttonStage1,buttonStage2,buttonStage3;

    SeekBar seekBarS,seekBarStage;

    RelativeLayout  Footer;
    RelativeLayout layoutBody1,layoutBody2;

    ImageView myimage;
    LinearLayout.LayoutParams imgFooter;
    LinearLayout LinearLayout1;

    TextView textView2;
    TextView touchBody1,touchBody2;

    //Сокет, с помощью которого мы будем отправлять данные на Arduino
    BluetoothSocket clientSocket;

    boolean StopThread = false;
    int Volt = 0;
    int Freq = 0;
    int S = 0;
    int StageMode = 1;
    int ActivElectrodStage1=-2;
    int ActivElStage1 = -1;
    int ActivElectrodStage2;
    float x;
    float y;
    int ActivrButtonStage3 = 0;
    boolean TouchADDStage2 = true;
    boolean TouchRemoveStage2 = true;

        short im101 = 22;
        short im102 = 24;
    short im103 = 3;
    short im104 = 4;
                    short im105 = 11;//++++
                    short im106 = 10;//++++
    short im107 = 7;
         short im108 = 21;
        short im109 = 71; //
                    short im110 = 12;//++++
        short im111 = 71;//
            short im112 = 23;//
         short im113 = 71;//
                    short im114 = 9; //++++
        short im115 = 71;//
        short im116 = 71;
        short im117 = 71;//
        short im118 = 18;//
        short im119 = 71;//
        short im120 = 71;//
                    short im221 = 16;//++++
                    short im222 = 14;//++++
    short im223 = 23;
                    short im224 = 13;//++++
        short im225 = 19;
    short im226 = 26;
        short im227 = 18;//++++20
         short im228 = 20;
    short im229 = 29;
    short im230 = 30;
                    short im231 = 15;//++++
        short im232 = 17;//++++
    short im233 = 33;
    short im234 = 34;
    short im235 = 35;
        short im236 = 21;
    long TimeLast = System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindElements();
        SetListenerElement();
        InitializationBluetooth();

        InitializationElements();


    }
    private void InitializationElements(){
        main_body1 = (ImageView)findViewById(R.id.mainBody1);
        main_body2 = (ImageView)findViewById(R.id.mainBody2);

        image221 = (ImageView)findViewById(R.id.imageN221);
        image222 = (ImageView)findViewById(R.id.imageN222);
        image223 = (ImageView)findViewById(R.id.imageN223);
        image224 = (ImageView)findViewById(R.id.imageN224);
        image225 = (ImageView)findViewById(R.id.imageN225);
        image226 = (ImageView)findViewById(R.id.imageN226);
        image227 = (ImageView)findViewById(R.id.imageN227);
        image228 = (ImageView)findViewById(R.id.imageN228);
        image229 = (ImageView)findViewById(R.id.imageN229);
        image230 = (ImageView)findViewById(R.id.imageN230);
        image231 = (ImageView)findViewById(R.id.imageN231);
        image232 = (ImageView)findViewById(R.id.imageN232);
        image233 = (ImageView)findViewById(R.id.imageN233);
        image234 = (ImageView)findViewById(R.id.imageN234);
        image235 = (ImageView)findViewById(R.id.imageN235);
        image236 = (ImageView)findViewById(R.id.imageN236);

        image101 = (ImageView)findViewById(R.id.imageN101);
        image102 = (ImageView)findViewById(R.id.imageN102);
        image103 = (ImageView)findViewById(R.id.imageN103);
        image104 = (ImageView)findViewById(R.id.imageN104);
        image105 = (ImageView)findViewById(R.id.imageN105);
        image106 = (ImageView)findViewById(R.id.imageN106);
        image107 = (ImageView)findViewById(R.id.imageN107);
        image108 = (ImageView)findViewById(R.id.imageN108);
        image109 = (ImageView)findViewById(R.id.imageN109);
        image110 = (ImageView)findViewById(R.id.imageN110);
        image111 = (ImageView)findViewById(R.id.imageN111);
        image112 = (ImageView)findViewById(R.id.imageN112);
        image113 = (ImageView)findViewById(R.id.imageN113);
        image114 = (ImageView)findViewById(R.id.imageN114);
        image115 = (ImageView)findViewById(R.id.imageN115);
        image116 = (ImageView)findViewById(R.id.imageN116);
        image117 = (ImageView)findViewById(R.id.imageN117);
        image118 = (ImageView)findViewById(R.id.imageN118);
        image119 = (ImageView)findViewById(R.id.imageN119);
        image120 = (ImageView)findViewById(R.id.imageN120);


        main_body1.setImageResource(R.drawable.main_body1);
        main_body2.setImageResource(R.drawable.main_body2);

        image221.setImageResource(R.drawable.n221);
        image222.setImageResource(R.drawable.n222);
        image223.setImageResource(R.drawable.n223);
        image224.setImageResource(R.drawable.n224);
        image225.setImageResource(R.drawable.n225);
        image226.setImageResource(R.drawable.n226);
        image227.setImageResource(R.drawable.n227);
        image228.setImageResource(R.drawable.n228);
        image229.setImageResource(R.drawable.n229);
        image230.setImageResource(R.drawable.n230);
        image231.setImageResource(R.drawable.n231);
        image232.setImageResource(R.drawable.n232);
        image233.setImageResource(R.drawable.n233);
        image234.setImageResource(R.drawable.n234);
        image235.setImageResource(R.drawable.n235);
        image236.setImageResource(R.drawable.n236);

        image101.setImageResource(R.drawable.n101);
        image102.setImageResource(R.drawable.n102);
        image103.setImageResource(R.drawable.n103);
        image104.setImageResource(R.drawable.n104);
        image105.setImageResource(R.drawable.n105);
        image106.setImageResource(R.drawable.n106);
        image107.setImageResource(R.drawable.n107);
        image108.setImageResource(R.drawable.n108);
        image109.setImageResource(R.drawable.n109);
        image110.setImageResource(R.drawable.n110);
        image111.setImageResource(R.drawable.n111);
        image112.setImageResource(R.drawable.n112);
        image113.setImageResource(R.drawable.n113);
        image114.setImageResource(R.drawable.n114);
        image115.setImageResource(R.drawable.n115);
        image116.setImageResource(R.drawable.n116);
        image117.setImageResource(R.drawable.n117);
        image118.setImageResource(R.drawable.n118);
        image119.setImageResource(R.drawable.n119);
        image120.setImageResource(R.drawable.n120);

        image101.setVisibility(View.INVISIBLE);
        image102.setVisibility(View.INVISIBLE);
        image103.setVisibility(View.INVISIBLE);
        image104.setVisibility(View.INVISIBLE);
        image105.setVisibility(View.INVISIBLE);
        image106.setVisibility(View.INVISIBLE);
        image107.setVisibility(View.INVISIBLE);
        image108.setVisibility(View.INVISIBLE);
        image109.setVisibility(View.INVISIBLE);
        image110.setVisibility(View.INVISIBLE);
        image111.setVisibility(View.INVISIBLE);
        image112.setVisibility(View.INVISIBLE);
        image113.setVisibility(View.INVISIBLE);
        image114.setVisibility(View.INVISIBLE);
        image115.setVisibility(View.INVISIBLE);
        image116.setVisibility(View.INVISIBLE);
        image117.setVisibility(View.INVISIBLE);
        image118.setVisibility(View.INVISIBLE);
        image119.setVisibility(View.INVISIBLE);
        image120.setVisibility(View.INVISIBLE);


        touchBody2.setVisibility(View.INVISIBLE);

        main_body2.setVisibility(View.INVISIBLE);
        image221.setVisibility(View.INVISIBLE);
        image222.setVisibility(View.INVISIBLE);
        image223.setVisibility(View.INVISIBLE);
        image224.setVisibility(View.INVISIBLE);
        image225.setVisibility(View.INVISIBLE);
        image226.setVisibility(View.INVISIBLE);
        image227.setVisibility(View.INVISIBLE);
        image228.setVisibility(View.INVISIBLE);
        image229.setVisibility(View.INVISIBLE);
        image230.setVisibility(View.INVISIBLE);
        image231.setVisibility(View.INVISIBLE);
        image232.setVisibility(View.INVISIBLE);
        image233.setVisibility(View.INVISIBLE);
        image234.setVisibility(View.INVISIBLE);
        image235.setVisibility(View.INVISIBLE);
        image236.setVisibility(View.INVISIBLE);
    }

    private void InitializationBluetooth (){

        //Включаем bluetooth. Если он уже включен, то ничего не произойдет
        String enableBT = BluetoothAdapter.ACTION_REQUEST_ENABLE;
        startActivityForResult(new Intent(enableBT), 0);
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        try{
            //20:16:05:19:17:78
            //98:D3:32:20:96:C3
            BluetoothDevice device = bluetooth.getRemoteDevice("20:16:05:19:17:78");
            //Инициируем соединение с устройством
            Method m = device.getClass().getMethod(
                    "createRfcommSocket", new Class[] {int.class});

            clientSocket = (BluetoothSocket) m.invoke(device, 1);
            clientSocket.connect();
            //В случае появления любых ошибок, выводим в лог сообщение
        } catch (IOException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (SecurityException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.d("BLUETOOTH", e.getMessage());
        } catch (InvocationTargetException e) {
            Log.d("BLUETOOTH", e.getMessage());
        }
        //Выводим сообщение об успешном подключении
        Toast.makeText(getApplicationContext(), "CONNECTED", Toast.LENGTH_LONG).show();

    }
    private void RefreshTach(){
        int MPBottomHeader = imgFooter.bottomMargin;
        int MPLeftHeader = imgFooter.leftMargin;
        int MPRightHeader = imgFooter.rightMargin;
        int MPTopHeader = imgFooter.topMargin;
        MPLeftHeader = 2;
        MPTopHeader = 2;
        Volt = 0;
        Freq = 0;

        imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
        myimage.setLayoutParams(imgFooter);
    }

    private void SetListenerElement(){

        seekBarS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if((int)(seekBar.getProgress()*2.55) < 15){
                    seekBar.setProgress((int)(15/2.55));
                }
                if((int)(seekBar.getProgress()*2.55) >239){
                    seekBar.setProgress((int)(240/2.55));
                }

                S = (int)(seekBar.getProgress()*2.55);
                SendBluetoothS();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarStage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(seekBarStage.getProgress() <17){
                    seekBarStage.setProgress(3);

                    buttonStage1.setAlpha((float)0.8);
                    buttonStage2.setAlpha((float)0.3);
                    buttonStage3.setAlpha((float)0.3);

                    VisibleBody1();
                    InvisibleBody2();


                    SetVisibleRec(true);
                    SendBluetoothStage1(0,false);
                    RefreshTach();
                    ActivElectrodStage1 = 0;

                    buttonBack.setVisibility(View.VISIBLE);
                    buttonFront.setVisibility(View.VISIBLE);
                    seekBarS.setVisibility(View.VISIBLE);
                    buttonOff.setVisibility(View.VISIBLE);

                    if(StageMode == 2 || StageMode == 3){
                        ShiftElementsLeft();
                    }

                    StageMode = 1;

                }else if(seekBarStage.getProgress() >40 ){
                    seekBarStage.setProgress(48);

                    buttonStage3.setAlpha((float)0.8);
                    buttonStage2.setAlpha((float)0.3);
                    buttonStage1.setAlpha((float)0.3);

                    VisibleBody1();
                    VisibleBody2();

                    seekBarS.setVisibility(View.INVISIBLE);

                    SetVisibleRec(false);
                    SendBluetoothStage1(0,false);
                    RefreshTach();
                    ActivElectrodStage1 = 0;
                    buttonOff.setVisibility(View.INVISIBLE);
                    buttonBack.setVisibility(View.INVISIBLE);
                    buttonFront.setVisibility(View.INVISIBLE);

                    if(StageMode != 2 && StageMode != 3){
                        ShiftElementsRight();
                    }

                    StageMode = 3;



                }else if(seekBarStage.getProgress() >16 &&seekBarStage.getProgress() < 41){
                    seekBarStage.setProgress(25);

                    buttonStage2.setAlpha((float)0.8);
                    buttonStage1.setAlpha((float)0.3);
                    buttonStage3.setAlpha((float)0.3);

                    VisibleBody1();
                    VisibleBody2();
                    seekBarS.setVisibility(View.INVISIBLE);
                    buttonOff.setVisibility(View.INVISIBLE);
                    SetVisibleRec(false);

                    SendBluetoothStage1(0,false);
                    RefreshTach();
                    ActivElectrodStage1 = 0;

                    buttonBack.setVisibility(View.INVISIBLE);
                    buttonFront.setVisibility(View.INVISIBLE);

                    if(StageMode != 2 && StageMode != 3){
                        ShiftElementsRight();
                    }

                    StageMode = 2;
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendBluetoothStage1(0,false);
                RefreshTach();




            }
        });

        buttonStage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonStage1.setAlpha((float)0.8);
                buttonStage2.setAlpha((float)0.3);
                buttonStage3.setAlpha((float)0.3);

                VisibleBody1();
                InvisibleBody2();


                SetVisibleRec(true);
                SendBluetoothStage1(0,false);
                RefreshTach();
                ActivElectrodStage1 = 0;

                buttonBack.setVisibility(View.VISIBLE);
                buttonFront.setVisibility(View.VISIBLE);
                seekBarS.setVisibility(View.VISIBLE);
                buttonOff.setVisibility(View.VISIBLE);

                if(StageMode == 2 || StageMode == 3){
                    ShiftElementsLeft();
                }

                StageMode = 1;



            }

        });
        buttonStage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonStage2.setAlpha((float)0.8);
                buttonStage1.setAlpha((float)0.3);
                buttonStage3.setAlpha((float)0.3);

                VisibleBody1();
                VisibleBody2();
                seekBarS.setVisibility(View.INVISIBLE);
                buttonOff.setVisibility(View.INVISIBLE);
                SetVisibleRec(false);

                SendBluetoothStage1(0,false);
                RefreshTach();
                ActivElectrodStage1 = 0;

                buttonBack.setVisibility(View.INVISIBLE);
                buttonFront.setVisibility(View.INVISIBLE);

                if(StageMode != 2 && StageMode != 3){
                    ShiftElementsRight();
                }

                StageMode = 2;


            }
        });

        buttonStage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonStage3.setAlpha((float)0.8);
                buttonStage2.setAlpha((float)0.3);
                buttonStage1.setAlpha((float)0.3);

                VisibleBody1();
                VisibleBody2();

                seekBarS.setVisibility(View.INVISIBLE);

                SetVisibleRec(false);
                SendBluetoothStage1(0,false);
                RefreshTach();
                ActivElectrodStage1 = 0;
                buttonOff.setVisibility(View.INVISIBLE);
                buttonBack.setVisibility(View.INVISIBLE);
                buttonFront.setVisibility(View.INVISIBLE);

                if(StageMode != 2 && StageMode != 3){
                    ShiftElementsRight();
                }

                StageMode = 3;



            }
        });

        //------------КнопкиКвадрата--------------

        buttonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        CreateThreadTimer('0');
                        LeftHeader();
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        StopThread();
                        break;
                }
                return true;
            }

        });
        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        CreateThreadTimer('1');
                        RightHeader();
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        StopThread();
                        break;
                }
                return true;
            }

        });
        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        CreateThreadTimer('2');
                        TopHeader();
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        StopThread();
                        break;
                }
                return true;
            }

        });
        buttonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        CreateThreadTimer('3');
                        ButtonHeader();
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        StopThread();
                        break;
                }
                return true;
            }

        });


        touchBody1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int X_event = (int) motionEvent.getX();
                int Y_event = (int) motionEvent.getY();

                int X = (int)((double)X_event/((double) touchBody1.getHeight())*100);
                int Y = (int)((double)Y_event/((double) touchBody1.getHeight())*100);


                if(StageMode == 1 ) {
                    ActivElStage1=ActivElectrodStage1;
                    InvisibleBody1();
                    touchBody1.setVisibility(View.VISIBLE);
                    main_body1.setVisibility(View.VISIBLE);
                }



                if((X>(0+(100-Y)*0.18 ) && Y<100)&&
                        ((X<(10+(100-Y)*0.3))&&Y>54)){


                    if(StageMode == 1 ) {
                        image101.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im101) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im101;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2){

                        if(image101.getVisibility() == image101.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im101), true);
                            ActivElectrodStage2 = im101;
                            image101.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im101, false);
                            image101.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;


                        }
                    }else if(StageMode == 3){
                        short valueIm = im101;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image101.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>9 && Y <55) &&
                        (X<26 && Y>30)){


                    if(StageMode == 1 ) {
                        image102.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im102) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im102;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image102.getVisibility()== image102.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im102), true);

                            image102.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im102, false);
                            image102.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im102;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image102.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);

                    }

                }else if((X>10 && Y<31)&&
                        X<28 && Y>5){


                    if(StageMode == 1 ) {
                        image103.setVisibility(View.VISIBLE);

                        if(ActivElectrodStage1 != im103) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im103;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image103.getVisibility()== image103.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im103), true);

                            image103.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im103, false);
                            image103.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im103;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image103.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);


                    }


                }else if((X>75 && Y<28)&&
                        (X<92 && Y>5)){


                    if(StageMode == 1) {
                        image104.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im104) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im104;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {

                        if(image104.getVisibility()== image104.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im104), true);

                            image104.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2) {
                            SendBluetoothStage2(im104, false);
                            image104.setVisibility(View.INVISIBLE);
                            TouchADDStage2 = false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im104;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image104.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>75 && Y<55)&&
                        (X<95 && Y>27)){


                    if(StageMode == 1) {
                        image105.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im105) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im105;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image105.getVisibility()== image105.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im105), true);

                            image105.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im105, false);
                            image105.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im105;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image105.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>(85-(100-Y)*0.18)&&Y<100)&&
                        ((X<(100-(100-Y)*0.16))&&Y>54)){


                    if(StageMode == 1) {
                        image106.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im106) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im106;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image106.getVisibility()== image106.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im106), true);

                            image106.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im106, false);
                            image106.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im106;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image106.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }


                }else if ((X>38 && Y<60)&&
                        (X<56 && Y>35)){


                    if(StageMode == 1) {
                        image107.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im107) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im107;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image107.getVisibility()== image107.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im107), true);

                            image107.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im107, false);
                            image107.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im107;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image107.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }



                }else if ((X>38 && Y<100)&&
                        (X<56 && Y>59)){


                    if(StageMode == 1) {
                        image108.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im108) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im108;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image108.getVisibility()== image108.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im108), true);

                            image108.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im108, false);
                            image108.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im108;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image108.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }


                }else if ((X>55 && Y<60)&&
                        (X<69 && Y>35)){


                    if(StageMode == 1) {
                        image109.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im109) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im109;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image109.getVisibility()== image109.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im109), true);

                            image109.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im109, false);
                            image109.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im109;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image109.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }


                }else if ((X>55 && Y<100)&&
                        (X<68 && Y>59)){


                    if(StageMode == 1) {
                        image110.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im110) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im110;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image110.getVisibility()== image110.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im110), true);

                            image110.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im110, false);
                            image110.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    } else if(StageMode == 3){
                        short valueIm = im110;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image110.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>26 && Y<60)&&
                        (X<39 && Y>35)){


                    if(StageMode == 1) {
                        image111.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im111) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im111;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image111.getVisibility()== image111.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im111), true);

                            image111.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im111, false);
                            image111.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im111;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image111.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>26 && Y<84)&&
                        (X<39 && Y>59)){


                    if(StageMode == 1) {
                        image112.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im112) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im112;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image112.getVisibility()== image112.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im112), true);

                            image112.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im112, false);
                            image112.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im112;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image112.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>67 && Y<60)&&
                        (X<76 && Y>35)){


                    if(StageMode == 1) {
                        image113.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im113) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im113;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image113.getVisibility()== image113.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im113), true);

                            image113.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im113, false);
                            image113.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im113;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image113.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>67 && Y<84)&&
                        (X<76 && Y>59)){


                    if(StageMode == 1) {
                        image114.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im114) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im114;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image114.getVisibility()== image114.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im114), true);

                            image114.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im114, false);
                            image114.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im114;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image114.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>24 && Y<100)&&
                        (X<40 && Y>83)){


                    if(StageMode == 1) {
                        image115.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im115) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im115;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image115.getVisibility()== image115.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im115), true);

                            image115.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im115, false);
                            image115.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im115;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image115.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>67 && Y<100)&&
                        (X<77 && Y>83)){



                    if(StageMode == 1) {
                        image116.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im116) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im116;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image116.getVisibility()== image116.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im116), true);

                            image116.setVisibility(View.VISIBLE);
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im116, false);
                            image116.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im116;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image116.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>27 && Y<36)&&
                        (X<55 && Y>9)){



                    if(StageMode == 1) {
                        image117.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im117) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im117;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image117.getVisibility()== image117.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im117), true);

                            image117.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im117, false);
                            image117.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im117;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image117.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>54 && Y<36)&&
                        (X<76 && Y>9)){



                    if(StageMode == 1) {
                        image118.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im118) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im118;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image118.getVisibility()== image118.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im118), true);

                            image118.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if (TouchRemoveStage2){
                            SendBluetoothStage2(im118, false);
                            image118.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im118;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image118.setVisibility(View.VISIBLE);


                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>30 && Y<10)&&
                        (X<55 && Y>0)){


                    if(StageMode == 1) {
                        image119.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im119) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im119;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image119.getVisibility()== image119.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im119), true);

                            image119.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im119, false);
                            image119.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im119;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image119.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }

                }else if ((X>54 && Y<10)&&
                        (X<75 && Y>0)){


                    if(StageMode == 1) {
                        image120.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im120) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im120;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image120.getVisibility()== image120.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im120), true);

                            image120.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im120, false);
                            image120.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im120;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody1();
                        }
                        image120.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm,true);
                    }

                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    TouchADDStage2 = true;
                    TouchRemoveStage2 = true;
                    if(StageMode==3) {
                        SendBluetoothStage2(0, false);
                        VisibleBody1();
                    }
                }
                return true;
            }
        });

        touchBody2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int X_event = (int) motionEvent.getX();
                int Y_event = (int) motionEvent.getY();

                int X = (int)((double)X_event/((double) touchBody1.getHeight())*100);
                int Y = (int)((double)Y_event/((double) touchBody1.getHeight())*100);



                if(StageMode==1) {
                    InvisibleBody2();
                    touchBody2.setVisibility(View.VISIBLE);
                    main_body2.setVisibility(View.VISIBLE);
                }

                if((X>(0+(100-Y)*0.1 ) && Y<100)&&
                        ((X<(15+(100-Y)*0.3))&&Y>54)){



                    if(StageMode == 1) {
                        image221.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im221) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im221;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }

                    }else if(StageMode == 2) {

                        if(image221.getVisibility()== image221.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im221), true);

                            image221.setVisibility(View.VISIBLE);
                            TouchRemoveStage2 = false;

                        }else if (TouchRemoveStage2){
                            image221.setVisibility(View.INVISIBLE);
                            SendBluetoothStage2(im221, false);
                            TouchADDStage2=false;

                        }
                    }else if(StageMode == 3){
                        short valueIm = im221;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image221.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }

                }else if((X>8 && Y <55) &&
                        (X<24 && Y>23)) {

                        if(StageMode == 1) {
                            image222.setVisibility(View.VISIBLE);
                            if(ActivElectrodStage1 != im222) {
                                RestartValue();
                                try {
                                    Thread.currentThread().sleep(60);
                                } catch (Exception e) {
                                }
                                ActivElectrodStage1 = im222;
                                SendBluetoothStage1(ActivElectrodStage1, true);
                            }

                        }else if(StageMode == 2) {
                            if((image222.getVisibility()== image222.INVISIBLE)&&TouchADDStage2){
                                SendBluetoothStage2((im222), true);

                                image222.setVisibility(View.VISIBLE);
                                TouchRemoveStage2 = false;
                            }else if(TouchRemoveStage2){
                                SendBluetoothStage2(im222, false);
                                image222.setVisibility(View.INVISIBLE);
                                TouchADDStage2=false;
                            }
                        }else if(StageMode == 3){
                            short valueIm = im222;
                            if(ActivrButtonStage3 != valueIm){
                                ActivrButtonStage3 = valueIm;
                                SendBluetoothStage2(0,false);
                                VisibleBody2();
                            }
                            image222.setVisibility(View.VISIBLE);

                            SendBluetoothStage2(valueIm,true);
                        }



                    }else if((X>8 && Y <34) &&
                        (X<36 && Y>3)) {



                    if(StageMode == 1) {

                        image223.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im223) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im223;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if((image223.getVisibility()== image223.INVISIBLE) && TouchADDStage2){
                            SendBluetoothStage2((im223), true);

                            TouchRemoveStage2=false;
                            image223.setVisibility(View.VISIBLE);
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im223, false);
                            image223.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im223;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image223.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }



                }else if((X>25 && Y <16) &&
                        (X<50 && Y>0)) {



                    if(StageMode == 1) {
                        image224.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im224) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im224;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if((image224.getVisibility()== image224.INVISIBLE) && TouchADDStage2){
                            SendBluetoothStage2((im224), true);

                            TouchRemoveStage2=false;
                            image224.setVisibility(View.VISIBLE);
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im224, false);
                            image224.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im224;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image224.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>35 && Y <45) &&
                        (X<50 && Y>15)) {


                    if(StageMode == 1) {
                        image235.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im235) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im235;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image235.getVisibility()== image235.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im235), true);

                            image235.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im235, false);
                            image235.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im235;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image235.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>49 && Y <45) &&
                        (X<65 && Y>15)) {


                    if(StageMode == 1) {
                        image236.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im236) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im236;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image236.getVisibility()== image236.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im236), true);

                            image236.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im236, false);
                            image236.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im236;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image236.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>(90-(100-Y)*0.3)&&Y<100)&&
                        ((X<(100-(100-Y)*0.11))&&Y>54)){



                    if(StageMode == 1) {
                        image228.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im228) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im228;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image228.getVisibility()== image228.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im228), true);

                            image228.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if (TouchRemoveStage2){
                            image228.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                            SendBluetoothStage2(im228, false);
                        }
                    }else if(StageMode == 3){
                        short valueIm = im228;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image228.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>73 && Y <55) &&
                        (X<91 && Y>20)) {



                    if(StageMode == 1) {
                        image227.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im227) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im227;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image227.getVisibility()== image227.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im227), true);

                            image227.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im227, false);
                            image227.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im227;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image227.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>64 && Y <33) &&
                        (X<90 && Y>4)) {



                    if(StageMode == 1) {
                        image226.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im226) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im226;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image226.getVisibility()== image226.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im226), true);

                            image226.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im226, false);
                            image226.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im226;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image226.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>49 && Y <16) &&
                        (X<70 && Y>0)) {


                    if(StageMode == 1) {
                        image225.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im225) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im225;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image225.getVisibility()== image225.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im225), true);

                            image225.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im225, false);
                            image225.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im225;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image225.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>23 && Y <55) &&
                        (X<48 && Y>34)) {


                    if(StageMode == 1) {
                        image229.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im229) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im229;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image229.getVisibility()== image229.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im229), true);

                            image229.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im229, false);
                            image229.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im229;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image229.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>50 && Y <55) &&
                        (X<75 && Y>32)) {


                    if(StageMode == 1) {
                        image230.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im230) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im230;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image230.getVisibility()== image230.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im230), true);

                            image230.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im230, false);
                            image230.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im230;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image230.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>25 && Y <82) &&
                        (X<50 && Y>54)) {

                    if(StageMode == 1) {

                        image231.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im231) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im231;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image231.getVisibility()== image231.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im231), true);

                            image231.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im231, false);
                            image231.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im231;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image231.setVisibility(View.VISIBLE);

                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>49 && Y <82) &&
                        (X<72 && Y>54)) {


                    if(StageMode == 1) {
                        image232.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im232) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im232;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image232.getVisibility()== image232.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im232), true);

                            image232.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im232, false);
                            image232.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im232;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image232.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>24 && Y <100) &&
                        (X<50 && Y>81)) {


                    if(StageMode == 1) {
                        image233.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im233) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im233;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    }else if(StageMode == 2) {
                        if(image233.getVisibility()== image233.INVISIBLE && TouchADDStage2){
                            SendBluetoothStage2((im233), true);

                            image233.setVisibility(View.VISIBLE);
                            TouchRemoveStage2=false;
                        }else if(TouchRemoveStage2){
                            SendBluetoothStage2(im233, false);
                            image233.setVisibility(View.INVISIBLE);
                            TouchADDStage2=false;
                        }
                    }else if(StageMode == 3){
                        short valueIm = im233;
                        if(ActivrButtonStage3 != valueIm){
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0,false);
                            VisibleBody2();
                        }
                        image233.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm,true);
                    }


                }else if((X>49 && Y <100) &&
                        (X<75 && Y>81)) {



                    if (StageMode == 1) {
                        image234.setVisibility(View.VISIBLE);
                        if(ActivElectrodStage1 != im234) {
                            RestartValue();
                            try {
                                Thread.currentThread().sleep(60);
                            } catch (Exception e) {
                            }
                            ActivElectrodStage1 = im234;
                            SendBluetoothStage1(ActivElectrodStage1, true);
                        }
                    } else if (StageMode == 2) {
                        if (image234.getVisibility() == image234.INVISIBLE && TouchADDStage2) {
                            SendBluetoothStage1((im234), true);

                            image234.setVisibility(View.VISIBLE);
                            TouchRemoveStage2 = false;
                        } else if (TouchRemoveStage2) {
                            SendBluetoothStage2(im234, false);
                            image234.setVisibility(View.INVISIBLE);
                            TouchADDStage2 = false;
                        }
                    } else if (StageMode == 3) {
                        short valueIm = im234;
                        if (ActivrButtonStage3 != valueIm) {
                            ActivrButtonStage3 = valueIm;
                            SendBluetoothStage2(0, false);
                            VisibleBody2();
                        }
                        image234.setVisibility(View.VISIBLE);
                        SendBluetoothStage2(valueIm, true);
                    }
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    TouchADDStage2 = true;
                    TouchRemoveStage2 = true;
                    if(StageMode==3) {
                        SendBluetoothStage2(0, false);
                        VisibleBody2();
                    }
                }

                return true;
            }
        });


        //---ТАЧ_Квадрата------------------
        textView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                x = event.getX();
                y = event.getY();
                int leftX = (int) x;
                int TopY = (int) y;
                int leftXFix;
                int TopYFix;
                int MPBottomHeader = imgFooter.bottomMargin;
                int MPLeftHeader = imgFooter.leftMargin;
                int MPRightHeader = imgFooter.rightMargin;
                int MPTopHeader = imgFooter.topMargin;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        if (leftX >= 4 && TopY >= 4 && leftX<= 298 && TopY<=298) {
                            leftXFix = leftX;
                            double dleftX = (double)leftXFix * 0.416*2;
                            MPLeftHeader = (int)dleftX;
                            TopYFix = TopY;
                            double dTopY = (double)TopYFix * 0.416*2;
                            MPTopHeader = (int)dTopY;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                        if (leftX >= 4 && TopY >= 4 && leftX<= 298 && TopY<=298) {
                            leftXFix = leftX;
                            double dleftX = (double)leftXFix * 0.416*2;
                            MPLeftHeader = (int)dleftX;
                            TopYFix = TopY;
                            double dTopY = (double)TopYFix * 0.416*2;
                            MPTopHeader = (int)dTopY;
                            showInfo();
                        }
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                    case MotionEvent.ACTION_CANCEL:
                        if (leftX >= 4 && TopY >= 4 && leftX<= 298 && TopY<=298) {
                            leftXFix = leftX;
                            double dleftX = (double)leftXFix * 0.416*2;
                            MPLeftHeader = (int)dleftX;
                            TopYFix = TopY;
                            double dTopY = (double)TopYFix * 0.416*2;
                            MPTopHeader = (int)dTopY;
                            showInfo();
                        }
                        break;
                }
                imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
                myimage.setLayoutParams(imgFooter);
                return true;

            }
        });


        //-------Поворот тела----------
        buttonFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleBody1();
                InvisibleBody2();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisibleBody2();
                InvisibleBody1();
            }
        });
    }
    private void RestartValue(){
        SendBluetoothStage1(0,false);

    }

    private void VisibleBody1(){
        main_body1.setVisibility(View.VISIBLE);
        image101.setVisibility(View.INVISIBLE);
        image102.setVisibility(View.INVISIBLE);
        image103.setVisibility(View.INVISIBLE);
        image104.setVisibility(View.INVISIBLE);
        image105.setVisibility(View.INVISIBLE);
        image106.setVisibility(View.INVISIBLE);
        image107.setVisibility(View.INVISIBLE);
        image108.setVisibility(View.INVISIBLE);
        image109.setVisibility(View.INVISIBLE);
        image110.setVisibility(View.INVISIBLE);
        image111.setVisibility(View.INVISIBLE);
        image112.setVisibility(View.INVISIBLE);
        image113.setVisibility(View.INVISIBLE);
        image114.setVisibility(View.INVISIBLE);
        image115.setVisibility(View.INVISIBLE);
        image116.setVisibility(View.INVISIBLE);
        image117.setVisibility(View.INVISIBLE);
        image118.setVisibility(View.INVISIBLE);
        image119.setVisibility(View.INVISIBLE);
        image120.setVisibility(View.INVISIBLE);
        touchBody1.setVisibility(View.VISIBLE);

    }
    private void InvisibleBody1(){
        main_body1.setVisibility(View.INVISIBLE);
        image101.setVisibility(View.INVISIBLE);
        image102.setVisibility(View.INVISIBLE);
        image103.setVisibility(View.INVISIBLE);
        image104.setVisibility(View.INVISIBLE);
        image105.setVisibility(View.INVISIBLE);
        image106.setVisibility(View.INVISIBLE);
        image107.setVisibility(View.INVISIBLE);
        image108.setVisibility(View.INVISIBLE);
        image109.setVisibility(View.INVISIBLE);
        image110.setVisibility(View.INVISIBLE);
        image111.setVisibility(View.INVISIBLE);
        image112.setVisibility(View.INVISIBLE);
        image113.setVisibility(View.INVISIBLE);
        image114.setVisibility(View.INVISIBLE);
        image115.setVisibility(View.INVISIBLE);
        image116.setVisibility(View.INVISIBLE);
        image117.setVisibility(View.INVISIBLE);
        image118.setVisibility(View.INVISIBLE);
        image119.setVisibility(View.INVISIBLE);
        image120.setVisibility(View.INVISIBLE);
        touchBody1.setVisibility(View.INVISIBLE);

    }
    private void VisibleBody2(){

        main_body2.setVisibility(View.VISIBLE);
        image221.setVisibility(View.INVISIBLE);
        image222.setVisibility(View.INVISIBLE);
        image223.setVisibility(View.INVISIBLE);
        image224.setVisibility(View.INVISIBLE);
        image225.setVisibility(View.INVISIBLE);
        image226.setVisibility(View.INVISIBLE);
        image227.setVisibility(View.INVISIBLE);
        image228.setVisibility(View.INVISIBLE);
        image229.setVisibility(View.INVISIBLE);
        image230.setVisibility(View.INVISIBLE);
        image231.setVisibility(View.INVISIBLE);
        image232.setVisibility(View.INVISIBLE);
        image233.setVisibility(View.INVISIBLE);
        image234.setVisibility(View.INVISIBLE);
        image235.setVisibility(View.INVISIBLE);
        image236.setVisibility(View.INVISIBLE);
        touchBody2.setVisibility(View.VISIBLE);
    }
    private void InvisibleBody2(){

        main_body2.setVisibility(View.INVISIBLE);
        image221.setVisibility(View.INVISIBLE);
        image222.setVisibility(View.INVISIBLE);
        image223.setVisibility(View.INVISIBLE);
        image224.setVisibility(View.INVISIBLE);
        image225.setVisibility(View.INVISIBLE);
        image226.setVisibility(View.INVISIBLE);
        image227.setVisibility(View.INVISIBLE);
        image228.setVisibility(View.INVISIBLE);
        image229.setVisibility(View.INVISIBLE);
        image230.setVisibility(View.INVISIBLE);
        image231.setVisibility(View.INVISIBLE);
        image232.setVisibility(View.INVISIBLE);
        image233.setVisibility(View.INVISIBLE);
        image234.setVisibility(View.INVISIBLE);
        image235.setVisibility(View.INVISIBLE);
        image236.setVisibility(View.INVISIBLE);
        touchBody2.setVisibility(View.INVISIBLE);
    }


    private void SetVisibleRec(boolean visible){
        if(visible) {
            buttonUp.setVisibility(View.VISIBLE);
            buttonDown.setVisibility(View.VISIBLE);
            buttonLeft.setVisibility(View.VISIBLE);
            buttonRight.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myimage.setVisibility(View.VISIBLE);
            LinearLayout1.setVisibility(View.VISIBLE);


        }else {
            buttonUp.setVisibility(View.INVISIBLE);
            buttonDown.setVisibility(View.INVISIBLE);
            buttonLeft.setVisibility(View.INVISIBLE);
            buttonRight.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myimage.setVisibility(View.INVISIBLE);
            LinearLayout1.setVisibility(View.INVISIBLE);





        }
    }
    private void ShiftElementsLeft(){
        VisibleBody1();
        InvisibleBody2();
        layoutBody1.setLeft(50);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 24, 650, 100);
        layoutBody2.setLayoutParams(layoutParams);

    }
    private void ShiftElementsRight(){

        VisibleBody1();
        VisibleBody2();
        layoutBody2.setRight(500);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(650, 24, 0, 100);
        layoutBody2.setLayoutParams(layoutParams);

    }



    private static final Object lock = new Object();
    private void SendBluetoothS(){        //бесполезные переменные
        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                   synchronized (lock) {
                       char s = 'S';
                       int temp;
                       char s1, s2, s3;
                       OutputStream outStream = clientSocket.getOutputStream();


                       temp = (S / 100) + 48;
                       s1 = (char) temp;
                       temp = ((S / 10) % 10) + 48;
                       s2 = (char) temp;
                       temp = (S % 10) + 48;
                       s3 = (char) temp;

                       outStream.write(s);
                       outStream.write(s1);
                       outStream.write(s2);
                       outStream.write(s3);

                   }
                }catch (Exception e){}
            }
        });
        threadSend.start();

    }

    private void SendBluetoothStage2(final int Channel,final  boolean stage) {
        if ((System.currentTimeMillis() - TimeLast) > 50) {
            TimeLast = System.currentTimeMillis();
            Thread threadSend = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int temp;
                        char chen1, chen2, chen3;
                        char n = 'N';

                        OutputStream outStream = clientSocket.getOutputStream();
                        if (stage) {
                            temp = 49;
                        } else {
                            temp = 48;
                        }
                        if (Channel >= 10) {
                            chen1 = (char) temp;
                            temp = (Channel / 10) % 10 + 48;
                            chen2 = (char) temp;
                            temp = (Channel) % 10 + 48;
                            chen3 = (char) temp;
                        } else {
                            chen1 = (char) temp;
                            temp = 48;
                            chen2 = (char) temp;
                            temp = (Channel) % 10 + 48;
                            chen3 = (char) temp;
                        }
                        outStream.write(n);
                        outStream.write(chen1);
                        outStream.write(chen2);
                        outStream.write(chen3);

                    } catch (Exception e) {
                    }
                }
            });
            threadSend.start();


        }
    }

    private void SendBluetoothStage1(final int Channel,final boolean stage) {
        if ((System.currentTimeMillis() - TimeLast) > 50) {

            TimeLast = System.currentTimeMillis();
            Thread threadSend = new Thread(new Runnable() {
                @Override
                public void run() {
                    int temp;
                    char volt1, volt2, volt3;
                    char freq1, freq2, freq3;
                    char chen1, chen2, chen3;
                    char n = 'N';
                    char r = 'R';
                    char t = 'T';

                    try {
                        OutputStream outStream = clientSocket.getOutputStream();

                        if (Channel != 0 && stage) {

                            temp = (Volt / 100) + 48;
                            volt1 = (char) temp;
                            temp = ((Volt / 10) % 10) + 48;
                            volt2 = (char) temp;
                            temp = (Volt % 10) + 48;
                            volt3 = (char) temp;

                            outStream.write(t);
                            outStream.write(volt1);
                            outStream.write(volt2);
                            outStream.write(volt3);


                            temp = (Freq / 100) + 48;
                            freq1 = (char) temp;
                            temp = ((Freq / 10) % 10) + 48;
                            freq2 = (char) temp;
                            temp = (Freq % 10) + 48;
                            freq3 = (char) temp;

                            outStream.write(r);
                            outStream.write(freq1);
                            outStream.write(freq2);
                            outStream.write(freq3);


                            if (stage) {
                                temp = 49;
                            } else {
                                temp = 48;
                            }
                            if (Channel >= 10) {
                                chen1 = (char) temp;
                                temp = (Channel / 10) % 10 + 48;
                                chen2 = (char) temp;
                                temp = (Channel) % 10 + 48;
                                chen3 = (char) temp;
                            } else {
                                chen1 = (char) temp;
                                temp = 48;
                                chen2 = (char) temp;
                                temp = (Channel) % 10 + 48;
                                chen3 = (char) temp;
                            }

                            outStream.write(n);
                            outStream.write(chen1);
                            outStream.write(chen2);
                            outStream.write(chen3);

                        } else {
                            if (stage) {
                                temp = 49;
                            } else {
                                temp = 48;
                            }
                            chen1 = (char) temp;
                            temp = (Channel / 10) % 10 + 48;
                            chen2 = (char) temp;
                            temp = (Channel) % 10 + 48;
                            chen3 = (char) temp;

                            outStream.write(n);
                            outStream.write(chen1);
                            outStream.write(chen2);
                            outStream.write(chen3);
                        }
                    } catch (Exception e) {
                        Log.e("Send", e + "");
                    }
                }
            });
            threadSend.start();


        }
    }
    private void CreateThreadTimer(final char CheckButton){
        Thread threadButton = new Thread(new Runnable() {
            @Override
            public void run() {
                StopThread = false;
                long TotalTime;
                long TimeStart = System.currentTimeMillis();
                while(!StopThread){
                    TotalTime = System.currentTimeMillis() - TimeStart;
                    if(TotalTime >= 40){
                        TimeStart += 40;
                        if(CheckButton == '0') {
                            LeftHeader();
                        }
                        if(CheckButton == '1') {
                            RightHeader();
                        }
                        if(CheckButton == '2') {
                            TopHeader();
                        }
                        if(CheckButton == '3') {
                            ButtonHeader();
                        }
                    }
                }
            }
        });
        threadButton.start();


    }


    private void StopThread(){
        StopThread = true;
    }
    void showInfo() {
        int MPLeftHeader = imgFooter.leftMargin;
        int MPTopHeader = imgFooter.topMargin;
        MPTopHeader = MPTopHeader - 4;
        MPLeftHeader = MPLeftHeader -4;
        String str = Integer.toString(MPLeftHeader);
        String str1 = Integer.toString(MPTopHeader);
        double dbl = Double.parseDouble(str);
        double dbl1 = Double.parseDouble(str1);
        dbl = dbl / 0.9461538;
        dbl1 = dbl1 / 0.9461538;

        int in = (int) dbl;
        int in1 = (int) dbl1;
        if(in > 255){
            in = 255;
        }
        if(in < 0){
            in = 0;
        }
        if(in1 > 255){
            in1 = 255;
        }
        if(in1 < 0){
            in1 = 0;
        }
        Volt = in;
        Freq = in1;
        SendBluetoothStage1(ActivElectrodStage1,true);

        final String strdbl = Integer.toString(Volt);
        final String str1dbl1 = Integer.toString(Freq);

    }

    private void LeftHeader(){
        int MPBottomHeader = imgFooter.bottomMargin;
        int MPLeftHeader = imgFooter.leftMargin;
        int MPRightHeader = imgFooter.rightMargin;
        int MPTopHeader = imgFooter.topMargin;

        if (MPLeftHeader < 245) {
            MPLeftHeader = MPLeftHeader + 2;
            imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myimage.setLayoutParams(imgFooter);
                    }
                });

            } catch (Exception e) {
                Log.d("MyImage", e + "");
            }

            showInfo();
        }

    }
   private void RightHeader(){
        int MPBottomHeader = imgFooter.bottomMargin;
        int MPLeftHeader = imgFooter.leftMargin;
        int MPRightHeader = imgFooter.rightMargin;
        int MPTopHeader = imgFooter.topMargin;

        if (MPLeftHeader > 5) {
            MPLeftHeader = MPLeftHeader - 2;
            imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myimage.setLayoutParams(imgFooter);
                    }
                });

            } catch (Exception e) {
                Log.d("MyImage", e + "");
            }

            showInfo();
        }

    }
   private void TopHeader(){
        int MPBottomHeader = imgFooter.bottomMargin;
        int MPLeftHeader = imgFooter.leftMargin;
        int MPRightHeader = imgFooter.rightMargin;
        int MPTopHeader = imgFooter.topMargin;

        if (MPTopHeader < 245) {
            MPTopHeader = MPTopHeader + 2;
            imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myimage.setLayoutParams(imgFooter);
                    }
                });

            } catch (Exception e) {
                Log.d("MyImage", e + "");
            }

            showInfo();
        }

    }
    private void ButtonHeader(){
        int MPBottomHeader = imgFooter.bottomMargin;
        int MPLeftHeader = imgFooter.leftMargin;
        int MPRightHeader = imgFooter.rightMargin;
        int MPTopHeader = imgFooter.topMargin;

        if (MPTopHeader > 5) {
            MPTopHeader = MPTopHeader - 2;
            imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myimage.setLayoutParams(imgFooter);
                    }
                });

            } catch (Exception e) {
                Log.d("MyImage", e + "");
            }

            showInfo();
        }

    }

    private void FindElements(){

        touchBody1 = (TextView)findViewById(R.id.touchBody1);
        touchBody2 = (TextView)findViewById(R.id.touchBody2);

        buttonOff = (Button) findViewById(R.id.buttonOff);
        buttonUp = (Button) findViewById(R.id.buttonUp);
        buttonDown = (Button) findViewById(R.id.buttonDown);
        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonRight = (Button) findViewById(R.id.buttonRight);


        seekBarS = (SeekBar)findViewById(R.id.seekbar);
        seekBarStage = (SeekBar)findViewById(R.id.seekbar2);

        textView2 = (TextView) findViewById(R.id.textView2);

        myimage = (ImageView) findViewById(R.id.myimage);
        imgFooter = (LinearLayout.LayoutParams) myimage.getLayoutParams();
        LinearLayout1 = (LinearLayout) findViewById(R.id.topleft);

        layoutBody1 = (RelativeLayout) findViewById(R.id.relativeLayout);
        layoutBody2 = (RelativeLayout) findViewById(R.id.relativeLayout2);

        buttonFront = (Button)findViewById(R.id.button5);
        buttonBack = (Button) findViewById(R.id.button58);

        buttonStage1 = (Button) findViewById(R.id.button_stage1);
        buttonStage2 = (Button) findViewById(R.id.button_stage2);
        buttonStage3 = (Button) findViewById(R.id.button_stage3);


    }

}


