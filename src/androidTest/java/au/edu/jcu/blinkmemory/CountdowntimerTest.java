package au.edu.jcu.blinkmemory;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.shadows.ShadowCountDownTimer;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.robolectric.Shadows.shadowOf;
//
//import android.os.CountDownTimer;
//
//@RunWith(RobolectricTestRunner.class)
//public class CountdownTimerTest {
//
//    @Test
//    public void testCountDownTimer() {
//        final int expectedCount = 3;
//        final long expectedInterval = 1000;
//        final long expectedMillisInFuture = expectedCount * expectedInterval;
//
//        CountDownTimer countDownTimer = new CountDownTimer(expectedMillisInFuture, expectedInterval) {
//            private int tickCount = 0;
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tickCount++;
//                assertEquals(expectedMillisInFuture - millisUntilFinished, expectedInterval * tickCount);
//            }
//
//            @Override
//            public void onFinish() {
//                assertEquals(expectedCount, tickCount);
//            }
//        };
//
//        ShadowCountDownTimer shadowCountDownTimer = shadowOf(countDownTimer);
//        assertEquals(expectedMillisInFuture, shadowCountDownTimer.getMillisInFuture());
//        assertEquals(expectedInterval, shadowCountDownTimer.getCountdownInterval());
//
//        // Check if the timer is not running before start
//        assertFalse(shadowCountDownTimer.isRunning());
//        // start the timer
//        countDownTimer.start();
//        // check if the timer is running now
//        assertTrue(shadowCountDownTimer.isRunning());
//
//        // wait for the timer to finish
//        shadowCountDownTimer.getScheduler().advanceBy(expectedMillisInFuture);
//
//        assertEquals(0, shadowCountDownTimer.getCount());
//        assertFalse(shadowCountDownTimer.isRunning());
//    }
//
//    @Test
//    public void testCancel() {
//        final int expectedCount = 3;
//        final long expectedInterval = 1000;
//        final long expectedMillisInFuture = expectedCount * expectedInterval;
//
//        CountDownTimer countDownTimer = new CountDownTimer(expectedMillisInFuture, expectedInterval) {
//            private int tickCount = 0;
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tickCount++;
//            }
//
//            @Override
//            public void onFinish() {
//                assertEquals(0, 1);
//            }
//        };
//
//        ShadowCountDownTimer shadowCountDownTimer = shadowOf(countDownTimer);
//        assertFalse(shadowCountDownTimer.isRunning());
//
//        countDownTimer.start();
//        assertTrue(shadowCountDownTimer.isRunning());
//
//        countDownTimer.cancel();
//        // Check if the timer is not running after cancel
//        assertFalse(shadowCountDownTimer.isRunning());
//    }
//}

