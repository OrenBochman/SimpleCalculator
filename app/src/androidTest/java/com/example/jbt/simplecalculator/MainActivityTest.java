package com.example.jbt.simplecalculator;

import android.app.Activity;
import android.app.Application;
import android.location.LocationProvider;
import android.view.Menu;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.ActivityTestRule;

//import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;


public class MainActivityTest {

  //  @Rule public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    LocationProvider  mockLocationProvider;

    @Before void setup(){

          //mockLocationProvider ;

        //CalculatorApplication application= (CalculatorApplication) getTargetContext();
        CalculatorApplication application = getApplicationContext();
        application.setLocationProvider(mockLocationProvider);

    }

    @Test void uiShouldUpdateAfterLocationUpdate(){

    }



}