package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ViewAnimator;

import com.BillRift.presenters.AddTransactionPresenter;
import com.BillRift.views.AddTransactionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 11/8/2016.
 */

public class AddTransactionFragment extends MvpFragment<AddTransactionPresenter> implements AddTransactionView {
    public static final String GROUP_ID_KEY = "GROUP_ID_KEY";

    private static final int POSITION_VIEW = 0;
    private static final int POSITION_LOADING = 1;

    private ViewAnimator viewAnimator;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private EditText amntEditText;
    private Button submitBtn;
    private Button scanBtn;
    private List<String> names;
    private Listener listener;

    @Override
    protected AddTransactionPresenter createPresenter() {
        return new AddTransactionPresenter(getArguments().getInt(GROUP_ID_KEY));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        viewAnimator = (ViewAnimator) view.findViewById(R.id.animator);

        spinner = (Spinner)view.findViewById(R.id.person_dropdown);
        spinnerAdapter = new ArrayAdapter<CharSequence>(getActivity(), R.layout.support_simple_spinner_dropdown_item);
//        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.blank_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.updatePerson(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        spinner.setAdapter(spinnerAdapter);

        amntEditText = (EditText)view.findViewById(R.id.transaction_amount);
        amntEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.updateAmount(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        submitBtn = (Button) view.findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.submit();
            }
        });
        scanBtn = (Button) view.findViewById(R.id.btn_scan);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.scan();
            }
        });

        names = new ArrayList<>();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void showLoading() {
        viewAnimator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showView(List<String> names) {
        if (this.names.isEmpty()) {
            this.names.addAll(names);

            spinnerAdapter.addAll(names);
        }
        viewAnimator.setDisplayedChild(POSITION_VIEW);
    }

    @Override
    public void setAmount(String amount) {
        amntEditText.setText(amount);
        amntEditText.setSelection(amount.length());
    }

    @Override
    public void setSelectedName(String selectedName) {
        int index = names.indexOf(selectedName);
        if (index >= 0) {
            spinner.setSelection(index);
        }
    }

    @Override
    public void onSubmit() {
        if (listener != null) {
            listener.finishSubmission();
        }
    }

    @Override
    public void onScan() {
        if (listener != null) {
            listener.goToScan();
        }
    }

    public interface Listener {
        void finishSubmission();
        void goToScan();
    }
}
