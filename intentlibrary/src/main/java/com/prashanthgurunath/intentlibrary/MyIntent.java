package com.prashanthgurunath.intentlibrary;


import android.app.Activity;
import android.app.SearchManager;
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

    public void openFacebook()
    {
        String url = "https://www.facebook.com";
        openBrowser(url);
    }

    public void openTwitter()
    {
        String url = "https://twitter.com";
        openBrowser(url);
    }

    public void openAmazon()
    {
        String url = "https://www.amazon.in/";
        openBrowser(url);
    }

    public void followDeveloperOnLinkedin()
    {
        String url = "https://www.linkedin.com/in/prashanth-gurunath-2834a6155/";
        openBrowser(url);
    }

    public MyIntent searchMeaning(String text)
    {
        i = new Intent(Intent.ACTION_WEB_SEARCH);
        i.putExtra(SearchManager.QUERY, text);
        return this;
    }



    // Phone Intent
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


}  // end of class MyIntent
