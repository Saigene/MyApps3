package com.example.myapps.AddCustomer;

import android.app.AppComponentFactory;
import android.app.DatePickerDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapps.Global;
import com.example.myapps.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddFragment extends Fragment {
    private Button addbtn;
    private EditText textName;
    private EditText textCpno;
    private Spinner spinnerShift;
    private EditText textOrder;
    private EditText textOrderprice;
    private Spinner spinnerMonthstopay;
    private EditText textAdditional;
    private EditText textAdditionalprice;
    private BottomNavigationView bottomenavigationAddconfirm;
    private EditText textBookDate;
    private EditText textDownpayment;
    private EditText textColor;
    private Button btndate;
    private TextView user;



    private DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            String dateselect=format.format(c.getTime());
            textBookDate.setText(dateselect);
        }
    };

  //  private

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_fragment,container,false);
        addbtn = (Button) v.findViewById(R.id.addbtn);
        textName = (EditText) v.findViewById(R.id.editTextName);
        textCpno = (EditText) v.findViewById(R.id.editTextCpno);
        spinnerShift = (Spinner) v.findViewById(R.id.spinnerShift);
        textOrder = (EditText) v.findViewById(R.id.editTextOrder);
        textOrderprice = (EditText) v.findViewById(R.id.editTextOrderPrice);
        spinnerMonthstopay = (Spinner) v.findViewById(R.id.spinnerMonthstoPay);
        textAdditional=(EditText)v.findViewById(R.id.editTextAdditonal);
        textAdditionalprice=(EditText)v.findViewById(R.id.editTextAdditonalPrice);
        textDownpayment=(EditText)v.findViewById(R.id.editTextDownpayment);
        textColor=(EditText)v.findViewById(R.id.editTextColor);
        textBookDate=(EditText)v.findViewById(R.id.editBookDate);
        btndate=(Button)v.findViewById(R.id.btndate);
        user=(TextView)v.findViewById(R.id.textuser);


        Global userdata = Global.getInstance();
        String _userdata=userdata.getValue();

        user.setText(_userdata);

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), setListener, year,month,day);
                datePickerDialog.show();
            }
        });




        addbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addCustomer();
            }
        });
        return v;
    }



    private void addCustomer(){
        String _Name = textName.getText().toString().trim();
        String _Cpno = textCpno.getText().toString().trim();
        String _Shift = spinnerShift.getSelectedItem().toString();
        String _Order = textOrder.getText().toString().trim();
        String _Orderprice = textOrderprice.getText().toString().trim();
        String _Monthstopay = spinnerMonthstopay.getSelectedItem().toString();
        String _Additional=textAdditional.getText().toString().trim();
        String _Additionaprice=textAdditionalprice.getText().toString().trim();
        String _Downpayment=textDownpayment.getText().toString().trim();
        String _BookDate=textBookDate.getText().toString().trim();
        String _Color=textColor.getText().toString().trim();



        if(!TextUtils.isEmpty(_Name) && !TextUtils.isEmpty(_Cpno) && !TextUtils.isEmpty(_Order) && !TextUtils.isEmpty(_Orderprice) && !TextUtils.isEmpty(_Additional) && !TextUtils.isEmpty(_Additionaprice) && !TextUtils.isEmpty(_BookDate) && !TextUtils.isEmpty(_Downpayment) && !TextUtils.isEmpty(_Color))
        {
            Bundle bundle =new Bundle();
            bundle.putString("KeyName", _Name);
            bundle.putString("KeyCpno", _Cpno);
            bundle.putString("KeyShift", _Shift);
            bundle.putString("KeyOrder", _Order);
            bundle.putString("KeyOrderprice", _Orderprice);
            bundle.putString("KeyMonthstopay", _Monthstopay);
            bundle.putString("KeyAdditional", _Additional);
            bundle.putString("KeyAdditionalprice",_Additionaprice);
            bundle.putString("KeyDownpayment", _Downpayment);
            bundle.putString("KeyBookDate", _BookDate);
            bundle.putString("KeyColor",_Color);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        AddConfirmFragment addConfirmFragment = new AddConfirmFragment();
        addConfirmFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer, addConfirmFragment);
        fragmentTransaction.commit();


        }else {
            Toast.makeText(getActivity(), "Make sure you answer all the blank", Toast.LENGTH_LONG).show();
        }


    }
}
