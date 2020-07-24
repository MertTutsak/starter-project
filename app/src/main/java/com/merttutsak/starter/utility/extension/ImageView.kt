package com.merttutsak.starter.utility.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.model.Circle
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.merttutsak.starter.utility.transform.CircleTransform
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

enum class ImgLibType { GLIDE, PICASSO }

/** parameters -->
 *
 * url -> should be url
 * placeholder -> can be null
 * scaleType -> can be null
 * isCircle -> cannot be null, default value is false
 * hasCache -> cannot be null, default value is true
 * thumbnail -> cannot be null, default value is 0.5 (0..1)
 * libType -> cannot be null, default value is GLIDE (GLIDE/PICASSO)
 */
fun ImageView.loadImage(
    url: String,
    placeholder: Drawable? = null,
    scaleType: ImageView.ScaleType? = null,
    isCircle: Boolean = false,
    hasCache: Boolean = true,
    thumbnail: Float = 0.5f,
    libType: ImgLibType = ImgLibType.GLIDE
) {
    when (libType) {
        ImgLibType.PICASSO -> {
            val requestCreator = Picasso.get()
                .load(url)

            //TODO thumbnail research/write

            hasCache.isNotTrue {
                requestCreator
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
            }

            isCircle.isTrue {
                requestCreator.transform(CircleTransform())
            }

            placeholder.notNull {
                requestCreator.placeholder(it)
            }

            when (scaleType) {
                ImageView.ScaleType.FIT_CENTER -> {
                    requestCreator.fit()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    requestCreator.centerInside()
                }
                ImageView.ScaleType.CENTER_CROP -> {
                    requestCreator.centerCrop()
                }
            }

            requestCreator.into(this)
        }
        else -> {
            val requestBuilder = Glide.with(context)
                .load(url)
                .thumbnail(thumbnail)

            placeholder.notNull {
                requestBuilder.placeholder(placeholder)
            }

            hasCache.isNotTrue {
                //TODO research/write
            }

            when (scaleType) {
                ImageView.ScaleType.FIT_CENTER -> {
                    requestBuilder.fitCenter()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    requestBuilder.centerInside()
                }
                ImageView.ScaleType.CENTER_CROP -> {
                    requestBuilder.centerCrop()
                }
            }

            isCircle.isTrue {
                requestBuilder.apply(RequestOptions.circleCropTransform())
            }

            requestBuilder
                .into(this)
        }
    }
}

/** parameters -->
 *
 * drawable -> should be url
 * placeholder -> can be null
 * scaleType -> can be null
 * isCircle -> cannot be null, default value is false
 * hasCache -> cannot be null, default value is true
 * thumbnail -> cannot be null, default value is 0.5 (0..1)
 * libType -> cannot be null, default value is GLIDE (GLIDE/PICASSO)
 */
fun ImageView.loadImage(
    drawable: Drawable,
    placeholder: Drawable? = null,
    scaleType: ImageView.ScaleType? = null,
    isCircle: Boolean = false,
    hasCache: Boolean = true,
    thumbnail: Float = 0.5f,
    libType: ImgLibType = ImgLibType.GLIDE
) {
    when (libType) {
        ImgLibType.PICASSO -> {
            throw Exception("Picasso is unsupported")
        }

        else -> {
            val requestBuilder = Glide.with(context)
                .load(drawable)
                .thumbnail(thumbnail)

            placeholder.notNull {
                requestBuilder.placeholder(placeholder)
            }

            hasCache.isNotTrue {
                //TODO research/write
            }

            when (scaleType) {
                ImageView.ScaleType.FIT_CENTER -> {
                    requestBuilder.fitCenter()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    requestBuilder.centerInside()
                }
                ImageView.ScaleType.CENTER_CROP -> {
                    requestBuilder.centerCrop()
                }
            }

            isCircle.isTrue {
                requestBuilder.apply(RequestOptions.circleCropTransform())
            }

            requestBuilder
                .into(this)
        }
    }
}

/** parameters -->
 *
 * url -> should be url
 * placeholder -> can be null
 * scaleType -> can be null
 * isCircle -> cannot be null, default value is false
 * hasCache -> cannot be null, default value is true
 * thumbnail -> cannot be null, default value is 0.5 (0..1)
 * libType -> cannot be null, default value is GLIDE (GLIDE/PICASSO)
 */
fun ImageView.loadImage(
    uri: Uri,
    placeholder: Drawable? = null,
    scaleType: ImageView.ScaleType? = null,
    isCircle: Boolean = false,
    hasCache: Boolean = true,
    thumbnail: Float = 0.5f,
    libType: ImgLibType = ImgLibType.GLIDE
) {
    when (libType) {
        ImgLibType.PICASSO -> {
            val requestCreator = Picasso.get()
                .load(uri)

            //TODO thumbnail research/write

            hasCache.isNotTrue {
                requestCreator
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
            }

            isCircle.isTrue {
                requestCreator.transform(CircleTransform())
            }

            placeholder.notNull {
                requestCreator.placeholder(it)
            }

            when (scaleType) {
                ImageView.ScaleType.FIT_CENTER -> {
                    requestCreator.fit()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    requestCreator.centerInside()
                }
                ImageView.ScaleType.CENTER_CROP -> {
                    requestCreator.centerCrop()
                }
            }

            requestCreator.into(this)
        }
        else -> {
            val requestBuilder = Glide.with(context)
                .load(uri)
                .thumbnail(thumbnail)

            placeholder.notNull {
                requestBuilder.placeholder(placeholder)
            }

            hasCache.isNotTrue {
                //TODO research/write
            }

            when (scaleType) {
                ImageView.ScaleType.FIT_CENTER -> {
                    requestBuilder.fitCenter()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    requestBuilder.centerInside()
                }
                ImageView.ScaleType.CENTER_CROP -> {
                    requestBuilder.centerCrop()
                }
            }

            isCircle.isTrue {
                requestBuilder.apply(RequestOptions.circleCropTransform())
            }

            requestBuilder
                .into(this)
        }
    }
}

fun ImageView.loadImageFromBase64(base64Str: String) {
    val decodedString = Base64.decode(base64Str, Base64.DEFAULT);
    val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size);
    if (bitmap != null && bitmap.height != 0 && bitmap.width != 0) {
        this.setImageBitmap(bitmap)
    }
}

fun ImageView.createQrCodeWithColor(code: String, color: Int, bgColor: Int) {
    val writer = QRCodeWriter()
    try {
        val bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix[x, y]) color else bgColor)
            }
        }
        this.setImageBitmap(bmp)
    } catch (e: WriterException) {
        e.printStackTrace()
    }
}