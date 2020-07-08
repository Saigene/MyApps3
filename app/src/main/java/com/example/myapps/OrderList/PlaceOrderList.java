package com.example.myapps.OrderList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapps.Adapter.OrderlistAdapter;
import com.example.myapps.Adapter.PlaceOrderListAdapter;
import com.example.myapps.DatabaseClass.AddListDatabaseClass;
import com.example.myapps.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderList extends Fragment {

        List<AddListDatabaseClass> customerlist = new ArrayList<>();
        List<AddListDatabaseClass> customeritemlist = new ArrayList<>();
        ListView listitemview;
        OrderList orderList=new OrderList();
        @Nullable
        @Override

        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.orderlist_item_all, container, false);
            listitemview = (ListView) v.findViewById(R.id.orderlistitemall);
           // customerlist=orderList.getItemlist();
            Log.i("test2",customerlist.toString());
            PlaceOrderListAdapter placeOrderListAdapter=new PlaceOrderListAdapter(getActivity(),orderList.getItemlist());
            listitemview.setAdapter(placeOrderListAdapter);
            return v;
        }

}
