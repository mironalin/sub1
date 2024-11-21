package ro.ase.sub1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddInvoiceActivity extends AppCompatActivity {
    public static final String SEND_INVOICE_KEY="invoiceKey";
    EditText etCustomerCode, etInvoiceType, etAmount;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        initComponents();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    Intent intent = new Intent();
                    intent.putExtra(SEND_INVOICE_KEY, getObjectFromComponent());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean isValid() {
        if (etCustomerCode.getText() == null || etCustomerCode.getText().toString().trim().isEmpty() || etCustomerCode.getText().toString().trim().length() != 13 || !etCustomerCode.getText().toString().trim().matches("[0-9]+")) {
            Toast.makeText(getApplicationContext(), "Customer code is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etInvoiceType.getText() == null || etInvoiceType.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invoice type is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etAmount.getText() == null || etAmount.getText().toString().trim().isEmpty() || Float.parseFloat(etAmount.getText().toString().trim()) < 0) {
            Toast.makeText(getApplicationContext(), "Amount is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Invoice getObjectFromComponent() {
        Long customerCode = Long.parseLong(etCustomerCode.getText().toString().trim());
        String invoiceType = etInvoiceType.getText().toString().trim();
        float amount = Float.parseFloat(etAmount.getText().toString().trim());

        return new Invoice(customerCode, invoiceType, amount);
    }
    private void initComponents() {
        etCustomerCode = findViewById(R.id.etCustomerCode);
        etInvoiceType = findViewById(R.id.etInvoiceType);
        etAmount = findViewById(R.id.etAmount);
        btnSave = findViewById(R.id.btnSave);
    }
}