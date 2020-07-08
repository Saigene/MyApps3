package com.example.myapps.ListCustomer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapps.DatabaseClass.DetailPayDatabaseClass;
import com.example.myapps.DatabaseClass.PaymentDatabaseClass;
import com.example.myapps.DatabaseClass.UpdatedOrUpdateClass;
import com.example.myapps.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.myapps.ListCustomer.ListFragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class  UpdateCustomerListFragment extends AppCompatActivity {

    //  @Nullable
    //  @Override
    //  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //      View v = inflater.inflate(R.layout.updatecustomerlist_fragment, container, false);
    //     Toolbar toolbar =(Toolbar) v.findViewById(R.id.updatetoolbar);
    //}

    TextView textName;
    TextView textCpno;
    TextView textShift;
    TextView textOrder;
    TextView textOrderprice;
    TextView textMonthstopay;
    TextView textAdditional;
    TextView textAdditionalprice;
    TextView textTotalprice, textTotalwithin, textTotalpricewoless, textDownpayment;
    TextView textBalance;

    EditText editTextpaymid1;
    EditText editTextpayend1;
    EditText editTextpaymid2;
    EditText editTextpayend2;
    EditText editTextpaymid3;
    EditText editTextpayend3;
    EditText editTextpaymid4;
    EditText editTextpayend4;

    TextView Mid1Date;
    TextView End1Date;
    TextView Mid2Date;
    TextView End2Date;
    TextView Mid3Date;
    TextView End3Date;
    TextView Mid4Date;
    TextView End4Date;

    TextView Mid1pay;
    TextView End1pay;
    TextView Mid2pay;
    TextView End2pay;
    TextView Mid3pay;
    TextView End3pay;
    TextView Mid4pay;
    TextView End4pay;

    Button btnmid1pay;
    Button btnend1pay;
    Button btnmid2pay;
    Button btnend2pay;
    Button btnmid3pay;
    Button btnend3pay;
    Button btnmid4pay;
    Button btnend4pay;
    TextView textSukli;
    TextView textkulang;

    TextView textmid1detailpay;
    TextView textend1detailpay;
    TextView textmid2detailpay;
    TextView textend2detailpay;
    TextView textmid3detailpay;
    TextView textend3detailpay;
    TextView textmid4detailpay;
    TextView textend4detailpay;
    TextView textupdateddetailpay;
    TextView textediteddetailpay;

    EditText updatedialogpaytext;
    Button updatedialogbtn;
    Button updatecanceldialogbtn;
    Button updatedialogbtnpayment;
    Button updatedialogbtnclear;
    Button updatedialogbtnkulang;
    Button updatedialogbtnsukli;

    Button btneditinfo;
    Button btneditpayment;
    Button btneditcancel;

    Button btneditpaymentdialog;
    EditText editdailogpayment;
    TextView editoldpayment;

    String updateid;

    Button btnupdate;
    Button btnedit;
    Button buttoneditcancel;
    Button buttoneditconfirm;

    Button buttondetail;

    DatabaseReference customerupdate;
    DatabaseReference cusomerpaymentupdate;
    DatabaseReference customerupdateorupdated;
    DatabaseReference customerdetailpay;

    private String detailpay;
    private String detaildate;

    public int comparepay=0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatecustomerlist_fragment);
        textName=(TextView)findViewById(R.id.textUpdateName);
        textCpno=(TextView)findViewById(R.id.textUpdateCpno);
        textShift=(TextView)findViewById(R.id.textUpdateShift);
        textOrder=(TextView)findViewById(R.id.textUpdateOrder);
        textOrderprice=(TextView)findViewById(R.id.textUpdateOrderPrice);
        textMonthstopay=(TextView)findViewById(R.id.textUpdateMontstopay);
        textAdditional=(TextView)findViewById(R.id.textUpdateAddtional);
        textAdditionalprice=(TextView)findViewById(R.id.textUpdateAdditionalPrice);
        textTotalprice=(TextView)findViewById(R.id.textUpdateTotalprice);
        textBalance=(TextView)findViewById(R.id.textUpdateTotalBalance);
        textSukli=(TextView)findViewById(R.id.textUpdateSukli);
        textkulang=(TextView)findViewById(R.id.textUpdateKulang);
        textTotalwithin=(TextView)findViewById(R.id.textUpdateTotalpricewithint);
        textTotalpricewoless=(TextView)findViewById(R.id.textUpdateTotalpricewoless);
        textDownpayment=(TextView)findViewById(R.id.textUpdateDownpayment);
        btnupdate=(Button)findViewById(R.id.buttonupdated);
        btnedit=(Button)findViewById(R.id.buttonedit);
        buttondetail=(Button)findViewById(R.id.buttondetail);

        textmid1detailpay = (TextView)findViewById(R.id.textmid1detailpay);
        textend1detailpay = (TextView)findViewById(R.id.textend1detailpay);
        textmid2detailpay = (TextView)findViewById(R.id.textmid2detailpay);
        textend2detailpay = (TextView)findViewById(R.id.textend2detailpay);
        textmid3detailpay = (TextView)findViewById(R.id.textmid3detailpay);
        textend3detailpay = (TextView)findViewById(R.id.textend3detailpay);
        textmid4detailpay = (TextView)findViewById(R.id.textmid4detailpay);
        textend4detailpay = (TextView)findViewById(R.id.textend4detailpay);
        textupdateddetailpay = (TextView)findViewById(R.id.textupdatedetailpay);
        textediteddetailpay = (TextView)findViewById(R.id.texteditdetailpay);

        textmid1detailpay.setVisibility(View.INVISIBLE);
        textend1detailpay.setVisibility(View.INVISIBLE);
        textmid2detailpay.setVisibility(View.INVISIBLE);
        textend2detailpay.setVisibility(View.INVISIBLE);
        textmid3detailpay.setVisibility(View.INVISIBLE);
        textend3detailpay.setVisibility(View.INVISIBLE);
        textmid4detailpay.setVisibility(View.INVISIBLE);
        textend4detailpay.setVisibility(View.INVISIBLE);
        textupdateddetailpay.setVisibility(View.INVISIBLE);
        textediteddetailpay.setVisibility(View.INVISIBLE);


        buttoneditcancel=(Button)findViewById(R.id.btneditcancel);
        buttoneditconfirm=(Button)findViewById(R.id.btneditconfirm);
        buttoneditconfirm.setVisibility(View.INVISIBLE);
        buttoneditcancel.setVisibility(View.INVISIBLE);

        editTextpaymid1=(EditText)findViewById(R.id.edittextUpdateMid1AmountPay);
        editTextpayend1=(EditText)findViewById(R.id.edittextUpdateEnd1AmountPay);
        editTextpaymid2=(EditText)findViewById(R.id.edittextUpdateMid2AmountPay);
        editTextpayend2=(EditText)findViewById(R.id.edittextUpdateEnd2AmountPay);
        editTextpaymid3=(EditText)findViewById(R.id.edittextUpdateMid3AmountPay);
        editTextpayend3=(EditText)findViewById(R.id.edittextUpdateEnd3AmountPay);
        editTextpaymid4=(EditText)findViewById(R.id.edittextUpdateMid4AmountPay);
        editTextpayend4=(EditText)findViewById(R.id.edittextUpdateEnd4AmountPay);

        Mid1Date=(TextView)findViewById(R.id.textUpdateDateMid1);
        End1Date=(TextView)findViewById(R.id.textUpdateDateEnd1);
        Mid2Date=(TextView)findViewById(R.id.textUpdateDateMid2);
        End2Date=(TextView)findViewById(R.id.textUpdateDateEnd2);
        Mid3Date=(TextView)findViewById(R.id.textUpdateDateMid3);
        End3Date=(TextView)findViewById(R.id.textUpdateDateEnd3);
        Mid4Date=(TextView)findViewById(R.id.textUpdateDateMid4);
        End4Date=(TextView)findViewById(R.id.textUpdateDateEnd4);

        Mid1pay=(TextView)findViewById(R.id.textUpdateMid1AmountPay);
        End1pay=(TextView)findViewById(R.id.textUpdateEnd1AmountPay);
        Mid2pay=(TextView)findViewById(R.id.textUpdateMid2AmountPay);
        End2pay=(TextView)findViewById(R.id.textUpdateEnd2AmountPay);
        Mid3pay=(TextView)findViewById(R.id.textUpdateMid3AmountPay);
        End3pay=(TextView)findViewById(R.id.textUpdateEnd3AmountPay);
        Mid4pay=(TextView)findViewById(R.id.textUpdateMid4AmountPay);
        End4pay=(TextView)findViewById(R.id.textUpdateEnd4AmountPay);

        btnmid1pay=(Button)findViewById(R.id.buttonmid1pay);
        btnend1pay=(Button)findViewById(R.id.buttonend1pay);
        btnmid2pay=(Button)findViewById(R.id.buttonmid2pay);
        btnend2pay=(Button)findViewById(R.id.buttonend2pay);
        btnmid3pay=(Button)findViewById(R.id.buttonmid3pay);
        btnend3pay=(Button)findViewById(R.id.buttonend3pay);
        btnmid4pay=(Button)findViewById(R.id.buttonmid4pay);
        btnend4pay=(Button)findViewById(R.id.buttonend4pay);


        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

            String _Name = bundle.getString("Keyupdatename");
            String _Cp = bundle.getString("Keyupdatecpno");
            String _Shift = bundle.getString("keyupdateshift");
            String _Order = bundle.getString("Keyupdateorder");
            String _Orderprice = bundle.getString("Keyupdateorderprice");
            String _Monthstopay = bundle.getString("Keymonthstopay");
            String _Additional = bundle.getString("Keyadditional");
            String _Additionalprice = bundle.getString("Keyadditionalprice");
            String _Totalprice = bundle.getString("Keytotalprice");
            String _Totalpricewithint = bundle.getString("Keytotalpricewithin");
            String _Totalpricewoless = bundle.getString("Keytotalpricewoless");
            String _Downpayment = bundle.getString("Keydownpayment");

            String _Mid1date = bundle.getString("Keymid1date");
            String _End1date = bundle.getString("Keyend1date");
            String _Mid2date = bundle.getString("Keymid2date");
            String _End2date = bundle.getString("Keyend2date");
            String _Mid3date = bundle.getString("Keymid3date");
            String _End3date = bundle.getString("Keyend3date");
            String _Mid4date = bundle.getString("Keymid4date");
            String _End4date = bundle.getString("Keyend4date");

            String _Mid1PAY = bundle.getString("Keymid1pay");
            String _End1PAY = bundle.getString("Keyend1pay");
            String _Mid2PAY = bundle.getString("Keymid2pay");
            String _End2PAY = bundle.getString("Keyend2pay");
            String _Mid3PAY = bundle.getString("Keymid3pay");
            String _End3PAY = bundle.getString("Keyend3pay");
            String _Mid4PAY = bundle.getString("Keymid4pay");
            String _End4PAY = bundle.getString("Keyend4pay");
            final String _Id = bundle.getString("KeyId");

       /*     Double _Pmid1 = bundle.getDouble("Keypmid1");
            Double _Pend1 = bundle.getDouble("Keypend1");
            Double _Pmid2 = bundle.getDouble("Keypmid2");
            Double _Pend2 = bundle.getDouble("Keypend2");
            Double _Pmid3 = bundle.getDouble("Keypmid3");
            Double _Pend3 = bundle.getDouble("Keypend3");
            Double _Pmid4 = bundle.getDouble("Keypmid4");
            Double _Pend4 = bundle.getDouble("Keypend4");
            Double _balance1 = bundle.getDouble("KeyBalance");
            Double _sukli = bundle.getDouble("Keysukli");
            Double _kulang = bundle.getDouble("Keykulang");

            final String _Pid = bundle.getString("Keypuid");
            final String _Id = bundle.getString("KeyId");

            editTextpaymid1.setText(String.valueOf(_Pmid1));
            editTextpayend1.setText(String.valueOf(_Pend1));
            editTextpaymid2.setText(String.valueOf(_Pmid2));
            editTextpayend2.setText(String.valueOf(_Pend2));
            editTextpaymid3.setText(String.valueOf(_Pmid3));
            editTextpayend3.setText(String.valueOf(_Pend3));
            editTextpaymid4.setText(String.valueOf(_Pmid4));
            editTextpayend4.setText(String.valueOf(_Pend4));
            textBalance.setText(String.valueOf(_balance1));
            textSukli.setText(String.valueOf(_sukli));
            textkulang.setText(String.valueOf(_kulang));
*/
            textName.setText(_Name);
            textCpno.setText(_Cp);
            textShift.setText(_Shift);
            textOrder.setText(_Order);
            textOrderprice.setText(_Orderprice);
            textMonthstopay.setText(_Monthstopay);
            textAdditional.setText(_Additional);
            textAdditionalprice.setText(_Additionalprice);
            textTotalprice.setText(_Totalprice);
            textTotalwithin.setText(_Totalpricewithint);
            textDownpayment.setText(_Downpayment);
            textTotalpricewoless.setText(_Totalpricewoless);


            Mid1Date.setText(_Mid1date);
            End1Date.setText(_End1date);
            Mid2Date.setText(_Mid2date);
            End2Date.setText(_End2date);
            Mid3Date.setText(_Mid3date);
            End3Date.setText(_End3date);
            Mid4Date.setText(_Mid4date);
            End4Date.setText(_End4date);

            Mid1pay.setText(_Mid1PAY);
            End1pay.setText(_End1PAY);
            Mid2pay.setText(_Mid2PAY);
            End2pay.setText(_End2PAY);
            Mid3pay.setText(_Mid3PAY);
            End3pay.setText(_End3PAY);
            Mid4pay.setText(_Mid4PAY);
            End4pay.setText(_End4PAY);

          /*  btnmid1pay.setText(bundle.getString("Keymid1update"));
            btnend1pay.setText(bundle.getString("Keyend1update"));
            btnmid2pay.setText(bundle.getString("Keymid2update"));
            btnend2pay.setText(bundle.getString("Keyend2update"));
            btnmid3pay.setText(bundle.getString("Keymid3update"));
            btnend3pay.setText(bundle.getString("Keyend3update"));
            btnmid4pay.setText(bundle.getString("Keymid4update"));
            btnend4pay.setText(bundle.getString("Keyend4update"));
            updateid = bundle.getString("Keyupdateid");*/

            customerupdate = FirebaseDatabase.getInstance().getReference("Customer_Detail").child(_Id);
            cusomerpaymentupdate = FirebaseDatabase.getInstance().getReference("Customer Payment").child(_Id);
            customerupdateorupdated = FirebaseDatabase.getInstance().getReference("Customer status").child(updateid);
            customerdetailpay = FirebaseDatabase.getInstance().getReference("Customer Detail Pay").child(_Id);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



            


            switch (_Monthstopay) {
                case "1 Month":
                    Log.i("btnmonth", "1month");
                    if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) >= Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) < Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid1pay.setEnabled(true);
                        Log.i("Test", btnmid1pay.getText().toString());
                    }

                    if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) >= Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) < Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend1pay.setEnabled(true);
                    }

                    btnmid2pay.setVisibility(View.INVISIBLE);
                    btnend2pay.setVisibility(View.INVISIBLE);
                    btnmid3pay.setVisibility(View.INVISIBLE);
                    btnend3pay.setVisibility(View.INVISIBLE);
                    btnmid4pay.setVisibility(View.INVISIBLE);
                    btnend4pay.setVisibility(View.INVISIBLE);
                    editTextpaymid1.setVisibility(View.VISIBLE);
                    editTextpayend1.setVisibility(View.VISIBLE);
                    editTextpaymid2.setVisibility(View.INVISIBLE);
                    editTextpayend2.setVisibility(View.INVISIBLE);
                    editTextpaymid3.setVisibility(View.INVISIBLE);
                    editTextpayend3.setVisibility(View.INVISIBLE);
                    editTextpaymid4.setVisibility(View.INVISIBLE);
                    editTextpayend4.setVisibility(View.INVISIBLE);
                    break;
                case "2 Months":
                    Log.i("btnmonth", "2month");
                    if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) >= Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) < Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid1pay.setEnabled(true);
                        Log.i("Test", btnmid1pay.getText().toString());
                    }

                    if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) >= Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) < Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend1pay.setEnabled(true);
                    }

                    if (btnmid2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid2.getText().toString()) >= Double.parseDouble(_Mid2PAY)) {
                        btnmid2pay.setEnabled(false);
                        btnmid2pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid2.getText().toString()) < Double.parseDouble(_Mid2PAY)) {
                        btnmid2pay.setEnabled(false);
                        btnmid2pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid2pay.setEnabled(true);
                    }

                    if (btnend2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend2.getText().toString()) >= Double.parseDouble(_End2PAY)) {
                        btnend2pay.setEnabled(false);
                        btnend2pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend2.getText().toString()) < Double.parseDouble(_End2PAY)) {
                        btnend2pay.setEnabled(false);
                        btnend2pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend2pay.setEnabled(true);
                    }

                    btnmid3pay.setVisibility(View.INVISIBLE);
                    btnend3pay.setVisibility(View.INVISIBLE);
                    btnmid4pay.setVisibility(View.INVISIBLE);
                    btnend4pay.setVisibility(View.INVISIBLE);
                    editTextpaymid1.setVisibility(View.VISIBLE);
                    editTextpayend1.setVisibility(View.VISIBLE);
                    editTextpaymid2.setVisibility(View.VISIBLE);
                    editTextpayend2.setVisibility(View.VISIBLE);
                    editTextpaymid3.setVisibility(View.INVISIBLE);
                    editTextpayend3.setVisibility(View.INVISIBLE);
                    editTextpaymid4.setVisibility(View.INVISIBLE);
                    editTextpayend4.setVisibility(View.INVISIBLE);
                    break;
                case "3 Months":
                    Log.i("btnmonth", "3month");
                    if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) >= Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) < Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid1pay.setEnabled(true);
                        Log.i("Test", btnmid1pay.getText().toString());
                    }

                    if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) >= Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) < Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend1pay.setEnabled(true);
                    }

                    if (btnmid2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid2.getText().toString()) >= Double.parseDouble(_Mid2PAY)) {
                        btnmid2pay.setEnabled(false);
                        btnmid2pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid2.getText().toString()) < Double.parseDouble(_Mid2PAY)) {
                        btnmid2pay.setEnabled(false);
                        btnmid2pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid2pay.setEnabled(true);
                    }

                    if (btnend2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend2.getText().toString()) >= Double.parseDouble(_End2PAY)) {
                        btnend2pay.setEnabled(false);
                        btnend2pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend2.getText().toString()) < Double.parseDouble(_End2PAY)) {
                        btnend2pay.setEnabled(false);
                        btnend2pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend2pay.setEnabled(true);
                    }

                    if (btnmid3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid3.getText().toString()) >= Double.parseDouble(_Mid3PAY)) {
                        btnmid3pay.setEnabled(false);
                        btnmid3pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid3.getText().toString()) < Double.parseDouble(_Mid3PAY)) {
                        btnmid3pay.setEnabled(false);
                        btnmid3pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid3pay.setEnabled(true);
                    }

                    if (btnend3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend3.getText().toString()) >= Double.parseDouble(_End3PAY)) {
                        btnend3pay.setEnabled(false);
                        btnend3pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend3.getText().toString()) < Double.parseDouble(_End3PAY)) {
                        btnend3pay.setEnabled(false);
                        btnend3pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend3pay.setEnabled(true);
                    }


                    btnmid4pay.setVisibility(View.INVISIBLE);
                    btnend4pay.setVisibility(View.INVISIBLE);
                    editTextpaymid1.setVisibility(View.VISIBLE);
                    editTextpayend1.setVisibility(View.VISIBLE);
                    editTextpaymid2.setVisibility(View.VISIBLE);
                    editTextpayend2.setVisibility(View.VISIBLE);
                    editTextpaymid3.setVisibility(View.VISIBLE);
                    editTextpayend3.setVisibility(View.VISIBLE);
                    editTextpaymid4.setVisibility(View.INVISIBLE);
                    editTextpayend4.setVisibility(View.INVISIBLE);
                    break;
                case "4 Months":
                    Log.i("btnmonth", "4month");
                    if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) >= Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid1.getText().toString()) < Double.parseDouble(_Mid1PAY)) {
                        btnmid1pay.setEnabled(false);
                        btnmid1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid1pay.setEnabled(true);
                    }

                    if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) >= Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend1pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend1.getText().toString()) < Double.parseDouble(_End1PAY)) {
                        btnend1pay.setEnabled(false);
                        btnend1pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend1pay.setEnabled(true);
                    }

                    if (btnmid2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid2.getText().toString()) >= Double.parseDouble(_Mid2PAY)) {
                        btnmid2pay.setEnabled(false);
                        btnmid2pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid2.getText().toString()) < Double.parseDouble(_Mid2PAY)) {
                        btnmid2pay.setEnabled(false);
                        btnmid2pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid2pay.setEnabled(true);
                    }

                    if (btnend2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend2.getText().toString()) >= Double.parseDouble(_End2PAY)) {
                        btnend2pay.setEnabled(false);
                        btnend2pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend2pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend2.getText().toString()) < Double.parseDouble(_End2PAY)) {
                        btnend2pay.setEnabled(false);
                        btnend2pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend2pay.setEnabled(true);
                    }

                    if (btnmid3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid3.getText().toString()) >= Double.parseDouble(_Mid3PAY)) {
                        btnmid3pay.setEnabled(false);
                        btnmid3pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid3.getText().toString()) < Double.parseDouble(_Mid3PAY)) {
                        btnmid3pay.setEnabled(false);
                        btnmid3pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid3pay.setEnabled(true);
                    }

                    if (btnend3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend3.getText().toString()) >= Double.parseDouble(_End3PAY)) {
                        btnend3pay.setEnabled(false);
                        btnend3pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend3pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend3.getText().toString()) < Double.parseDouble(_End3PAY)) {
                        btnend3pay.setEnabled(false);
                        btnend3pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend3pay.setEnabled(true);
                    }


                    if (btnmid4pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid4.getText().toString()) >= Double.parseDouble(_Mid4PAY)) {
                        btnmid4pay.setEnabled(false);
                        btnmid4pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnmid4pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpaymid4.getText().toString()) < Double.parseDouble(_Mid4PAY)) {
                        btnmid4pay.setEnabled(false);
                        btnmid4pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnmid4pay.setEnabled(true);
                    }

                    if (btnend4pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend4.getText().toString()) >= Double.parseDouble(_End4PAY)) {
                        btnend4pay.setEnabled(false);
                        btnend4pay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else if (btnend4pay.getText().toString().equals("Updated") && Double.parseDouble(editTextpayend4.getText().toString()) < Double.parseDouble(_End4PAY)) {
                        btnend4pay.setEnabled(false);
                        btnend4pay.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    } else {
                        btnend4pay.setEnabled(true);
                    }


                    editTextpaymid1.setVisibility(View.VISIBLE);
                    editTextpayend1.setVisibility(View.VISIBLE);
                    editTextpaymid2.setVisibility(View.VISIBLE);
                    editTextpayend2.setVisibility(View.VISIBLE);
                    editTextpaymid3.setVisibility(View.VISIBLE);
                    editTextpayend3.setVisibility(View.VISIBLE);
                    editTextpaymid4.setVisibility(View.VISIBLE);
                    editTextpayend4.setVisibility(View.VISIBLE);
                    break;
            }

            nulldetailedpay();

            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seteditbutton(1);
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UpdateCustomerListFragment.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogview = inflater.inflate(R.layout.edit_dialog, null);
                    dialogBuilder.setView(dialogview)
                            .setCancelable(false);
                    btneditinfo = (Button) dialogview.findViewById(R.id.btneditdiaglogInfo);
                    btneditpayment = (Button) dialogview.findViewById(R.id.btneditdiaglogpayment);
                    btneditcancel = (Button) dialogview.findViewById(R.id.btneditdiaglogcancel);
                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    btneditinfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    btneditpayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnmid1pay.getText().toString().equals("Updated")) {
                                btnmid1pay.setText("Edit");
                                setenablebtn(1);
                            }else {
                                btnmid1pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnend1pay.getText().toString().equals("Updated")) {
                                btnend1pay.setText("Edit");
                                setenablebtn(2);
                            }else {
                                btnend1pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnmid2pay.getText().toString().equals("Updated")) {
                                btnmid2pay.setText("Edit");
                                setenablebtn(3);
                            }else{
                                btnmid2pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnend2pay.getText().toString().equals("Updated")) {
                                btnend2pay.setText("Edit");
                                setenablebtn(4);
                            }else {
                                btnend2pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnmid3pay.getText().toString().equals("Updated")) {
                                btnmid3pay.setText("Edit");
                                setenablebtn(5);
                            }else{
                                btnmid3pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnend3pay.getText().toString().equals("Updated")) {
                                btnend3pay.setText("Edit");
                                setenablebtn(6);
                            }else{
                                btnend3pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnmid4pay.getText().toString().equals("Updated")) {
                                btnmid4pay.setText("Edit");
                                setenablebtn(7);
                            }else {
                                btnmid4pay.setVisibility(View.INVISIBLE);
                            }
                            if (btnend4pay.getText().toString().equals("Updated")) {
                                btnend4pay.setText("Edit");
                                setenablebtn(8);
                            }else {
                                btnend4pay.setVisibility(View.INVISIBLE);
                            }

                            textupdateddetailpay.setText("Edited");
                            alertDialog.dismiss();

                        }
                    });
                    btneditcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seteditbutton(2);
                            alertDialog.dismiss();
                        }
                    });


                }
            });

            buttoneditconfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (btnmid1pay.getText().toString().equals("Edit")) {
                        btnmid1pay.setText("Updated");
                    }
                    if (btnend1pay.getText().toString().equals("Edit")) {
                        btnend1pay.setText("Updated");
                    }
                    if (btnmid2pay.getText().toString().equals("Edit")) {
                        btnmid2pay.setText("Updated");
                    }
                    if (btnend2pay.getText().toString().equals("Edit")) {
                        btnend2pay.setText("Updated");
                    }
                    if (btnmid3pay.getText().toString().equals("Edit")) {
                        btnmid3pay.setText("Updated");
                    }
                    if (btnend3pay.getText().toString().equals("Edit")) {
                        btnend3pay.setText("Updated");
                    }
                    if (btnmid4pay.getText().toString().equals("Edit")) {
                        btnmid4pay.setText("Updated");
                    }
                    if (btnend4pay.getText().toString().equals("Edit")) {
                        btnend4pay.setText("Updated");
                    }
                    seteditbutton(2);
                    setenablebtn(8);
                }
            });

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Double _Mid1paid = Double.parseDouble(editTextpaymid1.getText().toString().trim());
                    Double _End1paid = Double.parseDouble(editTextpayend1.getText().toString().trim());
                    Double _Mid2paid = Double.parseDouble(editTextpaymid2.getText().toString().trim());
                    Double _End2paid = Double.parseDouble(editTextpayend2.getText().toString().trim());
                    Double _Mid3paid = Double.parseDouble(editTextpaymid3.getText().toString().trim());
                    Double _End3paid = Double.parseDouble(editTextpayend3.getText().toString().trim());
                    Double _Mid4paid = Double.parseDouble(editTextpaymid4.getText().toString().trim());
                    Double _End4paid = Double.parseDouble(editTextpayend4.getText().toString().trim());
                    Double _Kulang = Double.parseDouble(textkulang.getText().toString().trim());
                    Double _Sukli = Double.parseDouble(textSukli.getText().toString().trim());
                    Double _Balance = Double.parseDouble(textBalance.getText().toString().trim());
                    String _mid1textupdated = btnmid1pay.getText().toString().trim();
                    String _end1textupdated = btnend1pay.getText().toString().trim();
                    String _mid2textupdated = btnmid2pay.getText().toString().trim();
                    String _end2textupdated = btnend2pay.getText().toString().trim();
                    String _mid3textupdated = btnmid3pay.getText().toString().trim();
                    String _end3textupdated = btnend3pay.getText().toString().trim();
                    String _mid4textupdated = btnmid4pay.getText().toString().trim();
                    String _end4textupdated = btnend4pay.getText().toString().trim();
              //      String _textmid1detailpay = textmid1detailpay.getText().toString().trim();



                    PaymentDatabaseClass paymentDatabaseClass = new PaymentDatabaseClass(_Mid1paid, _End1paid, _Mid2paid, _End2paid, _Mid3paid, _End3paid, _Mid4paid, _End4paid, _Id, _Balance, _Sukli, _Kulang);
                    UpdatedOrUpdateClass updatedOrUpdateClass = new UpdatedOrUpdateClass(updateid, _mid1textupdated, _end1textupdated, _mid2textupdated, _end2textupdated, _mid3textupdated, _end3textupdated, _mid4textupdated, _end4textupdated);
                    cusomerpaymentupdate.setValue(paymentDatabaseClass);
                    customerupdateorupdated.setValue(updatedOrUpdateClass);

                    if(!textupdateddetailpay.getText().toString().equals("null")){
                        if(btnmid1pay.getText().toString().equals("Updated") && !textmid1detailpay.getText().toString().equals("null")) {
                            savedetailed(textmid1detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnend1pay.getText().toString().equals("Updated") && !textend1detailpay.getText().toString().equals("null")) {
                            savedetailed(textend1detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnmid2pay.getText().toString().equals("Updated") && !textmid2detailpay.getText().toString().equals("null")) {
                            savedetailed(textmid2detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnend2pay.getText().toString().equals("Updated") && !textend2detailpay.getText().toString().equals("null")) {
                            savedetailed(textend2detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnmid3pay.getText().toString().equals("Updated") && !textmid3detailpay.getText().toString().equals("null")) {
                            savedetailed(textmid3detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnend3pay.getText().toString().equals("Updated") && !textend3detailpay.getText().toString().equals("null")) {
                            savedetailed(textend3detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnmid4pay.getText().toString().equals("Updated") && !textmid4detailpay.getText().toString().equals("null")) {
                            savedetailed(textmid4detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }
                        if(btnend4pay.getText().toString().equals("Updated") && !textend4detailpay.getText().toString().equals("null")) {
                            savedetailed(textend4detailpay.getText().toString(), textupdateddetailpay.getText().toString());
                        }

                    }
                    nulldetailedpay();
                    Toast.makeText(getApplicationContext(), "Successfully Update", Toast.LENGTH_LONG).show();
                }
            });

            btnmid1pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 1;
                    if (btnmid1pay.getText().toString().equals("Update")) {
                        btnupdated(btnmid1pay, Mid1pay, textBalance, btnend1pay, textSukli, textkulang);
                    } else if (btnmid1pay.getText().toString().equals("Edit")) {
                        btnmid1pay.setEnabled(true);
                        setBtneditpayment(editTextpaymid1, Mid1pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });
            btnend1pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 2;
                    if (btnend1pay.getText().toString().equals("Update")) {
                        btnupdated(btnend1pay, End1pay, textBalance, btnmid2pay, textSukli, textkulang);
                    } else if (btnend1pay.getText().toString().equals("Edit")) {
                        btnend1pay.setEnabled(true);
                        setBtneditpayment(editTextpayend1, End1pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });

            btnmid2pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 3;
                    if (btnmid2pay.getText().toString().equals("Update")) {
                        btnupdated(btnmid2pay, Mid2pay, textBalance, btnend2pay, textSukli, textkulang);
                    } else if (btnmid2pay.getText().toString().equals("Edit")) {
                        btnmid2pay.setEnabled(true);
                        setBtneditpayment(editTextpaymid2, Mid2pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });

            btnend2pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 4;
                    if (btnend2pay.getText().toString().equals("Update")) {
                        btnupdated(btnend2pay, End2pay, textBalance, btnmid3pay, textSukli, textkulang);
                    } else if (btnend2pay.getText().toString().equals("Edit")) {
                        btnend2pay.setEnabled(true);
                        setBtneditpayment(editTextpayend2, End2pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });
            btnmid3pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 5;
                    if (btnmid3pay.getText().toString().equals("Update")) {
                        btnupdated(btnmid3pay, Mid3pay, textBalance, btnend3pay, textSukli, textkulang);
                    } else if (btnmid3pay.getText().toString().equals("Edit")) {
                        btnmid3pay.setEnabled(true);
                        setBtneditpayment(editTextpaymid3, Mid3pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });
            btnend3pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 6;
                    if (btnend3pay.getText().toString().equals("Update")) {
                        btnupdated(btnend3pay, End3pay, textBalance, btnmid4pay, textSukli, textkulang);
                    } else if (btnend3pay.getText().toString().equals("Edit")) {
                        btnend3pay.setEnabled(true);
                        setBtneditpayment(editTextpayend3, End3pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });

            btnmid4pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 7;
                    if (btnmid4pay.getText().toString().equals("Update")) {
                        btnupdated(btnmid4pay, Mid4pay, textBalance, btnend4pay, textSukli, textkulang);
                    } else if (btnmid4pay.getText().toString().equals("Edit")) {
                        btnmid4pay.setEnabled(true);
                        setBtneditpayment(editTextpaymid4, Mid4pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });

            btnend4pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comparepay = 8;
                    if (btnend4pay.getText().toString().equals("Update")) {
                        btnupdated(btnend4pay, End4pay, textBalance, btnend4pay, textSukli, textkulang);
                    } else if (btnend4pay.getText().toString().equals("Edit")) {
                        btnend4pay.setEnabled(true);
                        setBtneditpayment(editTextpayend4, End4pay, textkulang, textSukli, textTotalwithin);
                    }
                }
            });

        buttondetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UpdateCustomerListFragment.this, DetailFragment.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("Keydetailsid",_Id);
                bundle1.putString("Keydetailname", textName.getText().toString());
                bundle1.putString("Keydetailorder",textOrder.getText().toString());
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
        });
    }

    private void  btnupdated(final Button btnupdate, final TextView needtopay, final TextView vbalance, final Button nextbtnupdate, final TextView sukli, final TextView kulang){

        AlertDialog.Builder dialogBuilder  = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.update_dialog,null);
        dialogBuilder.setView(dialogview)
                     .setCancelable(false);

        updatedialogpaytext = (EditText) dialogview.findViewById(R.id.dialogpay);
        updatedialogbtn=(Button) dialogview.findViewById(R.id.btndialogupdate);
        updatecanceldialogbtn=(Button)dialogview.findViewById(R.id.btndialogupdatecancel);
        updatedialogbtnpayment=(Button)dialogview.findViewById(R.id.btndialogpayment);
        updatedialogbtnclear=(Button)dialogview.findViewById(R.id.btndialogclear);
        updatedialogbtnkulang=(Button)dialogview.findViewById(R.id.btndialogkulang);
        updatedialogbtnsukli=(Button)dialogview.findViewById(R.id.btndialogsukli);

        updatedialogbtnkulang.setText(kulang.getText().toString().trim());
        updatedialogbtnsukli.setText(sukli.getText().toString().trim());
        updatedialogbtnpayment.setText(needtopay.getText().toString().trim());

        updatedialogpaytext.setEnabled(false);
        updatedialogbtnpayment.setEnabled(false);

        updatedialogbtnkulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double x = Double.parseDouble(needtopay.getText().toString()) + Double.parseDouble(kulang.getText().toString());
                updatedialogbtnpayment.setText(String.valueOf(x));
                updatedialogbtnkulang.setText(String.valueOf(0.0));
                if(Double.parseDouble(updatedialogbtnkulang.getText().toString())==0.0 && Double.parseDouble(updatedialogbtnsukli.getText().toString() )==0.0){
                updatedialogpaytext.setEnabled(true);
                updatedialogbtnpayment.setEnabled(true);
                }
            }
        });

        updatedialogbtnsukli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double y=Double.parseDouble(needtopay.getText().toString()) - Double.parseDouble(sukli.getText().toString());
                if(y<=0){
                    updatedialogpaytext.setText(needtopay.getText().toString().trim());
                }else{
                    updatedialogbtnpayment.setText(String.valueOf(y));
                    updatedialogbtnsukli.setText(String.valueOf(0.0));
                    if(Double.parseDouble(updatedialogbtnkulang.getText().toString())==0.0 && Double.parseDouble(updatedialogbtnsukli.getText().toString() )==0.0){
                        updatedialogpaytext.setEnabled(true);
                        updatedialogbtnpayment.setEnabled(true);
                    }
                }
            }
        });

        if(Double.parseDouble(updatedialogbtnkulang.getText().toString())==0.0 && Double.parseDouble(updatedialogbtnsukli.getText().toString() )==0.0){
            updatedialogpaytext.setEnabled(true);
            updatedialogbtnpayment.setEnabled(true);
        }
            updatedialogbtnpayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatedialogpaytext.setText(updatedialogbtnpayment.getText().toString().trim());

                }
            });

        updatedialogbtnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedialogpaytext.setText("");
            }
        });

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        updatedialogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedialogpaytext.setEnabled(false);
                if (!TextUtils.isEmpty(updatedialogpaytext.getText().toString())){
                    textupdateddetailpay.setText(" ");
                    Double _newBalance = Double.parseDouble(vbalance.getText().toString()) - Double.parseDouble(updatedialogpaytext.getText().toString());
                    textBalance.setText(String.format("%.2f",_newBalance));
                    nextbtnupdate.setEnabled(true);
                    if(comparepay==1){
                        editTextpaymid1.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnmid1pay.setText("Updated");
                        textmid1detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==2){
                        editTextpayend1.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnend1pay.setText("Updated");
                        textend1detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==3){
                        editTextpaymid2.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnmid2pay.setText("Updated");
                        textmid2detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==4){
                        editTextpayend2.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnend2pay.setText("Updated");
                        textend2detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==5){
                        editTextpaymid3.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnmid3pay.setText("Updated");
                        textmid3detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==6){
                        editTextpayend3.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnend3pay.setText("Updated");
                        textend3detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==7){
                        editTextpaymid4.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnmid4pay.setText("Updated");
                        textmid4detailpay.setText(updatedialogpaytext.getText().toString());
                    }
                    if(comparepay==8){
                        editTextpayend4.setText(updatedialogpaytext.getText().toString());
                        comparepay=0;
                        btnend4pay.setText("Updated");
                        textend4detailpay.setText(updatedialogpaytext.getText().toString());
                    }


                    if (Double.parseDouble(updatedialogpaytext.getText().toString()) >= Double.parseDouble(updatedialogbtnpayment.getText().toString().trim())) {
                        Double _sukli = Double.parseDouble(updatedialogpaytext.getText().toString()) - Double.parseDouble(updatedialogbtnpayment.getText().toString().trim());
                        btnupdate.setEnabled(false);
                        btnupdate.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                        textSukli.setText(String.format("%.2f",_sukli));
                        textkulang.setText(String.valueOf(0.0));

                         alertDialog.dismiss();
                    }else{
                         Double _kulang=Double.parseDouble(updatedialogbtnpayment.getText().toString())-Double.parseDouble(updatedialogpaytext.getText().toString());
                         btnupdate.setEnabled(false);
                         double _sukli = 0;
                         textSukli.setText(String.format("%.2f",_sukli));
                         btnupdate.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                         textkulang.setText(String.format("%.2f",_kulang));
                         alertDialog.dismiss();
                     }
                 }else{
                    Toast.makeText(getApplicationContext(),"Please insert Payment", Toast.LENGTH_LONG).show();
                }

            }
        });

        updatecanceldialogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    private void setBtneditpayment(final EditText editpayment, final TextView needtopay, final TextView oldkulang, final TextView oldsukli, final TextView totalpay){

        AlertDialog.Builder dialogBuilder  =new AlertDialog.Builder(UpdateCustomerListFragment.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.editpayment_dialog,null);
        dialogBuilder.setView(dialogview)
                .setCancelable(false);
        btneditpaymentdialog=(Button) dialogview.findViewById(R.id.btnupdatededit);
        editdailogpayment=(EditText)dialogview.findViewById(R.id.editdialogpayment);
        editoldpayment=(TextView)dialogview.findViewById(R.id.editoldpayment);
        editoldpayment.setText(editpayment.getText().toString());
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btneditpaymentdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editdailogpayment.getText().toString())){
                    if(Double.parseDouble(editoldpayment.getText().toString()) > Double.parseDouble(editdailogpayment.getText().toString())){
                        Double x = Double.parseDouble(editoldpayment.getText().toString()) - Double.parseDouble(editdailogpayment.getText().toString());
                        if(Double.parseDouble(oldsukli.getText().toString()) == 0 && Double.parseDouble(oldkulang.getText().toString()) == 0){
                            textkulang.setText(String.format("%.2f",x));
                        }else if(Double.parseDouble(oldkulang.getText().toString()) != 0){
                            Double newkulang = Double.parseDouble(oldkulang.getText().toString()) + x;
                            textkulang.setText(String.format("%.2f",newkulang));
                        }else if(Double.parseDouble(oldsukli.getText().toString()) != 0){
                            if(x > Double.parseDouble(oldsukli.getText().toString())){
                                Double newkulang = x - Double.parseDouble(oldsukli.getText().toString());
                                textSukli.setText(String.valueOf(0));
                                textkulang.setText(String.format("%.2f",newkulang));
                            }
                            if(x <= Double.parseDouble(oldsukli.getText().toString())){
                                Double newsukli = Double.parseDouble(oldsukli.getText().toString()) - x;
                                textSukli.setText(String.format("%.2f",newsukli));
                            }
                        }
                    }

                    if(Double.parseDouble(editdailogpayment.getText().toString()) > Double.parseDouble(editoldpayment.getText().toString())){
                        Double x = Double.parseDouble(editdailogpayment.getText().toString()) - Double.parseDouble(editoldpayment.getText().toString());
                        if(Double.parseDouble(oldsukli.getText().toString()) == 0 && Double.parseDouble(oldkulang.getText().toString()) == 0){
                            textSukli.setText(String.format("%.2f",x));
                        }else if(Double.parseDouble(oldsukli.getText().toString()) != 0){
                            Double newsukli = Double.parseDouble(oldsukli.getText().toString()) + x;
                            textSukli.setText(String.format("%.2f",newsukli));
                        }else if(Double.parseDouble(oldkulang.getText().toString()) != 0){
                            if(x > Double.parseDouble(oldkulang.getText().toString())){
                                Double newsukli = x - Double.parseDouble(oldkulang.getText().toString());
                                textkulang.setText(String.valueOf(0));
                                textSukli.setText(String.format("%.2f",newsukli));
                            }
                            if(x <= Double.parseDouble(oldkulang.getText().toString())){
                                Double newkulang = Double.parseDouble(oldkulang.getText().toString()) - x;
                                textkulang.setText(String.format("%.2f",newkulang));
                            }
                        }
                    }


                }

                if(comparepay==1){
                    if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                        editTextpaymid1.setText(editdailogpayment.getText().toString());
                        comparepay = 0;
                        btnmid1pay.setText("Updated");
                        textmid1detailpay.setText(editdailogpayment.getText().toString());
                    }else{
                        editTextpaymid1.setText(editpayment.getText().toString());
                    }
                }

                if(comparepay==2){
                    if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                        editTextpayend1.setText(editdailogpayment.getText().toString());
                        comparepay = 0;
                        btnend1pay.setText("Updated");
                        textend1detailpay.setText(editdailogpayment.getText().toString());
                    }else{
                        editTextpayend1.setText(editpayment.getText().toString());
                    }
                }

                if(comparepay==3){
                    if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                        editTextpaymid2.setText(editdailogpayment.getText().toString());
                        comparepay = 0;
                        btnmid2pay.setText("Updated");
                        textmid2detailpay.setText(editdailogpayment.getText().toString());
                    }else{
                        editTextpaymid2.setText(editpayment.getText().toString());
                    }
                }

                if(comparepay==4) {
                    if (!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                        editTextpayend2.setText(editdailogpayment.getText().toString());
                        comparepay = 0;
                        btnend2pay.setText("Updated");
                        textend2detailpay.setText(editdailogpayment.getText().toString());
                    } else {
                        editTextpayend2.setText(editpayment.getText().toString());
                    }
                }

                if(comparepay==5){
                        if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                            editTextpaymid3.setText(editdailogpayment.getText().toString());
                            comparepay = 0;
                            btnmid3pay.setText("Updated");
                            textmid3detailpay.setText(editdailogpayment.getText().toString());
                        }else{
                            editTextpaymid3.setText(editpayment.getText().toString());
                        }
                }

                if(comparepay==6){
                        if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                            editTextpayend3.setText(editdailogpayment.getText().toString());
                            comparepay = 0;
                            btnend3pay.setText("Updated");
                            textend3detailpay.setText(editdailogpayment.getText().toString());
                        }else{
                            editTextpayend3.setText(editpayment.getText().toString());
                        }

                }

                if(comparepay==7){
                    if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                        editTextpaymid4.setText(editdailogpayment.getText().toString());
                        comparepay = 0;
                        btnmid4pay.setText("Updated");
                        textmid4detailpay.setText(editdailogpayment.getText().toString());
                    }else{
                        editTextpaymid4.setText(editpayment.getText().toString());
                    }
                }

                if(comparepay==8){
                    if(!TextUtils.isEmpty(editdailogpayment.getText().toString())) {
                        editTextpayend4.setText(editdailogpayment.getText().toString());
                        comparepay = 0;
                        btnend4pay.setText("Updated");
                        textend4detailpay.setText(editdailogpayment.getText().toString());
                    }else{
                        editTextpayend4.setText(editpayment.getText().toString());
                    }

                }

                Double _sumpay = Double.parseDouble(editTextpaymid1.getText().toString()) + Double.parseDouble(editTextpayend1.getText().toString()) + Double.parseDouble(editTextpaymid2.getText().toString()) + Double.parseDouble(editTextpayend2.getText().toString()) + Double.parseDouble(editTextpaymid3.getText().toString()) + Double.parseDouble(editTextpayend3.getText().toString()) + Double.parseDouble(editTextpaymid4.getText().toString()) + Double.parseDouble(editTextpayend4.getText().toString());
                if(_sumpay < Double.parseDouble(totalpay.getText().toString())) {
                    Double _newBalance = Double.parseDouble(totalpay.getText().toString()) - _sumpay;
                    textBalance.setText(String.format("%.2f",_newBalance));
                }else if(_sumpay >= Double.parseDouble(totalpay.getText().toString())){
                    textBalance.setText(String.valueOf(0));
                }

                alertDialog.dismiss();
            }
        });

    }

    private void setenablebtn(int a){

        btnmid1pay.setVisibility(View.INVISIBLE);
        btnend1pay.setVisibility(View.INVISIBLE);
        btnmid2pay.setVisibility(View.INVISIBLE);
        btnend2pay.setVisibility(View.INVISIBLE);
        btnmid3pay.setVisibility(View.INVISIBLE);
        btnend3pay.setVisibility(View.INVISIBLE);
        btnmid4pay.setVisibility(View.INVISIBLE);
        btnend4pay.setVisibility(View.INVISIBLE);

        for(int x=1 ;x<=a; x++) {
            if (x == 1) {
                btnmid1pay.setEnabled(true);
                btnmid1pay.setVisibility(View.VISIBLE);
            }
            if (x == 2) {
                btnend1pay.setEnabled(true);
                btnend1pay.setVisibility(View.VISIBLE);
            }
            if (x == 3) {
                btnmid2pay.setEnabled(true);
                btnmid2pay.setVisibility(View.VISIBLE);
            }
            if (x == 4) {
                btnend2pay.setEnabled(true);
                btnend2pay.setVisibility(View.VISIBLE);
            }
            if (x == 5) {
                btnmid3pay.setEnabled(true);
                btnmid3pay.setVisibility(View.VISIBLE);
            }
            if (x == 6) {
                btnend3pay.setEnabled(true);
                btnend3pay.setVisibility(View.VISIBLE);
            }
            if (x == 7) {
                btnmid4pay.setEnabled(true);
                btnmid4pay.setVisibility(View.VISIBLE);
            }
            if (x == 8) {
                btnend4pay.setEnabled(true);
                btnend4pay.setVisibility(View.VISIBLE);
            }
        }

    }

private void seteditbutton(int x){
        if(x==1) {
            btnedit.setEnabled(false);
            buttoneditcancel.setVisibility(View.VISIBLE);
            buttoneditconfirm.setVisibility(View.VISIBLE);
            btnupdate.setVisibility(View.INVISIBLE);
            buttondetail.setVisibility(View.INVISIBLE);
        }

    if(x==2) {
        btnedit.setEnabled(true);
        buttoneditcancel.setVisibility(View.INVISIBLE);
        buttoneditconfirm.setVisibility(View.INVISIBLE);
        btnupdate.setVisibility(View.VISIBLE);
        buttondetail.setVisibility(View.VISIBLE);
    }
}

private void savedetailed(String textdetailpay, String detailstatus){
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy HH:mm:ss", Locale.getDefault());
    Calendar cal = Calendar.getInstance();
    String date = dateFormat.format(cal.getTime());
    String id = customerdetailpay.push().getKey();
    DetailPayDatabaseClass detailPayDatabaseClass = new DetailPayDatabaseClass(textdetailpay, date, detailstatus);
    customerdetailpay.child(id).setValue(detailPayDatabaseClass);
}

private void nulldetailedpay(){
    textmid1detailpay.setText("null");
    textend1detailpay.setText("null");
    textmid2detailpay.setText("null");
    textend2detailpay.setText("null");
    textmid3detailpay.setText("null");
    textend3detailpay.setText("null");
    textmid4detailpay.setText("null");
    textend4detailpay.setText("null");
    textupdateddetailpay.setText("null");
    }

}