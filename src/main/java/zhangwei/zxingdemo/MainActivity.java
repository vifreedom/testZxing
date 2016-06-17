package zhangwei.zxingdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_qrcode)
    Button mBtnQrcode;
    @Bind(R.id.tv_result)
    TextView mTvResult;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.btn_get_image)
    Button mBtnGetImage;
    @Bind(R.id.iv_image)
    ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_qrcode, R.id.btn_get_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qrcode://二维码扫描
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.btn_get_image://获取图片
                Bitmap bitmap = EncodingUtils.createQRCode(mEtContent.getText().toString(),500,500, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                mIvImage.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == 0){
            String result = data.getStringExtra("result");
            Log.i("zhangwei", "result222: " + result);
            mTvResult.setText(result);
        }
    }
}
