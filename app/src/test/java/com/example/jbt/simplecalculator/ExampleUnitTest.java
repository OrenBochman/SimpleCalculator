package com.example.jbt.simplecalculator;

import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

//import org.robolectric.RuntimeEnvironment;
//import static androidx.test.core.app.launchActivity;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ExampleUnitTest {

    Double a = 1.0;
    Double b = 2.0;
    //Activity activity;
    ActivityScenario<MainActivity> scenario;

    @Before
   public void setup(){
     //   activity = Robolectric.setupActivity(MainActivity.class);
        //GIVEN
        scenario = ActivityScenario.launch(MainActivity.class);

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Test the menus text.
     *
     * @throws Exception
     */
    @Test
    public void onCreate_shouldInflateTheMenu() throws Exception {
        //GIVEN
        //scenario = ActivityScenario.launch(MainActivity.class);

        //WHEN
        scenario.onActivity(activity -> {
            final Menu menu = shadowOf(activity).getOptionsMenu();

            //THEN
            assertThat(menu.findItem(R.id.item1).getTitle()).isEqualTo("First menu item");
            assertThat(menu.findItem(R.id.item2).getTitle()).isEqualTo("Second menu item");
        });



    }

//    @Test
//    public void clickingLogin_shouldStartLoginActivity() {
//        WelcomeActivity activity = Robolectric.setupActivity(WelcomeActivity.class);
//        activity.findViewById(R.id.login).performClick();
//
//        Intent expectedIntent = new Intent(activity, LoginActivity.class);
//        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());
//    }

    /**
     * Testing error.
     *
     *
     */
    @Test
    public void clickingPlus_shouldCreateErrorMessage() {
        scenario.onActivity(activity -> {
            //WHEN
            activity.findViewById(R.id.btnPlus).performClick();
            String resourceString = ApplicationProvider.getApplicationContext().getString(R.string.badinput);

            //THEN
            TextView result = activity.findViewById(R.id.textResult);
            assertThat(result.getText().toString()).isEqualTo(resourceString);
        });
    }

    @Test
    public void divisionByZero_shouldBeInfinity() {
        scenario.onActivity(activity -> {

            //WHEN
            EditText topText = activity.findViewById(R.id.editNum1);
            topText.setText("1.0");
            EditText bottomText = activity.findViewById(R.id.editNum2);
            bottomText.setText("0.0");

            activity.findViewById(R.id.btnDiv).performClick();


            //THEN
            TextView result = activity.findViewById(R.id.textResult);
            assertThat(result.getText().toString()).isEqualTo("Result = Infinity");

        });



    }

    @Test
    public void missingValue_shouldCreateErrorMessage() {
        scenario.onActivity(activity -> {

            EditText bottomText = activity.findViewById(R.id.editNum2);
            bottomText.setText("0");

            activity.findViewById(R.id.btnDiv).performClick();

            String resourceString = ApplicationProvider.getApplicationContext().getString(R.string.badinput);
            TextView result = activity.findViewById(R.id.textResult);

            assertThat(result.getText().toString()).isEqualTo(resourceString);
        });
    }

    @Test
    public void HappyPath_additionShouldSucceed() {

        // WHEN
        scenario.onActivity(activity -> {

            EditText topText = activity.findViewById(R.id.editNum1);
            topText.setText(a.toString());
            EditText bottomText = activity.findViewById(R.id.editNum2);
            bottomText.setText(b.toString());

            activity.findViewById(R.id.btnPlus).performClick();


        });

        //THEN
        scenario.onActivity(activity -> {
            TextView result = activity.findViewById(R.id.textResult);
            assertThat(result.getText().toString()).isEqualTo("Result = 3.00");
        });


    }

    @Test
    public void HappyPath_subtractionShouldSucceed() {
        scenario.onActivity(activity -> {

            EditText topText = activity.findViewById(R.id.editNum1);
            topText.setText(a.toString());
            EditText bottomText = activity.findViewById(R.id.editNum2);
            bottomText.setText(b.toString());
            activity.findViewById(R.id.btnMinus).performClick();

        });

        //THEN
        scenario.onActivity(activity -> {
            TextView result = activity.findViewById(R.id.textResult);
            assertThat(result.getText().toString()).isEqualTo("Result = -1.00");
        });
    }

    @Test
    public void HappyPath_ProductShouldSucceed() {

        // WHEN
        scenario.onActivity(activity -> {

            EditText topText = activity.findViewById(R.id.editNum1);
            topText.setText(a.toString());
            EditText bottomText = activity.findViewById(R.id.editNum2);
            bottomText.setText(b.toString());

            activity.findViewById(R.id.btnMult).performClick();

        });

        //THEN
        scenario.onActivity(activity -> {
            TextView result = activity.findViewById(R.id.textResult);
            assertThat(result.getText().toString()).isEqualTo("Result = 2.00");
        });
    }

    @Test
    public void HappyPath_DivShouldSucceed() {
        //WHEN
        scenario.onActivity(activity -> {


                    EditText topText = activity.findViewById(R.id.editNum1);
                    topText.setText(a.toString());
                    EditText bottomText = activity.findViewById(R.id.editNum2);
                    bottomText.setText(b.toString());

                    activity.findViewById(R.id.btnDiv).performClick();
        });

        //THEN
        scenario.onActivity(activity -> {
            TextView result = activity.findViewById(R.id.textResult);
            assertThat(result.getText().toString()).isEqualTo("Result = 0.50");
        });
    }

    /**
     *
     * Test UI state resilience to Configuration changes.
     *
     * Android preserves the EditText values but the TextBox holding the calculation result
     * gets discarded.
     *
     * This is remedied in onSaveInstanceState and onRestoreInstanceState which persists and restore
     * the UI state during the session.
     *
     */
    @Test
    public void ResultsShouldBeRetainedAfterConfigurationChange() {

        //WHEN
        scenario.onActivity(activity -> {

            EditText topText = activity.findViewById(R.id.editNum1);
            topText.setText(a.toString());
            EditText bottomText = activity.findViewById(R.id.editNum2);
            bottomText.setText(b.toString());
            activity.findViewById(R.id.btnDiv).performClick();
        });  // Your activity is resumed.

        scenario.recreate();
        // data is saved into Bundle with onSaveInstanceState(Bundle),
        // The current Activity is destroyed after its
        // then it creates a new Activity with the saved Bundle.

        scenario.moveToState(Lifecycle.State.CREATED);

        // THEN
        scenario.onActivity(activity -> {

            TextView result = activity.findViewById(R.id.textResult);
            String actual = result.getText().toString();
            assertThat(actual).isEqualTo("Result = 0.50");

        });

    }

}