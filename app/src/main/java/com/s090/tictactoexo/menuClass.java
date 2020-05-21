package com.s090.tictactoexo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.core.content.res.ResourcesCompat;

// Custom NumberPicker class
public class menuClass extends NumberPicker {
    Typeface type;

    public menuClass(Context context) {
        super(context);
    }

    public menuClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributeSet(attrs);
    }

    public menuClass(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }

    private void processAttributeSet(AttributeSet attrs) {
        //This method reads the parameters given in the xml file and sets the properties according to it
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 0));
        String[] vals = {"Single Player Mode","2 Player Mode","3 Player Mode","4 Player Mode"};
        this.setDisplayedValues(vals);
    }
    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }
    private void updateView(View view) {
        if (view instanceof EditText) {

            ((EditText) view).setTextSize(25);

        }
    }
}

