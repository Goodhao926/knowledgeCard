package club.goodhao;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import club.goodhao.model.CardBag;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("club.goodhao", appContext.getPackageName());
    }


    @Test
    public void testLitepal(){
        // 存储数据
        CardBag cardBag = new CardBag();
        cardBag.setName("四级英语");
        cardBag.save();

    }
    @Test
    public void testPrintDb(){
        List<CardBag> cardBags = LitePal.findAll(CardBag.class);
        Log.i(TAG, "testPrintDb: " + cardBags);

    }
}