package ca.dal.cs.csci3130.a2;

import android.content.Context;
import android.content.Intent;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);
    public IntentsTestRule<MainActivity> myIntentRule=new IntentsTestRule<>(MainActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }


    /*** AT-I**/
    @Test
    public void checkIfRegistrationPageIsShown() {
        onView(withId(R.id.userName)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.emailAddress)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.registerButton)).check(matches(withText("Register")));
    }

    /*** AT-II**/
    @Test
    public void checkIfUserNameIsEmpty() {
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.userName)).perform(typeText(""));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_USER_NAME)));
    }

    /*** AT-III**/
    @Test
    public void checkIfUserNameIsAlphaNumeric() {
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.userName)).perform(typeText("abc123"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.ALPHA_NUMERIC_USER_NAME)));
    }

    /*** AT-III**/
    @Test
    public void checkIfUserNameIsNotAlphaNumeric() {
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.userName)).perform(typeText("12&6*!"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.NON_ALPHA_NUMERIC_USER_NAME)));
    }

    /*** AT-IV**/
    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.userName)).perform(typeText("abc123"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.VALID_EMAIL_ADDRESS)));
    }

    /*** AT-IV**/
    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.userName)).perform(typeText("abc123"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }

    /***At-V**/
    @Test
    public void checkIfMoved2WelcomePage(){
        onView(withId(R.id.userName)).perform(typeText("abc123"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        intended(hasComponent(WelcomeActivity.class.getName()));
    }


}