package au.edu.jcu.blinkmemory;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
//public class ActivityButtonTest {
//
//    @Rule
//    public ActivityTestRule<ActivityButtonTest> activityRule =
//            new ActivityTestRule<>(ActivityButtonTest.class);
//    @Test
//    public void testButtonClick() {
//        onView(withId(R.id.button))
//                .perform(click());
//        onView(withId(R.id.text_view))
//                .check(matches(withText("Button Clicked")));
//    }
//}