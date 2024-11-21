package ro.ase.sub1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_FRAGMENT = "key";
    private List<Invoice> invoices;
    private ActivityResultLauncher<Intent> launcher;
    private InvoiceAdapter adapter;
    ListView lvInvoices;
    Button btnAdd, btnDetails;


    Fragment1 fragment1 = new Fragment1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getApplicationContext(), AddInvoiceActivity.class));
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(fragment1);
                sendData(fragment1, getString(R.string.student_name));
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    private void sendData(Fragment fragment, String data) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FRAGMENT, data);
        fragment.setArguments(bundle);
    }

    private void initComponents() {
        invoices = new ArrayList<>();
        lvInvoices = findViewById(R.id.lvInvoices);
        btnAdd = findViewById(R.id.btnAdd);
        btnDetails = findViewById(R.id.btnDetails);
        adapter = new InvoiceAdapter(getApplicationContext(), R.layout.lv_invoices, invoices, getLayoutInflater());
        lvInvoices.setAdapter(adapter);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        invoices.add((Invoice) result.getData().getSerializableExtra(AddInvoiceActivity.SEND_INVOICE_KEY));
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}