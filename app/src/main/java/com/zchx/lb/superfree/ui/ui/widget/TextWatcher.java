package com.zchx.lb.superfree.ui.ui.widget;

import android.support.design.widget.TextInputLayout;


public abstract class TextWatcher implements android.text.TextWatcher {
    public String getEditString() {
        return textInputLayout.getEditText().getText().toString();
    }

    private TextInputLayout textInputLayout;

    public TextWatcher(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
