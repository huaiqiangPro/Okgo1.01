package com.mybox.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Consts {
    private Consts(){}

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    public static final String URL_SEPARATOR = ";";
    public static final String FORMAT_AUTHOR = "报道人：%s";
}
