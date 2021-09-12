package club.goodhao.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import java.util.ArrayList;
import java.util.List;

import club.goodhao.activity.QRActivity;

public class QRUtil {
    private static final int PERMISSION_REQUESTCODE = 100;
    private static QRCodeReaderView.OnQRCodeReadListener listener;
    private static Activity activity;

    public QRUtil(Activity activity, QRCodeReaderView.OnQRCodeReadListener listener) {
        QRUtil.activity = activity;
        QRUtil.listener = listener;
    }
    public static void start(){
        Intent intent = new Intent(MyApplication.getContext(), QRActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

    public static QRCodeReaderView.OnQRCodeReadListener getListener() {
        return QRUtil.listener;
    }

    public static void setListener(QRCodeReaderView.OnQRCodeReadListener listener) {
        QRUtil.listener = listener;
    }
}
