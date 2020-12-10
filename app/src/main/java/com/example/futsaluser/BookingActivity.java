package com.example.futsaluser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    DatePickerDialog Picker;
    EditText ETLapangan,ETStatus,ETNama,ETtanggal,ETJam,ETTelephone;
    Button BBooking;
    String id,lapangan,status;
    Spinner SSlotjam;
    DatabaseReference Datadb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ETNama= findViewById(R.id.ETNama);
        ETLapangan = findViewById(R.id.ETLapangan);
        ETStatus = findViewById(R.id.ETStatus);
        ETtanggal = findViewById(R.id.ETtanggal);
        ETtanggal.setInputType(InputType.TYPE_NULL);
        SSlotjam = findViewById(R.id.SSlotJam);
        ETTelephone = findViewById(R.id.ETTelephone);
        BBooking = findViewById(R.id.BBooking);
        ETJam = findViewById(R.id.ETjam);
        //mengirim data lapangan,status berdasarkan id booking activity
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        lapangan = intent.getStringExtra("lapangan");
        status = intent.getStringExtra("status");
        ETLapangan.setText(lapangan);
        ETStatus.setText(status);

        Datadb = FirebaseDatabase.getInstance().getReference().child("Booking");
        ETtanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                //datepicker dialog
                Picker = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ETtanggal.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                Picker.show();
            }
        });
        BBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });
    }

    private void InsertData() {
        String id,lapangan,status,nama,tanggal,slotjam,jam,telephone;
        id = Datadb.push().getKey();
        lapangan = ETLapangan.getText().toString();
        status =  ETStatus.getText().toString();
        nama = ETNama.getText().toString();
        tanggal = ETtanggal.getText().toString();
        jam = ETJam.getText().toString();
        slotjam = SSlotjam.getSelectedItem().toString();
        telephone = ETTelephone.getText().toString();
        DataBooking dataBooking = new DataBooking(id,lapangan,status,nama,tanggal,slotjam,jam,telephone);
        Datadb.child(id).setValue(dataBooking);
        Toast.makeText(BookingActivity.this,"Lapangan Telah DiBooking",Toast.LENGTH_SHORT).show();


    }
}