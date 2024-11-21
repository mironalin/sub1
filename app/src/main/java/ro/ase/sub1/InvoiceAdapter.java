package ro.ase.sub1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class InvoiceAdapter extends ArrayAdapter<Invoice> {
    private int resource;
    private List<Invoice> objects;
    private LayoutInflater inflater;

    private TextView tvCustomerCode, tvInvoiceType;

    public InvoiceAdapter(@NonNull Context context, int resource, @NonNull List<Invoice> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.resource = resource;
        this.objects = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        initComponents(view);
        setData(objects.get(position));
        if (position % 2 == 0) {
            tvCustomerCode.setTextColor(Color.BLUE);
            tvInvoiceType.setTextColor(Color.BLUE);
        }
        return view;
    }

    private void setData(Invoice invoice) {
        tvCustomerCode.setText(String.valueOf(invoice.getCustomerCode()));
        tvInvoiceType.setText(invoice.getInvoiceType());
    }

    private void initComponents(View view) {
        tvCustomerCode = view.findViewById(R.id.tvCustomerCode);
        tvInvoiceType = view.findViewById(R.id.tvInvoiceType);
    }
}
