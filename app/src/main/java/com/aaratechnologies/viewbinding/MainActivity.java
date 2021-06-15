package com.aaratechnologies.viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aaratechnologies.viewbinding.databinding.ActivityMainBinding;
import com.aaratechnologies.viewbinding.databinding.RowTextBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tv.setText("Welcome");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, new BlankFragment());
                fragmentTransaction.commit();
            }
        });
//        binding.newbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "New Button Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
        binding.recyc.setHasFixedSize(true);
        binding.recyc.setLayoutManager(new LinearLayoutManager(this));
        List<String> strings = new ArrayList<>();
        strings.clear();
        strings.add("Raj kamal Jaiswal");
        strings.add("Harshit Gupta");
        strings.add("Raj Jaiswal");
        strings.add("Raunak Kashyap");
        strings.add("Rajat Jaiswal");
        strings.add("Ranjeet Jaiswal");
        strings.add("Shubham Gupta");

        AdapterTest adapterTest = new AdapterTest(strings);
        binding.recyc.setAdapter(adapterTest);
        adapterTest.notifyDataSetChanged();

    }

    public class AdapterTest extends RecyclerView.Adapter<AdapterTest.mViewHolder> {

        List<String> strings;

        public AdapterTest(List<String> strings) {
            this.strings = strings;
        }

        @NonNull
        @Override
        public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            RowTextBinding rowTextBinding = RowTextBinding.inflate(layoutInflater, parent, false);
            return new mViewHolder(rowTextBinding);

            /*     OR
              return new ViewHolder(RowTextBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
                */
        }

        @Override
        public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
            holder.recyclerRowBinding.textView.setText(strings.get(position));
            holder.recyclerRowBinding.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "" + strings.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return strings == null ? 0 : strings.size();
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            RowTextBinding recyclerRowBinding;

            //            public mViewHolder(@NonNull View itemView) {
//                super(itemView);
//            }
            public mViewHolder(@NonNull RowTextBinding recyclerRowBinding) {
                super(recyclerRowBinding.getRoot());
                this.recyclerRowBinding = recyclerRowBinding;
            }
        }
    }
}