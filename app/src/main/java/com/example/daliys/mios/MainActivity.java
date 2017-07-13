package com.example.daliys.mios;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends Activity  {
    //Экземпляры классов наших кнопок
    Button buttonOff;
    Button buttonFront,buttonBack;
    TextView textViewValue;
    TextView textViewStage3;

    //--------Body-------
    Button button18,button6,button7,button20,button9,
            button10,button14,button12,button16;

    //------------Руки--------
    Button button27,button24, button22;
    Button button29,button31,button32;
    Button button4;

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
    int ActivElectrodStage1;
    int ActivElectrodStage2;
    float x;
    float y;
    int ActivrButtonStage3 = 0;

    short im101 = 1;
    short im102 = 2;
    short im103 = 3;
    short im104 = 4;
    short im105 = 5;
    short im106 = 6;
    short im107 = 7;
    short im108 = 8;
    short im109 = 9;
    short im110 = 10;
    short im111 = 11;
    short im112 = 12;
    short im113 = 13;
    short im114 = 14;
    short im115 = 15;
    short im116 = 16;
    short im117 = 17;
    short im118 = 18;
    short im119 = 19;
    short im120 = 20;
    short im221 = 21;
    short im222 = 22;
    short im223 = 23;
    short im224 = 24;
    short im225 = 25;
    short im226 = 26;
    short im227 = 27;
    short im228 = 28;
    short im229 = 29;
    short im230 = 30;
    short im231 = 31;
    short im232 = 32;
    short im233 = 33;
    short im234 = 34;
    short im235 = 35;
    short im236 = 36;

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
            BluetoothDevice device = bluetooth.getRemoteDevice("98:D3:32:20:96:C3");
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
        textViewValue.setText("0 0");
        imgFooter.setMargins(MPLeftHeader, MPTopHeader, MPRightHeader, MPBottomHeader);
        myimage.setLayoutParams(imgFooter);
    }

    private void SetListenerElement(){


        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendBluetoothStage1(0,false);
                RefreshTach();
                ActivElectrodStage1 = 0;
            }
        });

        buttonStage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonStage1.setAlpha((float)0.8);
                buttonStage2.setAlpha((float)0.3);
                buttonStage3.setAlpha((float)0.3);


                textViewStage3.setVisibility(View.INVISIBLE);

                SetVisibleRec(true);
                SendBluetoothStage1(0,false);
                RefreshTach();
                ActivElectrodStage1 = 0;

                buttonBack.setVisibility(View.VISIBLE);
                buttonFront.setVisibility(View.VISIBLE);

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

                textViewStage3.setVisibility(View.INVISIBLE);


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

                textViewStage3.setVisibility(View.VISIBLE);


                SetVisibleRec(false);
                SendBluetoothStage1(0,false);
                RefreshTach();
                ActivElectrodStage1 = 0;

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


      /*  ///-----------Body-------
        button18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 12;
                            button18.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button18.getAlpha() == (float)(Alphas)){
                                button18.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(12, false);
                            }else{
                                button18.setAlpha((float) Alphas);
                                SendBluetoothStage2(12, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 10;
                            button6.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button6.getAlpha() == (float)(Alphas)){
                                button6.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(10, false);
                            }else{
                                button6.setAlpha((float) Alphas);
                                SendBluetoothStage2(10, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });


        button20.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 11;
                            button20.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button20.getAlpha() == (float)(Alphas)){
                                button20.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(11, false);
                            }else{
                                button20.setAlpha((float) Alphas);
                                SendBluetoothStage2(11, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });


        button10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 2;
                            button10.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button10.getAlpha() == (float)(Alphas)){
                                button10.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(2, false);
                            }else{
                                button10.setAlpha((float) Alphas);
                                SendBluetoothStage2(2, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 9;
                            button9.setAlpha((float)Alphas);
                            //button9.setLeft(20);
                        }else if(StageMode == 2) {
                            if(button9.getAlpha() == (float)(Alphas)){
                                button9.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(9, false);
                            }else{
                                button9.setAlpha((float) Alphas);
                                SendBluetoothStage2(9, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        //button9.setLeft(655);
        button14.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 4;
                            button14.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button14.getAlpha() == (float)(Alphas)){
                                button14.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(4, false);
                            }else{
                                button14.setAlpha((float) Alphas);
                                SendBluetoothStage2(4, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });


        button12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 1;
                            button12.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button12.getAlpha() == (float)(Alphas)){
                                button12.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(1, false);
                            }else{
                                button12.setAlpha((float) Alphas);
                                SendBluetoothStage2(1, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 3;
                            button16.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button16.getAlpha() == (float)(Alphas)){
                                button16.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(3, false);
                            }else{
                                button16.setAlpha((float) Alphas);
                                SendBluetoothStage2(3, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });





        //---------Back-----------------------------


        button45.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 8;
                            button45.setAlpha((float)Alphas);

                        }else if(StageMode == 2) {
                            if(button45.getAlpha() == (float)(Alphas)){
                                button45.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(8, false);
                            }else{
                                button45.setAlpha((float) Alphas);
                                SendBluetoothStage2(8, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button44.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 6;

                            button44.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button44.getAlpha() == (float)(Alphas)){
                                button44.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(6, false);
                            }else{
                                button44.setAlpha((float) Alphas);
                                SendBluetoothStage2(6, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button43.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 14;

                            button43.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button43.getAlpha() == (float)(Alphas)){
                                button43.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(14, false);
                            }else{
                                button43.setAlpha((float) Alphas);
                                SendBluetoothStage2(14, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button42.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 16;
                            button42.setAlpha((float)Alphas);

                        }else if(StageMode == 2) {
                            if(button42.getAlpha() == (float)(Alphas)){
                                button42.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(16, false);
                            }else{
                                button42.setAlpha((float) Alphas);
                                SendBluetoothStage2(16, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });




        button37.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 7;
                            button37.setAlpha((float)Alphas);

                        }else if(StageMode == 2) {
                            if(button37.getAlpha() == (float)(Alphas)){
                                button37.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(7, false);
                            }else{
                                button37.setAlpha((float) Alphas);
                                SendBluetoothStage2(7, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button36.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 5;
                            button36.setAlpha((float)Alphas);

                        }else if(StageMode == 2) {
                            if(button36.getAlpha() == (float)(Alphas)){
                                button36.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(5, false);
                            }else{
                                button36.setAlpha((float) Alphas);
                                SendBluetoothStage2(5, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button35.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 13;
                            button35.setAlpha((float)Alphas);

                        }else if(StageMode == 2) {
                            if(button35.getAlpha() == (float)(Alphas)){
                                button35.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(13, false);
                            }else{
                                button35.setAlpha((float) Alphas);
                                SendBluetoothStage2(13, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button34.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 15;
                            button34.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button34.getAlpha() == (float)(Alphas)){
                                button34.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(15, false);
                            }else{
                                button34.setAlpha((float) Alphas);
                                SendBluetoothStage2(15, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });

        //----------------arms Left--------------------


        button32.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 17;
                            button32.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button32.getAlpha() == (float)(Alphas)){
                                button32.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(17, false);
                            }else{
                                button32.setAlpha((float) Alphas);
                                SendBluetoothStage2(17, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button31.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 18;
                            button31.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button31.getAlpha() == (float)(Alphas)){
                                button31.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(18, false);
                            }else{
                                button31.setAlpha((float) Alphas);
                                SendBluetoothStage2(18, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });

        button29.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 19;
                            button29.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button29.getAlpha() == (float)(Alphas)){
                                button29.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(19, false);
                            }else{
                                button29.setAlpha((float) Alphas);
                                SendBluetoothStage2(19, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 20;
                            button7.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button7.getAlpha() == (float)(Alphas)){
                                button7.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(20, false);
                            }else{
                                button7.setAlpha((float) Alphas);
                                SendBluetoothStage2(20, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button57.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 21;
                            button57.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button57.getAlpha() == (float)(Alphas)){
                                button57.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(21, false);
                            }else{
                                button57.setAlpha((float) Alphas);
                                SendBluetoothStage2(21, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button54.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 22;
                            button54.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button54.getAlpha() == (float)(Alphas)){
                                button54.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(22, false);
                            }else{
                                button54.setAlpha((float) Alphas);
                                SendBluetoothStage2(22, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button56.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 23;
                            button56.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button56.getAlpha() == (float)(Alphas)){
                                button56.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(23, false);
                            }else{
                                button56.setAlpha((float) Alphas);
                                SendBluetoothStage2(23, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button55.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 24;
                            button55.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button55.getAlpha() == (float)(Alphas)){
                                button55.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(24, false);
                            }else{
                                button55.setAlpha((float) Alphas);
                                SendBluetoothStage2(24, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });




        //----arms Right--------

        button27.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 25;
                            button27.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button27.getAlpha() == (float)(Alphas)){
                                button27.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(25, false);
                            }else{
                                button27.setAlpha((float) Alphas);
                                SendBluetoothStage2(25, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button24.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 26;
                            button24.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button24.getAlpha() == (float)(Alphas)){
                                button24.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(26, false);
                            }else{
                                button24.setAlpha((float) Alphas);
                                SendBluetoothStage2(26, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });

        button22.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 27;
                            button22.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button22.getAlpha() == (float)(Alphas)){
                                button22.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(27, false);
                            }else{
                                button22.setAlpha((float) Alphas);
                                SendBluetoothStage2(27, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button50.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 28;
                            button50.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button50.getAlpha() == (float)(Alphas)){
                                button50.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(28, false);
                            }else{
                                button50.setAlpha((float) Alphas);
                                SendBluetoothStage2(28, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 29;
                            button4.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button4.getAlpha() == (float)(Alphas)){
                                button4.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(29, false);
                            }else{
                                button4.setAlpha((float) Alphas);
                                SendBluetoothStage2(29, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button53.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 30;
                            button53.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button53.getAlpha() == (float)(Alphas)){
                                button53.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(30, false);
                            }else{
                                button53.setAlpha((float) Alphas);
                                SendBluetoothStage2(30, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button52.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 31;
                            button52.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button52.getAlpha() == (float)(Alphas)){
                                button52.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(31, false);
                            }else{
                                button52.setAlpha((float) Alphas);
                                SendBluetoothStage2(31, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        button51.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажати
                        if(StageMode == 1) {
                            CleanAlpha();
                            RestartValue();
                            ActivElectrodStage1 = 32;
                            button51.setAlpha((float)Alphas);
                        }else if(StageMode == 2) {
                            if(button51.getAlpha() == (float)(Alphas)){
                                button51.setAlpha((float)AlphasOff);
                                SendBluetoothStage1(32, false);
                            }else{
                                button51.setAlpha((float) Alphas);
                                SendBluetoothStage2(32, true);
                            }
                        }
                        break;
                }
                return true;
            }
        });
*/
/*        buttonS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S = 0;
                buttonS1.setAlpha((float)(Alphas));
                buttonS2.setAlpha((float)(AlphasOff));
                buttonS3.setAlpha((float)(AlphasOff));
                buttonS4.setAlpha((float)(AlphasOff));
                buttonS5.setAlpha((float)(AlphasOff));
                SendBluetoothS(ActivElectrodStage1 , true);

            }
        });

        buttonS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S = 50;
                buttonS1.setAlpha((float)(AlphasOff));
                buttonS2.setAlpha((float)(Alphas));
                buttonS3.setAlpha((float)(AlphasOff));
                buttonS4.setAlpha((float)(AlphasOff));
                buttonS5.setAlpha((float)(AlphasOff));
                SendBluetoothS(ActivElectrodStage1 , true);

            }
        });

        buttonS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S = 100;
                buttonS1.setAlpha((float)(AlphasOff));
                buttonS2.setAlpha((float)(AlphasOff));
                buttonS3.setAlpha((float)(Alphas));
                buttonS4.setAlpha((float)(AlphasOff));
                buttonS5.setAlpha((float)(AlphasOff));
                SendBluetoothS(ActivElectrodStage1 , true);

            }
        });

        buttonS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S = 150;
                buttonS1.setAlpha((float)(AlphasOff));
                buttonS2.setAlpha((float)(AlphasOff));
                buttonS3.setAlpha((float)(AlphasOff));
                buttonS4.setAlpha((float)(Alphas));
                buttonS5.setAlpha((float)(AlphasOff));
                SendBluetoothS(ActivElectrodStage1 , true);

            }
        });
        buttonS5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S = 250;
                buttonS1.setAlpha((float)(AlphasOff));
                buttonS2.setAlpha((float)(AlphasOff));
                buttonS3.setAlpha((float)(AlphasOff));
                buttonS4.setAlpha((float)(AlphasOff));
                buttonS5.setAlpha((float)(Alphas));
                SendBluetoothS(ActivElectrodStage1 , true);

            }
        });*/
        touchBody1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int X_event = (int) motionEvent.getX();
                int Y_event = (int) motionEvent.getY();

                int X = (int)((double)X_event/((double) touchBody1.getHeight())*100);
                int Y = (int)((double)Y_event/((double) touchBody1.getHeight())*100);
                touchBody1.setText("x:"+X+"y:"+Y);

                if(StageMode == 1) {
                    InvisibleBody1();
                    touchBody1.setVisibility(View.VISIBLE);
                    main_body1.setVisibility(View.VISIBLE);
                }

                if((X>(0+(100-Y)*0.18 ) && Y<100)&&
                        ((X<(10+(100-Y)*0.3))&&Y>54)){
                    touchBody1.setText("HERE101");
                    image101.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im101;
                    }else if(StageMode == 2){

                        if(image101.getVisibility() == image101.INVISIBLE){
                            SendBluetoothStage1((im101), false);
                            ActivElectrodStage2 = im101;
                        }else{
                            SendBluetoothStage2(im101, true);
                        }
                    }


                }else if((X>9 && Y <55) &&
                        (X<26 && Y>30)){
                    touchBody1.setText("HERE102");
                    image102.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im102;
                    }else if(StageMode == 2) {
                        if(image102.getVisibility()== image102.VISIBLE){
                            SendBluetoothStage1((im102), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im102, true);
                        }
                    }

                }else if((X>10 && Y<31)&&
                        X<28 && Y>5){
                    touchBody1.setText("HERE103");
                    image103.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im103;
                    }else if(StageMode == 2) {
                        if(image103.getVisibility()== image103.VISIBLE){
                            SendBluetoothStage1((im103), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im103, true);
                        }
                    }


                }else if((X>75 && Y<28)&&
                        (X<92 && Y>5)){
                    touchBody1.setText("HERE104");
                    image104.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im104;
                    }else if(StageMode == 2) {
                        if(image104.getVisibility()== image104.VISIBLE){
                            SendBluetoothStage1((im104), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im104, true);
                        }
                    }


                }else if((X>75 && Y<55)&&
                        (X<95 && Y>27)){
                    touchBody1.setText("HERE105");
                    image105.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im105;
                    }else if(StageMode == 2) {
                        if(image105.getVisibility()== image105.VISIBLE){
                            SendBluetoothStage1((im105), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im105, true);
                        }
                    }


                }else if((X>(85-(100-Y)*0.18)&&Y<100)&&
                        ((X<(100-(100-Y)*0.16))&&Y>54)){
                    touchBody1.setText("HERE106");
                    image106.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im106;
                    }else if(StageMode == 2) {
                        if(image106.getVisibility()== image106.VISIBLE){
                            SendBluetoothStage1((im106), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im106, true);
                        }
                    }


                }else if ((X>38 && Y<60)&&
                        (X<56 && Y>35)){
                    touchBody1.setText("HERE107");
                    image107.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im107;
                    }else if(StageMode == 2) {
                        if(image107.getVisibility()== image107.VISIBLE){
                            SendBluetoothStage1((im107), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im107, true);
                        }
                    }



                }else if ((X>38 && Y<100)&&
                        (X<56 && Y>59)){
                    touchBody1.setText("HERE108");
                    image108.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im108;
                    }else if(StageMode == 2) {
                        if(image108.getVisibility()== image108.VISIBLE){
                            SendBluetoothStage1((im108), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im108, true);
                        }
                    }


                }else if ((X>55 && Y<60)&&
                        (X<69 && Y>35)){
                    touchBody1.setText("HERE109");
                    image109.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im109;
                    }else if(StageMode == 2) {
                        if(image109.getVisibility()== image109.VISIBLE){
                            SendBluetoothStage1((im109), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im109, true);
                        }
                    }


                }else if ((X>55 && Y<100)&&
                        (X<68 && Y>59)){
                    touchBody1.setText("HERE110");
                    image110.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im110;
                    }else if(StageMode == 2) {
                        if(image110.getVisibility()== image110.VISIBLE){
                            SendBluetoothStage1((im110), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im110, true);
                        }
                    }

                }else if ((X>26 && Y<60)&&
                        (X<39 && Y>35)){
                    touchBody1.setText("HERE111");
                    image111.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im111;
                    }else if(StageMode == 2) {
                        if(image111.getVisibility()== image111.VISIBLE){
                            SendBluetoothStage1((im111), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im111, true);
                        }
                    }

                }else if ((X>26 && Y<84)&&
                        (X<39 && Y>59)){
                    touchBody1.setText("HERE112");
                    image112.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im112;
                    }else if(StageMode == 2) {
                        if(image112.getVisibility()== image112.VISIBLE){
                            SendBluetoothStage1((im112), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im112, true);
                        }
                    }

                }else if ((X>67 && Y<60)&&
                        (X<76 && Y>35)){
                    touchBody1.setText("HERE113");
                    image113.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im113;
                    }else if(StageMode == 2) {
                        if(image113.getVisibility()== image113.VISIBLE){
                            SendBluetoothStage1((im113), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im113, true);
                        }
                    }

                }else if ((X>67 && Y<84)&&
                        (X<76 && Y>59)){
                    touchBody1.setText("HERE114");
                    image114.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im114;
                    }else if(StageMode == 2) {
                        if(image114.getVisibility()== image114.VISIBLE){
                            SendBluetoothStage1((im114), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im114, true);
                        }
                    }

                }else if ((X>24 && Y<100)&&
                        (X<40 && Y>83)){
                    touchBody1.setText("HERE115");
                    image115.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im115;
                    }else if(StageMode == 2) {
                        if(image115.getVisibility()== image115.VISIBLE){
                            SendBluetoothStage1((im115), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im115, true);
                        }
                    }

                }else if ((X>67 && Y<100)&&
                        (X<77 && Y>83)){
                    touchBody1.setText("HERE116");
                    image116.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im116;
                    }else if(StageMode == 2) {
                        if(image116.getVisibility()== image116.VISIBLE){
                            SendBluetoothStage1((im116), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im116, true);
                        }
                    }

                }else if ((X>27 && Y<36)&&
                        (X<55 && Y>9)){
                    touchBody1.setText("HERE117");
                    image117.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im117;
                    }else if(StageMode == 2) {
                        if(image117.getVisibility()== image117.VISIBLE){
                            SendBluetoothStage1((im117), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im117, true);
                        }
                    }

                }else if ((X>54 && Y<36)&&
                        (X<76 && Y>9)){
                    touchBody1.setText("HERE118");
                    image118.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im118;
                    }else if(StageMode == 2) {
                        if(image118.getVisibility()== image118.VISIBLE){
                            SendBluetoothStage1((im118), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im118, true);
                        }
                    }

                }else if ((X>30 && Y<10)&&
                        (X<55 && Y>0)){
                    touchBody1.setText("HERE119");
                    image119.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im119;
                    }else if(StageMode == 2) {
                        if(image119.getVisibility()== image119.VISIBLE){
                            SendBluetoothStage1((im119), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im119, true);
                        }
                    }

                }else if ((X>54 && Y<10)&&
                        (X<75 && Y>0)){
                    touchBody1.setText("HERE120");
                    image120.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im120;
                    }else if(StageMode == 2) {
                        if(image120.getVisibility()== image120.VISIBLE){
                            SendBluetoothStage1((im120), false);
                            touchBody1.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im120, true);
                        }
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
                touchBody2.setText("x:"+X+"y:"+Y);

                if(StageMode==1) {
                    InvisibleBody2();
                    touchBody2.setVisibility(View.VISIBLE);
                    main_body2.setVisibility(View.VISIBLE);
                }

                if((X>(0+(100-Y)*0.1 ) && Y<100)&&
                        ((X<(15+(100-Y)*0.3))&&Y>54)){
                    touchBody2.setText("HERE221");


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im221;
                        image221.setVisibility(View.VISIBLE);

                    }else if(StageMode == 2) {
                        if(image221.getVisibility()== image221.INVISIBLE){
                            SendBluetoothStage1((im221), false);
                            touchBody2.setText("TOUCH");
                            image221.setVisibility(View.VISIBLE);

                        }else{
                            image221.setVisibility(View.INVISIBLE);
                            SendBluetoothStage2(im221, true);
                        }
                    }

                }else if((X>8 && Y <55) &&
                        (X<24 && Y>23)) {
                    touchBody2.setText("HERE222");
                    image222.setVisibility(View.VISIBLE);


                        if(StageMode == 1) {
                            RestartValue();
                            ActivElectrodStage1 = im222;
                        }else if(StageMode == 2) {
                            if(image222.getVisibility()== image222.VISIBLE){
                                SendBluetoothStage1((im222), false);
                                touchBody2.setText("TOUCH");
                            }else{
                                SendBluetoothStage2(im222, true);
                            }
                        }



                    }else if((X>8 && Y <34) &&
                        (X<36 && Y>3)) {
                    touchBody2.setText("HERE223");
                    image223.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im223;
                    }else if(StageMode == 2) {
                        if(image223.getVisibility()== image223.VISIBLE){
                            SendBluetoothStage1((im223), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im223, true);
                        }
                    }



                }else if((X>25 && Y <16) &&
                        (X<50 && Y>0)) {
                    touchBody2.setText("HERE224");
                    image224.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im224;
                    }else if(StageMode == 2) {
                        if(image224.getVisibility()== image224.VISIBLE){
                            SendBluetoothStage1((im224), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im224, true);
                        }
                    }


                }else if((X>35 && Y <45) &&
                        (X<50 && Y>15)) {
                    touchBody2.setText("HERE235");
                    image235.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im235;
                    }else if(StageMode == 2) {
                        if(image235.getVisibility()== image235.VISIBLE){
                            SendBluetoothStage1((im235), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im235, true);
                        }
                    }


                }else if((X>49 && Y <45) &&
                        (X<65 && Y>15)) {
                    touchBody2.setText("HERE236");
                    image236.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im236;
                    }else if(StageMode == 2) {
                        if(image236.getVisibility()== image236.VISIBLE){
                            SendBluetoothStage1((im236), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im236, true);
                        }
                    }


                }else if((X>(90-(100-Y)*0.3)&&Y<100)&&
                        ((X<(100-(100-Y)*0.11))&&Y>54)){
                    touchBody2.setText("HERE228");
                    image228.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im228;
                    }else if(StageMode == 2) {
                        if(image228.getVisibility()== image228.VISIBLE){
                            SendBluetoothStage1((im228), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im228, true);
                        }
                    }


                }else if((X>73 && Y <55) &&
                        (X<91 && Y>20)) {
                    touchBody2.setText("HERE227");
                    image227.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im227;
                    }else if(StageMode == 2) {
                        if(image227.getVisibility()== image227.VISIBLE){
                            SendBluetoothStage1((im227), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im227, true);
                        }
                    }


                }else if((X>64 && Y <33) &&
                        (X<90 && Y>4)) {
                    touchBody2.setText("HERE226");
                    image226.setVisibility(View.VISIBLE);


                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im226;
                    }else if(StageMode == 2) {
                        if(image226.getVisibility()== image226.VISIBLE){
                            SendBluetoothStage1((im226), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im226, true);
                        }
                    }


                }else if((X>49 && Y <16) &&
                        (X<70 && Y>0)) {
                    touchBody2.setText("HERE225");
                    image225.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im225;
                    }else if(StageMode == 2) {
                        if(image225.getVisibility()== image225.VISIBLE){
                            SendBluetoothStage1((im225), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im225, true);
                        }
                    }


                }else if((X>23 && Y <55) &&
                        (X<48 && Y>34)) {
                    touchBody2.setText("HERE229");
                    image229.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im229;
                    }else if(StageMode == 2) {
                        if(image229.getVisibility()== image229.VISIBLE){
                            SendBluetoothStage1((im229), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im229, true);
                        }
                    }


                }else if((X>50 && Y <55) &&
                        (X<75 && Y>32)) {
                    touchBody2.setText("HERE230");
                    image230.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im230;
                    }else if(StageMode == 2) {
                        if(image230.getVisibility()== image230.VISIBLE){
                            SendBluetoothStage1((im230), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im230, true);
                        }
                    }


                }else if((X>25 && Y <82) &&
                        (X<50 && Y>54)) {
                    touchBody2.setText("HERE231");
                    image231.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im231;
                    }else if(StageMode == 2) {
                        if(image231.getVisibility()== image231.VISIBLE){
                            SendBluetoothStage1((im231), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im231, true);
                        }
                    }


                }else if((X>49 && Y <82) &&
                        (X<72 && Y>54)) {
                    touchBody2.setText("HERE232");
                    image232.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im232;
                    }else if(StageMode == 2) {
                        if(image232.getVisibility()== image232.VISIBLE){
                            SendBluetoothStage1((im232), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im232, true);
                        }
                    }


                }else if((X>24 && Y <100) &&
                        (X<50 && Y>81)) {
                    touchBody2.setText("HERE233");
                    image233.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im233;
                    }else if(StageMode == 2) {
                        if(image233.getVisibility()== image233.VISIBLE){
                            SendBluetoothStage1((im233), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im233, true);
                        }
                    }


                }else if((X>49 && Y <100) &&
                        (X<75 && Y>81)) {
                    touchBody2.setText("HERE234");
                    image234.setVisibility(View.VISIBLE);

                    if(StageMode == 1) {
                        RestartValue();
                        ActivElectrodStage1 = im234;
                    }else if(StageMode == 2) {
                        if(image234.getVisibility()== image234.VISIBLE){
                            SendBluetoothStage1((im234), false);
                            touchBody2.setText("TOUCH");
                        }else{
                            SendBluetoothStage2(im234, true);
                        }
                    }


                }

                return true;
            }
        });



    textViewStage3.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if (StageMode == 3) {

                        int X = (int) event.getX();
                        int Y = (int) event.getY();
                        int Side = 0;
                        X += Side;
                        Y += Side;
                        textViewStage3.setText(X + " " + Y);

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:   // Нажатие
                                ListenerButtonStage3(X, Y);
                                break;
                            case MotionEvent.ACTION_UP:     // Отпускание
                                //textViewStage3.setText(X+" "+Y);
                                SendBluetoothStage2(0, false);
                                break;
                            case MotionEvent.ACTION_MOVE:   // Движение
                                ListenerButtonStage3(X, Y);
                                // Пердняя часть пресса
                                break;
                        }
                    }
                        //textViewStage3.setText(((button43.getLeft() -(button44.getLeft()+button44.getWidth()))/2)+"");

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
            textViewValue.setVisibility(View.VISIBLE);

        }else {
            buttonUp.setVisibility(View.INVISIBLE);
            buttonDown.setVisibility(View.INVISIBLE);
            buttonLeft.setVisibility(View.INVISIBLE);
            buttonRight.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myimage.setVisibility(View.INVISIBLE);
            LinearLayout1.setVisibility(View.INVISIBLE);
            textViewValue.setVisibility(View.INVISIBLE);




        }
    }
    private void ShiftElementsLeft(){
        VisibleBody1();
        InvisibleBody2();
        layoutBody1.setLeft(50);


       /* budy2.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) budy2.getLayoutParams();
        int budy2L = Footer.leftMargin;
        int budy2T = Footer.topMargin;
        int budy2R = Footer.rightMargin;
        int budy2B = Footer.bottomMargin;
        budy2L -= 630;
        Footer.setMargins(budy2L,budy2T,budy2R,budy2B);
        budy2.setLayoutParams(Footer);

        button9.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button9.getLayoutParams();
        int button9L = Footer.leftMargin;
        int button9T = Footer.topMargin;
        int button9R = Footer.rightMargin;
        int button9B = Footer.bottomMargin;
        button9L -= 630;
        Footer.setMargins(button9L,button9T,button9R,button9B);
        button9.setLayoutParams(Footer);

        button10.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button10.getLayoutParams();
        int button10L = Footer.leftMargin;
        int button10T = Footer.topMargin;
        int button10R = Footer.rightMargin;
        int button10B = Footer.bottomMargin;
        button10L -= 630;
        Footer.setMargins(button10L,button10T,button10R,button10B);
        button10.setLayoutParams(Footer);

        button12.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button12.getLayoutParams();
        int button12L = Footer.leftMargin;
        int button12T = Footer.topMargin;
        int button12R = Footer.rightMargin;
        int button12B = Footer.bottomMargin;
        button12L -= 630;
        Footer.setMargins(button12L,button12T,button12R,button12B);
        button12.setLayoutParams(Footer);

        button14.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button14.getLayoutParams();
        int button14L = Footer.leftMargin;
        int button14T = Footer.topMargin;
        int button14R = Footer.rightMargin;
        int button14B = Footer.bottomMargin;
        button14L -= 630;
        Footer.setMargins(button14L,button14T,button14R,button14B);
        button14.setLayoutParams(Footer);

        button16.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button16.getLayoutParams();
        int button16L = Footer.leftMargin;
        int button16T = Footer.topMargin;
        int button16R = Footer.rightMargin;
        int button16B = Footer.bottomMargin;
        button16L -= 630;
        Footer.setMargins(button16L,button16T,button16R,button16B);
        button16.setLayoutParams(Footer);

        button18.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button18.getLayoutParams();
        int button18L = Footer.leftMargin;
        int button18T = Footer.topMargin;
        int button18R = Footer.rightMargin;
        int button18B = Footer.bottomMargin;
        button18L -= 630;
        Footer.setMargins(button18L,button18T,button18R,button18B);
        button18.setLayoutParams(Footer);

        button20.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button20.getLayoutParams();
        int button20L = Footer.leftMargin;
        int button20T = Footer.topMargin;
        int button20R = Footer.rightMargin;
        int button20B = Footer.bottomMargin;
        button20L -= 630;
        Footer.setMargins(button20L,button20T,button20R,button20B);
        button20.setLayoutParams(Footer);

        button22.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button22.getLayoutParams();
        int button22L = Footer.leftMargin;
        int button22T = Footer.topMargin;
        int button22R = Footer.rightMargin;
        int button22B = Footer.bottomMargin;
        button22L -= 630;
        Footer.setMargins(button22L,button22T,button22R,button22B);
        button22.setLayoutParams(Footer);

        button24.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button24.getLayoutParams();
        int button24L = Footer.leftMargin;
        int button24T = Footer.topMargin;
        int button24R = Footer.rightMargin;
        int button24B = Footer.bottomMargin;
        button24L -= 630;
        Footer.setMargins(button24L,button24T,button24R,button24B);
        button24.setLayoutParams(Footer);

        button27.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button27.getLayoutParams();
        int button27L = Footer.leftMargin;
        int button27T = Footer.topMargin;
        int button27R = Footer.rightMargin;
        int button27B = Footer.bottomMargin;
        button27L -= 630;
        Footer.setMargins(button27L,button27T,button27R,button27B);
        button27.setLayoutParams(Footer);

        button29.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button29.getLayoutParams();
        int button29L = Footer.leftMargin;
        int button29T = Footer.topMargin;
        int button29R = Footer.rightMargin;
        int button29B = Footer.bottomMargin;
        button29L -= 630;
        Footer.setMargins(button29L,button29T,button29R,button29B);
        button29.setLayoutParams(Footer);

        button31.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button31.getLayoutParams();
        int button31L = Footer.leftMargin;
        int button31T = Footer.topMargin;
        int button31R = Footer.rightMargin;
        int button31B = Footer.bottomMargin;
        button31L -= 630;
        Footer.setMargins(button31L,button31T,button31R,button31B);
        button31.setLayoutParams(Footer);

        button32.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button32.getLayoutParams();
        int button32L = Footer.leftMargin;
        int button32T = Footer.topMargin;
        int button32R = Footer.rightMargin;
        int button32B = Footer.bottomMargin;
        button32L -= 630;
        Footer.setMargins(button32L,button32T,button32R,button32B);
        button32.setLayoutParams(Footer);

        button53.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button53.getLayoutParams();
        int button53L = Footer.leftMargin;
        int button53T = Footer.topMargin;
        int button53R = Footer.rightMargin;
        int button53B = Footer.bottomMargin;
        button53L -= 630;
        Footer.setMargins(button53L,button53T,button53R,button53B);
        button53.setLayoutParams(Footer);

        button54.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button54.getLayoutParams();
        int button54L = Footer.leftMargin;
        int button54T = Footer.topMargin;
        int button54R = Footer.rightMargin;
        int button54B = Footer.bottomMargin;
        button54L -= 630;
        Footer.setMargins(button54L,button54T,button54R,button54B);
        button54.setLayoutParams(Footer);

        button50.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button50.getLayoutParams();
        int button50L = Footer.leftMargin;
        int button50T = Footer.topMargin;
        int button50R = Footer.rightMargin;
        int button50B = Footer.bottomMargin;
        button50L -= 630;
        Footer.setMargins(button50L,button50T,button50R,button50B);
        button50.setLayoutParams(Footer);

        button4.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button4.getLayoutParams();
        int button4L = Footer.leftMargin;
        int button4T = Footer.topMargin;
        int button4R = Footer.rightMargin;
        int button4B = Footer.bottomMargin;
        button4L -= 630;
        Footer.setMargins(button4L,button4T,button4R,button4B);
        button4.setLayoutParams(Footer);

        button6.setVisibility(View.INVISIBLE);
        Footer = (RelativeLayout.LayoutParams) button6.getLayoutParams();
        int button6L = Footer.leftMargin;
        int button6T = Footer.topMargin;
        int button6R = Footer.rightMargin;
        int button6B = Footer.bottomMargin;
        button6L -= 630;
        Footer.setMargins(button6L,button6T,button6R,button6B);
        button6.setLayoutParams(Footer);*/
    }
    private void ShiftElementsRight(){

        VisibleBody1();
        VisibleBody2();
        layoutBody2.setRight(500);
      /*  main_body2.setVisibility(View.VISIBLE);*/
/*        Footer = (RelativeLayout.LayoutParams) budy2.getLayoutParams();
        int budy2L = Footer.leftMargin;
        int budy2T = Footer.topMargin;
        int budy2R = Footer.rightMargin;
        int budy2B = Footer.bottomMargin;
        budy2L += 630;
        Footer.setMargins(budy2L,budy2T,budy2R,budy2B);
        budy2.setLayoutParams(Footer);

        button9.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button9.getLayoutParams();
        int button9L = Footer.leftMargin;
        int button9T = Footer.topMargin;
        int button9R = Footer.rightMargin;
        int button9B = Footer.bottomMargin;
        button9L += 630;
        Footer.setMargins(button9L,button9T,button9R,button9B);
        button9.setLayoutParams(Footer);

        button10.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button10.getLayoutParams();
        int button10L = Footer.leftMargin;
        int button10T = Footer.topMargin;
        int button10R = Footer.rightMargin;
        int button10B = Footer.bottomMargin;
        button10L += 630;
        Footer.setMargins(button10L,button10T,button10R,button10B);
        button10.setLayoutParams(Footer);

        button12.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button12.getLayoutParams();
        int button12L = Footer.leftMargin;
        int button12T = Footer.topMargin;
        int button12R = Footer.rightMargin;
        int button12B = Footer.bottomMargin;
        button12L += 630;
        Footer.setMargins(button12L,button12T,button12R,button12B);
        button12.setLayoutParams(Footer);

        button14.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button14.getLayoutParams();
        int button14L = Footer.leftMargin;
        int button14T = Footer.topMargin;
        int button14R = Footer.rightMargin;
        int button14B = Footer.bottomMargin;
        button14L += 630;
        Footer.setMargins(button14L,button14T,button14R,button14B);
        button14.setLayoutParams(Footer);

        button16.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button16.getLayoutParams();
        int button16L = Footer.leftMargin;
        int button16T = Footer.topMargin;
        int button16R = Footer.rightMargin;
        int button16B = Footer.bottomMargin;
        button16L += 630;
        Footer.setMargins(button16L,button16T,button16R,button16B);
        button16.setLayoutParams(Footer);

        button18.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button18.getLayoutParams();
        int button18L = Footer.leftMargin;
        int button18T = Footer.topMargin;
        int button18R = Footer.rightMargin;
        int button18B = Footer.bottomMargin;
        button18L += 630;
        Footer.setMargins(button18L,button18T,button18R,button18B);
        button18.setLayoutParams(Footer);

        button20.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button20.getLayoutParams();
        int button20L = Footer.leftMargin;
        int button20T = Footer.topMargin;
        int button20R = Footer.rightMargin;
        int button20B = Footer.bottomMargin;
        button20L += 630;
        Footer.setMargins(button20L,button20T,button20R,button20B);
        button20.setLayoutParams(Footer);

        button22.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button22.getLayoutParams();
        int button22L = Footer.leftMargin;
        int button22T = Footer.topMargin;
        int button22R = Footer.rightMargin;
        int button22B = Footer.bottomMargin;
        button22L += 630;
        Footer.setMargins(button22L,button22T,button22R,button22B);
        button22.setLayoutParams(Footer);

        button24.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button24.getLayoutParams();
        int button24L = Footer.leftMargin;
        int button24T = Footer.topMargin;
        int button24R = Footer.rightMargin;
        int button24B = Footer.bottomMargin;
        button24L += 630;
        Footer.setMargins(button24L,button24T,button24R,button24B);
        button24.setLayoutParams(Footer);

        button27.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button27.getLayoutParams();
        int button27L = Footer.leftMargin;
        int button27T = Footer.topMargin;
        int button27R = Footer.rightMargin;
        int button27B = Footer.bottomMargin;
        button27L += 630;
        Footer.setMargins(button27L,button27T,button27R,button27B);
        button27.setLayoutParams(Footer);

        button29.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button29.getLayoutParams();
        int button29L = Footer.leftMargin;
        int button29T = Footer.topMargin;
        int button29R = Footer.rightMargin;
        int button29B = Footer.bottomMargin;
        button29L += 630;
        Footer.setMargins(button29L,button29T,button29R,button29B);
        button29.setLayoutParams(Footer);

        button31.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button31.getLayoutParams();
        int button31L = Footer.leftMargin;
        int button31T = Footer.topMargin;
        int button31R = Footer.rightMargin;
        int button31B = Footer.bottomMargin;
        button31L += 630;
        Footer.setMargins(button31L,button31T,button31R,button31B);
        button31.setLayoutParams(Footer);

        button32.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button32.getLayoutParams();
        int button32L = Footer.leftMargin;
        int button32T = Footer.topMargin;
        int button32R = Footer.rightMargin;
        int button32B = Footer.bottomMargin;
        button32L += 630;
        Footer.setMargins(button32L,button32T,button32R,button32B);
        button32.setLayoutParams(Footer);

        button53.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button53.getLayoutParams();
        int button53L = Footer.leftMargin;
        int button53T = Footer.topMargin;
        int button53R = Footer.rightMargin;
        int button53B = Footer.bottomMargin;
        button53L += 630;
        Footer.setMargins(button53L,button53T,button53R,button53B);
        button53.setLayoutParams(Footer);

        button54.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button54.getLayoutParams();
        int button54L = Footer.leftMargin;
        int button54T = Footer.topMargin;
        int button54R = Footer.rightMargin;
        int button54B = Footer.bottomMargin;
        button54L += 630;
        Footer.setMargins(button54L,button54T,button54R,button54B);
        button54.setLayoutParams(Footer);

        button50.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button50.getLayoutParams();
        int button50L = Footer.leftMargin;
        int button50T = Footer.topMargin;
        int button50R = Footer.rightMargin;
        int button50B = Footer.bottomMargin;
        button50L += 630;
        Footer.setMargins(button50L,button50T,button50R,button50B);
        button50.setLayoutParams(Footer);

        button4.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button4.getLayoutParams();
        int button4L = Footer.leftMargin;
        int button4T = Footer.topMargin;
        int button4R = Footer.rightMargin;
        int button4B = Footer.bottomMargin;
        button4L += 630;
        Footer.setMargins(button4L,button4T,button4R,button4B);
        button4.setLayoutParams(Footer);

        button6.setVisibility(View.VISIBLE);
        Footer = (RelativeLayout.LayoutParams) button6.getLayoutParams();
        int button6L = Footer.leftMargin;
        int button6T = Footer.topMargin;
        int button6R = Footer.rightMargin;
        int button6B = Footer.bottomMargin;
        button6L += 630;
        Footer.setMargins(button6L,button6T,button6R,button6B);
        button6.setLayoutParams(Footer);*/

        /*main_body1.setVisibility(View.VISIBLE);*/
/*        button7.setVisibility(View.VISIBLE);
        button34.setVisibility(View.VISIBLE);
        button35.setVisibility(View.VISIBLE);
        button36.setVisibility(View.VISIBLE);
        button37.setVisibility(View.VISIBLE);
        button42.setVisibility(View.VISIBLE);
        button43.setVisibility(View.VISIBLE);
        button44.setVisibility(View.VISIBLE);
        button45.setVisibility(View.VISIBLE);
        button51.setVisibility(View.VISIBLE);
        button52.setVisibility(View.VISIBLE);
        button55.setVisibility(View.VISIBLE);
        button56.setVisibility(View.VISIBLE);
        button57.setVisibility(View.VISIBLE);*/
    }
    private void ListenerButtonStage3(int X, int Y){
        if((X>button42.getLeft() - ((button42.getLeft() - (button43.getLeft()+button43.getWidth()))/2)) &&
                (X<button42.getLeft()+button42.getWidth()+(button42.getWidth()/1.5)) &&
                Y>button43.getTop() - (button43.getHeight()/4) &&
                (Y<button43.getTop()+button43.getHeight() + ((button35.getTop() - (button43.getTop() + button43.getHeight() ))/2))){


            if(ActivrButtonStage3 != 16){
                ActivrButtonStage3 = 16;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(16,true);
            textViewStage3.setText("Button42");
        }

        else if((X>button43.getLeft() - ((button43.getLeft() - (button44.getLeft()+button44.getWidth()))/2)) &&
                (X<button43.getLeft()+button43.getWidth() + ((button42.getLeft() - ((button43.getLeft()+button43.getWidth())))/2)) &&
                Y>button43.getTop() - (button43.getHeight()/4) &&
                (Y<button43.getTop()+button43.getHeight() + ((button35.getTop() - (button43.getTop() + button43.getHeight() ))/2))){

            if(ActivrButtonStage3 != 14){
                ActivrButtonStage3 = 14;
                SendBluetoothStage2(0,false);
            }


            SendBluetoothStage2(14,true);
            textViewStage3.setText("Button43");
        }

        else if((X>button44.getLeft() - ((button44.getLeft() - (button45.getLeft()+button45.getWidth()))/2) )&&
                (X<button44.getLeft()+button44.getWidth()+((button43.getLeft() - (button44.getLeft()+button44.getWidth()))/2)) &&
                Y>button43.getTop() - (button43.getHeight()/4) &&
                (Y<button43.getTop()+button43.getHeight() + ((button35.getTop() - (button43.getTop() + button43.getHeight() ))/2))){

            if(ActivrButtonStage3 != 6){
                ActivrButtonStage3 = 6;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(6,true);
            textViewStage3.setText("Button44");
        }


        else if((X>button45.getLeft() - (button45.getWidth()/1.5 )) &&
                (X<button45.getLeft()+button45.getWidth()+((button44.getLeft() - (button45.getLeft()+button45.getWidth()))/2)) &&
                Y>button43.getTop() - (button43.getHeight()/4) &&
                (Y<button43.getTop()+button43.getHeight() + ((button35.getTop() - (button43.getTop() + button43.getHeight() ))/2))){
            if(ActivrButtonStage3 != 8){
                ActivrButtonStage3 = 8;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(8,true);
            textViewStage3.setText("Button45");
        }

        ////

        else if((X>button34.getLeft() - ((button34.getLeft() - (button35.getLeft()+button35.getWidth()))/2)) &&
                (X<button34.getLeft()+button34.getWidth()+(button34.getWidth()/1.5)) &&
                (Y>button35.getTop() - ((button35.getTop()-(button43.getTop() + button43.getHeight()))/2))&&
                (Y<button35.getTop()+button35.getHeight())){

            if(ActivrButtonStage3 != 15){
                ActivrButtonStage3 = 15;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(15,true);
            textViewStage3.setText("Button34");
        }

        else if((X>button35.getLeft() - ((button35.getLeft() - (button36.getLeft()+button36.getWidth()))/2)) &&
                (X<button35.getLeft()+button35.getWidth() + ((button34.getLeft() - ((button35.getLeft()+button35.getWidth())))/2)) &&
                (Y>button35.getTop() - ((button35.getTop()-(button43.getTop() + button43.getHeight()))/2))&&
                (Y<button35.getTop()+button35.getHeight())){

            if(ActivrButtonStage3 != 13){
                ActivrButtonStage3 = 13;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(13,true);
            textViewStage3.setText("Button35");
        }

        else if((X>button36.getLeft() - ((button36.getLeft() - (button37.getLeft()+button37.getWidth()))/2) )&&
                (X<button36.getLeft()+button36.getWidth()+((button35.getLeft() - (button36.getLeft()+button36.getWidth()))/2)) &&
                (Y>button35.getTop() - ((button35.getTop()-(button43.getTop() + button43.getHeight()))/2))&&
                (Y<button35.getTop()+button35.getHeight())){

            if(ActivrButtonStage3 != 5){
                ActivrButtonStage3 = 5;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(5,true);

            textViewStage3.setText("Button36");
        }
        else if((X>button37.getLeft() - (button37.getWidth()/1.5 )) &&
                (X<button37.getLeft()+button37.getWidth()+((button36.getLeft() - (button37.getLeft()+button37.getWidth()))/2)) &&
                (Y>button35.getTop() - ((button35.getTop()-(button43.getTop() + button43.getHeight()))/2))&&
                (Y<button35.getTop()+button35.getHeight())){

            if(ActivrButtonStage3 != 7){
                ActivrButtonStage3 = 7;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(7,true);
            textViewStage3.setText("Button37");
        }
        //-------Левая рука перед

        else if((X>button55.getLeft() - (button55.getWidth()/1.5 )) &&
                (X< button55.getLeft() + button55.getWidth() + (button55.getWidth()/1.5)) &&
                (Y>button55.getTop() - ((button55.getTop() - (button51.getTop()+button51.getHeight())))/2) &&
                (Y<button55.getTop()+button55.getHeight())){

            if(ActivrButtonStage3 != 24){
                ActivrButtonStage3 = 24;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(24,true);
            textViewStage3.setText("Button55");
        }
        else if((X>button51.getLeft() - (button51.getWidth()/1.5 )) &&
                (X< button51.getLeft() + button51.getWidth() + (button51.getWidth()/1.5)) &&
                (Y>button51.getTop() - (button51.getTop()-(button52.getTop()+button52.getHeight())/2)) &&
                (Y<button51.getTop()+button51.getHeight() + ((button55.getTop() - (button51.getTop()+button51.getHeight() ))/2))){

            if(ActivrButtonStage3 != 32){
                ActivrButtonStage3 = 32;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(32,true);
            textViewStage3.setText("Button51");
        }
        else if((X>button52.getLeft() - (button52.getWidth()/1.5 )) &&
                (X< button52.getLeft() + button52.getWidth() + (button52.getWidth()/1.5)) &&
                (Y>button52.getTop()) &&
                (Y<button52.getTop()+button52.getHeight() + ((button51.getTop() - (button52.getTop()+button52.getHeight() ))/2))){

            if(ActivrButtonStage3 != 31){
                ActivrButtonStage3 = 31;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(31,true);
            textViewStage3.setText("Button52");
        }

        //-------Правая рука перед

        else if((X>button7.getLeft() - (button7.getWidth()/1.5 )) &&
                (X< button7.getLeft() + button7.getWidth() + (button7.getWidth()/1.5)) &&
                (Y>button7.getTop() - ((button7.getTop() - (button57.getTop()+button57.getHeight())))/2) &&
                (Y<button7.getTop()+button7.getHeight())){

            if(ActivrButtonStage3 != 20){
                ActivrButtonStage3 = 20;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(20,true);
            textViewStage3.setText("Button7");
        }
        else if((X>button57.getLeft() - (button57.getWidth()/1.5 )) &&
                (X< button57.getLeft() + button57.getWidth() + (button57.getWidth()/1.5)) &&
                (Y>button57.getTop() - (button57.getTop()-(button56.getTop()+button56.getHeight())/2)) &&
                (Y<button57.getTop()+button57.getHeight() + ((button7.getTop() - (button57.getTop()+button57.getHeight() ))/2))){

            if(ActivrButtonStage3 != 21){
                ActivrButtonStage3 = 21;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(21,true);
            textViewStage3.setText("Button57");
        }
        else if((X>button56.getLeft() - (button56.getWidth()/1.5 )) &&
                (X< button56.getLeft() + button56.getWidth() + (button56.getWidth()/1.5)) &&
                (Y>button56.getTop()) &&
                (Y<button56.getTop()+button56.getHeight() + ((button57.getTop() - (button56.getTop()+button56.getHeight() ))/2))){

            if(ActivrButtonStage3 != 23){
                ActivrButtonStage3 = 23;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(23,true);
            textViewStage3.setText("Button56");
        }


        //Cпина

        else if((X>button6.getLeft() - ((button6.getLeft() - (button27.getLeft()+button27.getWidth()))/2)) &&
                (X<button6.getLeft()+button6.getWidth()+(button6.getWidth()/1.5)) &&
                Y>button27.getTop() - (button27.getHeight()/4) &&
                (Y<button27.getTop()+button27.getHeight() + ((button20.getTop() - (button27.getTop() + button27.getHeight() ))/2))){

            if(ActivrButtonStage3 != 10){
                ActivrButtonStage3 = 10;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(10,true);
            textViewStage3.setText("Button6");
        }

        else if((X>button27.getLeft() - ((button27.getLeft() - (button24.getLeft()+button24.getWidth()))/2)) &&
                (X<button27.getLeft()+button27.getWidth() + ((button6.getLeft() - ((button27.getLeft()+button27.getWidth())))/2)) &&
                Y>button27.getTop() - (button27.getHeight()/4) &&
                (Y<button27.getTop()+button27.getHeight() + ((button20.getTop() - (button27.getTop() + button27.getHeight() ))/2))){

            if(ActivrButtonStage3 != 25){
                ActivrButtonStage3 = 25;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(25,true);
            textViewStage3.setText("Button27");
        }
        else if((X>button24.getLeft() - ((button24.getLeft() - (button50.getLeft()+button50.getWidth()))/2) )&&
                (X<button24.getLeft()+button24.getWidth()+((button27.getLeft() - (button24.getLeft()+button24.getWidth()))/2)) &&
                Y>button27.getTop() - (button27.getHeight()/4) &&
                (Y<button27.getTop()+button27.getHeight() + ((button27.getTop() - (button27.getTop() + button27.getHeight() ))/2))){

            if(ActivrButtonStage3 != 26){
                ActivrButtonStage3 = 26;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(26,true);

            textViewStage3.setText("Button18");
        }
        else if((X>button50.getLeft() - (button50.getWidth()/1.5 )) &&
                (X<button50.getLeft()+button50.getWidth()+((button24.getLeft() - (button50.getLeft()+button50.getWidth()))/2)) &&
                Y>button27.getTop() - (button27.getHeight()/4) &&
                (Y<button27.getTop()+button27.getHeight() + ((button27.getTop() - (button27.getTop() + button27.getHeight() ))/2))){

            if(ActivrButtonStage3 != 28){
                ActivrButtonStage3 = 28;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(28,true);
            textViewStage3.setText("Button50");
        }
        /////////////////////

        else if((X>button22.getLeft() - ((button22.getLeft() - (button20.getLeft()+button20.getWidth()))/2)) &&
                (X<button22.getLeft()+button22.getWidth()+(button22.getWidth()/1.5)) &&
                Y>button20.getTop() - (button20.getHeight()/4) &&
                (Y<button20.getTop()+button20.getHeight() + ((button12.getTop() - (button20.getTop() + button20.getHeight() ))/2))){

            if(ActivrButtonStage3 != 27){
                ActivrButtonStage3 = 27;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(27,true);
            textViewStage3.setText("Button22");
        }

        else if((X>button20.getLeft() - ((button20.getLeft() - (button18.getLeft()+button18.getWidth()))/2)) &&
                (X<button20.getLeft()+button20.getWidth() + ((button22.getLeft() - ((button20.getLeft()+button20.getWidth())))/2)) &&
                Y>button20.getTop() - (button20.getHeight()/4) &&
                (Y<button20.getTop()+button20.getHeight() + ((button12.getTop() - (button20.getTop() + button20.getHeight() ))/2))){

            if(ActivrButtonStage3 != 11){
                ActivrButtonStage3 = 11;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(11,true);
            textViewStage3.setText("Button20");
        }
        else if((X>button18.getLeft() - ((button18.getLeft() - (button16.getLeft()+button16.getWidth()))/2) )&&
                (X<button18.getLeft()+button18.getWidth()+((button20.getLeft() - (button18.getLeft()+button18.getWidth()))/2)) &&
                Y>button20.getTop() - (button20.getHeight()/4) &&
                (Y<button20.getTop()+button20.getHeight() + ((button12.getTop() - (button20.getTop() + button20.getHeight() ))/2))){

            if(ActivrButtonStage3 != 12){
                ActivrButtonStage3 = 12;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(12,true);
            textViewStage3.setText("Button18");
        }
        else if((X>button16.getLeft() - (button16.getWidth()/1.5 )) &&
                (X<button16.getLeft()+button16.getWidth()+((button18.getLeft() - (button16.getLeft()+button16.getWidth()))/2)) &&
                Y>button20.getTop() - (button20.getHeight()/4) &&
                (Y<button20.getTop()+button20.getHeight() + ((button12.getTop() - (button20.getTop() + button20.getHeight() ))/2))){

            if(ActivrButtonStage3 != 3){
                ActivrButtonStage3 =3;
                SendBluetoothStage2(0,false);
            }
            SendBluetoothStage2(3,true);
            textViewStage3.setText("Button16");
        }



        ////

        else if((X>button14.getLeft() - ((button14.getLeft() - (button12.getLeft()+button12.getWidth()))/2)) &&
                (X<button14.getLeft()+button14.getWidth()+(button14.getWidth()/1.5)) &&
                (Y>button12.getTop() - ((button12.getTop()-(button20.getTop() + button20.getHeight()))/2))&&
                (Y<button14.getTop()+button14.getHeight())) {

            if(ActivrButtonStage3 != 4){
                ActivrButtonStage3 = 4;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(4, true);
            textViewStage3.setText("Button14");
        }

        else if((X>button12.getLeft() - ((button12.getLeft() - (button10.getLeft()+button10.getWidth()))/2)) &&
                (X<button12.getLeft()+button12.getWidth() + ((button14.getLeft() - ((button12.getLeft()+button12.getWidth())))/2)) &&
                (Y>button12.getTop() - ((button12.getTop()-(button20.getTop() + button20.getHeight()))/2))&&
                (Y<button12.getTop()+button12.getHeight())){

            if(ActivrButtonStage3 != 1){
                ActivrButtonStage3 = 1;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(1,true);
            textViewStage3.setText("Button12");
        }

        else if((X>button10.getLeft() - ((button10.getLeft() - (button9.getLeft()+button9.getWidth()))/2) )&&
                (X<button10.getLeft()+button10.getWidth()+((button12.getLeft() - (button10.getLeft()+button10.getWidth()))/2)) &&
                (Y>button12.getTop() - ((button12.getTop()-(button20.getTop() + button20.getHeight()))/2))&&
                (Y<button12.getTop()+button12.getHeight())){

            if(ActivrButtonStage3 != 2){
                ActivrButtonStage3 = 2;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(2,true);
            textViewStage3.setText("Button10");
        }
        else if((X>button9.getLeft() - (button9.getWidth()/1.5 )) &&
                (X<button9.getLeft()+button9.getWidth()+((button10.getLeft() - (button9.getLeft()+button9.getWidth()))/2)) &&
                (Y>button12.getTop() - ((button12.getTop()-(button20.getTop() + button20.getHeight()))/2))&&
                (Y<button12.getTop()+button12.getHeight())){

            if(ActivrButtonStage3 != 9){
                ActivrButtonStage3 =9;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(9,true);
            textViewStage3.setText("Button9");
        }

        //-------Левая рука Зад

        else if((X>button54.getLeft() - (button54.getWidth()/1.5 )) &&
                (X< button54.getLeft() + button54.getWidth() + (button54.getWidth()/1.5)) &&
                (Y>button54.getTop() - ((button54.getTop() - (button4.getTop()+button4.getHeight())))/2) &&
                (Y<button54.getTop()+button54.getHeight())){

            if(ActivrButtonStage3 != 22){
                ActivrButtonStage3 = 22;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(22,true);
            textViewStage3.setText("Button54");
        }
        else if((X>button4.getLeft() - (button4.getWidth()/1.5 )) &&
                (X< button4.getLeft() + button4.getWidth() + (button4.getWidth()/1.5)) &&
                (Y>button4.getTop() - (button4.getTop()-(button53.getTop()+button53.getHeight())/2)) &&
                (Y<button4.getTop()+button4.getHeight() + ((button54.getTop() - (button4.getTop()+button4.getHeight() ))/2))){

            if(ActivrButtonStage3 != 8){
                ActivrButtonStage3 = 8;
                SendBluetoothStage2(0,false);
            }
            SendBluetoothStage2(8,true);
            textViewStage3.setText("Button4");
        }
        else if((X>button53.getLeft() - (button53.getWidth()/1.5 )) &&
                (X< button53.getLeft() + button53.getWidth() + (button53.getWidth()/1.5)) &&
                (Y>button53.getTop()) &&
                (Y<button53.getTop()+button53.getHeight() + ((button4.getTop() - (button53.getTop()+button53.getHeight() ))/2))){

            if(ActivrButtonStage3 != 30){
                ActivrButtonStage3 = 30;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(30,true);
            textViewStage3.setText("Button53");
        }

        //-------Правая рука Зад

        else if((X>button32.getLeft() - (button32.getWidth()/1.5 )) &&
                (X< button32.getLeft() + button32.getWidth() + (button32.getWidth()/1.5)) &&
                (Y>button32.getTop() - ((button32.getTop() - (button31.getTop()+button31.getHeight())))/2) &&
                (Y<button32.getTop()+button32.getHeight())){

            if(ActivrButtonStage3 != 17){
                ActivrButtonStage3 = 17;
                SendBluetoothStage2(0,false);

            }

            SendBluetoothStage2(17,true);
            textViewStage3.setText("Button32");
        }
        else if((X>button31.getLeft() - (button31.getWidth()/1.5 )) &&
                (X< button31.getLeft() + button31.getWidth() + (button31.getWidth()/1.5)) &&
                (Y>button31.getTop() - (button31.getTop()-(button29.getTop()+button29.getHeight())/2)) &&
                (Y<button31.getTop()+button31.getHeight() + ((button32.getTop() - (button31.getTop()+button31.getHeight() ))/2))){

            if(ActivrButtonStage3 != 18){
                ActivrButtonStage3 = 18;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(18,true);
            textViewStage3.setText("Button31");
        }
        else if((X>button29.getLeft() - (button29.getWidth()/1.5 )) &&
                (X< button29.getLeft() + button29.getWidth() + (button29.getWidth()/1.5)) &&
                (Y>button29.getTop()) &&
                (Y<button29.getTop()+button29.getHeight() + ((button31.getTop() - (button29.getTop()+button29.getHeight() ))/2))){

            if(ActivrButtonStage3 != 19){
                ActivrButtonStage3 = 19;
                SendBluetoothStage2(0,false);
            }

            SendBluetoothStage2(19,true);
            textViewStage3.setText("Button29");
        }else{
            SendBluetoothStage2(0,false);
            if(ActivrButtonStage3 != 0){
                ActivrButtonStage3 = 0;
                SendBluetoothStage2(0,false);
            }
        }


    }


    private void SendBluetoothTemp(final char t){
        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OutputStream outStream = clientSocket.getOutputStream();
                    if(t == 'H') {
                        outStream.write('H');
                        outStream.write('O');
                        outStream.write('T');
                        outStream.write('C');
                    }else if(t == 'C'){
                        outStream.write('C');
                        outStream.write('O');
                        outStream.write('L');
                        outStream.write('D');
                    }

                }catch (Exception e){}
            }
        });
        threadSend.start();
        try {
            threadSend.join();
        }catch (Exception e){}
    }
    private void SendBluetoothS(final int Channel,final  boolean stage){        //бесполезные переменные
        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    char s = 'S';
                    int temp;
                    char s1,s2,s3;
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


                }catch (Exception e){}
            }
        });
        threadSend.start();

    }

    private void SendBluetoothStage2(final int Channel,final  boolean stage){

        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int temp;
                    char chen1,chen2,chen3;
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

                }catch (Exception e){}
            }
        });
        threadSend.start();



    }

    private void SendBluetoothStage1(final int Channel,final boolean stage){

        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run() {
                int temp;
                char volt1,volt2,volt3;
                char freq1,freq2,freq3;
                char chen1,chen2,chen3;
                char n = 'N';
                char r = 'R';
                char t = 'T';

                try {
                    OutputStream outStream = clientSocket.getOutputStream();

                    if(Channel !=0 && stage) {

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

                    }else{
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
                }catch (Exception e){
                    Log.e("Send",e+"");
                }
            }
        });
        threadSend.start();



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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewValue.setText(strdbl+ " "+str1dbl1+" ");
            }
        });
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
        textViewValue = (TextView) findViewById(R.id.textViewValue);
        textViewStage3 = (TextView) findViewById(R.id.textViewStage3);

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


