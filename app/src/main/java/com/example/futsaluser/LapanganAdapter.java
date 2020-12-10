package com.example.futsaluser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LapanganAdapter extends RecyclerView.Adapter<LapanganAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DataLapangan> dataLapangan;

    public LapanganAdapter(Context c, ArrayList<DataLapangan> dataLapangan){
        this.context = c;
        this.dataLapangan = dataLapangan;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataLapangan dLapangan = this.dataLapangan.get(position);
        holder.TVlapangan.setText(dLapangan.getLapangan());
        holder.TVstatus.setText(dLapangan.getStatus());
        /*holder.BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Lapangan").child(dLapangan.getId());
                databaseReference.removeValue();
                Toast.makeText(context,"Data Berhasil Dihapus",Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.BtnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BookingActivity.class);
                i.putExtra("id",dLapangan.getId());
                i.putExtra("lapangan",dLapangan.getLapangan());
                i.putExtra("status",dLapangan.getStatus());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataLapangan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TVlapangan,TVstatus;
        Button BtnBooking;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TVlapangan = itemView.findViewById(R.id.TVlapangan);
            TVstatus = itemView.findViewById(R.id.TVstatus);
            BtnBooking = itemView.findViewById(R.id.BtnBooking);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
