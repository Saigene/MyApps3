package com.example.myapps.AddCustomer;

import android.app.UiAutomation;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapps.Global;
import com.example.myapps.R;
import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.CacheRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import com.example.myapps.DatabaseClass.*;
public class AddConfirmFragment extends Fragment{
    Boolean release = false;
    private Button confirmbtn;
    private Button cancelbtn;
    private TextView confirmName;
    private TextView confirmCpno;
    private TextView confirmShift;
    private TextView confirmOrder;
    private TextView confirmOrderPrice;
    private TextView confirmMonthstoPay;
    private TextView confirmAdditional;
    private TextView confirmAdditionalPrice;
    private TextView confirmTotalPrice;
    private TextView confirmDownpayment;
    private TextView confirmTotalpricewoutint;
    private TextView confirmColor;
  //  private Calendar calendar;

    private String confirmkeyname;
    private String confirmkeycp;
    private String confirmkeyshift;
    private String confirmkeyorder;
    private String confirmkeyorderprice;
    private String confirmkeymonthstopay;
    private String confirmkeyadditional;
    private String confirmkeyadditionalprice;
    private String confirmkeybookdate;
    private String confirmkeydownpayment;
    private String confirmkeycolor;

    private DatabaseReference databasecustomerlistR;
    private DatabaseReference databaselistcustomer;
    private DatabaseReference databasepayment;
    private DatabaseReference databasetextupdated;
    private DatabaseReference databasedetailpay;
    private TextView confirmdate;

    private double pmid1;
    private double pend1;
    private double pmid2;
    private double pend2;
    private double pmid3;
    private double pend3;
    private double pmid4;
    private double pend4;
    private double sukli;
    private double kulang;

    private String mid1textupdated;
    private String end1textupdated;
    private String mid2textupdated;
    private String end2textupdated;
    private String mid3textupdated;
    private String end3textupdated;
    private String mid4textupdated;
    private String end4textupdated;

    private String detailpay;
    private String detaildate;

    private String _userdata;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.addconfirm_fragment,container,false);
        confirmbtn = v.findViewById(R.id.confirmbtn);
        cancelbtn = v.findViewById(R.id.cancelbtn);
        confirmName = v.findViewById(R.id.confirmName);
        confirmCpno = v.findViewById(R.id.confirmCpno);
        confirmShift = v.findViewById(R.id.confirmShift);
        confirmOrder = v.findViewById(R.id.confirmOrder_);
        confirmOrderPrice = v.findViewById(R.id.confirmOrderPrice_);
        confirmMonthstoPay = v.findViewById(R.id.confirmMonthsPay_);
        confirmAdditional = v.findViewById(R.id.confirmAdditonal);
        confirmAdditionalPrice = v.findViewById(R.id.confirmAdditonalPrice);
        confirmTotalPrice = v.findViewById(R.id.confirmTotalPrice);
        confirmDownpayment=v.findViewById(R.id.confirmDownpayment);
        confirmTotalpricewoutint=v.findViewById(R.id.confirmTotalPricewoutint);
        confirmColor=v.findViewById(R.id.confirmColor);
        databaselistcustomer = FirebaseDatabase.getInstance().getReference("Customer Info");
        databasepayment=FirebaseDatabase.getInstance().getReference("Customer Payment");
        databasetextupdated=FirebaseDatabase.getInstance().getReference("Customer status");
        databasedetailpay=FirebaseDatabase.getInstance().getReference("Customer Detail");
        databasecustomerlistR=FirebaseDatabase.getInstance().getReference("Customer Pending");

        confirmdate = v.findViewById(R.id.confirmDate);

        View overlay = v.findViewById(R.id.addconfirmlayout);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_FULLSCREEN);

        Global userdata = Global.getInstance();
        _userdata=userdata.getValue();


        Bundle bundle = getArguments();


        confirmkeyname = bundle.getString("KeyName");
        confirmkeycp = bundle.getString("KeyCpno");
        confirmkeyshift = bundle.getString("KeyShift");
        confirmkeyorder = bundle.getString("KeyOrder");
        confirmkeyorderprice = bundle.getString("KeyOrderprice");
        confirmkeymonthstopay = bundle.getString("KeyMonthstopay");
        confirmkeyadditional=bundle.getString("KeyAdditional");
        confirmkeyadditionalprice=bundle.getString("KeyAdditionalprice");
        confirmkeybookdate=bundle.getString("KeyBookDate");
        confirmkeydownpayment=bundle.getString("KeyDownpayment");
        confirmkeycolor=bundle.getString("KeyColor");


        confirmName.setText(confirmkeyname);
        confirmCpno.setText(confirmkeycp);
        confirmShift.setText(confirmkeyshift);
        confirmOrder.setText(confirmkeyorder);
        confirmOrderPrice.setText(confirmkeyorderprice);
        confirmMonthstoPay.setText(confirmkeymonthstopay);
        confirmdate.setText(confirmkeybookdate);

        confirmDownpayment.setText(confirmkeydownpayment);
        confirmAdditional.setText(confirmkeyadditional);
        confirmAdditionalPrice.setText(confirmkeyadditionalprice);
        confirmColor.setText(confirmkeycolor);

        double intconfirmTotalPrice=Integer.parseInt(confirmOrderPrice.getText().toString()) + Integer.parseInt(confirmAdditionalPrice.getText().toString())-Double.parseDouble(confirmDownpayment.getText().toString());
        confirmTotalPrice.setText(String.valueOf(intconfirmTotalPrice));

        double intconfirmTotalPricewoutint=Integer.parseInt(confirmOrderPrice.getText().toString()) + Integer.parseInt(confirmAdditionalPrice.getText().toString());
        confirmTotalpricewoutint.setText(String.valueOf(intconfirmTotalPricewoutint));

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save to data base
              //  saveconfirmdata();
                callcalculation();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to add fragment
                cancelconfirm();


            }
        });

        return v;
    }


    private void cancelconfirm(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new AddFragment());
        fragmentTransaction.commit();
    }

    private void saveconfirmdata(){

    }

    private void callcalculation(){

        String _monthstopay = confirmMonthstoPay.getText().toString().trim();
        switch (_monthstopay){
            case "1 Month":
                Log.i("Test","1 month " );
                onemonthcalculation();
                break;
            case "2 Months":
                Log.i("Test","2 months " );
                twomonthcalculation();
                break;
            case "3 Months":
                Log.i("Test","3 months " );
                threemonthcalculation();
                break;
            case "4 Months":
                Log.i("Test","4 months " );
                fourmonthcalculation();
                break;


        }

    }

    private void onemonthcalculation(){
        pmid1=0;
        pend1=0;
        pmid2=0;
        pend2=0;
        pmid3=0;
        pend3=0;
        pmid4=0;
        pend4=0;
        sukli=0;
        kulang=0;

        String _Name = confirmName.getText().toString().trim();
        String _Cpno = confirmCpno.getText().toString().trim();
        String _Shift = confirmShift.getText().toString().trim();
        String _Order = confirmOrder.getText().toString().trim();
        String _Orderprice = confirmOrderPrice.getText().toString().trim();
        String _Monthstopay = confirmMonthstoPay.getText().toString().trim();
        String _DateRegister = confirmdate.getText().toString().trim();

        String _Additional=confirmAdditional.getText().toString().trim();
        String _AdditionalPrice=confirmAdditionalPrice.getText().toString().trim();
        String _TotalPrice=confirmTotalPrice.getText().toString().trim();
        String _TotalPricewoutless=confirmTotalpricewoutint.getText().toString().trim();
        String _Downpayment=confirmDownpayment.getText().toString().trim();
        String _Color=confirmColor.getText().toString().trim();

        double totalprice=Integer.parseInt(confirmOrderPrice.getText().toString()) + Integer.parseInt(confirmAdditionalPrice.getText().toString()) - Double.parseDouble(confirmDownpayment.getText().toString());
        double interest = (5.0/100.0)*totalprice;
        double _1stmonthpay = (totalprice/1)+interest;

        double mid1=_1stmonthpay/2;
        double end1=_1stmonthpay/2;
        double _balance = mid1+end1;


        Calendar c = returnvaluemethon(_DateRegister);
        c.add(Calendar.DATE,15);
        String Cmid1=DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,15);
        String Cend1=DateFormat.getDateInstance().format(c.getTime());
        Log.i("Test2",Cmid1);

        String Mid1=String.format("%.2f",mid1);
        String End1=String.format("%.2f",end1);
        String _Balance=String.format("%.2f",_balance);

        String Uid=databaselistcustomer.push().getKey().concat(_Name);
    //    AddListDatabaseClass addListDatabaseClass = new AddListDatabaseClass(_Downpayment,_TotalPricewoutless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,null,null,null,null,null,null,_Additional,_AdditionalPrice,_TotalPrice,_Balance,Cmid1,Cend1,null,null,null,null,null,null,_Color,release);
        PaymentDatabaseClass paymentDatabaseClass = new PaymentDatabaseClass(pmid1,pend1,pmid2,pend2,pmid3,pend3,pmid4,pend4,Uid,_balance,sukli,kulang);
        AddListDatabaseClassR addListDatabaseClassR = new AddListDatabaseClassR(_Downpayment,_TotalPricewoutless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,null,null,null,null,null,null,_Additional,_AdditionalPrice,_TotalPrice,_Balance,Cmid1,Cend1,null,null,null,null,null,null,_Color,_userdata);

        databasecustomerlistR.child(Uid).setValue(addListDatabaseClassR);
        databasepayment.child(Uid).setValue(paymentDatabaseClass);
        textupdated(Uid);
      //  detailpay(Uid);
        Toast.makeText(getActivity(), "Customer Added ", Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new AddFragment());
        fragmentTransaction.commit();
    }

    private void twomonthcalculation(){
        pmid1=0;
        pend1=0;
        pmid2=0;
        pend2=0;
        pmid3=0;
        pend3=0;
        pmid4=0;
        pend4=0;
        kulang=0;
        sukli=0;

        String _Name = confirmName.getText().toString().trim();
        String _Cpno = confirmCpno.getText().toString().trim();
        String _Shift = confirmShift.getText().toString().trim();
        String _Order = confirmOrder.getText().toString().trim();
        String _Orderprice = confirmOrderPrice.getText().toString().trim();
        String _Monthstopay = confirmMonthstoPay.getText().toString().trim();
        String _DateRegister = confirmdate.getText().toString().trim();
        String _Additional=confirmAdditional.getText().toString().trim();
        String _AdditionalPrice=confirmAdditionalPrice.getText().toString().trim();
        String _TotalPrice=confirmTotalPrice.getText().toString().trim();
        String _Totalpricewoless=confirmTotalpricewoutint.getText().toString().trim();
        String _Downpayment=confirmDownpayment.getText().toString().trim();
        String _Color=confirmColor.getText().toString().trim();

        double totalprice=Integer.parseInt(confirmOrderPrice.getText().toString()) + Integer.parseInt(confirmAdditionalPrice.getText().toString()) - Double.parseDouble(confirmDownpayment.getText().toString());

        Calendar c = returnvaluemethon(_DateRegister);
        c.add(Calendar.DATE,15);
        String Cmid1 = DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,15);
        String Cend1 = DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,15);
        String Cmid2=DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,15);
        String Cend2=DateFormat.getDateInstance().format(c.getTime());

        double interest = (5.0/100.0)*totalprice;
        double _1stmonthpay=(totalprice/2)+interest;
        double _1stmonthnoint=totalprice/2;
        double _2ndinterest = (5.0/100.0)*(totalprice-_1stmonthnoint);
        double _2ndmonthpay = (totalprice/2)+_2ndinterest;


        double mid1=_1stmonthpay/2;
        double end1=_1stmonthpay/2;
        double mid2=_2ndmonthpay/2;
        double end2=_2ndmonthpay/2;
        double _balance = mid1+end1+mid2+end2;
        String Mid1=String.format("%.2f",mid1);
        String End1=String.format("%.2f",end1);
        String Mid2=String.format("%.2f",mid2);
        String End2=String.format("%.2f",end2);
        String _Balance=String.format("%.2f",_balance);

        String Uid=databaselistcustomer.push().getKey().concat(_Name);
     //   AddListDatabaseClass addListDatabaseClass = new AddListDatabaseClass(_Downpayment,_Totalpricewoless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,Mid2,End2,null,null,null,null,_Additional,_AdditionalPrice,_TotalPrice, _Balance, Cmid1,Cend1,Cmid2,Cend2,null,null,null,null,_Color,release );
        PaymentDatabaseClass paymentDatabaseClass = new PaymentDatabaseClass(pmid1,pend1,pmid2,pend2,pmid3,pend3,pmid4,pend4,Uid,_balance,sukli,kulang);
        AddListDatabaseClassR addListDatabaseClassR = new AddListDatabaseClassR(_Downpayment,_Totalpricewoless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,Mid2,End2,null,null,null,null,_Additional,_AdditionalPrice,_TotalPrice, _Balance, Cmid1,Cend1,Cmid2,Cend2,null,null,null,null,_Color,_userdata );

        databasecustomerlistR.child(Uid).setValue(addListDatabaseClassR);
        databasepayment.child(Uid).setValue(paymentDatabaseClass);
        textupdated(Uid);
      //  detailpay(Uid);
        Toast.makeText(getActivity(), "Customer Added ", Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new AddFragment());
        fragmentTransaction.commit();


    }
    private void threemonthcalculation(){
        pmid1=0;
        pend1=0;
        pmid2=0;
        pend2=0;
        pmid3=0;
        pend3=0;
        pmid4=0;
        pend4=0;
        sukli=0;
        kulang=0;
        String _Name = confirmName.getText().toString().trim();
        String _Cpno = confirmCpno.getText().toString().trim();
        String _Shift = confirmShift.getText().toString().trim();
        String _Order = confirmOrder.getText().toString().trim();
        String _Orderprice = confirmOrderPrice.getText().toString().trim();
        String _Monthstopay = confirmMonthstoPay.getText().toString().trim();
        String _DateRegister = confirmdate.getText().toString().trim();
        String _Additional=confirmAdditional.getText().toString().trim();
        String _AdditionalPrice=confirmAdditionalPrice.getText().toString().trim();
        String _TotalPrice=confirmTotalPrice.getText().toString().trim();
        String _Totalpricewoless=confirmTotalpricewoutint.getText().toString().trim();
        String _Downpayment=confirmDownpayment.getText().toString().trim();
        String _Color=confirmColor.getText().toString().trim();

        double totalprice=Integer.parseInt(confirmOrderPrice.getText().toString()) + Integer.parseInt(confirmAdditionalPrice.getText().toString())-Double.parseDouble(confirmDownpayment.getText().toString());

        double interest = (5.0/100.0)*totalprice;
        double _1stmonthpay=(totalprice/3)+interest;
        double _1stmonthnoint=totalprice/3;
        double _2ndmonthnoint=totalprice/3;
        double _2ndinterest = (5.0/100.0)*(totalprice-_1stmonthnoint);
        double _2ndmonthpay = (totalprice/3)+_2ndinterest;
        double _3rdinterest=(5.0/100.0)*(totalprice-_1stmonthnoint-_2ndmonthnoint);
        double _3rdmonthspay=(totalprice/3)+_3rdinterest;

        Calendar c = returnvaluemethon(_DateRegister);
        c.add(Calendar.DATE,15);
        String Cmid1 = DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,30);
        String Cend1 = DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,45);
        String Cmid2=DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,60);
        String Cend2=DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE, 75);
        String Cmid3=DateFormat.getDateInstance().format(c.getTime());
        c.add(Calendar.DATE,90);
        String Cend3=DateFormat.getDateInstance().format(c.getTime());

        double mid1=_1stmonthpay/2;
        double end1=_1stmonthpay/2;
        double mid2=_2ndmonthpay/2;
        double end2=_2ndmonthpay/2;
        double mid3=_3rdmonthspay/2;
        double end3=_3rdmonthspay/2;
        double _balance=mid1+end1+mid2+end2+mid3+end3;
        String Mid1=String.format("%.2f",mid1);
        String End1=String.format("%.2f",end1);
        String Mid2=String.format("%.2f",mid2);
        String End2=String.format("%.2f",end2);
        String Mid3=String.format("%.2f",mid3);
        String End3=String.format("%.2f",end3);
        String _Balance=String.format("%.2f",_balance);

        String Uid=databaselistcustomer.push().getKey().concat(_Name);
       // AddListDatabaseClass addListDatabaseClass = new AddListDatabaseClass(_Downpayment,_Totalpricewoless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,Mid2,End2,Mid3,End3,null,null,_Additional,_AdditionalPrice,_TotalPrice,_Balance,Cmid1,Cend1,Cmid2,Cend2,Cmid3,Cend3,null,null,_Color,release);
        PaymentDatabaseClass paymentDatabaseClass = new PaymentDatabaseClass(pmid1,pend1,pmid2,pend2,pmid3,pend3,pmid4,pend4,Uid,_balance,sukli,kulang);
        AddListDatabaseClassR addListDatabaseClassR = new AddListDatabaseClassR(_Downpayment,_Totalpricewoless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,Mid2,End2,Mid3,End3,null,null,_Additional,_AdditionalPrice,_TotalPrice,_Balance,Cmid1,Cend1,Cmid2,Cend2,Cmid3,Cend3,null,null,_Color,_userdata);

        databasecustomerlistR.child(Uid).setValue(addListDatabaseClassR);
        databasepayment.child(Uid).setValue(paymentDatabaseClass);
        textupdated(Uid);
      //  detailpay(Uid);
        Toast.makeText(getActivity(), "Customer Added ", Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new AddFragment());
        fragmentTransaction.commit();

    }
    private void fourmonthcalculation(){
        pmid1=0;
        pend1=0;
        pmid2=0;
        pend2=0;
        pmid3=0;
        pend3=0;
        pmid4=0;
        pend4=0;
        sukli=0;
        kulang=0;
        String _Name = confirmName.getText().toString().trim();
        String _Cpno = confirmCpno.getText().toString().trim();
        String _Shift = confirmShift.getText().toString().trim();
        String _Order = confirmOrder.getText().toString().trim();
        String _Orderprice = confirmOrderPrice.getText().toString().trim();
        String _Monthstopay = confirmMonthstoPay.getText().toString().trim();
        String _DateRegister = confirmdate.getText().toString().trim();
        String _Additional=confirmAdditional.getText().toString().trim();
        String _AdditionalPrice=confirmAdditionalPrice.getText().toString().trim();
        String _TotalPrice=confirmTotalPrice.getText().toString().trim();
        String _Totalpricewoless=confirmTotalpricewoutint.getText().toString().trim();
        String _Downpayment=confirmDownpayment.getText().toString().trim();
        String _Color=confirmColor.getText().toString().trim();

        double totalprice=Integer.parseInt(confirmOrderPrice.getText().toString()) + Integer.parseInt(confirmAdditionalPrice.getText().toString())-Double.parseDouble(confirmDownpayment.getText().toString());
        double interest = (5.0/100.0)*totalprice;
        double _1stmonthpay=(totalprice/4)+interest;
        double _1stmonthnoint=totalprice/4;
        double _2ndmonthnoint=totalprice/4;
        double _3rdmonthnoint=totalprice/4;
        double _2ndinterest = (5.0/100.0)*(totalprice-_1stmonthnoint);
        double _2ndmonthpay = (totalprice/4)+_2ndinterest;
        double _3rdinterest=(5.0/100.0)*(totalprice-_1stmonthnoint-_2ndmonthnoint);
        double _3rdmonthspay=(totalprice/4)+_3rdinterest;
        double _4thinterest = (5.0/100.0)*(totalprice-_1stmonthnoint-_2ndmonthnoint-_3rdmonthnoint);
        double _4thmonthspay=(totalprice/4)+_4thinterest;




        Calendar c = returnvaluemethon(_DateRegister);

        c.add(Calendar.DATE,15);
        String Cmid1 = DateFormat.getDateInstance().format(c.getTime()); //mid1

        c.add(Calendar.DATE,15);
        String Cend1 = DateFormat.getDateInstance().format(c.getTime());//end1

        c.add(Calendar.DATE,15);
        String Cmid2=DateFormat.getDateInstance().format(c.getTime());//mid2

        c.add(Calendar.DATE,15);
        String Cend2=DateFormat.getDateInstance().format(c.getTime());//end2

        c.add(Calendar.DATE, 15);
        String Cmid3=DateFormat.getDateInstance().format(c.getTime());//mid3

        c.add(Calendar.DATE,15);
        String Cend3=DateFormat.getDateInstance().format(c.getTime());//end3

        c.add(Calendar.DATE,15);
        String Cmid4=DateFormat.getDateInstance().format(c.getTime());//mid4

        c.add(Calendar.DATE,15);
        String Cend4=DateFormat.getDateInstance().format(c.getTime());//end4

        double mid1=_1stmonthpay/2;
        double end1=_1stmonthpay/2;
        double mid2=_2ndmonthpay/2;
        double end2=_2ndmonthpay/2;
        double mid3=_3rdmonthspay/2;
        double end3=_3rdmonthspay/2;
        double mid4=_4thmonthspay/2;
        double end4=_4thmonthspay/2;
        double _balance=mid1+end1+mid2+end2+mid3+end3+mid4+end4;
        String.format("%.2f",mid1);
        String Mid1=String.format("%.2f",mid1);
        String End1=String.format("%.2f",end1);
        String Mid2=String.format("%.2f",mid2);
        String End2=String.format("%.2f",end2);
        String Mid3=String.format("%.2f",mid3);
        String End3=String.format("%.2f",end3);
        String Mid4=String.format("%.2f",mid4);
        String End4=String.format("%.2f",end4);
        String _Balance=String.format("%.2f",_balance);

        Log.i("test",Cmid1);

        String Uid=databaselistcustomer.push().getKey().concat(_Name);

       // AddListDatabaseClass addListDatabaseClass = new AddListDatabaseClass(_Downpayment, _Totalpricewoless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,Mid2,End2,Mid3,End3,Mid4,End4,_Additional,_AdditionalPrice,_TotalPrice,_Balance,Cmid1,Cend1,Cmid2,Cend2,Cmid3,Cend3,Cmid4,Cend4,_Color,release);

        AddListDatabaseClassR addListDatabaseClassR = new AddListDatabaseClassR(_Downpayment, _Totalpricewoless, Uid, _Name, _Cpno, _Shift, _Order, _Orderprice, _Monthstopay, _DateRegister,Mid1,End1,Mid2,End2,Mid3,End3,Mid4,End4,_Additional,_AdditionalPrice,_TotalPrice,_Balance,Cmid1,Cend1,Cmid2,Cend2,Cmid3,Cend3,Cmid4,Cend4,_Color,_userdata);

        PaymentDatabaseClass paymentDatabaseClass = new PaymentDatabaseClass(pmid1,pend1,pmid2,pend2,pmid3,pend3,pmid4,pend4,Uid,_balance,sukli,kulang);

       // databaselistcustomer.child(Uid).setValue(addListDatabaseClassR);
        databasecustomerlistR.child(Uid).setValue(addListDatabaseClassR);
        databasepayment.child(Uid).setValue(paymentDatabaseClass);
        textupdated(Uid);
        Toast.makeText(getActivity(), "Customer Added ", Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new AddFragment());
        fragmentTransaction.commit();

    }

    public static Calendar returnvaluemethon(String _DateRegister){
        Calendar calendar=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        try {
          Date  getdate = simpleDateFormat.parse(_DateRegister);
          calendar=Calendar.getInstance();
          calendar.setTime(getdate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return calendar;
    }

    public void textupdated(String uid){
        mid1textupdated="Update";
        end1textupdated="Update";
        mid2textupdated="Update";
        end2textupdated="Update";
        mid3textupdated="Update";
        end3textupdated="Update";
        mid4textupdated="Update";
        end4textupdated="Update";
        UpdatedOrUpdateClass updatedOrUpdateClass = new UpdatedOrUpdateClass(uid, mid1textupdated,end1textupdated,mid2textupdated,end2textupdated,mid3textupdated,end3textupdated,mid4textupdated,end4textupdated);
        databasetextupdated.child(uid).setValue(updatedOrUpdateClass);
    }
    /*public void detailpay(String uid){
        String _detailpay = "1";
        String _detaildate = "13 May 2020";
        DetailPayDatabaseClass detailPayDatabaseClass = new DetailPayDatabaseClass(_detailpay,_detaildate);
        databasedetailpay.child(uid).setValue(detailPayDatabaseClass);
    }*/
}

