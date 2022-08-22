package ru.nsu.ccfit.zuev.osu.helper;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

import org.anddev.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.source.BaseTextureAtlasSource;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class QualityFileBitmapSource extends BaseTextureAtlasSource implements
        IBitmapTextureAtlasSource {
    //tzl: 一个用于把file读取为Bitmap的类，一个bitmap对应一个对象，而且读取的逻辑是调用preLoad()或者onLoadBitmap()才执行的

    private int mWidth;
    private int mHeight;
    private Bitmap bitmap = null;

    private InputFactory fileBitmapInput;

    private int inSampleSize = ru.nsu.ccfit.zuev.osu.Config.getTextureQuality();

    public QualityFileBitmapSource(final File pFile) {
        this(pFile, 0, 0);
    }

    public QualityFileBitmapSource(final InputFactory pFile) {
        this(pFile, 0, 0);
    }

    public QualityFileBitmapSource(final InputFactory pFile, int inSampleSize) {
        this(pFile, 0, 0);
        this.inSampleSize = inSampleSize;
    }

    public QualityFileBitmapSource(final File pFile,
                                   final int pTexturePositionX, final int pTexturePositionY) {
        this(() -> new FileInputStream(pFile)//tzl: 实现了InputFactory接口的匿名类
                , pTexturePositionX, pTexturePositionY);
    }

    public QualityFileBitmapSource(final InputFactory pFile,
                                   final int pTexturePositionX, final int pTexturePositionY) {
        super(pTexturePositionX, pTexturePositionY);

        fileBitmapInput = pFile;

        //tzl: 读取图片为bitmap
        final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inJustDecodeBounds = true;
        decodeOptions.inSampleSize = inSampleSize;

        InputStream in = null;
        try {
            in = openInputStream();
            BitmapFactory.decodeStream(in, null, decodeOptions);//tzl: 为什么不接收返回值?只是为了检测?是的

            this.mWidth = decodeOptions.outWidth;
            this.mHeight = decodeOptions.outHeight;
        } catch (final IOException e) {
            Debug.e("Failed loading Bitmap in FileBitmapTextureAtlasSource. File: "
                    + pFile, e);
            this.mWidth = 0;
            this.mHeight = 0;
        } finally {
            StreamUtils.close(in);
        }

    }

    QualityFileBitmapSource(final InputFactory pFile, final int pTexturePositionX,
                            final int pTexturePositionY, final int pWidth, final int pHeight) {
        super(pTexturePositionX, pTexturePositionY);
        fileBitmapInput = pFile;
        this.mWidth = pWidth;
        this.mHeight = pHeight;
    }

    public InputStream openInputStream() throws IOException {
        return fileBitmapInput.openInput();
    }


    public QualityFileBitmapSource deepCopy() {
        QualityFileBitmapSource source = new QualityFileBitmapSource(this.fileBitmapInput, this.mTexturePositionX,
                this.mTexturePositionY, this.mWidth, this.mHeight);
        source.inSampleSize = inSampleSize;
        return source;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    public int getWidth() {
        return this.mWidth;
    }


    public int getHeight() {
        return this.mHeight;
    }

    public boolean preload() {
        bitmap = onLoadBitmap(Bitmap.Config.ARGB_8888);
        return bitmap != null;
    }


    public Bitmap onLoadBitmap(final Config pBitmapConfig) {
        //tzl: 把文件流解码为Bitmap的代码在这里，但是这个是个回调，是因为不用是不需要加载，怕浪费内存?
        if (bitmap != null) {
            final Bitmap bmp = bitmap;
            bitmap = null;
            return bmp;
        }
        final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inPreferredConfig = Config.ARGB_8888;//pBitmapConfig;
        decodeOptions.inSampleSize = inSampleSize;

        InputStream in = null;
        try {
            in = openInputStream();
            return BitmapFactory.decodeStream(in, null, decodeOptions);
        } catch (final IOException e) {
            Debug.e("Failed loading Bitmap in "
                            + this.getClass().getSimpleName() + ". File: " + this.fileBitmapInput,
                    e);
            return null;
        } finally {
            StreamUtils.close(in);
        }
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.fileBitmapInput + ")";
    }


    public interface InputFactory { //tzl: 看不懂这个工厂模式
        InputStream openInput() throws IOException;
    }

}
