package org.sangguo.draggertest.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import org.sangguo.draggertest.ui.custom.YTImageView;

/**
 * Created by chenwei on 2017/6/7.
 */

public class CircleImageView extends YTImageView {

  private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;

  private static final int COLORDRAWABLE_DIMENSION = 2;

  private static final int DEFAULT_BORDER_WIDTH = 0;
  private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
  private static final boolean DEFAULT_BORDER_OVERLAY = false;

  private final RectF mDrawableRect = new RectF();
  private final RectF mBorderRect = new RectF();

  private final Matrix mShaderMatrix = new Matrix();
  private Paint mBitmapPaint = new Paint();
  private Paint mBorderPaint = new Paint();

  private int mBorderColor = DEFAULT_BORDER_COLOR;
  private int mBorderWidth = DEFAULT_BORDER_WIDTH;

  private Bitmap mBitmap;
  private BitmapShader mBitmapShader;
  private int mBitmapWidth;
  private int mBitmapHeight;

  private float mDrawableRadius;
  private float mBorderRadius;

  private ColorFilter mColorFilter;

  private boolean mReady;
  private boolean mSetupPending;
  private boolean mBorderOverlay = DEFAULT_BORDER_OVERLAY;

  private boolean useCircle = true;

  public CircleImageView(Context context) {
    this(context, null);
  }

  public CircleImageView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    super.setScaleType(SCALE_TYPE);
    mReady = true;
    if (mSetupPending) {
      initShader();
      mSetupPending = false;
    }
  }

  @Override
  public ImageView.ScaleType getScaleType() {
    return SCALE_TYPE;
  }

  @Override
  public void setScaleType(ImageView.ScaleType scaleType) {

  }

  @Override
  public void setAdjustViewBounds(boolean adjustViewBounds) {
    if (adjustViewBounds) {
      throw new IllegalArgumentException("adjustViewBounds not supported.");
    }
  }

  public boolean isUseCircle() {
    return useCircle;
  }

  public void setUseCircle(boolean useCircle) {
    this.useCircle = useCircle;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (useCircle) {
      if (getDrawable() != null) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius, mBitmapPaint);
        if (mBorderWidth != 0) {
          canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBorderRadius, mBorderPaint);
        }
      }
    } else {
      super.onDraw(canvas);
    }
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    setup();
  }

  public int getBorderColor() {
    return mBorderColor;
  }

  public void setBorderColor(int borderColor) {
    if (borderColor == mBorderColor) {
      return;
    }

    mBorderColor = borderColor;
    mBorderPaint.setColor(mBorderColor);
    invalidate();
  }

  public void setBorderColorResource(@ColorRes int borderColorRes) {
    setBorderColor(getContext().getResources().getColor(borderColorRes));
  }

  public int getBorderWidth() {
    return mBorderWidth;
  }

  public void setBorderWidth(int borderWidth) {
    if (borderWidth == mBorderWidth) {
      return;
    }

    mBorderWidth = borderWidth;
    setup();
  }

  public boolean isBorderOverlay() {
    return mBorderOverlay;
  }

  public void setBorderOverlay(boolean borderOverlay) {
    if (borderOverlay == mBorderOverlay) {
      return;
    }

    mBorderOverlay = borderOverlay;
    setup();
  }

  @Override
  public void setImageBitmap(Bitmap bm) {
    super.setImageBitmap(bm);
    initShader();
  }

  @Override
  public void setImageDrawable(Drawable drawable) {
    super.setImageDrawable(drawable);
    initShader();
  }

  @Override
  public void setImageResource(@DrawableRes int resId) {
    super.setImageResource(resId);
    initShader();
  }

  @Override
  public void setImageURI(Uri uri) {
    super.setImageURI(uri);
    initShader();
  }

  private void initShader() {
    if (!mReady) {
      mSetupPending = true;
      return;
    }
    mBitmap = getBitmapFromDrawable(getDrawable());

    if (mBitmap != null) {
      mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      mBitmapPaint.setAntiAlias(true);
      mBitmapPaint.setShader(mBitmapShader);
      mBorderPaint.setStyle(Paint.Style.STROKE);
      mBorderPaint.setAntiAlias(true);
      setup();
    }
  }

  @Override
  public void setColorFilter(ColorFilter cf) {
    if (cf == mColorFilter) {
      return;
    }
    mColorFilter = cf;
    mBitmapPaint.setColorFilter(mColorFilter);
  }

  private Bitmap getBitmapFromDrawable(Drawable drawable) {
    if (drawable == null) {
      return null;
    }

    if (drawable instanceof BitmapDrawable) {
      return ((BitmapDrawable) drawable).getBitmap();
    }

    try {
      Bitmap bitmap;

      if (drawable instanceof ColorDrawable) {
        bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION,
            Bitmap.Config.ARGB_4444);
      } else {
        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
          bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
              Bitmap.Config.ARGB_4444);
        } else {
          return null;
        }
      }
      Canvas canvas = new Canvas(bitmap);
      drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      drawable.draw(canvas);
      return bitmap;
    } catch (OutOfMemoryError e) {
      e.printStackTrace();
      return null;
    }
  }

  private void setup() {
    if (!mReady) {
      mSetupPending = true;
      return;
    }
    if (mBitmap == null) {
      return;
    }

    mBorderPaint.setColor(mBorderColor);
    mBorderPaint.setStrokeWidth(mBorderWidth);

    mBitmapHeight = mBitmap.getHeight();
    mBitmapWidth = mBitmap.getWidth();

    mBorderRect.set(0, 0, getWidth(), getHeight());
    mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2,
        (mBorderRect.width() - mBorderWidth) / 2);

    mDrawableRect.set(mBorderRect);
    if (!mBorderOverlay) {
      mDrawableRect.inset(mBorderWidth, mBorderWidth);
    }
    mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);

    updateShaderMatrix();
    invalidate();
  }

  private void updateShaderMatrix() {
    float scale;
    float dx = 0;
    float dy = 0;

    mShaderMatrix.set(null);

    if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
      scale = mDrawableRect.height() / (float) mBitmapHeight;
      dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
    } else {
      scale = mDrawableRect.width() / (float) mBitmapWidth;
      dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
    }

    mShaderMatrix.setScale(scale, scale);
    mShaderMatrix.postTranslate((int) (dx + 0.5f) + mDrawableRect.left,
        (int) (dy + 0.5f) + mDrawableRect.top);

    mBitmapShader.setLocalMatrix(mShaderMatrix);
  }

  /**
   * 相当于ImageView销毁了
   */
  public void onDetachedFromWindow() {
    mBitmap = null;
    mBitmapShader = null;
    super.onDetachedFromWindow();
  }
}
