package com.example.contentobserverexample;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Method;

/**
 * Created by Suyono on 9/15/2015.
 * Copyright (c) 2015 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */
@SuppressWarnings("ALL")
public class JalankanSet extends LinearLayout {
    private Button mButton;
    private Context mContext;
    public int setResource(String name, String Type) {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }

    public JalankanSet(final Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View root = inflater.inflate(setResource("lay_set", "layout"), this, true);
            mButton = (Button) root.findViewById(setResource("ubah","id"));
            mButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(getContext(), set.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    v.getContext().startActivity(intent);
                    try {
                        Object service = context.getSystemService("statusbar");
                        Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
                        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                        if (currentapiVersion <= 16) {
                            Method collapse = statusbarManager.getMethod("collapse");
                            collapse.invoke(service);
                        } else {
                            Method collapse2 = statusbarManager.getMethod("collapsePanels");
                            collapse2.invoke(service);
                        }

                    } catch (Exception ex) { }

                }

            });
        }
    }
}
