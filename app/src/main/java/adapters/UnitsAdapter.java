package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sqube.industrialunitsconverter.R;

import java.util.ArrayList;
import java.util.Random;

import models.Unit;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitViewHolder> {
    private ArrayList<Unit> units;

    public UnitsAdapter(ArrayList<Unit> units){
        this.units = units;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit, parent, false);
        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.txtValue.setText(String.valueOf(unit.getValue()));
        holder.txtUnit.setText(unit.getUnit());
        holder.txtName.setText(unit.getName());
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    class UnitViewHolder extends RecyclerView.ViewHolder {
        TextView txtValue, txtUnit, txtName;
        public UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtValue = itemView.findViewById(R.id.txtValue);
            txtUnit = itemView.findViewById(R.id.txtUnit);
        }
    }
}
