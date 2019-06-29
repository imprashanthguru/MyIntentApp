package com.prashanthgurunath.intentlibrary;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public class MyIntent {
    Context context;
    Intent i;

    public MyIntent(Context context)
    {
        this.context = context;
    }

    public static MyIntent from(@NonNull Context context)      // to denote current context
    {
        return new MyIntent(context);
    }

    public MyIntent openBrowser(String url)
    {
        if (!TextUtils.isEmpty(url) && !url.contains("://")) // basic validation
        {
            url = "http://" + url;
        }

        i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        return this;
    }

    public Intent getIntent() {     // return intent
        return i;
    }

    private void startActivity(Intent intent)
    {
        if (!(context instanceof Activity)) // if context isn't an instance of the Activity
        {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public void display()
    {
        startActivity(getIntent());
    }

// to use the libraries
// MyIntent.from(this).openBrowser().display();
// MyIntent.from(this).dispDialNumber().show();


    //
    public MyIntent dispDialNumber() // if number isn't entered, simply open the phone dialler
    {
        i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
        return this;
    }

        public MyIntent dispDialNumber(String phoneNumber)
    {
        i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
        return this;
    }

    public MyIntent callNumber(String phoneNumber)
    {
        i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        return this;
    }

}
