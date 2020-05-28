package com.example.useofmaterialdesign;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("vpriJYMEmZoo9EdqM5JkkqGIodzm233q6Yj9LYDE")
                // if defined
                .clientKey("QaiaawPeTifhDFahCLyYCSVs1iQoJjVftksqLXso")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
