/*
    AddTransactionActivity.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.microblink.activity.SegmentScanActivity;
import com.microblink.ocr.ScanConfiguration;
import com.microblink.recognizers.blinkocr.engine.BlinkOCREngineOptions;
import com.microblink.recognizers.blinkocr.parser.generic.AmountParserSettings;
import com.microblink.recognizers.blinkocr.parser.generic.RawParserSettings;
import com.microblink.recognizers.blinkocr.parser.mobilecoupons.MobileCouponsParserSettings;
import com.microblink.results.ocr.OcrFont;

public class AddTransactionActivity extends FragmentActivity implements AddTransactionFragment.Listener {
    public static final String GROUP_ID_KEY = "GROUP_ID_KEY";
    private static final String NAME_RECEIPT = "Receipt";
    private static final int RECEIPT_REQUEST_CODE = 100;

    private Integer groupId;
    private AddTransactionFragment fragment;

    public static Intent makeIntent(Context from, Integer groupId) {
        Intent intent = new Intent(from, AddTransactionActivity.class);
        intent.putExtra(GROUP_ID_KEY, groupId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groupId = getIntent().getIntExtra(GROUP_ID_KEY, -1);

        setContentView(R.layout.activity_add_transaction);
        LinearLayout layout = (LinearLayout)findViewById(R.id.linear_layout);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setId(R.id.auto);

        fragment = new AddTransactionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TransactionListFragment.GROUP_ID_KEY, groupId);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(container.getId(), fragment).commit();

        layout.addView(container);
    }

    @Override
    public void finishSubmission() {
        finish();
    }

    @Override
    public void goToScan() {
        // BlinkInput OCR
        Intent intent = new Intent(this, SegmentScanActivity.class);
        intent.putExtra(SegmentScanActivity.EXTRAS_LICENSE_KEY, "RE737XBD-ATCTQ4RK-IIONOM33-3GGUK4TK-7WQFY2X5-UBOGV7NA-LRVP2AFI-KAIWDWMP");
        BlinkOCREngineOptions options = new BlinkOCREngineOptions();
        options.addAllDigitsToWhitelist(OcrFont.OCR_FONT_ANY);
        options.addCharToWhitelist('.', OcrFont.OCR_FONT_ANY);
        options.setMinimumLineHeight(10);
        options.setMaximumLineHeight(50);
        options.setMaximumCharsExpected(3000);
        options.setColorDropoutEnabled(false);

        RawParserSettings parserSettings = new RawParserSettings();
        parserSettings.setOcrEngineOptions(options);

        ScanConfiguration conf[] = new ScanConfiguration[] {
            new ScanConfiguration(R.string.receipt_title, R.string.receipt_msg, NAME_RECEIPT, parserSettings)
        };

        intent.putExtra(SegmentScanActivity.EXTRAS_SCAN_CONFIGURATION, conf);
        startActivityForResult(intent, RECEIPT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECEIPT_REQUEST_CODE && data != null) {
            String content = data.getBundleExtra(SegmentScanActivity.EXTRAS_SCAN_RESULTS).getString(NAME_RECEIPT);
            fragment.handleScanResults(content);
        }
    }
}
